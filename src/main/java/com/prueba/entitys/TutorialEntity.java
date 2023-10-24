package com.prueba.entitys;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.prueba.enums.States;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tutoriales")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
public class TutorialEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "state")
    private States states;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, targetEntity = TutorialDetailsEntity.class)
    @JoinColumn(name = "detalles_id")
    private TutorialDetailsEntity details;


}
