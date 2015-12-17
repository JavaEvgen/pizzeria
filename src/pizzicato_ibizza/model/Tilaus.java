package pizzicato_ibizza.model;

import java.util.ArrayList;
import java.sql.Date;
import java.sql.Timestamp;

public class Tilaus {

	private int tilaus_id;
	private Date tilaus_pvm;
	private double tilaus_hinta;
	private boolean toimitustapa;
	private String etunimi, sukunimi, puhelin, toimitusosoite, postinumero,
			kaupunki, status;
	private Asiakas asiakas;
	private ArrayList<Tilausrivi> tilausriviLista;
	private ArrayList<Juomarivi> juomarivilista;
	private String pizzatMaar;

	public Tilaus() {
		super();
		this.tilaus_id = 0;
		this.tilaus_pvm = null;
		this.tilaus_hinta = 0;
		this.toimitustapa = true;
		this.etunimi="";
		this.sukunimi="";
		this.puhelin="";
		this.toimitusosoite="";
		this.postinumero="";
		this.kaupunki="";
		this.status="";
		this.tilausriviLista = new ArrayList<Tilausrivi>(); // luodaan tyhjä
															// lista
		this.juomarivilista = new ArrayList<Juomarivi>();
		this.pizzatMaar = pizzatMaar;
	}

	public Tilaus(int tilaus_id, Date tilaus_pvm, double tilaus_hinta,
			boolean toimitustapa, String etunimi, String sukunimi,
			String puhelin, String toimitusosoite, String postinumero,
			String kaupunki, String status, ArrayList<Tilausrivi> tilausriviLista) {
		super();
		this.tilaus_id = tilaus_id;
		this.tilaus_pvm = tilaus_pvm;
		this.tilaus_hinta = tilaus_hinta;
		this.toimitustapa = toimitustapa;
		this.etunimi = etunimi;
		this.sukunimi = sukunimi;
		this.puhelin = puhelin;
		this.toimitusosoite = toimitusosoite;
		this.postinumero = postinumero;
		this.kaupunki = kaupunki;
		this.status = status;
		this.tilausriviLista = tilausriviLista;
		if (tilausriviLista == null){
			this.tilausriviLista = new ArrayList<Tilausrivi>();
		}else {
			this.tilausriviLista = tilausriviLista;
		}
	}
	
	
	public Tilaus(int tilaus_id, Date tilaus_pvm, double tilaus_hinta,
			boolean toimitustapa, String etunimi, String sukunimi,
			String puhelin, String toimitusosoite, String postinumero,
			String kaupunki, String status, Asiakas asiakas,
			ArrayList<Tilausrivi> tilausriviLista, String pizzatMaar) {
		super();
		this.tilaus_id = tilaus_id;
		this.tilaus_pvm = tilaus_pvm;
		this.tilaus_hinta = tilaus_hinta;
		this.toimitustapa = toimitustapa;
		this.etunimi = etunimi;
		this.sukunimi = sukunimi;
		this.puhelin = puhelin;
		this.toimitusosoite = toimitusosoite;
		this.postinumero = postinumero;
		this.kaupunki = kaupunki;
		this.status = status;
		this.asiakas = asiakas;
		this.tilausriviLista = tilausriviLista;
		if (tilausriviLista == null){
			this.tilausriviLista = new ArrayList<Tilausrivi>();
		}else {
			this.tilausriviLista = tilausriviLista;
		}
		this.pizzatMaar = pizzatMaar;
	}
	

	
	
	public Tilaus(int tilaus_id, Date tilaus_pvm, double tilaus_hinta,
			boolean toimitustapa, String etunimi, String sukunimi,
			String puhelin, String toimitusosoite, String postinumero,
			String kaupunki, String status, Asiakas asiakas,
			ArrayList<Tilausrivi> tilausriviLista,
			ArrayList<Juomarivi> juomarivilista, String pizzatMaar) {
		super();
		this.tilaus_id = tilaus_id;
		this.tilaus_pvm = tilaus_pvm;
		this.tilaus_hinta = tilaus_hinta;
		this.toimitustapa = toimitustapa;
		this.etunimi = etunimi;
		this.sukunimi = sukunimi;
		this.puhelin = puhelin;
		this.toimitusosoite = toimitusosoite;
		this.postinumero = postinumero;
		this.kaupunki = kaupunki;
		this.status = status;
		this.asiakas = asiakas;
		this.tilausriviLista = tilausriviLista;
		if (tilausriviLista == null){
			this.tilausriviLista = new ArrayList<Tilausrivi>();
		}else {
			this.tilausriviLista = tilausriviLista;
		}
		this.juomarivilista = juomarivilista;
		this.pizzatMaar = pizzatMaar;
	}

	public int getTilaus_id() {
		return tilaus_id;
	}

	public void setTilaus_id(int tilaus_id) {
		this.tilaus_id = tilaus_id;
	}

	public Date getTilaus_pvm() {
		return tilaus_pvm;
	}

	public void setTilaus_pvm(Date tilaus_pvm) {
		this.tilaus_pvm = tilaus_pvm;
	}
	/**
	 * Kaava joka laskee tilauksen loppuhinta
	 * @return
	 */
	public double getTilaus_hinta() {
		double loppuhinta = 0;
		for (int i = 0; i < tilausriviLista.size(); i++) {
			loppuhinta += tilausriviLista.get(i).getMaara()
					* tilausriviLista.get(i).getHinta();
			tilaus_hinta = loppuhinta;
		}
		return tilaus_hinta;
	}

	public double getTilaus_hinta1() {

		return tilaus_hinta;
	}

	public void setTilaus_hinta(double tilaus_hinta) {
		this.tilaus_hinta = tilaus_hinta;
	}

	public boolean isToimitustapa() {
		return toimitustapa;
	}

	public void setToimitustapa(boolean toimitustapa) {
		this.toimitustapa = toimitustapa;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getEtunimi() {
		return etunimi;
	}

	public void setEtunimi(String etunimi) {
		this.etunimi = etunimi;
	}

	public String getSukunimi() {
		return sukunimi;
	}

	public void setSukunimi(String sukunimi) {
		this.sukunimi = sukunimi;
	}

	public String getPuhelin() {
		return puhelin;
	}

	public void setPuhelin(String puhelin) {
		this.puhelin = puhelin;
	}

	public String getToimitusosoite() {
		return toimitusosoite;
	}

	public void setToimitusosoite(String toimitusosoite) {
		this.toimitusosoite = toimitusosoite;
	}

	public String getPostinumero() {
		return postinumero;
	}

	public void setPostinumero(String postinumero) {
		this.postinumero = postinumero;
	}

	public String getKaupunki() {
		return kaupunki;
	}

	public void setKaupunki(String kaupunki) {
		this.kaupunki = kaupunki;
	}

	public Asiakas getAsiakas() {
		return asiakas;
	}

	public String getPizzatMaar() {
		return pizzatMaar;
	}

	public void setPizzatMaar(String pizzatMaar) {
		this.pizzatMaar = pizzatMaar;
	}

	public Tilausrivi getTilausrivi(int index) { // palauttaa listassa
		// paikkanumerossa olevan
		// tilausrivin
		if (index >= 0 && index < tilausriviLista.size()) {
			return tilausriviLista.get(index);

		} else {
			return null;
		}
	}

	public int getTilausriviLkm() {
		return this.tilausriviLista.size();

	}

	public void addTilausrivi(Tilausrivi tilausrivi) { // Lisää parametrinä
		// saadun tilausrivin
		// tilausrivilistan
		// loppuun
		if (tilausrivi != null) {
			this.tilausriviLista.add(tilausrivi);
		}
	}

	public void removeTilausrivi(int index) { // Lisää parametrinä
		// saadun tilausrivin
		// tilausrivilistan
		// loppuun

		this.tilausriviLista.remove(index);
	}
	

	
	public ArrayList<Tilausrivi> getTilausrivilista(){
		return tilausriviLista;
	}
	
	public void setAsiakas(Asiakas asiakas) {
		this.asiakas = asiakas;
	}

	public ArrayList<Tilausrivi> getTilausriviLista() {
		return tilausriviLista;
	}

	public void setTilausriviLista(ArrayList<Tilausrivi> tilausriviLista) {
		this.tilausriviLista = tilausriviLista;
	}

	public ArrayList<Juomarivi> getJuomarivilista() {
		return juomarivilista;
	}

	public void setJuomarivilista(ArrayList<Juomarivi> juomarivilista) {
		this.juomarivilista = juomarivilista;
	}

	public Juomarivi getJuomarivi(int index) {
		if (index >= 0 && index < juomarivilista.size()) {
			return juomarivilista.get(index);
		} else {
			return null;
		}
	}

	public int getJuomariviLkm() {
		return this.juomarivilista.size();
	}

	public void addJuomarivi(Juomarivi juomarivi) {
		if (juomarivi != null) {
			this.juomarivilista.add(juomarivi);
		}
	}
	
	public void removeJuomarivi(int index) {
		this.juomarivilista.remove(index);
	}
	
	
	
	
	
	@Override
	public String toString() {
		return "Tilaus [tilaus_id=" + tilaus_id + ", tilaus_pvm=" + tilaus_pvm
				+ ", tilaus_hinta=" + tilaus_hinta + ", toimitustapa="
				+ toimitustapa + ", etunimi=" + etunimi + ", sukunimi="
				+ sukunimi + ", puhelin=" + puhelin + ", toimitusosoite="
				+ toimitusosoite + ", postinumero=" + postinumero
				+ ", kaupunki=" + kaupunki + ", status=" + status
				+ ", asiakas=" + asiakas + ", tilausriviLista="
				+ tilausriviLista + ", juomarivilista=" + juomarivilista
				+ ", pizzatMaar=" + pizzatMaar + "]";
	}


	

	

}
