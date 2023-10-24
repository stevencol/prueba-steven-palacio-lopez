package com.prueba.controller;


import com.prueba.dtos.TutorialDto;
import com.prueba.services.interfaces.ITutorialService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/tutorial")
public class TutorialController {

    @Autowired
    private ITutorialService tutorialService;

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createTutorial(@Valid @RequestBody TutorialDto tutorialDto, BindingResult result) {
        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors() || !tutorialDto.getStates().equals("VISIBLE") && !tutorialDto.getStates().equals("OCULTO")) {

            if (!tutorialDto.getStates().equals("VISIBLE") && !tutorialDto.getStates().equals("OCULTO")) {
                result.addError(new FieldError("state", "state", "El estado no es valido"));
            }
            result.getFieldErrors().forEach(error -> {
                response.put(error.getField(), error.getDefaultMessage());
            });
            response.put("error", "verifica los campos:");
            response.put("status", HttpStatus.BAD_REQUEST.toString());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        return tutorialService.createTutorial(tutorialDto);

    }

    @GetMapping("get/{id}")
    public ResponseEntity<Map<String, Object>> getTutorial(@PathVariable Long id) {

        return tutorialService.getTutorial(id);
    }

    @GetMapping("get-visible")
    public ResponseEntity<Map<String, Object>> getVisible() {

        return tutorialService.findAll();
    }


    @PutMapping("/edit")
    public ResponseEntity<Map<String, Object>> editTutorial(@Valid @RequestBody TutorialDto tutorialDto, BindingResult result) {
        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors() || !tutorialDto.getStates().equals("VISIBLE") && !tutorialDto.getStates().equals("OCULTO")) {

            if (!tutorialDto.getStates().equals("VISIBLE") && !tutorialDto.getStates().equals("OCULTO")) {
                result.addError(new FieldError("state", "state", "El estado no es valido"));
            }
            result.getFieldErrors().forEach(error -> {
                response.put(error.getField(), error.getDefaultMessage());
            });
            response.put("error", "verifica los campos:");
            response.put("status", HttpStatus.BAD_REQUEST.toString());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        return tutorialService.editTutorial(tutorialDto);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Object>> deleteById(@PathVariable Long id) {

        return tutorialService.deleteTutorial(id);
    }


}
