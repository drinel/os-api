package com.order.os.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.order.os.domain.Cliente;
import com.order.os.domain.OS;
import com.order.os.domain.Tecnico;
import com.order.os.domain.enums.Prioridade;
import com.order.os.domain.enums.Status;
import com.order.os.dtos.OSDTO;
import com.order.os.repositories.OSRepository;
import com.order.os.services.exceptions.ObjectNotFoundException;

@Service
public class OSService {
	
	@Autowired
	private OSRepository repository;
	@Autowired
	private TecnicoService tecService;
	@Autowired
	private ClienteService cliService;
	
	public OS findById(Long id) {
		Optional<OS> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + ", Tipo: " + OS.class.getName()));
	}
	
    public List<OS> findAll() {
    	return repository.findAll();
    }

	public OS create(@Valid OSDTO objDTO) {
		
		return fromDTO(objDTO);
	}
	
	public OS update(@Valid OSDTO objDTO) {
		findById(objDTO.getId());
		return fromDTO(objDTO);
	}

	private OS fromDTO(OSDTO obj) {
		OS newObj = new OS();
		
		newObj.setId(obj.getId());
		newObj.setObservacoes(obj.getObservacoes());
		newObj.setPrioridade(Prioridade.toEnum(obj.getPrioridade()));
		newObj.setStatus(Status.toEnum(obj.getStatus()));
		
		Tecnico tec = tecService.findById(obj.getTecnico());
		Cliente cli = cliService.findById(obj.getCliente());
		
		newObj.setTecnico(tec);
		newObj.setCliente(cli);
		
		if(newObj.getStatus().getCod().equals(2)) {
			newObj.setDataFechamento(LocalDateTime.now());
		}
		
		return repository.save(newObj);
	}

	
}
