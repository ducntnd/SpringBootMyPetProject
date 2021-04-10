package com.example.project.model.mapper;

import com.example.project.entity.User;
import com.example.project.model.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    UserDto userToUserDto(User user);
    User userDtoToUser(UserDto userDto);
}
