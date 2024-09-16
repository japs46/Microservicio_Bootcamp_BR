package com.backend.reactivo.infrastructure.repositories;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.backend.reactivo.infrastructure.entities.BootcampCapacidadEntity;

@Repository
public interface BootcampCapacidadEntityRepository extends ReactiveCrudRepository<BootcampCapacidadEntity, Long>{

}
