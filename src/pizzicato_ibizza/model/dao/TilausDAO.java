package pizzicato_ibizza.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import pizzicato_ibizza.model.Juoma;
import pizzicato_ibizza.model.Juomarivi;
import pizzicato_ibizza.model.Pizza;
import pizzicato_ibizza.model.Tayte;
import pizzicato_ibizza.model.Tilaus;
import pizzicato_ibizza.model.Tilausrivi;
import pizzicato_ibizza.model.dao.DataAccessObject;
import pizzicato_ibizza.model.dao.TilausDAO;

public class TilausDAO extends DataAccessObject {
	/**
	 * Lis‰t‰‰n tilauksia tietokantaan
	 * @param tilaus
	 * @throws SQLException
	 */
	@SuppressWarnings("resource")
	public void addTilaus(Tilaus tilaus) throws SQLException {
		Connection connection = null;
		PreparedStatement stmtInsert = null;

		try {
			// Luodaan yhteys ja aloitetaan transaktio:
			connection = getConnection();
			// Luodaan uusi henkilo tietokantaan:
			String sqlInsert = "INSERT INTO Tilaus(tilaus_hinta, toimitustapa, etunimi, sukunimi, "
					+ "puhelin, toimitusosoite, postinumero, kaupunki) VALUES (?,?,?,?,?,?,?,?)";
			stmtInsert = connection.prepareStatement(sqlInsert);

			stmtInsert.setDouble(1, tilaus.getTilaus_hinta());
			stmtInsert.setBoolean(2, tilaus.isToimitustapa());
			stmtInsert.setString(3, tilaus.getEtunimi());
			stmtInsert.setString(4, tilaus.getSukunimi());
			stmtInsert.setString(5, tilaus.getPuhelin());
			stmtInsert.setString(6, tilaus.getToimitusosoite());
			stmtInsert.setString(7, tilaus.getPostinumero());
			stmtInsert.setString(8, tilaus.getKaupunki());
			stmtInsert.executeUpdate();

			int maxid = 0;

			stmtInsert = connection
					.prepareStatement("SELECT MAX(tilaus_id) AS tilaus_id FROM Tilaus;");
			ResultSet rs = stmtInsert.executeQuery();
			if (rs.next()) {
				maxid = rs.getInt("tilaus_id");

				tilaus.setTilaus_id(maxid);

				sqlInsert = "INSERT INTO Tilausrivi(tilausrivi_id, pizza_id, tilaus_id, maara, koko, hinta) VALUES (?, ?, ?, ?, ?, ?)";
				stmtInsert = connection.prepareStatement(sqlInsert);
				Tilausrivi tilausrivi;
				Pizza pizza;

				for (int i = 0; i < tilaus.getTilausriviLkm(); i++) {
					tilausrivi = tilaus.getTilausrivi(i);
					pizza = tilausrivi.getPizza();
					stmtInsert.setInt(1, tilausrivi.getTilausrivi_id());
					stmtInsert.setInt(2, pizza.getPizza_id());
					stmtInsert.setInt(3, tilaus.getTilaus_id());
					stmtInsert.setInt(4, tilausrivi.getMaara());
					stmtInsert.setBoolean(5, tilausrivi.getKoko());
					stmtInsert.setDouble(6, tilausrivi.getHinta());
					stmtInsert.addBatch();
				}
				stmtInsert.executeBatch();

				/*sqlInsert = "INSERT INTO Juomarivi(juomarivi_id, tilaus_id, juoma_id, maara, hinta) VALUES (?,?,?,?,?);";
				stmtInsert = connection.prepareStatement(sqlInsert);
				Juomarivi juomarivi;
				Juoma juoma;
				for (int j = 0; j < tilaus.getJuomariviLkm(); j++) {
					juomarivi = tilaus.getJuomarivi(j);
					juoma = juomarivi.getJuoma();
					stmtInsert.setInt(1, juomarivi.getJuomarivi_id());
					stmtInsert.setInt(2, tilaus.getTilaus_id());
					stmtInsert.setInt(3, juoma.getJuoma_id());
					stmtInsert.setInt(4, juomarivi.getMaara());
					stmtInsert.setDouble(5, juomarivi.getHinta());
					stmtInsert.addBatch();
				}

				stmtInsert.executeBatch();*/

			}

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			close(stmtInsert, connection); // Suljetaan statement ja yhteys
		}
	}
	/**
	 * Haetaan kaikki tilaukset tietokannasta
	 * @return
	 */
	public ArrayList<Tilaus> findAll() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Tilaus> Tilaukset = new ArrayList<Tilaus>();
		Tilaus Tilaus = null;
		try {
			// Luodaan yhteys
			conn = getConnection();
			// Luodaan komento: haetaan kaikki rivit henkilo-taulusta
			String sqlSelect = "SELECT tilaus.tilaus_id, tilaus.tilaus_pvm, tilaus.tilaus_hinta, GROUP_CONCAT(pizza.pizza_nimi,' ', tr.maara,' ', tr.koko)AS pizzat, tilaus.toimitustapa, tilaus.asiakas_id, tilaus.etunimi, tilaus.sukunimi, tilaus.puhelin, tilaus.toimitusosoite, tilaus.postinumero, tilaus.kaupunki, tilaus.status FROM Tilaus tilaus JOIN Tilausrivi tr ON tilaus.tilaus_id = tr.tilaus_id JOIN Pizza pizza ON pizza.pizza_id = tr.pizza_id GROUP BY tilaus.tilaus_pvm ORDER BY tilaus.tilaus_pvm;";
			// Valmistellaan komento:
			stmt = conn.prepareStatement(sqlSelect);
			// L‰hetet‰‰n komento:
			rs = stmt.executeQuery(sqlSelect);
			// K‰yd‰‰n tulostaulun rivit l‰pi ja luetaan
			// readHenkilo()-metodilla:
			while (rs.next()) {
				Tilaus = readTilaus(rs);
				// lis‰t‰‰n henkilˆ listaan
				Tilaukset.add(Tilaus);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(rs, stmt, conn); // Suljetaan
		}

		return Tilaukset;
	}
	

	/**
	 * Haetaan vain ne tilauksen, joiden status on joko "tilattu" tai "valmis"
	 * @return
	 */
	public ArrayList<Tilaus> findAllforKokki() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Tilaus> tilaukset = new ArrayList<Tilaus>();
		Tilaus tilaus = null;
		Tilausrivi tilausrivi = null;
		int edellinenID = 0;
		int nykyinenID = 0;
		
		try {
			// Luodaan yhteys
			conn = getConnection();
			// Luodaan komento: haetaan kaikki rivit henkilo-taulusta
			String sqlSelect = "SELECT t.tilaus_id, t.tilaus_pvm, t.tilaus_hinta, t.toimitustapa, t.etunimi, t.sukunimi, t.puhelin, t.toimitusosoite, t.postinumero, t.postinumero, t.kaupunki, t.status, tr.tilausrivi_id, tr.maara, tr.koko, tr.hinta, p.pizza_id, p.pizza_nimi, p.kuvaus, p.hinta_norm, p.hinta_iso, p.status FROM Tilaus t JOIN Tilausrivi tr ON t.tilaus_id = tr.tilaus_id JOIN Pizza p ON p.pizza_id = tr.pizza_id ORDER BY t.tilaus_pvm DESC, t.tilaus_id;";
			
			// Valmistellaan komento:
			stmt = conn.prepareStatement(sqlSelect);
			// L‰hetet‰‰n komento:
			rs = stmt.executeQuery(sqlSelect);
			// K‰yd‰‰n tulostaulun rivit l‰pi ja luetaan
			// readHenkilo()-metodilla:
			while (rs.next()) {
				nykyinenID = rs.getInt("tilaus_id");
				if (nykyinenID != edellinenID) {
					tilaus = readTilausKokki(rs);
					// lis‰t‰‰n tilaus listaan
					tilaukset.add(tilaus);
					edellinenID = nykyinenID;
				}
				// lis‰t‰‰n tilausrivi tilaukselle
				tilausrivi = readTilausrivi(rs);
				tilaus.addTilausrivi(tilausrivi);
				

			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(rs, stmt, conn); // Suljetaan
		}

		return tilaukset;
	}
	/**
	 * Haetaan vain ne tilaukset joiden status on "valmis" tai "toimitettu"
	 * @return
	 */

	public ArrayList<Tilaus> findAllForKuski() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Tilaus> Tilaukset = new ArrayList<Tilaus>();
		Tilaus Tilaus = null;
		try {
			// Luodaan yhteys
			conn = getConnection();
			// Luodaan komento: haetaan kaikki rivit henkilo-taulusta
			String sqlSelect = "SELECT tilaus.tilaus_id, tilaus.tilaus_pvm, tilaus.tilaus_hinta, GROUP_CONCAT(pizza.pizza_nimi,';', tr.maara)AS pizzat, tilaus.toimitustapa, tilaus.asiakas_id, tilaus.etunimi, tilaus.sukunimi, tilaus.puhelin, tilaus.toimitusosoite, tilaus.postinumero, tilaus.kaupunki, tilaus.status FROM Tilaus tilaus JOIN Tilausrivi tr ON tilaus.tilaus_id = tr.tilaus_id JOIN Pizza pizza ON pizza.pizza_id = tr.pizza_id WHERE tilaus.status='valmis' GROUP BY tilaus.tilaus_pvm ORDER BY tilaus.tilaus_pvm;";
			// Valmistellaan komento:
			stmt = conn.prepareStatement(sqlSelect);
			// L‰hetet‰‰n komento:
			rs = stmt.executeQuery(sqlSelect);
			// K‰yd‰‰n tulostaulun rivit l‰pi ja luetaan
			// readHenkilo()-metodilla:
			while (rs.next()) {
				Tilaus = readTilaus(rs);
				// lis‰t‰‰n henkilˆ listaan
				Tilaukset.add(Tilaus);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(rs, stmt, conn); // Suljetaan
		}

		return Tilaukset;
	}
	/**
	 * Asetataan tietokannasta saadut tiedot tilauksesta muuttujiin
	 * @param rs
	 * @return
	 */
	private Tilaus readTilaus(ResultSet rs) {
		try {
			// Haetaan yhden henkilˆn tiedot kyselyn tulostaulun
			// (ResultSet-tyyppinen rs-olion) aktiiviselta tietorivilt‰
			int tilaus_id = rs.getInt("tilaus_id");
			Date tilaus_pvm = rs.getDate("tilaus_pvm");
			double tilaus_hinta = rs.getDouble("tilaus_hinta");
			boolean toimitustapa = rs.getBoolean("toimitustapa");
			String etunimi = rs.getString("etunimi");
			String sukunimi = rs.getString("sukunimi");
			String puhelin = rs.getString("puhelin");
			String toimitusosoite = rs.getString("toimitusosoite");
			String postinumero = rs.getString("postinumero");
			String kaupunki = rs.getString("kaupunki");
			String status = rs.getString("status");
			String pizzatMaar = rs.getString("pizzat");

			// Luodaan ja palautetaan uusi henkilo
			return new Tilaus(tilaus_id, tilaus_pvm, tilaus_hinta,
					toimitustapa, etunimi, sukunimi, puhelin, toimitusosoite,
					postinumero, kaupunki, status, null, null, null, pizzatMaar);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * Asetataan tietokannsta saadut tiedot kokille erikseen
	 * @param rs
	 * @return
	 */
	private Tilaus readTilausKokki(ResultSet rs) {
		try {
			// Haetaan yhden tilauksen tiedot kyselyn tulostaulun
			// (ResultSet-tyyppinen rs-olion) aktiiviselta tietorivilt‰
			int tilaus_id = rs.getInt("tilaus_id");
			//Date tilaus_pvm = rs.getDate("tilaus_pvm");
			Date tilaus_pvm = rs.getDate("tilaus_pvm");
			double tilaus_hinta = rs.getDouble("tilaus_hinta");
			boolean toimitustapa = rs.getBoolean("toimitustapa");
			String etunimi = rs.getString("etunimi");
			String sukunimi = rs.getString("sukunimi");
			String puhelin = rs.getString("puhelin");
			String toimitusosoite = rs.getString("toimitusosoite");
			String postinumero = rs.getString("postinumero");
			String kaupunki = rs.getString("kaupunki");
			String status = rs.getString("t.status");
			
			
			// Luodaan ja palautetaan uusi tilaus
			return new Tilaus(tilaus_id, tilaus_pvm, tilaus_hinta,
					toimitustapa, etunimi, sukunimi, puhelin, toimitusosoite,
					postinumero, kaupunki, status, null);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Haetaan ja asetetaan muttujiin tilausrivin tietoja
	 * @param rs
	 * @return
	 */
	private Tilausrivi readTilausrivi(ResultSet rs) {
		try {
			// Haetaan yhden henkilˆn tiedot kyselyn tulostaulun
			// (ResultSet-tyyppinen rs-olion) aktiiviselta tietorivilt‰
			int tilausrivi_id = rs.getInt("tilausrivi_id");
			int maara = rs.getInt("maara");
			boolean koko = rs.getBoolean("koko");
			double hinta = rs.getDouble("hinta");
			//kysyt‰‰n apumuuttujiin pizzan tietoja resultsetist‰
			int pizza_id = rs.getInt("pizza_id");
			String pizza_nimi = rs.getString("pizza_nimi");
			String kuvaus = rs.getString("kuvaus");
			double hinta_norm = rs.getDouble("hinta_norm");
			double hinta_iso = rs.getDouble("hinta_iso");
			boolean status = rs.getBoolean("p.status");
			// luodaan pizza-olio
			Pizza pizza = new Pizza(pizza_id, pizza_nimi, kuvaus,
					hinta_norm, hinta_iso, status);

			// Luodaan ja palautetaan uusi tilausrivi
			return new Tilausrivi(tilausrivi_id, maara, koko, hinta, pizza, null);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	

	/**
	 * T‰st‰ l‰htien siin‰ on metodit jotka muutava pelk‰ statusta
	 * tarkoitettu eri k‰ytt‰j‰ryhmille
	 * @param Tilaus
	 */

	private Pizza readPizza(ResultSet rs) {
		try {
			// Haetaan yhden pizzan tiedot kyselyn tulostaulun
			// (ResultSet-tyyppinen rs-olion) aktiiviselta tietorivilt‰
			//kysyt‰‰n apumuuttujiin pizzan tietoja resultsetist‰
			int pizza_id = rs.getInt("pizza_id");
			String pizza_nimi = rs.getString("pizza_nimi");
			String kuvaus = rs.getString("kuvaus");
			double hinta_norm = rs.getDouble("hinta_norm");
			double hinta_iso = rs.getDouble("hinta_iso");
			boolean status = rs.getBoolean("p.status");
	
			// luodaan ja palautetaan pizza-olio
			return new Pizza(pizza_id, pizza_nimi, kuvaus, hinta_norm, 
					hinta_iso, status, null);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	private Tayte readTayte(ResultSet rs) {
		try {
			// Haetaan yhden t‰ytteen tiedot kyselyn tulostaulun
			// (ResultSet-tyyppinen rs-olion) aktiiviselta tietorivilt‰
			//kysyt‰‰n apumuuttujiin t‰ytteen tietoja resultsetist‰
			int tayte_id = rs.getInt("tayte_id");
			String tayte_nimi = rs.getString("tayte_nimi");
			double hinta = rs.getDouble("ta.hinta");
			boolean tayte_status = rs.getBoolean("ta.status");
		
			// luodaan ja palautetaan t‰yte-olio
			return new Tayte(tayte_id, tayte_nimi, hinta, tayte_status);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	

	public void kokkiMuutaStatus(Tilaus Tilaus) {

		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = getConnection();
			String sqlInsert = "update Tilaus set status='valmis' where tilaus_id=?";
			stmt = conn.prepareStatement(sqlInsert);

			stmt.setInt(1, Tilaus.getTilaus_id());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			close(stmt, conn);
		}

	}

	public void kuskiMuutaStatus(Tilaus Tilaus) {

		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = getConnection();
			String sqlInsert = "update Tilaus set status='toimitettu' where tilaus_id=?";
			stmt = conn.prepareStatement(sqlInsert);

			stmt.setInt(1, Tilaus.getTilaus_id());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			close(stmt, conn);
		}

	}

	public void muutaStatus(Tilaus Tilaus) {

		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = getConnection();
			String sqlInsert = "update Tilaus set status='tilattu' where tilaus_id=?"; // kokki
																						// n‰kee
																						// tilatut
																						// tilaukset
																						// ja
																						// t‰st‰
																						// voi
																						// muuttaa
																						// statuksen
																						// valmiiksi
			stmt = conn.prepareStatement(sqlInsert);

			stmt.setInt(1, Tilaus.getTilaus_id());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			close(stmt, conn);
		}

	}
	
	/**
	 * Haetaan tilauksen tietokannasta id numeron perusteella
	 * @param tilaus_id
	 * @return
	 */
	public Tilaus getTilausById(int tilaus_id) {

		Connection conn = null;
		PreparedStatement stmt = null;
		Tilaus Tilaus = new Tilaus();

		try {
			conn = getConnection();
			stmt = conn
					.prepareStatement("select * from Tilaus where tilaus_id=?");
			stmt.setInt(1, tilaus_id);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				Tilaus.setTilaus_id(rs.getInt("tilaus_id"));
				Tilaus.setTilaus_pvm(rs.getDate("tilaus_pvm"));
				Tilaus.setTilaus_hinta(rs.getDouble("tilaus_hinta"));
				Tilaus.setToimitustapa(rs.getBoolean("toimitustapa"));
				Tilaus.setToimitusosoite(rs.getString("toimitusosoite"));
				Tilaus.setEtunimi(rs.getString("etunimi"));
				Tilaus.setPuhelin(rs.getString("puhelin"));
				Tilaus.setStatus(rs.getString("status"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt, conn);
		}

		return Tilaus;
	}

	/**
	 * Haetaan tilausrivissa oleva pizzan nimia
	 * @return
	 */
	
	public String tilausrivinPizzanNimi(){
		Tilausrivi tilausrivi = null;
		Pizza pizza = null;
		String pizzaNimi = null;
		Tilaus tilaus = new Tilaus();
		
		for (int i = 0; i < tilaus.getTilausriviLkm(); i++) {
			tilausrivi = tilaus.getTilausrivi(i);
			pizza = tilausrivi.getPizza();
			pizzaNimi = pizza.getPizza_nimi();
			
		}
		
		return pizzaNimi;
	}
		
	
}
