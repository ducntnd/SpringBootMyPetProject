package com.example.project.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
@Data
public class Post {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String category;
    @Column(nullable = false)
    private String sport;
    @Column(nullable = false, length = 100)
    private String title;
    @Column(nullable = false,length = 6500)
    private String content;
    @Column(nullable = false)
    private LocalDateTime lastUpdate;
    @PrePersist //Trước khi lưu khi khởi tạo record
    public void prePersist() {
        lastUpdate = LocalDateTime.now();
    }
    @PreUpdate //Khi cập nhật record
    public void preUpdate() {
        lastUpdate = LocalDateTime.now();
    }


    private String thumbnail;
    private String slug;
    private boolean isApproved;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;  //Tác giả viết post

    @ManyToOne(fetch = FetchType.LAZY)
    private Tag tag;

}
