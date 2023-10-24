package com.prueba.repositorys;

import com.prueba.entitys.TutorialDetailsEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TutorialDetailsRepository extends CrudRepository<TutorialDetailsEntity, Long> {
}
