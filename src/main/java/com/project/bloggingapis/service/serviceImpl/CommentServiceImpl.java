package com.project.bloggingapis.service.serviceImpl;

import com.project.bloggingapis.dto.CommentDTO;
import com.project.bloggingapis.exceptions.ResourceNotFoundException;
import com.project.bloggingapis.model.Comment;
import com.project.bloggingapis.model.Post;
import com.project.bloggingapis.model.User;
import com.project.bloggingapis.repository.CommentRepository;
import com.project.bloggingapis.repository.PostRepository;
import com.project.bloggingapis.repository.UserRepository;
import com.project.bloggingapis.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    PostRepository postRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ModelMapper modelMapper;
    @Override
    public CommentDTO createComment(CommentDTO commentDTO, Integer userId,Integer postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "Post Id : ", postId));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "User Id : ", userId));

        Comment comment = modelMapper.map(commentDTO, Comment.class);
        comment.setPost(post);
        comment.setUserName(user.getName());

        return modelMapper.map(commentRepository.save(comment),CommentDTO.class);
    }

    @Override
    public void deleteComment(Integer commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "Comment Id : ", commentId));

        commentRepository.delete(comment);
    }
}
