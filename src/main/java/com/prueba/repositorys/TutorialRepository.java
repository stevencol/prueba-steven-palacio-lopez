package com.prueba.repositorys;

import com.prueba.entitys.TutorialEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TutorialRepository extends CrudRepository<TutorialEntity, Long> {
}
