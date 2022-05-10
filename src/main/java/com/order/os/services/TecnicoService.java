package com.order.os.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.order.os.domain.Pessoa;
import com.order.os.domain.Tecnico;
import com.order.os.dtos.TecnicoDTO;
import com.order.os.repositories.PessoaRepository;
import com.order.os.repositories.TecnicoRepository;
import com.order.os.services.exceptions.DataIntegrityViolationException;
import com.order.os.services.exceptions.ObjectNotFoundException;

@Service
public class TecnicoService {
	
	@Autowired
	private TecnicoRepository repository;
	@Autowired
	private PessoaRepository pessoaRepo;
	
	public Tecnico findById(Long id) {
		Optional<Tecnico> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Tecnico.class.getName()));
		
		
	}

	public List<Tecnico> findAll() {
		
		return repository.findAll();
	}
	
	
	public Tecnico create(TecnicoDTO objDTO) {
         if(findByCPF(objDTO) != null) {
        	throw new DataIntegrityViolationException("CPF já cadastrado!");
         }
		return repository.save(new Tecnico(null, objDTO.getNome(), objDTO.getCpf(), objDTO.getTelefone()));
	}
	

	public Tecnico update(Long id, @Valid TecnicoDTO objDTO) {
		Tecnico oldObj = findById(id);
		
		if(findByCPF(objDTO) != null && findByCPF(objDTO).getId() != id) {
			throw new DataIntegrityViolationException("CPF já cadastrado!");
        
		}
		 
		oldObj.setNome(objDTO.getNome());
		oldObj.setCpf(objDTO.getCpf());
		oldObj.setTelefone(objDTO.getTelefone());
		
		return repository.save(oldObj);
	}
	
	
	public void deleteById(Long id) {
	    Tecnico obj = findById(id);
	    if(obj.getList().size()>0) {
	    	throw new DataIntegrityViolationException("Técnico possui Ordens de Serviço e não pode ser deletado");
	    }
		repository.deleteById(id);

		
	}
	
	
	private Pessoa findByCPF(TecnicoDTO objDTO) {
		Pessoa obj = pessoaRepo.findByCpf(objDTO.getCpf());
		if(obj != null) {
			return obj;
		}
		return null;
	}

	


}
