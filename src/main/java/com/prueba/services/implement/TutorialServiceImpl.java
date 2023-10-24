package com.prueba.services.implement;

import com.prueba.dtos.TutorialDto;
import com.prueba.dtos.UserDto;
import com.prueba.entitys.TutorialDetailsEntity;
import com.prueba.entitys.TutorialEntity;
import com.prueba.entitys.UserEntity;
import com.prueba.mappers.TutorialDetailsMapper;
import com.prueba.mappers.TutorialMapper;
import com.prueba.mappers.UserMaper;
import com.prueba.repositorys.TutorialDetailsRepository;
import com.prueba.repositorys.TutorialRepository;
import com.prueba.repositorys.UserRepository;
import com.prueba.services.interfaces.ITutorialDetaiService;
import com.prueba.services.interfaces.ITutorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class TutorialServiceImpl implements ITutorialService {

    @Autowired
    private TutorialRepository repository;
    @Autowired
    private TutorialMapper tutorialMapper;
    @Autowired
    private TutorialDetailsRepository detailsRepo;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TutorialDetailsMapper detailsMapper;
    @Autowired
    private UserMaper userMaper;

    @Override
    public ResponseEntity<Map<String, Object>> getTutorial(Long id) {

        Map<String, Object> response = new HashMap<>();
        if (id == null) {
            response.put("error", "No ingreso ninguna id");
            response.put("status", HttpStatus.BAD_REQUEST.toString());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        try {
            TutorialEntity tutorial = repository.findById(id).orElse(null);
            if (tutorial == null) {
                response.put("error", "No hay reghistros");
                response.put("status", HttpStatus.BAD_REQUEST.toString());
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }


            response.put("message", "Busqueda Completa");
            response.put("tutorial", tutorialMapper.getTutorilDto(tutorial));
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (DataAccessException ex) {
            response.put("error", "Se produjo un error mientras se buscaba: " + ex.getMessage());
            response.put("status", HttpStatus.BAD_REQUEST.toString());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

    }

    @Override
    public ResponseEntity<Map<String, Object>> deleteTutorial(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<Map<String, Object>> createTutorial(TutorialDto tutorialDto) {

        Map<String, Object> response = new HashMap<>();
        try {

            TutorialEntity tutorial = tutorialMapper.getTutorilEntity(tutorialDto);
            TutorialDetailsEntity details = new TutorialDetailsEntity();
            UserEntity user = userRepository.findById(tutorialDto.getDetails().getUser().getId()).orElse(null);
            details.setUser(user);
            tutorial.setDetails(details);
            System.out.println(repository.save(tutorial));


            response.put("message", "Se completo el registro ");
            response.put("status", HttpStatus.OK);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

        } catch (DataAccessException ex) {
            response.put("error", "Se produjo el siguiente error al guardar : " + ex.getMostSpecificCause());
            response.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> editTutorial(TutorialDto tutorialDto) {
        return null;
    }

    @Override
    public ResponseEntity<Map<String, Object>> getByVisible(TutorialDto tutorialDto) {
        return null;
    }
}
