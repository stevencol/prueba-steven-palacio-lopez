package com.prueba.mappers;

import com.prueba.dtos.TutorialDto;
import com.prueba.entitys.TutorialEntity;
import com.prueba.enums.States;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TutorialMapper {

    @Autowired
    private TutorialDetailsMapper detailsMapper;

    public TutorialEntity getTutorilEntity(TutorialDto tutorialDto) {

        return TutorialEntity.builder()
                .title(tutorialDto.getTitle())
                .states(States.valueOf(tutorialDto.getStates()))
                .details(detailsMapper.getTutorialDetailsEntity(tutorialDto.getDetails()))
                .build();

    }


    public TutorialDto getTutorilDto(TutorialEntity entity) {

        return TutorialDto.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .states(entity.getStates().toString())
                .details(detailsMapper.getTutorialDetailsDto(entity.getDetails()))
                .build();

    }

}
