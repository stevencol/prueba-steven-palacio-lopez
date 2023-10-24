package com.prueba.entitys;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "second_name")

    private String secondName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "user_document")
    private String userDocument;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, targetEntity = TutorialDetailsEntity.class, cascade = CascadeType.ALL)
    private Set<TutorialDetailsEntity> details;


}
