package com.project.bloggingapis.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {
    private Integer postId;
    private String postTitle;
    private String postContent;
    private Date date;
    private CategoryDTO category;
    private UserDTO user;
    private List<CommentDTO> comments = new ArrayList<>();
}
