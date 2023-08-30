package com.project.bloggingapis.repository;

import com.project.bloggingapis.model.Category;
import com.project.bloggingapis.model.Post;
import com.project.bloggingapis.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Integer> {

    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);
    List<Post> findByUserAndCategory(User user, Category category);
    List<Post> findByPostTitleContaining(String postTitle);

}
