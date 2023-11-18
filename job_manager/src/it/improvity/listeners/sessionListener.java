package it.improvity.listeners;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.hibernate.Session;

import it.improvity.hibernate.HibernateUtil;
import it.improvity.interfaces.Constants;

public class sessionListener implements HttpSessionListener, Constants {

	@Override
	public void sessionCreated(HttpSessionEvent event) {
		System.out.println("Sessione creata: " + event.getSession().getId());
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {

	}
}