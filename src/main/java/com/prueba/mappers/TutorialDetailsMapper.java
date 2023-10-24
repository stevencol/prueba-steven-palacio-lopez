package com.prueba.mappers;

import com.prueba.dtos.TutorialDetailsDto;
import com.prueba.entitys.TutorialDetailsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TutorialDetailsMapper {

    @Autowired
    UserMaper userMapper;

    public TutorialDetailsEntity getTutorialDetailsEntity(TutorialDetailsDto dto) {
        return TutorialDetailsEntity.builder().user(userMapper.getEntity(dto.getUser())).build();

    }

    public TutorialDetailsDto getTutorialDetailsDto(TutorialDetailsEntity entity) {
        return TutorialDetailsDto.builder()
                .id(entity.getId())
                .user(userMapper.getDto(entity.getUser())).craateAt(entity.getCraateAt()).build();

    }
}
