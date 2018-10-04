package com.sanluis.hbm.hibernate.vo.manytomany2;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name="AluAsig")
@Table(name="alu_asig")
public class AluAsig {

	@Id
	private int idAlumnoalumno_idalumno;
	

	@Id
	private int asignatura_idasignatura;
	
	private String nota;
	
	@ManyToOne
	@JoinColumn(name="idalumno")
	private Alumno alumno;
	
	@ManyToOne
	@JoinColumn(name="idasignatura")
	private Asignatura asignatura;
	

	public int getIdAlumnoalumno_idalumno() {
		return idAlumnoalumno_idalumno;
	}

	public void setIdAlumnoalumno_idalumno(int idAlumnoalumno_idalumno) {
		this.idAlumnoalumno_idalumno = idAlumnoalumno_idalumno;
	}

	public int getAsignatura_idasignatura() {
		return asignatura_idasignatura;
	}

	public void setAsignatura_idasignatura(int asignatura_idasignatura) {
		this.asignatura_idasignatura = asignatura_idasignatura;
	}

	public String getNota() {
		return nota;
	}

	public void setNota(String nota) {
		this.nota = nota;
	}

	public Alumno getAlumno() {
		return alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}

	public Asignatura getAsignatura() {
		return asignatura;
	}

	public void setAsignatura(Asignatura asignatura) {
		this.asignatura = asignatura;
	}
}
