package com.backend.reactivo.infrastructure.repositories;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.backend.reactivo.infrastructure.entities.CapacidadEntity;

import reactor.core.publisher.Flux;

@Repository
public interface CapacidadEntityRepository extends ReactiveCrudRepository<CapacidadEntity, Long> {

	@Query("SELECT c.id, c.nombre, c.descripcion " + "FROM Capacidad c "
			+ "INNER JOIN Bootcamp_Capacidad bc ON bc.capacidad_id = c.id " + "WHERE bc.bootcamp_id = :bootcampId")
	Flux<CapacidadEntity> findByBootcampId(Long bootcampId);
}
