package pizzicato_ibizza.model;

import java.util.ArrayList;

public class Pizza {

	private int pizza_id;
	private String pizza_nimi;
	private String kuvaus;
	private double hinta_norm;
	private double hinta_iso;
	private boolean status;
	private boolean fantasia;
	public ArrayList<Tayte> tt = new ArrayList<Tayte>();
	private String taytteet;
	

	public Pizza() {
		super();
		this.pizza_id = 0;
		this.pizza_nimi = "";
		this.kuvaus = "";
		this.hinta_norm = 0;
		this.hinta_iso = 0;
		this.status = true;
		this.tt = new ArrayList<Tayte>();
		this.taytteet = "";
	}

	
	public Pizza(int pizza_id, String pizza_nimi, String kuvaus,
			double hinta_norm, double hinta_iso, boolean status, ArrayList<Tayte> tt) {
		super();
		this.pizza_id = pizza_id;
		this.pizza_nimi = pizza_nimi;
		this.kuvaus = kuvaus;
		this.hinta_norm = hinta_norm;
		this.hinta_iso = hinta_iso;
		this.status = status;
		this.tt = tt;
		if (tt == null){
			this.tt = new ArrayList<Tayte>();
		} else {
			this.tt = tt;
		}
	}
	
	public Pizza(int pizza_id, String pizza_nimi, String kuvaus,
			double hinta_norm, double hinta_iso, boolean status) {
		super();
		this.pizza_id = pizza_id;
		this.pizza_nimi = pizza_nimi;
		this.kuvaus = kuvaus;
		this.hinta_norm = hinta_norm;
		this.hinta_iso = hinta_iso;
		this.status = status;
	}
	
	
	public Pizza(int pizza_id, String pizza_nimi, String kuvaus,
			double hinta_norm, double hinta_iso, boolean status, String taytteet, boolean fantasia) {
		super();
		this.pizza_id = pizza_id;
		this.pizza_nimi = pizza_nimi;
		this.kuvaus = kuvaus;
		this.hinta_norm = hinta_norm;
		this.hinta_iso = hinta_iso;
		this.status = status;
		this.taytteet = taytteet;
		this.fantasia = fantasia;
	}
	


	public String getTaytteet() {
		return taytteet;
	}

	public void setTaytteet(String taytteet) {
		this.taytteet = taytteet;
	}
	
	public Tayte getTayte(int index){
		if (index >= 0 && index < tt.size()) {
			return tt.get(index);
		} else {
			return null;
		}
	}
	
	public int getTaytelkm(){
		return this.tt.size();
	}
	
	
	public void addTayte(Tayte tayte){
		if (tayte != null) {
			this.tt.add(tayte);
		}
	}
	
	public void removeTayte(int index) {
		this.tt.remove(index);
	}
	
	public ArrayList<Tayte> getTaytelista(){
		return tt;
	}
	

	public int getPizza_id() {
		return pizza_id;
	}

	public void setPizza_id(int pizza_id) {
		this.pizza_id = pizza_id;
	}

	public String getPizza_nimi() {
		return pizza_nimi;
	}

	public void setPizza_nimi(String pizza_nimi) {
		this.pizza_nimi = pizza_nimi;
	}

	public String getKuvaus() {
		return kuvaus;
	}

	public void setKuvaus(String kuvaus) {
		this.kuvaus = kuvaus;
	}

	public double getHinta_norm() {
		return hinta_norm;
	}

	public void setHinta_norm(double hinta_norm) {
		this.hinta_norm = hinta_norm;
	}

	public double getHinta_iso() {
		return hinta_iso;
	}

	public void setHinta_iso(double hinta_iso) {
		this.hinta_iso = hinta_iso;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public boolean getFantasia() {
		return fantasia;
	}

	public void setFantasia(boolean fantasia) {
		this.fantasia = fantasia;
	}

	@Override
	public String toString() {
		return "Pizza [pizza_id=" + pizza_id + ", pizza_nimi=" + pizza_nimi
				+ ", kuvaus=" + kuvaus + ", hinta_norm=" + hinta_norm
				+ ", hinta_iso=" + hinta_iso + ", status=" + status + ", tt="
				+ tt + ", taytteet=" + taytteet + ", fantasia=" + fantasia
				+ "]";
	}


	

}
