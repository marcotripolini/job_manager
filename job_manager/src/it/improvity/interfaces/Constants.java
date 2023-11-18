package it.improvity.interfaces;

public interface Constants {
	// costants
	public static final String SUCCESS_NOLOGIN = "success_nologin";
	public static final String SUCCESS_NOPRODS = "success_noprods";

	public static final String SESSION_CHECKED = "SESSION_CHECKED";

	public static final String CART = "CARRELLO";
	public static final String TOTCART = "TOTCART";
	public static final String TOTDETT = "TOTDETT";
	public static final String CUSTOMER = "CLIENTE";
	public static final String VETRINA = "VETRINA";
	public static final String ALLCODES = "ALLCODES";
	public static final String LASTURL = "LASTURL";

	public static final String CATEGORIE1 = "CATEGORIE1";
	public static final String CATEGORIE2 = "CATEGORIE2";

	// hibernate queries
	public static final String HQL_LOGIN = "FROM ClientiETessera WHERE tessera = :tessera AND password = :password AND attivo = '1'";
	public static final String HQL_LOGIN_E = "FROM ClientiETessera WHERE (tessera = :tessera OR email= :email) AND password = :password AND attivo = '1'";

	public static final String HQL_LOGIN_R = "FROM ClientiETessera WHERE tessera = :tessera AND attivo = '1'";

	public static final String HQL_REMOTELOGIN = "FROM ClientiETessera WHERE id = :id_cliente";
	public static final String HQL_REMOTECART = "FROM Carrello WHERE id = :id_carrello";

	public static final String HQL_CARTDETT = "FROM Carrellodett WHERE codice_gds = :codice_gds AND id_carrello = :id_carrello";
	public static final String HQL_CARTDETT2 = "FROM Carrellodett WHERE codice_gds = :codice_gds AND descrizione like :descrizione AND id_carrello = :id_carrello";
	public static final String HQL_LISTCARTDETT = "FROM Carrellodett WHERE id_carrello = :id_carrello AND id_ordine IS NULL";

	public static final String HQL_LISTESPESA = "FROM Listaspesa WHERE id_cliente = :id_cliente ORDER BY data DESC, id DESC";
	public static final String HQL_LISTASPESA = "FROM Listaspesa WHERE id = :id_lista";
	public static final String HQL_LISTASPESADETT = "FROM Listaspesadett WHERE codice_gds = :codice_gds AND id_lista = :id_lista";

	public static final String HQL_LISTADESIDERI = "FROM Desideratidett WHERE id_cliente = :id_cliente";

	public static final String HQL_ORDINIEDETT = "FROM OrdiniEDettagli WHERE id_cliente = :id_cliente ORDER BY data DESC, id DESC";
	public static final String HQL_ORDINIBYDATE = "FROM OrdiniEDettagli WHERE data > :date";

	public static final String HQL_MARCHI = "FROM Marchi WHERE visualizza = '1' ORDER BY marchio";
	public static final String HQL_MARCHI2 = "FROM All_brands WHERE visualizza = '1' ORDER BY marchio";

	public static final String HQL_FORCEPRODS = "FROM Prodotti WHERE visibile = '1' AND codice_gds IN (:str)";

	public static final String HQL_OFFERS = "FROM Prodotti WHERE codice_gds LIKE '%_O' "
			+ "AND visibile = '1' "
			+ "AND valido_dal <= :date "
			+ "AND valido_fino_al > :date "
			+ "ORDER BY priorita_ricerca, marchio.marchio, titolo";

	public static final String HQL_LASTPRODS = "FROM Prodotti "
			+ "WHERE codice_gds NOT LIKE '%_O' "
			+ "AND visibile = '1' "
			+ "AND valido_dal <= :date "
			+ "AND (valido_fino_al > :date or valido_fino_al is null) "
			+ "AND codice_gds in (:codes) "
			+ "ORDER BY data_modifica DESC, priorita_ricerca, titolo, marchio.marchio ";

	public static final String HQL_BL = "FROM Bannerleft WHERE dal <= :date AND AL > :date";
	public static final String HQL_BR = "FROM Bannerright WHERE dal <= :date AND AL > :date";
	public static final String HQL_SL = "FROM Slider WHERE dal <= :date AND AL > :date";

	public static final String HQL_CAT1 = "FROM Categorie WHERE attiva_sn = 1 AND categoria_2 IS NULL ORDER BY categoria_menu, categoria_1";
	public static final String HQL_CAT2 = "FROM Categorie WHERE attiva_sn = 1 AND categoria_2 IS NOT NULL ORDER BY categoria_gds";

	public static final String HQL_CAT1_W = "FROM Categorie WHERE attiva_sn = 1 AND categoria_gds in (__catlist) order by seq";

	public static final String HQL_GETCAT = "FROM Categorie WHERE attiva_sn = 1 AND categoria_gds = :categoria_gds";
	public static final String HQL_LISTCAT = "FROM Categorie WHERE attiva_sn = 1 AND categoria_2 IS NOT NULL AND categoria_gds LIKE :categoria_gds ORDER BY categoria_2";

//	public static final String HQL_PRODSBYCAT = "FROM Prodotti "
//			+ "WHERE (categoria_prodotto_cliente LIKE :categoria_gds) "
//			+ "AND codice_gds in (:cod_gds) "
//			+ "AND visibile = true "
//			+ "AND valido_dal <= :date "
//			+ "AND (valido_fino_al > :date OR valido_fino_al IS NULL) "
//			+ "AND giacenza_magazzino > 0 "
//			+ "ORDER BY priorita_ricerca, titolo, marchio.marchio ";

	public static final String HQL_PRODSBYCAT = "FROM Prodotti "
			+ "WHERE (categoria_gds LIKE :categoria_gds) "
			+ "AND visibile = true "
			+ "AND valido_dal <= :date "
			+ "AND (valido_fino_al > :date OR valido_fino_al IS NULL) "
			+ "AND giacenza_magazzino > 0 "
			+ "ORDER BY menu_dish_mod_type, priorita_ricerca, titolo, marchio.marchio ";

	public static final String HQL_PRODSBYNEW = "FROM Prodotti WHERE visibile = true "
			+ "AND valido_dal <= :date "
			+ "AND (valido_fino_al > :date OR valido_fino_al IS NULL) "
			+ "AND codice_gds in (:cods) "
			+ "AND giacenza_magazzino > 0 "
			+ "ORDER BY id desc, priorita_ricerca";

	public static final String HQL_PRODSBYCAT_W = "FROM Prodotti WHERE (categoria_prodotto_cliente LIKE :categoria_gds) "
			+ "AND codice_gds != :cod_gds "
			+ "AND visibile = true "
			+ "AND valido_dal <= :date "
			+ "AND (valido_fino_al > :date OR valido_fino_al IS NULL) "
			+ "AND giacenza_magazzino > 0 "
			+ "ORDER BY priorita_ricerca, titolo, marchio.marchio";

	public static final String HQL_PRODSBYCATBR = "FROM Prodotti WHERE (categoria_prodotto_cliente = :categoria_gds) "
			+ "AND visibile = '1' "
			+ "AND valido_dal <= :date "
			+ "AND (valido_fino_al > :date OR valido_fino_al IS NULL) "
			+ "AND marchio.id IN (_brands_) "
			+ "AND giacenza_magazzino > 0 "
			+ "ORDER BY priorita_ricerca, titolo, marchio.marchio";

//	public static final String HQL_PRODBYCOD = "FROM All_products WHERE codice_gds = :codice_gds "
//			+ "AND visibile = '1' "
//			+ "AND valido_dal <= :date "
//			+ "AND (valido_fino_al > :date OR valido_fino_al IS NULL)";

	public static final String HQL_PRODBYCOD = "FROM Prodotti WHERE codice_gds = :codice_gds "
			+ "AND visibile = '1' "
			+ "AND giacenza_magazzino > 0 "
			+ "AND valido_dal <= :date "
			+ "AND (valido_fino_al > :date OR valido_fino_al IS NULL)";

	public static final String HQL_APRODBYCOD = "FROM All_products WHERE codice_gds = :codice_gds "
			+ "AND visibile = '1' "
			+ "AND giacenza_magazzino > 0 "
			+ "AND valido_dal <= :date "
			+ "AND (valido_fino_al > :date OR valido_fino_al IS NULL)";

	public static final String HQL_SITES = "FROM Siti WHERE carouselized = '1' AND centralized = '1' AND active = '1'";

	public static final String HQL_SEARCHBYKEY = "FROM Prodotti WHERE visibile = '1' "
					+ "AND (( :a ) OR ( :b )) "
					+ "AND codice_gds in (:cod_gds) "
					+ "AND valido_dal <= :date "
					+ "AND (valido_fino_al > :date OR valido_fino_al IS NULL) "
					+ "AND giacenza_magazzino > 0 "
					+ "ORDER BY priorita_ricerca, marchio.marchio, titolo";

	public static final String HQL_SEARCHBYKEY2 = "FROM All_products WHERE visibile = '1' "
			+ "AND (( :a ) OR ( :b )) "
			+ "AND valido_dal <= :date "
			+ "AND (valido_fino_al > :date OR valido_fino_al IS NULL) "
			+ "AND giacenza_magazzino > 0 "
			+ "ORDER BY priorita_ricerca, marchio.marchio, titolo";

	public static final String HQL_SEARCHBYCOD = "FROM Prodotti WHERE visibile = '1' "
			+ "AND CODICE_GDS IN (:cods) "
			+ "AND valido_dal <= :date "
			+ "AND (valido_fino_al > :date OR valido_fino_al IS NULL) "
			+ "AND giacenza_magazzino > 0 "
			+ "ORDER BY priorita_ricerca, marchio.marchio, titolo";

	public static final String HQL_SEARCHBYBRAND = "FROM Prodotti WHERE id_marchio = :id_marchio "
			+ "AND visibile = '1' "
			+ "AND valido_dal <= :date "
			+ "AND (valido_fino_al > :date OR valido_fino_al IS NULL) "
			+ "AND giacenza_magazzino > 0 "
			+ "ORDER BY titolo ";

	public static final String HQL_SEARCHBYBRANDINCAT = "FROM Prodotti "
			+ "WHERE id_marchio = :id_marchio "
			+ "AND codice_gds in (:cods) "
			+ "AND categoria_prodotto_cliente = :category "
			+ "AND visibile = '1' "
			+ "AND valido_dal <= :date "
			+ "AND (valido_fino_al > :date OR valido_fino_al IS NULL) "
			+ "AND giacenza_magazzino > 0 "
			+ "ORDER BY titolo ";

	public static final String SQL_CHECK_SESSION = "SELECT * FROM sessioni WHERE ip = ':id_sessione' AND attivo = '1'";

	public static final String HQL_COMUNISERVITI = "FROM Comuni_serviti WHERE servito_gds = '1' ORDER BY nome_comune";

	public static final String HQL_GETCLIENTE = "FROM ClientiETessera WHERE id = :id_cliente";
	public static final String HQL_GETCARRELLO = "FROM Carrello WHERE id = :id_carrello";
	public static final String HQL_GETORDINE = "FROM OrdiniEDettagli WHERE id = :id_ordine";
	public static final String HQL_LISTCARTDETTBYORDER = "FROM Carrellodett WHERE id_ordine = :id_ordine";


	// mysql queries

}
