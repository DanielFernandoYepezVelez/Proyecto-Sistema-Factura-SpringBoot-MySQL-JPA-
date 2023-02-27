package com.nextappoficial.springboot.app.invoice.sistem.models.entity;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "clients")
public class Client implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "No Puede Ser Vacío")
	@Size(min = 4, max = 25, message = "El Tamaño Tiene Que Estar Entre 4 Y 25")
	@Column(nullable = false)
	private String name;
	
	@NotEmpty(message = "No Puede Ser Vacío")
	@Column(name = "last_name")
	private String lastName;
	
	@NotEmpty(message = "No Puede Ser Vacío")
	@Email(message = "No Es Una Dirección De Correo Electrónico Válida")
	@Column(nullable = false, unique = true)
	private String email;
	
	@NotNull(message = "No Puede Ser Vacío")
	@Column(name = "created_at")
	@Temporal(TemporalType.DATE)
	private Date createdAt;
	
	private String photo;
	
	@NotNull(message = "La Región No Puede Ser Vacía")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "region_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Region region;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "client", cascade = CascadeType.ALL)
	private List<Invoice> invoices;

	public Client() {
		invoices = new ArrayList<>();
	}

	/* Este Metodo Se Activa Antes De Que Los Datos Se Inserten En La Base De Datos */
	/* @PrePersist
	public void prePersist() {
		createdAt = new Date();
	} */
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	public List<Invoice> getInvoices() {
		return invoices;
	}

	public void setInvoices(List<Invoice> invoices) {
		this.invoices = invoices;
	}

	@Serial
	private static final long serialVersionUID = 1L;
}
