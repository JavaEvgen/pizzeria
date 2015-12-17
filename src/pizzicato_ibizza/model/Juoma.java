package pizzicato_ibizza.model;

import java.util.ArrayList;

public class Juoma {
	private int juoma_id;
	private String juoma_nimi;
	private String koko;
	private double hinta;
	private boolean status;
	
	public Juoma() {
		super();
		this.juoma_id = 0;
		this.juoma_nimi = "";
		this.koko = "";
		this.hinta = 0;
		this.status = true;
	}
	public Juoma(int juoma_id, String juoma_nimi, String koko, double hinta,
			boolean status) {
		super();
		this.juoma_id = juoma_id;
		this.juoma_nimi = juoma_nimi;
		this.koko = koko;
		this.hinta = hinta;
		this.status = status;
	}
	public int getJuoma_id() {
		return juoma_id;
	}
	public void setJuoma_id(int juoma_id) {
		this.juoma_id = juoma_id;
	}
	public String getJuoma_nimi() {
		return juoma_nimi;
	}
	public void setJuoma_nimi(String juoma_nimi) {
		this.juoma_nimi = juoma_nimi;
	}
	public String getKoko() {
		return koko;
	}
	public void setKoko(String koko) {
		this.koko = koko;
	}
	public double getHinta() {
		return hinta;
	}
	public void setHinta(double hinta) {
		this.hinta = hinta;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Juoma [juoma_id=" + juoma_id + ", juoma_nimi=" + juoma_nimi
				+ ", koko=" + koko + ", hinta=" + hinta + ", status=" + status
				+ "]";
	}

	
	

}
