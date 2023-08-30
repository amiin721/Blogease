package com.project.bloggingapis.controller;

import com.project.bloggingapis.dto.CommentDTO;
import com.project.bloggingapis.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class CommentController {
    @Autowired
    CommentService commentService;
    @PostMapping("/user/{userId}/posts/{postId}/comments")
    public ResponseEntity<CommentDTO> createComment(@Valid @RequestBody CommentDTO commentDTO,
                                                    @PathVariable("userId") Integer userId,
                                                    @PathVariable("postId") Integer postId)
    {
        return new ResponseEntity<>(commentService.createComment(commentDTO,userId,postId), HttpStatusCode.valueOf(201));
    }

    @DeleteMapping("comments/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable("commentId") Integer commentId){
        commentService.deleteComment(commentId);
        return new ResponseEntity<>(HttpStatusCode.valueOf(204));
    }
}
