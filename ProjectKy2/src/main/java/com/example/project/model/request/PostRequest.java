package com.example.project.model.request;

import com.example.project.entity.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostRequest {
    private long id;
    private String title;
    private String content;
    private LocalDateTime lastUpdate;
    private String category;
    private String thumbnail;
    private long user_id;
    private Tag tag;
}
