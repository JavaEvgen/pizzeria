package pizzicato_ibizza.model;

public class Juomarivi {
	private int juomarivi_id;
	private Juoma juoma;
	private int maara;
	private double hinta;
	
	public Juomarivi() {
		super();
		this.juomarivi_id = 0;
		this.juoma = null;
		this.maara = 0;
		this.hinta = 0;
	}

	public Juomarivi(int juomarivi_id, Juoma juoma, int maara, double hinta) {
		super();
		this.juomarivi_id = juomarivi_id;
		this.juoma = juoma;
		this.maara = maara;
		this.hinta = hinta;
	}

	public int getJuomarivi_id() {
		return juomarivi_id;
	}

	public void setJuomarivi_id(int juomarivi_id) {
		this.juomarivi_id = juomarivi_id;
	}

	public Juoma getJuoma() {
		return juoma;
	}

	public void setJuoma(Juoma juoma) {
		this.juoma = juoma;
	}

	public int getMaara() {
		return maara;
	}

	public void setMaara(int maara) {
		this.maara = maara;
	}

	public double getHinta() {
		return hinta;
	}

	public void setHinta(double hinta) {
		this.hinta = hinta;
	}

	@Override
	public String toString() {
		return "Juomarivi [juomarivi_id=" + juomarivi_id + ", juoma=" + juoma
				+ ", maara=" + maara + ", hinta=" + hinta + "]";
	}
	
	
	

}
