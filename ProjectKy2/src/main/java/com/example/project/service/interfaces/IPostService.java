package com.example.project.service.interfaces;

import com.example.project.model.request.PostRequest;

public interface IPostService {
    void createPost(long id, PostRequest request);
}
