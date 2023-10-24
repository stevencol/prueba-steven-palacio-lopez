package com.prueba.services.interfaces;

import com.prueba.dtos.TutorialDto;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface ITutorialService {
    public ResponseEntity<Map<String, Object>> getTutorial(Long id);
    public ResponseEntity<Map<String, Object>> deleteTutorial(Long id);
    public ResponseEntity<Map<String, Object>> createTutorial(TutorialDto tutorialDto);
    public ResponseEntity<Map<String, Object>> editTutorial(TutorialDto tutorialDto);

}
