package com.prueba.dtos;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TutorialDto {

    private Long id;


    @Size(min = 3, message = "En titulo es demaciado coto")
    private String title;

    @NotNull
    private String states;

    @NotNull(message = "Los detalles  no puden ser nulos")
    private TutorialDetailsDto details;


}
