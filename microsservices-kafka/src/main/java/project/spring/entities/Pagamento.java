package project.spring.entities;

import java.math.BigDecimal;
import java.time.LocalDate;


import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_pagamento")
public class Pagamento {

	@Id
	private String id;
	
	private Long idApiPrincipal;
	
	private String customer;
	
	private LocalDate dueDate;
	private BigDecimal value;
	private String chavePix;
	private String billingType;
	private String status;
	
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_pagamento_id")
	private UsuarioPagamento usuario;
	
	public Pagamento() {

	}

	public Pagamento(String customer, LocalDate dueDate, BigDecimal value, String billingType, String status) {
		this.customer = customer;
		this.dueDate = dueDate;
		this.value = value;
		this.billingType = billingType;
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public String getBillingType() {
		return billingType;
	}

	public void setBillingType(String billingType) {
		this.billingType = billingType;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getChavePix() {
		return chavePix;
	}

	public void setChavePix(String chavePix) {
		this.chavePix = chavePix;
	}

	public UsuarioPagamento getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioPagamento usuario) {
		this.usuario = usuario;
	}

	public Long getIdApiPrincipal() {
		return idApiPrincipal;
	}

	public void setIdApiPrincipal(Long idApiPrincipal) {
		this.idApiPrincipal = idApiPrincipal;
	}

	
	
	
	
}
