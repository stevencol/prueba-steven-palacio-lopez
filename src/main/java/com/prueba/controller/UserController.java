package com.prueba.controller;

import com.prueba.dtos.UserDto;
import com.prueba.services.interfaces.IUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createuser(@Valid @RequestBody UserDto userDto, BindingResult result) {
        Map<String, Object> response = new HashMap<>();
        if (result.hasErrors()) {
            result.getFieldErrors().forEach(error -> {
                response.put(error.getField(), error.getDefaultMessage());
            });
            response.put("error", "Verifica los campos");
            response.put("status", HttpStatus.BAD_REQUEST.toString());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
        return userService.createUser(userDto);

    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Map<String, Object>> getuser(@PathVariable Long id) {
        return userService.getUser(id);
    }
}
