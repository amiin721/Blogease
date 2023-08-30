package com.project.bloggingapis.repository;

import com.project.bloggingapis.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Integer> {
}
