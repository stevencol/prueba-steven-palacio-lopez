package com.prueba.mappers;

import com.prueba.dtos.TutorialDto;
import com.prueba.entitys.TutorialEntity;
import com.prueba.enums.States;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

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


    public List<TutorialDto> userByVisibe(List<TutorialEntity> tutorials) {

        return tutorials.stream().filter(tutorial -> !tutorial.getStates().equals("VISIBLE")).map(this::getTutorilDto).collect(Collectors.toList());

    }


}
