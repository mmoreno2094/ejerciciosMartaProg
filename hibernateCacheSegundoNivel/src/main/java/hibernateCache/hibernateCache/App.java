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
				.setProperty("hibernate.cache.use_second_level_cache", "true")
				.setProperty("hibernate.cache.use_query_cache", "true")
				.setProperty("hibernate.cache.region.factory_class", "org.hibernate.cache.ehcache.EhCacheRegionFactory")
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
		
		//Pedir los mismos datos, vemos que en el log que se ha producido la consulta a la BD solo para los libros y no para el autor
		Autor aut3 = (Autor) newSession.load(Autor.class, new Long(1));
		printData(aut3, 3);
		

		//Pedir un autor con id=2, como será la primera vez se almacerará en la cache y lo veremos en el log
		Autor aut4 = (Autor) session.load(Autor.class, new Long(2));
		printData(aut4, 4);
		
		
		
		tx.commit();
		sessionFactory.close();
	}

	private static void printData(Autor aut, int contador) {
		List<Libro> libros = new ArrayList<Libro>();
		libros = aut.getLibros();
		System.out.println(contador + ":: Nombre Autor="+aut.getNombre() + " " + aut.getApellidos());
		System.out.println("Libros: ");
		for (int i = 0; i < libros.size(); i++) {
			System.out.print(libros.get(i).getTitulo() + ", ");
		}
		System.out.println();
	}
}

