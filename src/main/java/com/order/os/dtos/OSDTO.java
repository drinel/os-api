package com.order.os.dtos;

import java.io.Serializable;
import java.time.Instant;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.order.os.domain.OS;
import com.order.os.domain.enums.Prioridade;
import com.order.os.domain.enums.Status;

public class OSDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm", timezone = "Brazil/East")
	private Instant dataAbertura;
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm", timezone = "Brazil/East")
	private Instant dataFechamento;
	
	private Integer prioridade;
	
	@NotEmpty(message = "O campo OBSERVAÇÕES é obrigatório!")
	private String observacoes;
	
	private Integer status;


	private Long tecnico;

	private Long cliente;

	public OSDTO() {
		super();
	
	}

	public OSDTO(OS obj) {
		super();
		this.id = obj.getId();
		this.dataAbertura = obj.getDataAbertura();
		this.dataFechamento = obj.getDataFechamento();
		this.prioridade = obj.getPrioridade().getCod();
		this.observacoes = obj.getObservacoes();
		this.status = obj.getStatus().getCod();
		this.tecnico = obj.getTecnico().getId();
		this.cliente = obj.getCliente().getId();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Instant getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(Instant dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public Instant getDataFechamento() {
		return dataFechamento;
	}

	public void setDataFechamento(Instant dataFechamento) {
		this.dataFechamento = dataFechamento;
	}

	public Prioridade getPrioridade() {
		return Prioridade.toEnum(this.prioridade);
	}

	public void setPrioridade(Integer prioridade) {
		this.prioridade = prioridade;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public Status getStatus() {
		return Status.toEnum(this.status);
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getTecnico() {
		return tecnico;
	}

	public void setTecnico(Long tecnico) {
		this.tecnico = tecnico;
	}

	public Long getCliente() {
		return cliente;
	}

	public void setCliente(Long cliente) {
		this.cliente = cliente;
	}

	
}
