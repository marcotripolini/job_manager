package it.improvity.listeners;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.hibernate.Session;
import org.hibernate.impl.SessionImpl;

import com.opensymphony.xwork2.ActionContext;

import it.improvity.beans.Vetrina;
import it.improvity.dto.ApplicationConfiguration;
import it.improvity.hibernate.HibernateUtil;
import it.improvity.interfaces.Constants;
import it.improvity.utils.Config;
import it.improvity.utils.ReadProperty;

public class startListener implements ServletContextListener, Constants {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override

	public void contextInitialized(ServletContextEvent event) {
		try {
			String realpath = "";
			realpath = event.getServletContext().getRealPath("/");

			System.out.println("Starting Java Application >>>>>>>>>>>>>>>>>>> "
					+ event.getServletContext().getServletContextName());
			Properties p = new Properties();
			InputStream file = startListener.class.getClassLoader().getResourceAsStream("package.properties");
			p.load(file);

			new ReadProperty(realpath, event.getServletContext().getServletContextName());
			ReadProperty.getInstance();

			event.getServletContext().setAttribute("PROPERTIES", p);
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());

			String siteid = it.improvity.utils.Config.getSITEID();
			event.getServletContext().setAttribute("SITEID", siteid);

			System.out.println("Application successfully started at " + new Date().toString());

		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			ServletContext sc = event.getServletContext();
			InputStream input = event.getServletContext().getResourceAsStream("/WEB-INF/application.properties");
			Properties properties = new Properties();
			properties.load(input);

			ApplicationConfiguration ac = new ApplicationConfiguration();

			ac.setCancelUrl(properties.getProperty("CANCEL_URL").toString());
			ac.setReturnUrl(properties.getProperty("RETURN_URL").toString());

			// check the application property wheather the app is in live or sandbox and load config accordingly
			if(properties.getProperty("IS_APPLICATION_IN_SANDBOX").toString().equals("true")) {
				// load all properties for sandbox
				ac.setClientId(properties.getProperty("CLIENT_ID").toString());
				ac.setExpressCheckoutUrl(properties.getProperty("EXPRESS_CHECKOUT_SANDBOX_URL").toString());
				ac.setSecret(properties.getProperty("SECRET").toString());
				ac.setBnCode(properties.getProperty("BN_CODE").toString());
				ac.setReturnUrl(properties.getProperty("RETURN_URL").toString());
				ac.setCancelUrl(properties.getProperty("CANCEL_URL").toString());
				ac.setEnvironment("sandbox");

				System.out.println("Loading config for sandbox");
				sc.setAttribute("config", ac);
			} else {
				// load all properties for live
				ac.setClientId(properties.getProperty("CLIENT_ID_LIVE").toString());
				ac.setExpressCheckoutUrl(properties.getProperty("EXPRESS_CHECKOUT_LIVE_URL").toString());
				ac.setSecret(properties.getProperty("SECRET_LIVE").toString());
				ac.setBnCode(properties.getProperty("BN_CODE").toString());
				ac.setReturnUrl(properties.getProperty("RETURN_URL").toString());
				ac.setCancelUrl(properties.getProperty("CANCEL_URL").toString());
				ac.setEnvironment("production");
				System.out.println("Loading config for live");
				sc.setAttribute("config", ac);
			}
		} catch (IOException e) {
			System.out.println(e);
			e.printStackTrace();
		}
		System.out.println("Added Config properties in ServletContextListener");
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		try {
			// System.out.println("Stopping Java Application >>>>>>>>>>>>>>>>>>> " +
			// event.getServletContext().getServletContextName());
			Enumeration<Driver> drivers = DriverManager.getDrivers();
			while (drivers.hasMoreElements()) {
				Driver driver = drivers.nextElement();
				DriverManager.deregisterDriver(driver);
			}
			// HibernateUtil.shutdown();
			System.out.println("Application successfully stopped!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String getCategories(String siteid) {
		String result = "";
		Session sx = HibernateUtil.getSessionFactory().openSession();
		Connection conn = ((SessionImpl) sx).connection();
		String check_categories = "select categoria_prodotto_cliente as categoria_gds from `gds-i41_master`.rel_sites_categories where id_vetrina = '" + siteid + "'";
		Statement stmt = null;
		ResultSet rs = null;

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(check_categories);
			while (rs.next()) {
				if (rs.getString("categoria_gds") != null) {
					result = result + "'" + (rs.getString("categoria_gds").trim() + "',");
				}
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (result.endsWith(",")) {
			result = result.substring(0, result.length() - 1);
		}

		return result;

	}
}