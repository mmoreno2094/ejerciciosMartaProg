package com.sanluis.hbm.hibernate.vo.manytomany2;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity(name="Asignatura")
@Table(name="asignatura")
public class Asignatura {

	@Id
	@Column
	@GeneratedValue
	private int idasignatura;
	
	@Column
	private String nombre;
	
	@OneToMany(mappedBy="asignatura")
	private List<AluAsig> aluAsig;
}
