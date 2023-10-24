package com.prueba.entitys;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.prueba.enums.States;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tutoriales")
 @JsonInclude(JsonInclude.Include.NON_NULL)
public class TutotiralEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private  Long id;

    @Column(name="title")
    @Size(min = 3 , message = "En titulo es demaciado coto")
    private String title;

    @Column(name = "state")
    private States states;

    @OneToOne(cascade = CascadeType.ALL ,orphanRemoval = true, targetEntity = TutorialDetailsEntity.class)
    @JoinColumn(name ="detalles_id" )
    private TutorialDetailsEntity details;




}
