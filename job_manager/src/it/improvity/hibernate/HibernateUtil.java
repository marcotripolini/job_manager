package it.improvity.hibernate;

import org.hibernate.cfg.Configuration;
import org.hibernate.impl.SessionImpl;

import it.improvity.utils.Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class HibernateUtil {
	private static SessionFactory sessionFactory;
	static {
		try {
			sessionFactory = new Configuration()
			.configure()
			.setProperty("hibernate.connection.url", 		Config.getJDBCCONNECTION())      	// override configuration from xml file
			.setProperty("hibernate.connection.password", 	Config.getJDBPASSWORD()) 		// override configuration from xml file
			.setProperty("hibernate.connection.username", 	Config.getJDBCUSER())  		// override configuration from xml file
			.buildSessionFactory();
		} catch (Throwable ex) {
			ex.printStackTrace();
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static void shutdown() {
		getSessionFactory().close();
	}

	public synchronized static void saveOrUpdate(Object o) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.getTransaction().setTimeout(10);
			session.getTransaction().begin();
			session.saveOrUpdate(o);
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public synchronized static void delete(Object o) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.getTransaction().setTimeout(10);
			session.getTransaction().begin();
			session.delete(o);
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public synchronized static void directSQL(String sql) {
		// TODO AGGIUNGERE connessione JDBC SENZA CONVERSIONE HIBERNATE.
		// importante perchè la conversione della connessione esaurisce la memoria
		// ************************************************************************

//		Connection conn = ((SessionImpl) HibernateUtil.getSessionFactory().openSession()).connection();
		Connection conn = null;

		Statement stmt = null;
		try {
			conn = DriverManager.getConnection(
					it.improvity.utils.Config.getJDBCCONNECTION(),
					it.improvity.utils.Config.getJDBCUSER(),
					it.improvity.utils.Config.getJDBPASSWORD());
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}