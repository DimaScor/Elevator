package org.myproject.services.interfaces;

import org.myproject.pojo.UserDTO;
import org.myproject.entities.User;

import java.util.List;

public interface UserServiceMapper {
    UserDTO toDto(User user);
    User fromDto(UserDTO userDto);
    List<UserDTO> toDtoList(List<User> userList);
    List<User> fromDtoList(List<UserDTO> userDtoList);

    void updateEntityFromDto(UserDTO userDto, User user);

    User updateEntityFromDto(UserDTO userDto);

    UserDTO toPartialDto(User user);
}