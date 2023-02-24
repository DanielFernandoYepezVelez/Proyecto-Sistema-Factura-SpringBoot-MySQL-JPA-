package com.nextappoficial.springboot.app.invoice.sistem.models.entity;

import java.io.Serial;
import java.io.Serializable;
//import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
// import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class Role implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true, length = 40)
	private String name;
	
	/* @ManyToMany(mappedBy = "roles")
	private List<User> users; NO ES NECESARIO PARA ESTE EJEMPLO */

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

	@Serial
	private static final long serialVersionUID = 1L;

}