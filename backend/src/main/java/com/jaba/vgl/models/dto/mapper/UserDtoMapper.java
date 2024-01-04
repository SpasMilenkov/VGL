package com.jaba.vgl.models.dto.mapper;

import com.jaba.vgl.models.dto.UserDto;
import com.jaba.vgl.models.entities.User;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class UserDtoMapper implements Function<User, UserDto> {
    @Override
    public UserDto apply(User user) {
        return new UserDto( user.getNickname(), user.getEmail(), user.getSteamId());
    }
}
