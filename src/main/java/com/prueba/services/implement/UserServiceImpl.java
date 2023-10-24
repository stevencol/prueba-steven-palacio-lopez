package com.prueba.services.implement;

import com.prueba.dtos.UserDto;
import com.prueba.mappers.UserMaper;
import com.prueba.repositorys.UserRepository;
import com.prueba.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepos;
    @Autowired
    private UserMaper maper;

    @Override
    @Transactional
    public ResponseEntity<Map<String, Object>> createUser(UserDto user) {

        Map<String, Object> response = new HashMap<>();
        

        try {
            userRepos.save(maper.getEntity(user));

        } catch (DataAccessException ex) {
            response.put("error", "Se produjo el siguiente error al guardar : " + ex.getMostSpecificCause());
            response.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("message", "Se completo el registro ");
        response.put("status", HttpStatus.OK);

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<Map<String, Object>> getUser(Long id) {
        Map<String, Object> response = new HashMap<String, Object>();

        try {
            UserDto user = maper.getDto(userRepos.findById(id).orElse(null));
            if (user == null) {
                response.put("error", "No se encotron ningun registro");
                response.put("status", HttpStatus.BAD_REQUEST);
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
            }
            response.put("message", "Busqueda Correcta");
            response.put("user", user);
            response.put("status", HttpStatus.OK);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
        } catch (DataAccessException e) {
            response.put("error", "Se produjo error al : " + e.getMessage());
            response.put("status", HttpStatus.BAD_REQUEST);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);

        }

    }
}
