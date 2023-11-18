package it.improvity.utils;

import it.improvity.utils.ReadProperty;

public class Config {

	public static Boolean getDebug() {
		ReadProperty r = ReadProperty.getInstance();
		boolean debug = Boolean.parseBoolean(r.getProperty("debug"));
		return debug;
	}

	public static Boolean isDebug() {
		ReadProperty r = ReadProperty.getInstance();
		boolean debug = Boolean.parseBoolean(r.getProperty("debug"));
		return debug;
	}

	public static String getRealPath() {
		String directory = ReadProperty.getInstance().getRealPath();
		return directory;
	}

	public static String getSITEID() {
		ReadProperty r = ReadProperty.getInstance();
		String result = "";
		if (Config.isDebug() == true) {
			result = r.getProperty("debug_site_id");
		} else if (Config.isDebug() == false) {
			result = r.getProperty("release_site_id");
		}
		return result;
	}

	public static String getJDBCCONNECTION() {
		ReadProperty r = ReadProperty.getInstance();
		String result = "";
		if (Config.isDebug() == true) {
			result = r.getProperty("debug_jdbc_connection_1");
		} else if (Config.isDebug() == false) {
			result = r.getProperty("release_jdbc_connection_1");
		}
		return result;
	}

	public static String getJDBCUSER() {
		ReadProperty r = ReadProperty.getInstance();
		String result = "";
		if (Config.isDebug() == true) {
			result = r.getProperty("debug_jdbc_user_1");
		} else if (Config.isDebug() == false) {
			result = r.getProperty("release_jdbc_user_1");
		}
		return result;
	}

	public static String getJDBPASSWORD() {
		ReadProperty r = ReadProperty.getInstance();
		String result = "";
		if (Config.isDebug() == true) {
			result = r.getProperty("debug_jdbc_password_1");
		} else if (Config.isDebug() == false) {
			result = r.getProperty("release_jdbc_password_1");
		}
		return result;
	}

	public static String getJDBCCONNECTION2() {
		ReadProperty r = ReadProperty.getInstance();
		String result = "";
		if (Config.isDebug() == true) {
			result = r.getProperty("debug_jdbc_connection_2");
		} else if (Config.isDebug() == false) {
			result = r.getProperty("release_jdbc_connection_2");
		}
		return result;
	}

	public static String getJDBCUSER2() {
		ReadProperty r = ReadProperty.getInstance();
		String result = "";
		if (Config.isDebug() == true) {
			result = r.getProperty("debug_jdbc_user_2");
		} else if (Config.isDebug() == false) {
			result = r.getProperty("release_jdbc_user_2");
		}
		return result;
	}

	public static String getJDBPASSWORD2() {
		ReadProperty r = ReadProperty.getInstance();
		String result = "";
		if (Config.isDebug() == true) {
			result = r.getProperty("debug_jdbc_password_2");
		} else if (Config.isDebug() == false) {
			result = r.getProperty("release_jdbc_password_2");
		}
		return result;
	}


	public static String getMAX_RESULTS() {
		ReadProperty r = ReadProperty.getInstance();
		String result = "";
		if (Config.isDebug() == true) {
			result = r.getProperty("debug_max_results");
		} else if (Config.isDebug() == false) {
			result = r.getProperty("release_max_results");
		}
		return result;
	}

	public static String getMAX_FEATURED() {
		ReadProperty r = ReadProperty.getInstance();
		String result = "";
		if (Config.isDebug() == true) {
			result = r.getProperty("debug_max_featured");
		} else if (Config.isDebug() == false) {
			result = r.getProperty("release_max_featured");
		}
		return result;
	}

	public static Integer getABILITAFILTROMARCHI() {
		ReadProperty r = ReadProperty.getInstance();
		String result = "";
		if (Config.isDebug() == true) {
			result = r.getProperty("debug_abilita_filtro_marchi");
		} else if (Config.isDebug() == false) {
			result = r.getProperty("release_abilita_filtro_marchi");
		}
		return Integer.parseInt(result);
	}

	public static String getPRODUCTIMGPOS() {
		ReadProperty r = ReadProperty.getInstance();
		String result = "";
		if (Config.isDebug() == true) {
			result = r.getProperty("PRODUCTIMGPOS");
		} else if (Config.isDebug() == false) {
			result = r.getProperty("PRODUCTIMGPOS");
		}
		return result;
	}

	public static String getBANNERIMGPOS() {
		ReadProperty r = ReadProperty.getInstance();
		String result = "";
		if (Config.isDebug() == true) {
			result = r.getProperty("BANNERIMGPOS");
		} else if (Config.isDebug() == false) {
			result = r.getProperty("BANNERIMGPOS");
		}
		return result;
	}

	public static String getENABLE_QUANTITY_OFFERS() {
		ReadProperty r = ReadProperty.getInstance();
		String result = "";
		if (Config.isDebug() == true) {
			result = r.getProperty("debug_enable_quantity_offers");
		} else if (Config.isDebug() == false) {
			result = r.getProperty("release_enable_quantity_offers");
		}
		return result;
	}

	public static String getCONSEGNA() {
		ReadProperty r = ReadProperty.getInstance();
		String result = "";
		if (Config.isDebug() == true) {
			result = r.getProperty("debug_consegna");
		} else if (Config.isDebug() == false) {
			result = r.getProperty("release_consegna");
		}
		return result;
	}

	public static String getCAP_RIF() {
		ReadProperty r = ReadProperty.getInstance();
		String result = "";
		if (Config.isDebug() == true) {
			result = r.getProperty("debug_cap_rif");
		} else if (Config.isDebug() == false) {
			result = r.getProperty("release_cap_rif");
		}
		return result;
	}

	public static String getCITY_RIF() {
		ReadProperty r = ReadProperty.getInstance();
		String result = "";
		if (Config.isDebug() == true) {
			result = r.getProperty("debug_city_rif");
		} else if (Config.isDebug() == false) {
			result = r.getProperty("release_city_rif");
		}
		return result;
	}

	public static Integer getSCONTO_CARRELLO_PERC() {
		ReadProperty r = ReadProperty.getInstance();
		Integer result = 0;
		if (r.getProperty("debug_sconto_carrello_perc") != "") {
			if (Config.isDebug() == true) {
				result = Integer.parseInt(r.getProperty("debug_sconto_carrello_perc"));
			} else if (Config.isDebug() == false) {
				result = Integer.parseInt(r.getProperty("release_sconto_carrello_perc"));
			}
		}
		return result;
	}

	public static String getCategorie() {
		ReadProperty r = ReadProperty.getInstance();
		String result = "";
		if (Config.isDebug() == true) {
			result = r.getProperty("debug_categorie");
		} else if (Config.isDebug() == false) {
			result = r.getProperty("release_categorie");
		}
		return result;
	}

	public static String getMenuCompleto() {
		ReadProperty r = ReadProperty.getInstance();
		String result = "";
		if (Config.isDebug() == true) {
			result = r.getProperty("debug_menu_completo");
		} else if (Config.isDebug() == false) {
			result = r.getProperty("release_menu_completo");
		}
		return result;
	}

	public static String getMenuPrimo() {
		ReadProperty r = ReadProperty.getInstance();
		String result = "";
		if (Config.isDebug() == true) {
			result = r.getProperty("debug_menu_primo");
		} else if (Config.isDebug() == false) {
			result = r.getProperty("release_menu_primo");
		}
		return result;
	}

	public static String getMenuSecondo() {
		ReadProperty r = ReadProperty.getInstance();
		String result = "";
		if (Config.isDebug() == true) {
			result = r.getProperty("debug_menu_secondo");
		} else if (Config.isDebug() == false) {
			result = r.getProperty("release_menu_secondo");
		}
		return result;
	}

	public static String getMenuPrimoContorno() {
		ReadProperty r = ReadProperty.getInstance();
		String result = "";
		if (Config.isDebug() == true) {
			result = r.getProperty("debug_menu_primo_contorno");
		} else if (Config.isDebug() == false) {
			result = r.getProperty("release_menu_primo_contorno");
		}
		return result;
	}

	public static String getMenuSecondoContorno() {
		ReadProperty r = ReadProperty.getInstance();
		String result = "";
		if (Config.isDebug() == true) {
			result = r.getProperty("debug_menu_secondo_contorno");
		} else if (Config.isDebug() == false) {
			result = r.getProperty("release_menu_secondo_contorno");
		}
		return result;
	}

	public static String getMenuPrimoSecondo() {
		ReadProperty r = ReadProperty.getInstance();
		String result = "";
		if (Config.isDebug() == true) {
			result = r.getProperty("debug_menu_primo_secondo");
		} else if (Config.isDebug() == false) {
			result = r.getProperty("release_menu_primo_secondo");
		}
		return result;
	}
}