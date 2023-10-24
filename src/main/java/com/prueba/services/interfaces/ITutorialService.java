package com.prueba.services.interfaces;

import com.prueba.dtos.TutorialDto;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface ITutorialService {
    ResponseEntity<Map<String, Object>> getTutorial(Long id);

    ResponseEntity<Map<String, Object>> deleteTutorial(Long id);

    ResponseEntity<Map<String, Object>> createTutorial(TutorialDto tutorialDto);

    ResponseEntity<Map<String, Object>> editTutorial(TutorialDto tutorialDto);

    ResponseEntity<Map<String, Object>> findAll();

}
