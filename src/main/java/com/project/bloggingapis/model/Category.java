package com.project.bloggingapis.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categories")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Category {
    @Id
    @GeneratedValue(
            strategy= GenerationType.AUTO,
            generator="native"
    )
    private Long categoryId;
    @Column(name = "title", length = 100, nullable = false)
    private String categoryTitle;
    @Column(name = "description", nullable = false)
    private String categoryDescription;
    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL)
    private List<Post> posts=new ArrayList<>();

}
