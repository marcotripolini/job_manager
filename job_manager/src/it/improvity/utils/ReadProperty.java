package it.improvity.utils;

import java.io.*;
import java.util.*;

import it.improvity.interfaces.Constants;

public class ReadProperty implements Constants {

	private static String dir;
	private static ReadProperty instance;
	private static Properties pro = new Properties();
	private static String appname;

	@SuppressWarnings("static-access")
	public ReadProperty(String dir, String appname) {
		super();
		this.appname = appname;
		this.dir = dir;
		loadPropertyFile(this.dir + "WEB-INF/" + appname);
	}

	public static ReadProperty getInstance() {
		if (instance == null) {
			instance = new ReadProperty(dir, appname);
		}
		return instance;
	}

	public void loadPropertyFile(String filename) {
		if (filename.equals("nullWEB-INF/null")) {
			filename = "/Users/marco/Desktop/gds";
		}
		try {
			int check = 0;
			while (check == 0) {
				check = 1;
				File f = new File(filename + ".properties");
				if (f.exists()) {
					FileInputStream in = new FileInputStream(f);
					pro.load(in);
				} else {
					check = 0;
					System.out.println("Applicazione " + appname  + ": impossibile trovare il file di configurazione.");
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getProperty(String key) {
		String p = pro.getProperty(key);
		return (p);
	}

	public String getRealPath(){
		return dir;
	}

	public static String getAppname() {
		return appname;
	}

	public static void setAppname(String appname) {
		ReadProperty.appname = appname;
	}

}