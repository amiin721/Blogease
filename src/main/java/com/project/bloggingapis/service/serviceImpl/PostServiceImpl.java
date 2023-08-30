package com.project.bloggingapis.service.serviceImpl;

import com.project.bloggingapis.dto.PostDTO;
import com.project.bloggingapis.dto.PostPaginationResponse;
import com.project.bloggingapis.dto.UserDTO;
import com.project.bloggingapis.exceptions.ResourceNotFoundException;
import com.project.bloggingapis.model.Category;
import com.project.bloggingapis.model.Post;
import com.project.bloggingapis.model.User;
import com.project.bloggingapis.repository.CategoryRepository;
import com.project.bloggingapis.repository.PostRepository;
import com.project.bloggingapis.repository.UserRepository;
import com.project.bloggingapis.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    PostRepository postRepository;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Override
    public PostDTO createPost(PostDTO postDTO, Integer userId, Integer categoryId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "User Id : ", userId));

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(()->new ResourceNotFoundException("Category","CategoryId : ",categoryId));

        Post post = modelMapper.map(postDTO,Post.class);
        post.setDate(new Date());
        post.setUser(user);
        post.setCategory(category);

        System.out.println(post.getPostContent());
        System.out.println(post.getUser());

        return modelMapper.map(postRepository.save(post),PostDTO.class);
    }

    @Override
    public PostDTO updatePost(PostDTO postDTO, Integer postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "Post Id : ", postId));

        post.setPostContent(postDTO.getPostContent());
        post.setPostTitle(postDTO.getPostTitle());
        post.setDate(new Date());
        Post updatedPost = postRepository.save(post);
        return modelMapper.map(updatedPost, PostDTO.class);
    }

    @Override
    public PostDTO getPostById(Integer postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "Post Id : ", postId));

        return modelMapper.map(post, PostDTO.class);
    }

    @Override
    public PostPaginationResponse getAllPosts(int pageSize, int pageNumber, String sortBy, String sortDirection) {

        Sort sort = sortDirection.equalsIgnoreCase("ascending")?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNumber,pageSize,sort);
        Page<Post> postPage = postRepository.findAll(pageable);

        PostPaginationResponse postPaginationResponse = new PostPaginationResponse();

        postPaginationResponse.setData(postPage.getContent().stream().map(post -> modelMapper.map(post, PostDTO.class)).toList());
        postPaginationResponse.setPageNumber(postPage.getNumber());
        postPaginationResponse.setPageSize(postPage.getSize());
        postPaginationResponse.setTotalRecords(postPage.getTotalElements());
        postPaginationResponse.setTotalPages(postPage.getTotalPages());
        postPaginationResponse.setLastPage(postPage.isLast());

        return postPaginationResponse;
    }

    @Override
    public void deletePost(Integer postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "Post Id : ", postId));

        postRepository.delete(post);
    }

    @Override
    public List<PostDTO> getPostsByUser(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "User Id : ", userId));

        List<Post> posts = postRepository.findByUser(user);
        return posts.stream().map(post -> modelMapper.map(post, PostDTO.class)).toList();
    }

    @Override
    public List<PostDTO> getPostsByCategory(Integer categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(()->new ResourceNotFoundException("Category","CategoryId : ",categoryId));

        List<Post> posts = postRepository.findByCategory(category);
        return posts.stream().map(post -> modelMapper.map(post, PostDTO.class)).toList();
    }

    @Override
    public List<PostDTO> getPostsByUserAndCategory(Integer userId, Integer categoryId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "User Id : ", userId));

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(()->new ResourceNotFoundException("Category","CategoryId : ",categoryId));

        List<Post> posts = postRepository.findByUserAndCategory(user,category);
        return posts.stream().map(post -> modelMapper.map(post, PostDTO.class)).toList();
    }

    @Override
    public List<PostDTO> searchPostsByTitle(String keyword) {
        return postRepository.findByPostTitleContaining(keyword).stream().map(post -> modelMapper.map(post, PostDTO.class)).toList();
    }
}
