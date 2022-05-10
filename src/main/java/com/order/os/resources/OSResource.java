package com.order.os.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.order.os.dtos.OSDTO;
import com.order.os.services.OSService;

@CrossOrigin("*")
@RestController
@RequestMapping("/os")
public class OSResource {
	
	@Autowired 
	private OSService service;

	@GetMapping("/{id}")
	public ResponseEntity<OSDTO> findById(@PathVariable Long id){
		OSDTO obj = new OSDTO(service.findById(id));
		return ResponseEntity.ok().body(obj);
		
	}
	
	@GetMapping
	public ResponseEntity<List<OSDTO>> findAll(){
		List<OSDTO> list = service.findAll().stream().map(obj -> new OSDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(list);
		
	}
	
	@PostMapping
	public ResponseEntity<OSDTO> create(@Valid @RequestBody OSDTO objDTO){
		objDTO = new OSDTO(service.create(objDTO));
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(objDTO.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	// Não será usado id ja que está sendo tratado no service
	@PutMapping
	public ResponseEntity<OSDTO> update(@Valid @RequestBody OSDTO objDTO){
		objDTO = new OSDTO(service.update(objDTO));
		return ResponseEntity.ok().body(objDTO);
	}
}
