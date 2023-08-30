package com.project.bloggingapis.service;

import com.project.bloggingapis.dto.PostDTO;
import com.project.bloggingapis.dto.PostPaginationResponse;

import java.util.List;

public interface PostService {
    PostDTO createPost(PostDTO postDTO, Integer userId, Integer categoryId);
    PostDTO updatePost(PostDTO postDTO, Integer postId);
    PostDTO getPostById(Integer postId);
    PostPaginationResponse getAllPosts(int pageSize, int pageNumber, String sortBy, String sortDirection);
    void deletePost(Integer postId);
    List<PostDTO> getPostsByUser(Integer userId);
    List<PostDTO> getPostsByCategory(Integer categoryId);
    List<PostDTO> getPostsByUserAndCategory(Integer userId, Integer categoryId);
    List<PostDTO> searchPostsByTitle(String keyword);
}
