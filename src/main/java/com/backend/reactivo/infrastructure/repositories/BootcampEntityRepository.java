package com.backend.reactivo.infrastructure.repositories;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.backend.reactivo.infrastructure.entities.BootcampEntity;

@Repository
public interface BootcampEntityRepository extends ReactiveCrudRepository<BootcampEntity, Long>{
	
}
