package com.example.project.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String content;
    private LocalDateTime lastUpdate;

    @PrePersist //Trước khi lưu khi khởi tạo record
    public void prePersist() {
        lastUpdate = LocalDateTime.now();
    }

    @PreUpdate //Khi cập nhật record
    public void preUpdate() {
        lastUpdate = LocalDateTime.now();
    }

    public Comment(String content) {
        this.content = content;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    private User user; //Mỗi comment phải do một commenter viết

    public void setUser(User user) {
        user.getComments().add(this);
        this.user = user;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;
}
