package com.project.bloggingapis.service.serviceImpl;

import com.project.bloggingapis.configurations.Constants;
import com.project.bloggingapis.dto.UserDTO;
import com.project.bloggingapis.model.Role;
import com.project.bloggingapis.model.User;
import com.project.bloggingapis.repository.RoleRepository;
import com.project.bloggingapis.repository.UserRepository;
import com.project.bloggingapis.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import com.project.bloggingapis.exceptions.ResourceNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    RoleRepository roleRepository;
    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = modelMapper.map(userDTO,User.class);
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.getRoles().add(roleRepository.findById(Constants.ROLE_USER).get());
        return modelMapper.map(userRepository.save(user), UserDTO.class);
    }
    @Override
    public UserDTO updateUser(UserDTO userDTO, Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User","UserId : ",userId));
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setAbout(userDTO.getAbout());
        User updatedUser = userRepository.save(user);
        return modelMapper.map(updatedUser,UserDTO.class);
    }

    @Override
    public UserDTO getUserById(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "User Id : ", userId));

        return modelMapper.map(user,UserDTO.class);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(user -> modelMapper.map(user, UserDTO.class)).toList();
    }

    @Override
    public void deleteUser(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "User Id : ", userId));

        userRepository.delete(user);
    }
}
