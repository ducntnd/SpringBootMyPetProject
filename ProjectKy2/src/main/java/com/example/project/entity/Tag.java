package com.example.project.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "tag")
@Table(name = "tag")
@Data
public class Tag {
    @Id
    private long id;
    private String name;

    @OneToMany(cascade = CascadeType.MERGE)
    List<Post> posts = new ArrayList<>();
}
