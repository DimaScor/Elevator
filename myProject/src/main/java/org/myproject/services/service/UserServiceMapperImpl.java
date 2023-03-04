package org.myproject.services.service;

import org.modelmapper.ModelMapper;
import org.myproject.pojo.UserDTO;
import org.myproject.entities.User;
import org.myproject.services.interfaces.UserServiceMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class UserServiceMapperImpl implements UserServiceMapper {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDTO toDto(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    public User fromDto(UserDTO userDto) {
        return modelMapper.map(userDto, User.class);
    }

    @Override
    public List<UserDTO> toDtoList(List<User> userList) {
        return userList.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<User> fromDtoList(List<UserDTO> userDtoList) {
        return userDtoList.stream()
                .map(this::fromDto)
                .collect(Collectors.toList());
    }

    @Override
    public User updateEntityFromDto(UserDTO userDto) {
        User user = new User();
        updateEntityFromDto(userDto, user);
        return user;
    }

    @Override
    public void updateEntityFromDto(UserDTO userDto, User user) {
        modelMapper.map(userDto, user);
    }

    @Override
    public UserDTO toPartialDto(User user) {
        UserDTO userDto = new UserDTO();
        userDto.setUserId(user.getId());
        userDto.setFirstname(user.getFirstName());
        userDto.setLastname(user.getLastName());
        userDto.setUsername(user.getUsername());
        return userDto;
    }

}
