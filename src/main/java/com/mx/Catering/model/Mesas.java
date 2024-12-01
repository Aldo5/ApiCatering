package com.mx.Catering.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "MESAS")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Mesas {
	@Id
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "NUM_MESA")
	private Long numMesa;
	
	@Column(name = "NUM_SILLAS")
	private Long numSillas;
	
	
	//Fetch con eso indicamos como debe ser cargada la entidad
	//FetchType.EAGER -- indicamos que la relacion debe ser cargado al momento(procesos hilos)
	//Cardinalidad
	//Muchos modelos pertenecen a una marca
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_MESERO")
	Mesero mesero; //Esta variable de tipo objeto debe coincidir con lo que dice en el mappedBy = "marcas"
}
