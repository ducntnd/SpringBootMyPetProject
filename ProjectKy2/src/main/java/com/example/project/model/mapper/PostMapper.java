package com.example.project.model.mapper;

import com.example.project.entity.Post;
import com.example.project.entity.User;
import com.example.project.model.dto.UserDto;
import com.example.project.model.request.PostRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PostMapper {
    PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);
    @Mapping(target="user.id", source="post.user_id")
    Post postRequestToPost(PostRequest post);
//    User userDtoToUser(UserDto userDto);
}
