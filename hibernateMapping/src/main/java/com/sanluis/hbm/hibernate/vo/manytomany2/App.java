package com.sanluis.hbm.hibernate.vo.manytomany2;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class App {

	public static void main(String[] args) {
		Configuration cfg = new Configuration()
				.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver")
				.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/sanluis")
				.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect")
				.setProperty("hibernate.connection.username", "root")
				.setProperty("hibernate.connection.password", "")
				.setProperty("hibernate.show_sql", "true")
				.setProperty("hibernate.current_session_context_class", "thread")
				.addAnnotatedClass(Alumno.class)
				.addAnnotatedClass(Asignatura.class)
				.addAnnotatedClass(AluAsig.class);

		SessionFactory sf = cfg.buildSessionFactory();

		Session s = sf.getCurrentSession();
		
		s.beginTransaction();
		
		List<AluAsig> alumAsig = s.createQuery("from alu_asig").list();
		
		for (AluAsig aA : alumAsig) {
			System.out.println(aA.getAlumno().getIdalumno());
			System.out.println(aA.getAlumno().getNombre());
			System.out.println(aA.getAlumno().getApellidos());
		
		}
		
		
		s.getTransaction().commit();

	}

}
