package com.project.bloggingapis.controller;

import com.project.bloggingapis.dto.PostDTO;
import com.project.bloggingapis.dto.PostPaginationResponse;
import com.project.bloggingapis.configurations.Constants;
import com.project.bloggingapis.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class PostController {

    @Autowired
    PostService postService;
    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO,
                                              @PathVariable("userId") Integer userId,
                                              @PathVariable("categoryId") Integer categoryId)
    {
        return new ResponseEntity<>(postService.createPost(postDTO,userId,categoryId), HttpStatusCode.valueOf(201));
    }

    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostDTO> updatePost(@RequestBody PostDTO postDTO,
                                              @PathVariable("postId") Integer postId){
        return new ResponseEntity<>(postService.updatePost(postDTO,postId),HttpStatusCode.valueOf(200));
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable("postId") Integer postId){
        return new ResponseEntity<>(postService.getPostById(postId),HttpStatusCode.valueOf(200));
    }

    @GetMapping("/posts")
    public ResponseEntity<PostPaginationResponse> getAllPosts(
            @RequestParam(value = "pageSize", defaultValue = Constants.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(value = "pageNumber",defaultValue = Constants.PAGE_NUMBER,required = false) Integer pageNumber,
            @RequestParam(value = "sortBy",defaultValue = "postId",required = false) String sortBy,
            @RequestParam(value = "sortDirection",defaultValue = Constants.SORT_DIRECTION,required = false) String sortDirection)
    {
        return new ResponseEntity<>(postService.getAllPosts(pageSize,pageNumber,sortBy,sortDirection),HttpStatusCode.valueOf(200));
    }

    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<PostDTO> deletePost(@PathVariable("postId") Integer postId){
        postService.deletePost(postId);
        return new ResponseEntity<>(HttpStatusCode.valueOf(204));
    }

    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDTO>> getPostsByUser(@PathVariable("userId") Integer userId){
        return new ResponseEntity<>(postService.getPostsByUser(userId),HttpStatusCode.valueOf(200));
    }

    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDTO>> getPostsByCategory(@PathVariable("categoryId") Integer categoryId){
        return new ResponseEntity<>(postService.getPostsByCategory(categoryId),HttpStatusCode.valueOf(200));
    }

    @GetMapping("user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<List<PostDTO>> getPostsByUserAndCategory(
            @PathVariable("userId") Integer userId,
            @PathVariable("categoryId") Integer categoryId){
        return new ResponseEntity<>(postService.getPostsByUserAndCategory(userId,categoryId),HttpStatusCode.valueOf(200));
    }

    @GetMapping("/posts/search/title/{keyword}")
    public ResponseEntity<List<PostDTO>> searchPostsByTitle(
            @PathVariable("keyword") String keyword)
    {
        return new ResponseEntity<>(postService.searchPostsByTitle(keyword),HttpStatusCode.valueOf(200));
    }
}
