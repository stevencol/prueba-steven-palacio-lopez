package com.prueba.repositorys;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TutorialEntity extends CrudRepository <TutorialEntity, Long>{
}
