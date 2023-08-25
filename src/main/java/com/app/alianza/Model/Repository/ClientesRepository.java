package com.app.alianza.Model.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.alianza.Model.Entity.Clientes;

@Repository
public interface ClientesRepository extends CrudRepository<Clientes, Integer>{
	
	@Query(value = "SELECT * FROM clientes WHERE sharedkey = :sharedkey", nativeQuery = true)
	Optional<Clientes>buscarPorSharedKey(@Param("sharedkey") String sharedkey);
}
