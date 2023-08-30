package com.project.bloggingapis.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDTO {
    private int userId;

    @Pattern(regexp = "[a-zA-Z]{5,}", message = "Name must contain minimum 5 characters")
    private String name;

    @Pattern(regexp = "[a-z0-9]+@[a-z]+\\.[a-z]{2,3}", message = "Invalid email format")
    private String email;

    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$",message = "Invalid password format")
    private String password;

    @NotEmpty
    private String about;
}
