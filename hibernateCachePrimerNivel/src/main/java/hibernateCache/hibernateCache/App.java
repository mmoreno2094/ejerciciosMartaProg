package hibernateCache.hibernateCache;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


import hibernateCache.hibernateCache.Util.HibernateUtil;
import hibernateCache.hibernateCache.model.Autor;
import hibernateCache.hibernateCache.model.Libro;

/**
 * Hello world!
 *
 */
public class App  {
    public static void main( String[] args ) {
    	Configuration cfg = new Configuration()
				.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver")
				.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/editorial")
				.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect")
				.setProperty("hibernate.connection.username", "root")
				.setProperty("hibernate.connection.password", "")
				.setProperty("hibernate.show_sql", "true")
				.setProperty("hibernate.current_session_context_class", "thread")
				.addAnnotatedClass(Autor.class)
				.addAnnotatedClass(Libro.class);

		SessionFactory sessionFactory = cfg.buildSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		
		//recibir autor con id=1
		Autor aut = (Autor) session.load(Autor.class, new Long(1));
		printData(aut, 1);
		
		//esperar unos segundos para la actualizacion de los datos en la memoria cache
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Pedir los mismos datos, chequemos en el log que NO se ha producido ninguna consulta a la BD
		Autor aut2 = (Autor) session.load(Autor.class, new Long(1));
		printData(aut2, 2);
		
		//Create new session
		Session newSession = sessionFactory.openSession();
		
		//Pedir los mismos datos, chequemos en el log que se ha producido la consulta a la BD
		Autor aut3 = (Autor) session.load(Autor.class, new Long(1));
		printData(aut3, 3);
		
		//START: evict example to remove specific object from hibernate first level cache
		//Pedir un autor con id=2, como será la primera vez se almacerará en la cache y lo veremos en el log
		Autor aut4 = (Autor) session.load(Autor.class, new Long(2));
		printData(aut4, 4);
		
		/*//evict the employee object with id=1
		session.evict(emp);
		System.out.println("Session Contains Employee with id=1?"+session.contains(emp));

		//since object is removed from first level cache, you will see query in logs
		Employee emp4 = (Employee) session.load(Employee.class, new Long(1));
		printData(emp4,5);
		
		//this object is still present, so you won't see query in logs
		Employee emp5 = (Employee) session.load(Employee.class, new Long(2));
		printData(emp5,6);
		//END: evict example
		
		//START: clear example to remove everything from first level cache
		session.clear();
		Employee emp6 = (Employee) session.load(Employee.class, new Long(1));
		printData(emp6,7);
		Employee emp7 = (Employee) session.load(Employee.class, new Long(2));
		printData(emp7,8);
		
		System.out.println("Session Contains Employee with id=2?"+session.contains(emp7));*/
		
		tx.commit();
		sessionFactory.close();
	}

	private static void printData(Autor aut, int contador) {
		List<Libro> libros = new ArrayList<Libro>();
		libros = aut.getLibros();
		System.out.println(contador + ":: Nombre Autor="+aut.getNombre());
		System.out.println("Libros: ");
		for (int i = 0; i < libros.size(); i++) {
			System.out.print(libros.get(i).getTitulo() + ", ");
		}
		System.out.println();
	}
}

