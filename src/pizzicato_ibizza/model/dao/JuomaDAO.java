package pizzicato_ibizza.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import pizzicato_ibizza.model.Juoma;

public class JuomaDAO extends DataAccessObject {
	/**
	 * Haetaan kaikki joumat tietokannasta
	 * @return
	 */
	public ArrayList<Juoma> findAll() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Juoma> juomalista = new ArrayList<Juoma>();
		Juoma juoma = null;
		try {
			conn = getConnection();
			String sqlSelect = "SELECT juoma_id, juoma_nimi, koko, hinta, status FROM Juoma;";
			stmt = conn.prepareStatement(sqlSelect);
			rs = stmt.executeQuery(sqlSelect);
			while (rs.next()) {
				juoma = readJuoma(rs);
				juomalista.add(juoma);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			close(rs, stmt, conn);
		}
		return juomalista;
	}
	/**
	 * Asetetaan tietokannasta saadut juoman tiedot muuttujiin
	 * @param rs
	 * @return
	 */
	// @SuppressWarnings("unused")
	private Juoma readJuoma(ResultSet rs) {
		try {
			int juoma_id = rs.getInt("juoma_id");
			String juoma_nimi = rs.getString("juoma_nimi");
			String koko = rs.getString("koko");
			double hinta = rs.getDouble("hinta");
			boolean status = rs.getBoolean("status");
			return new Juoma(juoma_id, juoma_nimi, koko, hinta, status);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * Etsit‰‰n tietty juoma id numerolla
	 * @param juoma_id
	 * @return
	 */
	public Juoma getJuomaById(int juoma_id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		String juoma_nimi = "";
		String koko = "";
		double hinta = 0;
		boolean status = true;
		Juoma juoma = new Juoma(juoma_id, juoma_nimi, koko, hinta, status);

		try {
			conn = getConnection();
			stmt = conn
					.prepareStatement("SELECT * FROM Juoma WHERE juoma_id=?;");
			stmt.setInt(1, juoma_id);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				juoma.setJuoma_id(rs.getInt("juoma_id"));
				juoma.setJuoma_nimi(rs.getString("juoma_nimi"));
				juoma.setKoko(rs.getString("koko"));
				juoma.setHinta(rs.getDouble("hinta"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(stmt, conn);
		}
		return juoma;

	}


}



