package com.prueba.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.prueba.entitys.TutorialDetailsEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {


    private Long id;

    @Size(min = 3, message = "El nombre es miuy corto")
    private String firstName;

    private String secondName;

    @Size(min = 3, message = "El apellido es miuy corto")
    private String lastName;

    @Size(min = 4, message = "El documento es muy corto")
    private String userDocument;

}
