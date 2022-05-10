package com.order.os.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.order.os.domain.Cliente;
import com.order.os.domain.Pessoa;
import com.order.os.dtos.ClienteDTO;
import com.order.os.repositories.ClienteRepository;
import com.order.os.repositories.PessoaRepository;
import com.order.os.services.exceptions.DataIntegrityViolationException;
import com.order.os.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repository;
	@Autowired
	private PessoaRepository pessoaRepo;
	
	public Cliente findById(Long id) {
		Optional<Cliente> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
		
		
	}

	public List<Cliente> findAll() {
		
		return repository.findAll();
	}
	
	
	public Cliente create(ClienteDTO objDTO) {
         if(findByCPF(objDTO) != null) {
        	throw new DataIntegrityViolationException("CPF já cadastrado!");
         }
		return repository.save(new Cliente(null, objDTO.getNome(), objDTO.getCpf(), objDTO.getTelefone()));
	}
	

	public Cliente update(Long id, @Valid ClienteDTO objDTO) {
		Cliente oldObj = findById(id);
		
		if(findByCPF(objDTO) != null && findByCPF(objDTO).getId() != id) {
			throw new DataIntegrityViolationException("CPF já cadastrado!");
        
		}
		 
		oldObj.setNome(objDTO.getNome());
		oldObj.setCpf(objDTO.getCpf());
		oldObj.setTelefone(objDTO.getTelefone());
		
		return repository.save(oldObj);
	}
	
	
	public void deleteById(Long id) {
	    Cliente obj = findById(id);
	    if(obj.getList().size()>0) {
	    	throw new DataIntegrityViolationException("Cliente possui Ordens de Serviço e não pode ser deletado");
	    }
		repository.deleteById(id);

		
	}
	
	
	private Pessoa findByCPF(ClienteDTO objDTO) {
		Pessoa obj = pessoaRepo.findByCpf(objDTO.getCpf());
		if(obj != null) {
			return obj;
		}
		return null;
	}

	


}
