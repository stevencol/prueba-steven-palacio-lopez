package com.prueba.services.interfaces;

import com.prueba.dtos.UserDto;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface IUserService {

    ResponseEntity<Map<String, Object>> createUser(UserDto user);

    ResponseEntity<Map<String, Object>> getUser(Long id);
}
