package com.mx.Catering.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "MESERO")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Mesero {
	@Id
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "NOMBRE")
	private String nombre;
	
	@Column(name = "APP")
	private String  app;
	
	@Column(name = "APM")
	private String apm;
	
	@Column(name = "SUELDO")
	private Float sueldo;
	
	//Cardinalidas
	//Un mesero atiende varias mesas 
	@OneToMany(mappedBy = "mesero", cascade = CascadeType.ALL)
	@JsonIgnore//Se agrega para omitir una propiedad o lista de propiedades
	
	List<Mesas> lista = new ArrayList<Mesas>();

}
