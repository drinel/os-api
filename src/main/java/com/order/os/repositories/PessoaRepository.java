package com.order.os.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.order.os.domain.Pessoa;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long>{
	
//	@Query("SELECT obj FROM Pessoa obj WHERE obj.cpf = :cpf")
//	Pessoa findByCPF(@Param("cpf") String cpf);
//	
	Pessoa findByCpf(String cpf);

}
