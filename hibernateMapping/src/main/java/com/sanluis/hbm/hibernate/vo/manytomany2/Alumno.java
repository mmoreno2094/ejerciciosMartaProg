package com.sanluis.hbm.hibernate.vo.manytomany2;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity(name="Alumno")
@Table(name="alumno")
public class Alumno {

	@Id
	@GeneratedValue
	@Column
	private int idalumno;


	@Column
	private String nombre;
	
	@Column
	private String apellidos;
	
	@OneToMany(mappedBy="alumno")
	private List<AluAsig> aluAsig;
	

	public int getIdalumno() {
		return idalumno;
	}

	public void setIdalumno(int idalumno) {
		this.idalumno = idalumno;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public List<AluAsig> getAluAsig() {
		return aluAsig;
	}

	public void setAluAsig(List<AluAsig> aluAsig) {
		this.aluAsig = aluAsig;
	}
}
