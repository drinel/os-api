package com.order.os.resources.exceptions;

import java.io.Serializable;
import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonFormat;

public class StandardError implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm", timezone = "Brazil/East")
	private Instant instant;
	private Integer status;
	private String error;
	
	public StandardError() {
		super();
		
	}
	public StandardError(Instant instant, Integer status, String error) {
		super();
		this.instant = instant;
		this.status = status;
		this.error = error;
	}
	public Instant getInstant() {
		return instant;
	}
	public void setInstant(Instant instant) {
		this.instant = instant;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}	
	
	

}
