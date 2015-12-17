package pizzicato_ibizza.model;

public class Asiakas {
	
	private int asiakas_id;
	private String etunimi;
	private String sukunimi;
	private String email;
	private String puhelin_nro;
	private String katuosoite;
	private String postinumero;
	private String kayttajatunnus;
	private String salasana;
	private boolean valid;
	
	
	


	public Asiakas() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Asiakas(int asiakas_id, String etunimi, String sukunimi,
			String email, String puhelin_nro, String katuosoite,
			String postinumero, String kayttajatunnus, String salasana) {
		super();
		this.asiakas_id = asiakas_id;
		this.etunimi = etunimi;
		this.sukunimi = sukunimi;
		this.email = email;
		this.puhelin_nro = puhelin_nro;
		this.katuosoite = katuosoite;
		this.postinumero = postinumero;
		this.kayttajatunnus = kayttajatunnus;
		this.salasana = salasana;
	}


	public int getAsiakas_id() {
		return asiakas_id;
	}


	public void setAsiakas_id(int asiakas_id) {
		this.asiakas_id = asiakas_id;
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


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPuhelin_nro() {
		return puhelin_nro;
	}


	public void setPuhelin_nro(String puhelin_nro) {
		this.puhelin_nro = puhelin_nro;
	}


	public String getKatuosoite() {
		return katuosoite;
	}


	public void setKatuosoite(String katuosoite) {
		this.katuosoite = katuosoite;
	}


	public String getPostinumero() {
		return postinumero;
	}


	public void setPostinumero(String postinumero) {
		this.postinumero = postinumero;
	}


	public String getKayttajatunnus() {
		return kayttajatunnus;
	}


	public void setKayttajatunnus(String kayttajatunnus) {
		this.kayttajatunnus = kayttajatunnus;
	}


	public String getSalasana() {
		return salasana;
	}


	public void setSalasana(String salasana) {
		this.salasana = salasana;
	}



	@Override
	public String toString() {
		return "Kayttaja [asiakas_id=" + asiakas_id + ", etunimi=" + etunimi
				+ ", sukunimi=" + sukunimi + ", email=" + email
				+ ", puhelin_nro=" + puhelin_nro + ", katuosoite=" + katuosoite
				+ ", postinumero=" + postinumero + ", kayttajatunnus="
				+ kayttajatunnus + ", salasana=" + salasana + ", valid="
				+ valid + "]";
	}
	
	
	public boolean isValid(){
		return valid;
	}
	
	public void setValid(boolean newValid){
		valid=newValid;
	}
	
	
	

}
