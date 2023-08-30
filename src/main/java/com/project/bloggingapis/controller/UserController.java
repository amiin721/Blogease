package com.project.bloggingapis.controller;

import com.project.bloggingapis.dto.UserDTO;
import com.project.bloggingapis.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/")
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO){
        System.out.println("inside");
        return new ResponseEntity<>(userService.createUser(userDTO), HttpStatusCode.valueOf(201));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{userId}")
    public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UserDTO userDTO,@PathVariable("userId") Integer userId){
        return new ResponseEntity<>(userService.updateUser(userDTO,userId),HttpStatusCode.valueOf(200));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable("userId") Integer userId){
        userService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatusCode.valueOf(204));
    }

    @GetMapping("{userId}")
    public ResponseEntity<UserDTO> getUser(@PathVariable("userId") Integer userId){
        return new ResponseEntity<>(userService.getUserById(userId),HttpStatusCode.valueOf(200));
    }

    @GetMapping("/")
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        return new ResponseEntity<>(userService.getAllUsers(),HttpStatusCode.valueOf(200));
    }
}
