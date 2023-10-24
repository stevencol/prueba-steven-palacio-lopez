package com.prueba.entitys;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "datails")
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TutorialDetailsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @ManyToOne(targetEntity = UserEntity.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "createdAt")
    private Date craateAt;

    @PrePersist
    private void setCrate() {
        this.craateAt = new Date();
    }


}
