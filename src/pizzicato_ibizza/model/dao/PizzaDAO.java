package pizzicato_ibizza.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import pizzicato_ibizza.model.Tayte;
import pizzicato_ibizza.model.Pizza;
import pizzicato_ibizza.model.dao.DataAccessObject;
import pizzicato_ibizza.model.dao.PizzaDAO;

public class PizzaDAO extends DataAccessObject {

	/**
	 * lis‰‰ tuotteen tiedot tietokantaan
	 *
	 * @param henkilo Henkilo-luokan olio
	 * @throws SQLException
	 */
	public void addPizza(Pizza Pizza) throws SQLException {
		Connection connection = null;
		PreparedStatement stmtInsert = null;

		try {
			// Luodaan yhteys ja aloitetaan transaktio:
			connection = getConnection();
			// Luodaan uusi henkilo tietokantaan:
			String sqlInsert = "INSERT INTO Pizza(pizza_nimi, kuvaus, hinta_norm, hinta_iso, fantasia) VALUES (?, ?, ?, ?, ?)";
			stmtInsert = connection.prepareStatement(sqlInsert);

			stmtInsert.setString(1, Pizza.getPizza_nimi());
			stmtInsert.setString(2, Pizza.getKuvaus());
			stmtInsert.setDouble(3, Pizza.getHinta_norm());
			stmtInsert.setDouble(4, Pizza.getHinta_iso());
			stmtInsert.setBoolean(5, Pizza.getFantasia());
			stmtInsert.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		int maxid = 0;

		try {

			stmtInsert = connection
					.prepareStatement("select MAX(pizza_id) AS Pizza_id FROM Pizza;");

			ResultSet rs = stmtInsert.executeQuery();

			if (rs.next()) {
				maxid = rs.getInt("Pizza_id");

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		Pizza.setPizza_id(maxid);

		try {

			// Luodaan uusi henkilo tietokantaan:
			String sqlInsert = "INSERT INTO Pizzan_Tayte(pizza_id, tayte_id) VALUES (?, ?)";
			stmtInsert = connection.prepareStatement(sqlInsert);

			for (int i = 0; i < Pizza.tt.size(); i++) {

				stmtInsert.setInt(1, Pizza.getPizza_id());
				stmtInsert.setInt(2, Pizza.tt.get(i).getTayte_id());
				System.out.println(stmtInsert);
				stmtInsert.addBatch();
			}
			stmtInsert.executeBatch();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(stmtInsert, connection); // Suljetaan statement ja yhteys
		}
	}
	/**
	 * Haetaan kaikki pizzat tietokonnasta
	 * @return
	 */
	public ArrayList<Pizza> findAll() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Pizza> Pizzat = new ArrayList<Pizza>();
		Pizza Pizza = null;
		try {
			// Luodaan yhteys
			conn = getConnection();
			// Luodaan komento: haetaan kaikki rivit henkilo-taulusta

			String sqlSelect = "Select pizza.pizza_id, pizza.pizza_nimi, GROUP_CONCAT(' ',tayte.tayte_nimi)AS taytteet,pizza.kuvaus, pizza.hinta_norm, pizza.hinta_iso, pizza.status, pizza.fantasia FROM Pizza pizza JOIN Pizzan_Tayte pt ON pizza.pizza_id = pt.pizza_id JOIN Tayte tayte ON tayte.tayte_id = pt.tayte_id GROUP BY pizza.pizza_nimi ORDER BY pizza.hinta_norm;";

			// Valmistellaan komento:
			stmt = conn.prepareStatement(sqlSelect);
			// L‰hetet‰‰n komento:
			rs = stmt.executeQuery(sqlSelect);
			// K‰yd‰‰n tulostaulun rivit l‰pi ja luetaan
			// readHenkilo()-metodilla:
			while (rs.next()) {
				Pizza = readPizza(rs);
				// lis‰t‰‰n henkilˆ listaan
				System.out.println(Pizza.toString());
				Pizzat.add(Pizza);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(rs, stmt, conn); // Suljetaan
		}

		return Pizzat;
	}
	/**
	 * Haetaan kaikki pizzat, joiden status on "true"
	 * @return
	 */
	public ArrayList<Pizza> findAllforAll() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Pizza> Tuotteet = new ArrayList<Pizza>();
		Pizza Pizza = null;
		try {
			// Luodaan yhteys
			conn = getConnection();

			// Luodaan komento: haetaan kaikki rivit henkilo-taulusta
			String sqlSelect = "Select pizza.pizza_id, pizza.pizza_nimi, GROUP_CONCAT(' ',tayte.tayte_nimi)AS taytteet, pizza.kuvaus, pizza.hinta_norm, pizza.hinta_iso, pizza.status, pizza.fantasia FROM Pizza pizza JOIN Pizzan_Tayte pt ON pizza.pizza_id = pt.pizza_id JOIN Tayte tayte ON tayte.tayte_id = pt.tayte_id WHERE pizza.status=1 GROUP BY pizza.pizza_nimi ORDER BY pizza.hinta_norm;";

			// Valmistellaan komento:
			stmt = conn.prepareStatement(sqlSelect);
			// L‰hetet‰‰n komento:
			rs = stmt.executeQuery(sqlSelect);
			// K‰yd‰‰n tulostaulun rivit l‰pi ja luetaan
			// readHenkilo()-metodilla:
			while (rs.next()) {
				Pizza = readPizza(rs);
				// lis‰t‰‰n henkilˆ listaan
				Tuotteet.add(Pizza);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(rs, stmt, conn); // Suljetaan
		}

		return Tuotteet;
	}

	/**
	 * Lukee tietokannasta taulusta yhden tietorivin (henkilon tiedot). Luo ja
	 * palauttaa tietojen perusteella Henkilo-tyyppisen olion
	 *
	 * @param rs tietokannasta kyselyll‰ haettu tulostaulu 
	 * @return Henkilo henkilo-olio
	 */
	private Pizza readPizza(ResultSet rs) {
		try {
			// Haetaan yhden henkilˆn tiedot kyselyn tulostaulun
			// (ResultSet-tyyppinen rs-olion) aktiiviselta tietorivilt‰
			int pizza_id = rs.getInt("pizza_id");
			String pizza_nimi = rs.getString("pizza_nimi");
			String taytteet = rs.getString("taytteet");
			String kuvaus = rs.getString("kuvaus");
			double hinta_norm = rs.getDouble("hinta_norm");
			double hinta_iso = rs.getDouble("hinta_iso");
			boolean status = rs.getBoolean("status");
			boolean fantasia = rs.getBoolean("fantasia");
			// Luodaan ja palautetaan uusi henkilo
			return new Pizza(pizza_id, pizza_nimi, kuvaus, hinta_norm,
					hinta_iso, status, taytteet, fantasia);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * Poistetaan pizzan id numeron perusteella
	 * @param pizza_id
	 */
	public void deletePizza(int pizza_id) {
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = getConnection();
			String sqlInsert = "delete from Pizza where pizza_id=?";
			stmt = conn.prepareStatement(sqlInsert);
			// Parameters start with 1
			stmt.setInt(1, pizza_id);
			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(stmt, conn); // Suljetaan statement ja yhteys
		}
	}
	/**
	 * P‰ivitet‰‰n tietyn pizzan tietoja
	 * @param Pizza
	 */
	public void updatePizza(Pizza Pizza) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			String sqlInsert = "UPDATE Pizza set pizza_nimi=?, kuvaus=?, hinta_norm=?, hinta_iso=? WHERE pizza_id=?";
			stmt = conn.prepareStatement(sqlInsert);
			// Parameters start with 1

			stmt.setString(1, Pizza.getPizza_nimi());
			stmt.setString(2, Pizza.getKuvaus());
			stmt.setDouble(3, Pizza.getHinta_norm());
			stmt.setDouble(4, Pizza.getHinta_iso());
			stmt.setInt(5, Pizza.getPizza_id());

			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {

			// Luodaan uusi henkilo tietokantaan:
			String sqlInsert = "INSERT INTO Pizzan_Tayte(pizza_id, tayte_id) VALUES (?, ?)";
			stmt = conn.prepareStatement(sqlInsert);

			for (int i = 0; i < Pizza.tt.size(); i++) {

				stmt.setInt(1, Pizza.getPizza_id());
				stmt.setInt(2, Pizza.tt.get(i).getTayte_id());

				stmt.addBatch();
			}
			stmt.executeBatch();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(stmt, conn); // Suljetaan statement ja yhteys
		}

	}
	/**
	 * P‰ivitet‰‰n tietyn pizzan statusta
	 * @param Pizza
	 */
	public void updatePizzaStatus(Pizza Pizza) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			String sqlInsert = "update Pizza set status=? where pizza_id=?";
			stmt = conn.prepareStatement(sqlInsert);
			// Parameters start with 1

			stmt.setBoolean(1, Pizza.getStatus());
			stmt.setInt(2, Pizza.getPizza_id());

			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt, conn);
		}// Suljetaan

	}
	/**
	 * Etsit‰‰n pizza id numeron perusteella
	 * @param pizza_id
	 * @return
	 */
	public Pizza getPizzaById(int pizza_id) {

		Connection conn = null;
		PreparedStatement stmt = null;
		String pizza_nimi = null;
		String kuvaus = null;
		double hinta_norm = 0;
		double hinta_iso = 0;
		boolean status = true;
		boolean fantasia = true;
		String taytteet = null;
		Pizza Pizza = new Pizza(pizza_id, pizza_nimi, kuvaus, hinta_norm,
				hinta_iso, status, taytteet, fantasia);

		try {
			conn = getConnection();
			stmt = conn
					.prepareStatement("select * from Pizza where pizza_id=?");
			stmt.setInt(1, pizza_id);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				Pizza.setPizza_id(rs.getInt("pizza_id"));
				Pizza.setPizza_nimi(rs.getString("pizza_nimi"));
				Pizza.setKuvaus(rs.getString("kuvaus"));
				Pizza.setHinta_norm(rs.getDouble("hinta_norm"));
				Pizza.setHinta_iso(rs.getDouble("hinta_iso"));
				Pizza.setStatus(rs.getBoolean("status"));
				Pizza.setFantasia(rs.getBoolean("fantasia"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt, conn);
		}

		return Pizza;
	}
/*
	public int getPizzaMaxId() {

		Connection conn = null;
		PreparedStatement stmt = null;

		int maxid = 0;

		try {
			conn = getConnection();
			stmt = conn
					.prepareStatement("select MAX(pizza_id) AS pizza_id FROM Pizza;");

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				maxid = rs.getInt("pizza_id");

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt, conn);
		}

		return maxid;
	}
*/
	/**
	 * Poistetaan pizzan id numeron perusteella
	 * @param pizza_id
	 */
	public void deletePizzanTayte(int pizza_id) {
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = getConnection();
			String sqlInsert = "delete from Pizzan_Tayte where pizza_id=?";
			stmt = conn.prepareStatement(sqlInsert);
			// Parameters start with 1
			stmt.setInt(1, pizza_id);
			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(stmt, conn); // Suljetaan statement ja yhteys
		}
	}
	/**
	 * Saadan kaikki tietyn pizzan t‰ytteet sen id numeron perusteella
	 * @param pizza_id
	 * @return
	 */
	public ArrayList<Tayte> getTaytteetByPizzaId(int pizza_id) {

		Connection conn = null;
		PreparedStatement stmt = null;
		PreparedStatement findstmt = null;

		ArrayList<Tayte> tt = new ArrayList<Tayte>();
		int num = 0;
		try {
			conn = getConnection();
			findstmt = conn
					.prepareStatement("select pizza_id, count(*) as 'num' from Pizzan_Tayte where pizza_id=?;");
			findstmt.setInt(1, pizza_id);
			ResultSet rs1 = findstmt.executeQuery();

			if (rs1.next()) {
				num = rs1.getInt("num");

			}

			stmt = conn
					.prepareStatement("select * from Pizzan_Tayte where pizza_id=?");
			stmt.setInt(1, pizza_id);
			ResultSet rs = stmt.executeQuery();
			for (int i = 0; i < num; i++) {
				if (rs.next()) {

					Tayte tayte = new Tayte();

					tayte.setTayte_id(rs.getInt("tayte_id"));
					tt.add(tayte);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt, conn);
		}

		return tt;
	}

	@SuppressWarnings({ "null", "unused" })
	public ArrayList<Tayte> getTaytteetByPizza() {

		Connection conn = null;
		PreparedStatement stmt = null;
		PreparedStatement findstmt = null;

		ArrayList<Pizza> pizzat = null;

		int pizza_id = 0;
		for (int in = 0; in < pizzat.size(); in++) {

			pizza_id = pizzat.get(in).getPizza_id();

			ArrayList<Tayte> tt = new ArrayList<Tayte>();
			int num = 0;
			try {
				conn = getConnection();
				findstmt = conn
						.prepareStatement("select pizza_id, count(*) as 'num' from Pizzan_Tayte where pizza_id=?;");
				findstmt.setInt(1, pizza_id);
				ResultSet rs1 = findstmt.executeQuery();

				if (rs1.next()) {
					num = rs1.getInt("num");

				}

				stmt = conn
						.prepareStatement("select * from Pizzan_Tayte where pizza_id=?");
				stmt.setInt(1, pizza_id);
				ResultSet rs = stmt.executeQuery();
				for (int i = 0; i < num; i++) {
					if (rs.next()) {

						Tayte tayte = new Tayte();

						tayte.setTayte_id(rs.getInt("tayte_id"));
						tt.add(tayte);
					}
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(stmt, conn);
			}

			return tt;
		}
		return null;
	}

}
