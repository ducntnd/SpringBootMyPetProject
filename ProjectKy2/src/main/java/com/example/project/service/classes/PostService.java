package com.example.project.service.classes;

import com.example.project.entity.Post;
import com.example.project.entity.User;
import com.example.project.model.mapper.PostMapper;
import com.example.project.model.request.PostRequest;
import com.example.project.repository.PostRepository;
import com.example.project.repository.UserRepository;
import com.example.project.service.interfaces.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostService implements IPostService {
    @Autowired private PostRepository postRepository;
    @Autowired private UserRepository userRepository;

    @Override
    public void createPost(long id, PostRequest request){
        Optional<User> user=userRepository.findById(id);
        if(user.isPresent()){
            User user1=user.get();
            Post post= PostMapper.INSTANCE.postRequestToPost(request);
            user1.addPost(post);
            userRepository.flush();
        }
    }

}
