package it.improvity.utils;

import java.util.*;
import java.io.*;
import java.security.GeneralSecurityException;
import javax.mail.*;
import javax.mail.internet.*;
import com.sun.mail.util.MailSSLSocketFactory;

import it.improvity.hibernate.HibernateUtil;

import javax.activation.*;

//This class send HTML messages
public class SendMail {
//	private static final String MAIL_SERVER = "smtp";
//	private static final String SMTP_HOST_NAME = "mail.gds-italia.it";
//  private static final int SMTP_HOST_PORT = 25;
//  private static final String USER_NAME = "noreply@gds-italia.it";  // GMail user name (just the part before "@gmail.com")
//  private static final String PASSWORD = "gabriele2014"; // GMail password

	public static void sendMulti(String[] to, String[] cc, String[] bcc, String subject, String body) {

		String MAIL_SERVER = "smtp";
		final String SMTP_HOST_NAME;
		final String USER_NAME;
		final String PASSWORD;
		String from = "";
		int SMTP_HOST_PORT = 587;

		ReadProperty r = ReadProperty.getInstance();
		boolean debug = Boolean.parseBoolean(r.getProperty("debug"));

		if (debug == true) {
			SMTP_HOST_NAME = (r.getProperty("debugMAIL_SERVER"));
			USER_NAME = (r.getProperty("debugMAIL_USERNAME"));
			PASSWORD = (r.getProperty("debugMAIL_PASSWORD"));
			from = (r.getProperty("debugMAIL_FROM"));
		} else {
			SMTP_HOST_NAME = (r.getProperty("productionMAIL_SERVER"));
			USER_NAME = (r.getProperty("productionMAIL_USERNAME"));
			PASSWORD = (r.getProperty("productionMAIL_PASSWORD"));
			from = (r.getProperty("productionMAIL_FROM"));
		}

		// ---------------------------------------------STEP
		// 1---------------------------------------------
		System.out.println("\n 1st ===> Setup SMTP Mail Server Properties..!");
		// Get system properties
		Properties properties = System.getProperties();

		MailSSLSocketFactory sf = null;
		try {
			sf = new MailSSLSocketFactory();
			sf.setTrustAllHosts(true);
		} catch (GeneralSecurityException e) {
			e.printStackTrace();
		}

		// Setup mail server
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", SMTP_HOST_NAME);
		properties.put("mail.smtp.user", USER_NAME);
		properties.put("mail.smtp.password", PASSWORD);
		properties.put("mail.smtp.port", SMTP_HOST_PORT);
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.ssl.trust", "*");
		properties.put("mail.smtp.ssl.socketFactory", sf);

		// Setup mail server
		//properties.put("mail.smtp.starttls.enable", "true");
//		properties.put("mail.smtp.host", SMTP_HOST_NAME);
//		properties.put("mail.smtp.user", USER_NAME);
//		properties.put("mail.smtp.password", PASSWORD);
//		properties.put("mail.smtp.port", SMTP_HOST_PORT);
//		properties.put("mail.smtp.auth", "true");

		// ---------------------------------------------STEP
		// 2---------------------------------------------

		System.out.println("\n\n 2nd ===> Get Mail Session..");
		// Get the Session object.

		// creates a new session with an authenticator
		Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(USER_NAME, PASSWORD);
			}
		};

		Session session = Session.getInstance(properties, auth);

		// Create a default MimeMessage object.
		MimeMessage message = new MimeMessage(session);

		try {

			// ---------------------------------------------

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(USER_NAME));

			// ---------------------------------------------

			InternetAddress[] toAddress = new InternetAddress[to.length];

			// To get the array of toaddresses
			for (int i = 0; i < to.length; i++) {
				toAddress[i] = new InternetAddress(to[i]);
			}

			// Set To: header field of the header.
			for (int i = 0; i < toAddress.length; i++) {
				message.addRecipient(Message.RecipientType.TO, toAddress[i]);
			}

			InternetAddress[] ccAddress = new InternetAddress[cc.length];

			// To get the array of ccaddresses
			for (int i = 0; i < cc.length; i++) {
				ccAddress[i] = new InternetAddress(cc[i]);
			}

			// Set cc: header field of the header.
			for (int i = 0; i < ccAddress.length; i++) {
				message.addRecipient(Message.RecipientType.CC, ccAddress[i]);
			}

			InternetAddress[] bccAddress = new InternetAddress[bcc.length];

			// To get the array of bccaddresses
			for (int i = 0; i < bcc.length; i++) {
				bccAddress[i] = new InternetAddress(bcc[i]);
			}

			// Set bcc: header field of the header.
			for (int i = 0; i < bccAddress.length; i++) {
				message.addRecipient(Message.RecipientType.BCC, bccAddress[i]);
			}

			// Set Subject: header field
			message.setSubject(subject);

			// Now set the date to actual message
			message.setSentDate(new Date());

			// Now set the actual message
			message.setContent(body, "text/html");

			// ---------------------------------------------STEP
			// 3---------------------------------------------

			System.out.println("\n\n 3rd ===> Get Session and Send Mail");
			// Send message
			Transport transport = session.getTransport(MAIL_SERVER);
			transport.connect(SMTP_HOST_NAME, SMTP_HOST_PORT, USER_NAME, PASSWORD);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
			System.out.println("Sent Message Successfully....");
		} catch (AddressException ae) {
			ae.printStackTrace();
		} catch (MessagingException me) {
			me.printStackTrace();
		}
		// ---------------------------------------------------------------------------------------------------
	}

	// invio di email HTML senza allegati
	public static void sendMessage(String to, String subject, String HTMLText) {
		String host;
		String username;
		String password;
		String from = "";

		ReadProperty r = ReadProperty.getInstance();
		boolean debug = Boolean.parseBoolean(r.getProperty("debug"));

		if (debug == true) {
			host = (r.getProperty("debugMAIL_SERVER"));
			username = (r.getProperty("debugMAIL_USERNAME"));
			password = (r.getProperty("debugMAIL_PASSWORD"));
			from = (r.getProperty("debugMAIL_FROM"));
		} else {
			host = (r.getProperty("productionMAIL_SERVER"));
			username = (r.getProperty("productionMAIL_USERNAME"));
			password = (r.getProperty("productionMAIL_PASSWORD"));
			from = (r.getProperty("productionMAIL_FROM"));
		}

		MailSSLSocketFactory sf = null;
		try {
			sf = new MailSSLSocketFactory();
			sf.setTrustAllHosts(true);
		} catch (GeneralSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Create properties for the Session
		Properties props = new Properties();
		// If using static Transport.send(),
		// need to specify the mail server here
		props.put("mail.smtp.host", host);
		// To see what is going on behind the scene
		props.put("mail.debug", "false");
// 		necessario per GMAIL.
//      props.put("mail.smtp.starttls.enable" ,"true");
//
//		props.put("mail.imap.ssl.trust", "*");
//		props.put("mail.imap.ssl.socketFactory", sf);

		// Get a session
		Session session = Session.getInstance(props);

		try {
			// Get a Transport object to send e-mail
			Transport bus = session.getTransport("smtp");
			// Connect only once here
			// Transport.send() disconnects after each send
			// Usually, no username and password is required for SMTP
			// bus.connect();
			bus.connect(host, username, password);

			// Instantiate a message
			MimeMessage msg = new MimeMessage(session);

			// Set message attributes
			msg.setFrom(new InternetAddress(from));
			InternetAddress[] address = { new InternetAddress(to) };

			msg.setRecipients(Message.RecipientType.TO, address);
			InternetAddress[] cc = { new InternetAddress("tm@boostech.it") };
			msg.addRecipients(Message.RecipientType.CC, cc);
			// msg.setRecipients(Message.RecipientType.TO,InternetAddress.parse("tm@boostech.it,marco.tripolini@gmail.com",
			// true));
			// Parse a comma-separated list of email addresses. Be strict.

			// msg.setRecipients(Message.RecipientType.CC,InternetAddress.parse("tm@boostech.it",
			// true));
			// Parse comma/space-separated list. Cut some slack.
			// msg.setRecipients(Message.RecipientType.BCC,
			// InternetAddress.parse("tm@boostech.it", false));

			msg.setSubject(subject, "UTF-8");
			msg.setSentDate(new Date());

			// setHTMLContent(msg);
			msg.setDataHandler(new DataHandler(new HTMLDataSource(HTMLText)));
			msg.saveChanges();

			bus.sendMessage(msg, address);
			bus.close();

		} catch (MessagingException mex) {
			// Prints all nested (chained) exceptions as well
			mex.printStackTrace();
			// How to access nested exceptions
			while (mex.getNextException() != null) {
				// Get next exception in chain
				Exception ex = mex.getNextException();
				ex.printStackTrace();
				if (!(ex instanceof MessagingException))
					break;
				else
					mex = (MessagingException) ex;
			}
		}
	}

	// invio di email HTML senza allegati
	public static void sendfMessage(String from, String to, String subject, String HTMLText) {
		String host;
		String username;
		String password;

		ReadProperty r = ReadProperty.getInstance();

		boolean debug = Boolean.parseBoolean(r.getProperty("debug"));

		if (debug == true) {
			host = (r.getProperty("debugMAIL_SERVER"));
			username = (r.getProperty("debugMAIL_USERNAME"));
			password = (r.getProperty("debugMAIL_PASSWORD"));
			// from = (r.getProperty("debugMAIL_FROM"));
			// to = r.getProperty("debugMAIL_CONTROL");
		} else {
			host = (r.getProperty("productionMAIL_SERVER"));
			username = (r.getProperty("productionMAIL_USERNAME"));
			password = (r.getProperty("productionMAIL_PASSWORD"));
			// from = (r.getProperty("productionMAIL_FROM"));
		}

		// Create properties for the Session
		Properties props = new Properties();
		// If using static Transport.send(),
		// need to specify the mail server here
		props.put("mail.smtp.host", host);
		// To see what is going on behind the scene
		props.put("mail.debug", "false");
		// necessario per GMAIL.
		props.put("mail.smtp.starttls.enable", "true");

		// Get a session
		Session session = Session.getInstance(props);

		try {
			// Get a Transport object to send e-mail
			Transport bus = session.getTransport("smtp");
			// Connect only once here
			// Transport.send() disconnects after each send
			// Usually, no username and password is required for SMTP
			// bus.connect();
			bus.connect(host, username, password);

			// Instantiate a message
			MimeMessage msg = new MimeMessage(session);

			// Set message attributes
			msg.setFrom(new InternetAddress(from));
			InternetAddress[] address = { new InternetAddress(to) };
			msg.setRecipients(Message.RecipientType.TO, address);
			// Parse a comma-separated list of email addresses. Be strict.
			// msg.setRecipients(Message.RecipientType.CC,
			// InternetAddress.parse(to, true));
			// Parse comma/space-separated list. Cut some slack.
			// msg.setRecipients(Message.RecipientType.BCC,
			// InternetAddress.parse(to, false));

			msg.setSubject(subject, "UTF-8");
			msg.setSentDate(new Date());

			// setHTMLContent(msg);
			msg.setDataHandler(new DataHandler(new HTMLDataSource(HTMLText)));
			msg.saveChanges();

			bus.sendMessage(msg, address);
			bus.close();

		} catch (MessagingException mex) {
			// Prints all nested (chained) exceptions as well
			mex.printStackTrace();
			// How to access nested exceptions
			while (mex.getNextException() != null) {
				// Get next exception in chain
				Exception ex = mex.getNextException();
				ex.printStackTrace();
				if (!(ex instanceof MessagingException))
					break;
				else
					mex = (MessagingException) ex;
			}
		}
	}

	public static boolean sendMessage(String to, String subject, String messageText, String fileName) {
		String host;
		String username;
		String password;
		String from = "";

		ReadProperty r = ReadProperty.getInstance();

		boolean debug = Boolean.parseBoolean(r.getProperty("debug"));

		if (debug == true) {
			host = (r.getProperty("debugMAIL_SERVER"));
			username = (r.getProperty("debugMAIL_USERNAME"));
			password = (r.getProperty("debugMAIL_PASSWORD"));
			from = (r.getProperty("debugMAIL_FROM"));
			// to = r.getProperty("debugMAIL_CONTROL");
		} else {
			host = (r.getProperty("productionMAIL_SERVER"));
			username = (r.getProperty("productionMAIL_USERNAME"));
			password = (r.getProperty("productionMAIL_PASSWORD"));
			from = (r.getProperty("productionMAIL_FROM"));
		}

		// Create properties for the Session
		Properties props = new Properties();
		// If using static Transport.send(),
		// need to specify the mail server here
		props.put("mail.smtp.host", host);
		// To see what is going on behind the scene
		props.put("mail.debug", "false");
		// necessario per GMAIL.
		props.put("mail.smtp.starttls.enable", "true");

		// Get a session
		Session session = Session.getInstance(props);

		try {
			// Get a Transport object to send e-mail
			Transport bus = session.getTransport("smtp");
			// Connect only once here
			// Transport.send() disconnects after each send
			// Usually, no username and password is required for SMTP
			// bus.connect();
			bus.connect(host, username, password);

			MimeMessage msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(from));
			InternetAddress[] address = { new InternetAddress(to) };
			msg.setRecipients(Message.RecipientType.TO, address);
			msg.setSubject(subject);

			// create and fill the first message part
			MimeBodyPart mbp1 = new MimeBodyPart();
			// mbp1.setText(messageText);
			mbp1.setDataHandler(new DataHandler(new HTMLDataSource(messageText)));
			// mbp1.saveChanges();

			// create the second message part
			MimeBodyPart mbp2 = new MimeBodyPart();

			// attach the file to the message
			FileDataSource fds = new FileDataSource(fileName);
			mbp2.setDataHandler(new DataHandler(fds));
			mbp2.setFileName(fds.getName());

			// create the Multipart and add its parts to it
			Multipart mp = new MimeMultipart();
			mp.addBodyPart(mbp1);
			mp.addBodyPart(mbp2);

			// add the Multipart to the message
			msg.setContent(mp);

			// set the Date: header
			msg.setSentDate(new Date());
			// send the message
			bus.sendMessage(msg, address);
			bus.close();

			return true;
		} catch (MessagingException mex) {
			// Prints all nested (chained) exceptions as well
			mex.printStackTrace();
			// How to access nested exceptions
			while (mex.getNextException() != null) {
				// Get next exception in chain
				Exception ex = mex.getNextException();
				ex.printStackTrace();
				if (!(ex instanceof MessagingException))
					break;
				else
					mex = (MessagingException) ex;
			}
			return false;
		}
	}

	public static boolean sendMessage(String to, String subject, String messageText, String fileName1,
			String fileName2) {
		String host;
		String username;
		String password;
		String from = "";

		ReadProperty r = ReadProperty.getInstance();

		boolean debug = Boolean.parseBoolean(r.getProperty("debug"));

		if (debug == true) {
			host = (r.getProperty("debugMAIL_SERVER"));
			username = (r.getProperty("debugMAIL_USERNAME"));
			password = (r.getProperty("debugMAIL_PASSWORD"));
			from = (r.getProperty("debugMAIL_FROM"));
			// to = r.getProperty("debugMAIL_CONTROL");
		} else {
			host = (r.getProperty("productionMAIL_SERVER"));
			username = (r.getProperty("productionMAIL_USERNAME"));
			password = (r.getProperty("productionMAIL_PASSWORD"));
			from = (r.getProperty("productionMAIL_FROM"));
		}

		// Create properties for the Session
		Properties props = new Properties();
		// If using static Transport.send(),
		// need to specify the mail server here
		props.put("mail.smtp.host", host);
		// To see what is going on behind the scene
		props.put("mail.debug", "false");
		// necessario per GMAIL.
		props.put("mail.smtp.starttls.enable", "true");

		// Get a session
		Session session = Session.getInstance(props);

		try {
			// Get a Transport object to send e-mail
			Transport bus = session.getTransport("smtp");
			// Connect only once here
			// Transport.send() disconnects after each send
			// Usually, no username and password is required for SMTP
			// bus.connect();
			bus.connect(host, username, password);

			MimeMessage msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(from));
			InternetAddress[] address = { new InternetAddress(to) };
			msg.setRecipients(Message.RecipientType.TO, address);
			msg.setSubject(subject);

			// create and fill the first message part
			MimeBodyPart mbp1 = new MimeBodyPart();
			// mbp1.setText(messageText );
			mbp1.setDataHandler(new DataHandler(new HTMLDataSource(messageText)));

			// create the second message part
			MimeBodyPart mbp2 = new MimeBodyPart();

			// attach the file to the message
			FileDataSource fds = new FileDataSource(fileName1);
			mbp2.setDataHandler(new DataHandler(fds));
			mbp2.setFileName(fds.getName());

			// create the third message part
			MimeBodyPart mbp3 = new MimeBodyPart();

			// attach the file to the message
			FileDataSource fds2 = new FileDataSource(fileName2);
			mbp3.setDataHandler(new DataHandler(fds2));
			mbp3.setFileName(fds2.getName());

			// create the Multipart and add its parts to it
			Multipart mp = new MimeMultipart();
			mp.addBodyPart(mbp1);
			mp.addBodyPart(mbp2);
			mp.addBodyPart(mbp3);

			// add the Multipart to the message
			msg.setContent(mp);

			// set the Date: header
			msg.setSentDate(new Date());
			// send the message
			bus.sendMessage(msg, address);
			bus.close();

			return true;
		} catch (MessagingException mex) {
			// Prints all nested (chained) exceptions as well
			mex.printStackTrace();
			// How to access nested exceptions
			while (mex.getNextException() != null) {
				// Get next exception in chain
				Exception ex = mex.getNextException();
				ex.printStackTrace();
				if (!(ex instanceof MessagingException))
					break;
				else
					mex = (MessagingException) ex;
			}
			return false;
		}
	}

	public static boolean sendMessage(String to, String subject, String messageText, List<String> fileNames) {

		String host;
		String username;
		String password;
		String from = "";

		ReadProperty r = ReadProperty.getInstance();

		boolean debug = Boolean.parseBoolean(r.getProperty("debug"));

		if (debug == true) {
			host = (r.getProperty("debugMAIL_SERVER"));
			username = (r.getProperty("debugMAIL_USERNAME"));
			password = (r.getProperty("debugMAIL_PASSWORD"));
			from = (r.getProperty("debugMAIL_FROM"));
			// to = r.getProperty("debugMAIL_CONTROL");
		} else {
			host = (r.getProperty("productionMAIL_SERVER"));
			username = (r.getProperty("productionMAIL_USERNAME"));
			password = (r.getProperty("productionMAIL_PASSWORD"));
			from = (r.getProperty("productionMAIL_FROM"));
		}

		// Create properties for the Session
		Properties props = new Properties();
		// If using static Transport.send(),
		// need to specify the mail server here
		props.put("mail.smtp.host", host);
		// To see what is going on behind the scene
		props.put("mail.debug", "false");
		// necessario per GMAIL.
		props.put("mail.smtp.starttls.enable", "true");

		// Get a session
		Session session = Session.getInstance(props);

		try {
			// Get a Transport object to send e-mail
			Transport bus = session.getTransport("smtp");
			// Connect only once here
			// Transport.send() disconnects after each send
			// Usually, no username and password is required for SMTP
			// bus.connect();
			bus.connect(host, username, password);

			MimeMessage msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(from));
			InternetAddress[] address = { new InternetAddress(to) };
			msg.setRecipients(Message.RecipientType.TO, address);
			msg.setSubject(subject);

			// create and fill the first message part
			MimeBodyPart mbp1 = new MimeBodyPart();
			// mbp1.setText(messageText);
			mbp1.setDataHandler(new DataHandler(new HTMLDataSource(messageText)));

			// create the Multipart and add its parts to it
			Multipart mp = new MimeMultipart();
			mp.addBodyPart(mbp1);

			for (String s : fileNames) {
				// create the second message part
				MimeBodyPart mbp2 = new MimeBodyPart();
				// attach the file to the message
				FileDataSource fds = new FileDataSource(s);
				mbp2.setDataHandler(new DataHandler(fds));
				mbp2.setFileName(fds.getName());
				mp.addBodyPart(mbp2);
			}

			// add the Multipart to the message
			msg.setContent(mp);

			// set the Date: header
			msg.setSentDate(new Date());
			// send the message
			bus.sendMessage(msg, address);
			bus.close();

			return true;
		} catch (MessagingException mex) {
			// Prints all nested (chained) exceptions as well
			mex.printStackTrace();
			// How to access nested exceptions
			while (mex.getNextException() != null) {
				// Get next exception in chain
				Exception ex = mex.getNextException();
				ex.printStackTrace();
				if (!(ex instanceof MessagingException))
					break;
				else
					mex = (MessagingException) ex;
			}
			return false;
		}
	}

	// A simple, single-part text/plain e-mail.
	public static void setTextContent(Message msg) throws MessagingException {
		// Set message content

		String mytxt = "This is a test of sending a " + "plain text e-mail through Java.\n" + "Here is line 2.";

		mytxt = readMailFile("c:\\homer.bak");
		msg.setContent(mytxt, "text/plain");
	}

	// A simple multipart/mixed e-mail. Both body parts are text/plain.
	public static void setMultipartContent(Message msg) throws MessagingException {
		// Create and fill first part
		MimeBodyPart p1 = new MimeBodyPart();
		// p1.setText("This is part one of a test multipart e-mail.");

		p1.setText(readMailFile("c:/part1.txt"));
		// Create and fill second part
		MimeBodyPart p2 = new MimeBodyPart();
		// Here is how to set a charset on textual content
		// p2.setText("This is the second part", "us-ascii");
		p2.setText(readMailFile("c:/part2.txt"));
		// Create the Multipart. Add BodyParts to it.

		Multipart mp = new MimeMultipart();
		mp.addBodyPart(p1);
		mp.addBodyPart(p2);

		// Set Multipart as the message's content
		msg.setContent(mp);
	}

	// Set a file as an attachment. Uses JAF FileDataSource.
	public static void setFileAsAttachment(Message msg, String filename) throws MessagingException {

		// Create and fill first part
		MimeBodyPart p1 = new MimeBodyPart();
		p1.setText("This is part one of a test multipart e-mail." + "The second part is file as an attachment");

		// Create second part
		MimeBodyPart p2 = new MimeBodyPart();

		// Put a file in the second part
		FileDataSource fds = new FileDataSource(filename);
		p2.setDataHandler(new DataHandler(fds));
		p2.setFileName(fds.getName());

		// Create the Multipart. Add BodyParts to it.
		Multipart mp = new MimeMultipart();
		mp.addBodyPart(p1);
		mp.addBodyPart(p2);

		// Set Multipart as the message's content
		msg.setContent(mp);
	}

	// Set a single part html content.
	// Sending data of any type is similar.
	public static void setHTMLContent(Message msg) throws MessagingException {

		String html = "<html><head><title>" + msg.getSubject() + "</title></head><body><h1>" + msg.getSubject()
				+ "</h1><p>" + " Ciao! " + " Io sono un messaggio HTML" + " inviato tramite SendMail Java."
				+ " ho usato un server locale" + " protetto con indirizzo ip mascherato"
				+ " almeno proteggiamo l'indirizzo IP." + "</body></html>";

		// HTMLDataSource is an inner class
		msg.setDataHandler(new DataHandler(new HTMLDataSource(html)));
	}

	/*
	 * Inner class to act as a JAF datasource to send HTML e-mail content
	 */
	static class HTMLDataSource implements DataSource {
		private String html;

		public HTMLDataSource(String htmlString) {
			html = htmlString;
		}

		// Return html string in an InputStream.
		// A new stream must be returned each time.
		public InputStream getInputStream() throws IOException {
			if (html == null)
				throw new IOException("Null HTML");
			return new ByteArrayInputStream(html.getBytes());
		}

		public OutputStream getOutputStream() throws IOException {
			throw new IOException("This DataHandler cannot write HTML");
		}

		public String getContentType() {
			return "text/html";
		}

		public String getName() {
			return "JAF text/html dataSource to send e-mail only";
		}
	}

	private static String readMailFile(String fileName) {

		String messaggio = "";

		try {
			BufferedReader in = new BufferedReader(new FileReader(fileName));
			String str;
			while ((str = in.readLine()) != null) {
				messaggio = messaggio + str;
			}
			in.close();
		} catch (IOException e) {
		}
		return messaggio;
	}

	/**
	 * Recupera dal database (tabella email) il testo del messaggio email
	 *
	 * @param key : chiave identificativa del messaggio email da caricare
	 * @return : testo del messaggio caricato dal database
	 */
	public static String getEmailText(String key) {
		org.hibernate.Session sx = HibernateUtil.getSessionFactory().openSession();
		try {
			return ((String) sx.createQuery("select email from Email where key = '" + key + "'").uniqueResult())
					.toString();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sx.close();
		}
		return null;
	}

	/**
	 * Recupera dal database (tabella email) l'oggetto del messaggio email
	 *
	 * @param key : chiave identificativa del messaggio email da caricare
	 * @return : testo dell'oggetto caricato dal database
	 */
	public static String getEmailObject(String key) {
		org.hibernate.Session sx = HibernateUtil.getSessionFactory().openSession();
		try {
			return ((String) sx.createQuery("select title from Email where key = '" + key + "'").uniqueResult())
					.toString();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sx.close();
		}
		return null;
	}
}