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
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
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
        Map<String, Object> response = new HashMap<String, Object>();
        TutorialEntity tutorial = repository.findById(id).orElse(null);
        if (tutorial == null) {
            response.put("error", "No exite registro con esa id");
            response.put("status", HttpStatus.BAD_REQUEST.toString());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        try {
            repository.deleteById(id);
            response.put("message", "Eliminacion corretcta");
            response.put("status", HttpStatus.OK);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (DataAccessException e) {
            response.put("error", "Se presento : " + e.getMessage() + " al elminiar");
            response.put("status", HttpStatus.BAD_REQUEST.toString());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
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
        Map<String, Object> response = new HashMap<>();
        if (tutorialDto.getId() == null || tutorialDto.getDetails().getId() == null || tutorialDto.getDetails().getUser().getId() == null) {
            response.put("error", "Tutorial, Detalles , User tiene que tener id");
            response.put("status", HttpStatus.BAD_REQUEST);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
        try {
            TutorialEntity newTutorial = tutorialMapper.getTutorilEntity(tutorialDto);
            newTutorial.setId(tutorialDto.getId());
            System.out.println(newTutorial + "1 PASO");

            if (newTutorial == null) {
                response.put("error", "Verifica los datos");
                response.put("status", HttpStatus.BAD_REQUEST);
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
            }
            TutorialDetailsEntity details = newTutorial.getDetails();
            UserEntity user = userRepository.findById(tutorialDto.getDetails().getUser().getId()).orElse(null);
            details.setId(tutorialDto.getDetails().getId());
            details.setUser(user);
            details.setCraateAt(tutorialDto.getDetails().getCraateAt());
            newTutorial.setDetails(details);
            System.out.println(repository.save(newTutorial));

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
    public ResponseEntity<Map<String, Object>> findAll() {

        Map<String, Object> response = new HashMap<>();

        try {
            List<TutorialEntity> tutorials = (List<TutorialEntity>) repository.findAll();
            if (tutorials.isEmpty()) {
                response.put("message", "No se encontraron errores ");
                response.put("status", HttpStatus.NOT_FOUND);
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
            }
            response.put("message", "busqueda complteta");
            response.put("status", HttpStatus.OK.toString());
            response.put("tutoriales", tutorialMapper.userByVisibe(tutorials));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
        } catch (DataAccessException ex) {
            response.put("error", "Se produjo el siguiente error al consultar : " + ex.getMostSpecificCause());
            response.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
