package pizzicato_ibizza.model;

import java.util.ArrayList;

public class Tilausrivi {

	private int tilausrivi_id;
	private int maara;
	private boolean koko;
	private double hinta;
	private String lisatoiveet;
	private Pizza pizza;
	public ArrayList<Tayte> tayt;
	

	public Tilausrivi() {
		super();
		this.tilausrivi_id = 0;
		this.maara = 0;
		this.koko = true;
		this.hinta = 0;
		this.lisatoiveet = "";
		this.pizza = new Pizza();
		this.tayt = new ArrayList<Tayte>();
	}

	public Tilausrivi(int tilausrivi_id, int maara, boolean koko, double hinta,
			String lisatoiveet) {
		super();
		this.tilausrivi_id = tilausrivi_id;
		this.maara = maara;
		this.koko = koko;
		this.hinta = hinta;
		this.lisatoiveet = lisatoiveet;
	}
	
	
	public Tilausrivi(int tilausrivi_id, int maara, boolean koko, double hinta,
			String lisatoiveet, Pizza pizza) {
		super();
		this.tilausrivi_id = tilausrivi_id;
		this.maara = maara;
		this.koko = koko;
		this.hinta = hinta;
		this.lisatoiveet = lisatoiveet;
		this.pizza = pizza;
	}
	
	public Tilausrivi(int tilausrivi_id, int maara, boolean koko, double hinta,
			Pizza pizza, ArrayList<Tayte> tayt) {
		super();
		this.tilausrivi_id = tilausrivi_id;
		this.maara = maara;
		this.koko = koko;
		this.hinta = hinta;
		this.pizza = pizza;
		this.tayt = tayt;
		if (tayt == null){
			this.tayt = new ArrayList<Tayte>();
		} else {
			this.tayt = tayt;
		}
	}
	
	public Tilausrivi(int tilausrivi_id, int maara, boolean koko, double hinta,
			String lisatoiveet, Pizza pizza, ArrayList<Tayte> tayt) {
		super();
		this.tilausrivi_id = tilausrivi_id;
		this.maara = maara;
		this.koko = koko;
		this.hinta = hinta;
		this.pizza = pizza;
		this.lisatoiveet = lisatoiveet;
		this.tayt = tayt;
		if (tayt == null){
			this.tayt = new ArrayList<Tayte>();
		} else {
			this.tayt = tayt;
		}
	}
	
	public ArrayList<Tayte> getTayt() {
		return tayt;
	}

	public void setTayt(ArrayList<Tayte> tayt) {
		this.tayt = tayt;
	}

	public Tayte getTayte(int index){
		if (index >= 0 && index < tayt.size()) {
			return tayt.get(index);
		} else {
			return null;
		}
	}
	
	public int getTaytelkm(){
		return this.tayt.size();
	}
	
	
	public void addTayte(Tayte tayte){
		if (tayte != null) {
			this.tayt.add(tayte);
		}
	}
	
	public void removeTayte(int index) {
		this.tayt.remove(index);
	}
	
	public ArrayList<Tayte> getTaytelista(){
		return tayt;
	}
	
	
	public int getTilausrivi_id() {
		return tilausrivi_id;
	}

	public void setTilausrivi_id(int tilausrivi_id) {
		this.tilausrivi_id = tilausrivi_id;
	}

	public int getMaara() {
		return maara;
	}

	public void setMaara(int maara) {
		this.maara = maara;
	}

	public boolean getKoko() {
		return koko;
	}

	public void setKoko(boolean b) {
		this.koko = b;
	}

	public double getHinta() {
		return hinta;
	}

	public void setHinta(double hinta) {
		this.hinta = hinta;
	}

	public String getLisatoiveet() {
		return lisatoiveet;
	}

	public void setLisatoiveet(String lisatoiveet) {
		this.lisatoiveet = lisatoiveet;
	}

	public Pizza getPizza() {
		return pizza;
	}

	public void setPizza(Pizza pizza) {
		this.pizza = pizza;
	}
	
	
	
	

		

	@Override
	public String toString() {
		return "Tilausrivi [tilausrivi_id=" + tilausrivi_id + ", maara="
				+ maara + ", koko=" + koko + ", hinta=" + hinta
				+ ", lisatoiveet=" + lisatoiveet + ", pizza=" + pizza
				+ ", tayt=" + tayt + "]";
	}

	

}
