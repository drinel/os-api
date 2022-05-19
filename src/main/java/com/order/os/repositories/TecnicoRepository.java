package com.order.os.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.order.os.domain.Tecnico;

@Repository
public interface TecnicoRepository extends JpaRepository<Tecnico, Long>{

//	@Query("SELECT obj FROM Tecnico obj WHERE obj.cpf = :cpf")
//	Tecnico findByCPF(@Param("cpf") String cpf);
//	
	Tecnico findByCpf(String cpf);

}
 