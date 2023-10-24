package com.prueba.mappers;

import com.prueba.dtos.UserDto;
import com.prueba.entitys.UserEntity;
import org.springframework.stereotype.Component;

import javax.swing.text.html.parser.Entity;

@Component
public class UserMaper {

    public UserEntity getEntity(UserDto userDto){
    UserEntity entity= UserEntity
            .builder().
            firstName(userDto.getFirstName())
            .secondName(userDto.getSecondName())
            .lastName(userDto.getLastName())
            .userDocument(userDto.getUserDocument()).build();
    return entity;

    };



    public UserDto getDto(UserEntity userEntity){
        UserDto dto= UserDto
                .builder().
                firstName(userEntity.getFirstName())
                .secondName(userEntity.getSecondName())
                .lastName(userEntity.getLastName())
                .userDocument(userEntity.getUserDocument()).build();
        return dto;

    };


}
