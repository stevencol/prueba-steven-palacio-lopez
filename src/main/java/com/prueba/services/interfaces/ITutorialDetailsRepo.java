package com.prueba.services.interfaces;

import com.prueba.dtos.TutorialDetailsDto;
import com.prueba.dtos.TutorialDto;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface ITutorialDetailsRepo {

    public ResponseEntity<Map<String, Object>> getTutorial(Long id);
    public ResponseEntity<Map<String, Object>>deleteTutorial(Long id);
    public ResponseEntity<Map<String, Object>> createTutorial(TutorialDetailsDto tutorialDto);
    public   ResponseEntity<Map<String, Object>> editTutorial(TutorialDetailsDto tutorialDto);
}
