package com.prueba.services.interfaces;

import com.prueba.dtos.TutorialDetailsDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface ITutorialDetaiService {

    public ResponseEntity<Map<String, Object>> getTutorial(Long id);

    public ResponseEntity<Map<String, Object>> deleteTutorial(Long id);

    public ResponseEntity<Map<String, Object>> createTutorial(TutorialDetailsDto tutorialDto);

    public ResponseEntity<Map<String, Object>> editTutorial(TutorialDetailsDto tutorialDto);
}
