package com.example.project.model.dto;

import com.example.project.entity.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDto {
    private long id;

    private String fullname;

    private String email;

    @JsonIgnore
    private Set<Role> roles;
}
