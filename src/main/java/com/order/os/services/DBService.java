package com.order.os.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.order.os.domain.Cliente;
import com.order.os.domain.OS;
import com.order.os.domain.Tecnico;
import com.order.os.domain.enums.Prioridade;
import com.order.os.domain.enums.Status;
import com.order.os.repositories.ClienteRepository;
import com.order.os.repositories.OSRepository;
import com.order.os.repositories.TecnicoRepository;

@Service
public class DBService {
	

	@Autowired
	private TecnicoRepository tecnicoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private OSRepository osRepository;


	public void instanciaDB() {
		
		Tecnico t1 = new Tecnico(null, "Catarino Segundo", "754.205.870-33", "(88)98989-9898");
		
		Tecnico t2 = new Tecnico(null, "Ubaldo Ponsio", "903.799.380-06", "(88)95545-7575");
		
		Tecnico t3 = new Tecnico(null, "Lindomario Perpétuo", "104.310.990-06", "(81)94040-2165");
		
		Tecnico t4 = new Tecnico(null, "Mayaro Carlos", "192.357.310-11", "(80)97290-7135");
		
		Tecnico t5 = new Tecnico(null, "Suzelen Pereira", "476.756.560-08", "(80)93368-4750");
		

		Cliente c1 = new Cliente(null, "Samuela Covas", "539.527.150-34", "(88)97979-7071");
		
		Cliente c2 = new Cliente(null, "Sauron Siqueira", "224.620.580-87", "(91)93978-1515");
		
		Cliente c3 = new Cliente(null, "Pâmelo Novaes", "194.950.560-04", "(91)94365-9070");
		
		Cliente c4 = new Cliente(null, "Priscilo Alves", "882.317.350-70", "(83)93232-6168");
		
		Cliente c5 = new Cliente(null, "Xeron Istone", "436.918.320-08", "(80)94554-7863");
		
		

		OS os1 = new OS(null, Prioridade.ALTA, "Teste OS", Status.ANDAMENTO, t1, c1);

		t1.getList().add(os1);
		c1.getList().add(os1);

		tecnicoRepository.saveAll(Arrays.asList(t1,t2,t3,t4,t5));
		clienteRepository.saveAll(Arrays.asList(c1,c2,c3,c4,c5));
		osRepository.saveAll(Arrays.asList(os1));
	}
}
