package com.project.bloggingapis.service;

import com.project.bloggingapis.dto.CommentDTO;

public interface CommentService {
    CommentDTO createComment(CommentDTO commentDTO,Integer userId,Integer postId);
    void deleteComment(Integer commentId);
}
