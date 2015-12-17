package pizzicato_ibizza.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;






import pizzicato_ibizza.model.Asiakas;
import pizzicato_ibizza.model.dao.DataAccessObject;
import pizzicato_ibizza.model.dao.AsiakasDAO;

public class AsiakasDAO extends DataAccessObject {
	
	/**
	 * Asiakkaan lisääminen tietokantaan
	 * @param asiakas
	 * @throws SQLException
	 */
	public void addAsiakas(Asiakas asiakas) throws SQLException {
		Connection connection = null;
		PreparedStatement stmtInsert = null;
	

		try {
			// Luodaan yhteys ja aloitetaan transaktio:
			connection = getConnection();
			//Luodaan uusi henkilo tietokantaan:
			String sqlInsert = "INSERT INTO Asiakas(asiakas_id, etunimi, sukunimi, email, puhelin_nro, katuosoite, postinumero, kayttajatunnus, salasana) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			stmtInsert = connection.prepareStatement(sqlInsert);
			stmtInsert.setInt(1, asiakas.getAsiakas_id());
			stmtInsert.setString(2, asiakas.getEtunimi());
			stmtInsert.setString(3, asiakas.getSukunimi());
			stmtInsert.setString(4, asiakas.getEmail());
			stmtInsert.setString(5, asiakas.getPuhelin_nro());
			stmtInsert.setString(6, asiakas.getKatuosoite());
			stmtInsert.setString(7, asiakas.getPostinumero());
			stmtInsert.setString(8, asiakas.getKayttajatunnus());
			stmtInsert.setString(9, asiakas.getSalasana());
			stmtInsert.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(stmtInsert, connection); // Suljetaan statement ja yhteys
		}
	}


	
	/**
	 * Haetaan kaikki asiakkaat tietokannasta
	 * @return
	 */
	public ArrayList<Asiakas> findAll() {	
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Asiakas> asiakkaat = new ArrayList<Asiakas>();
		Asiakas asiakas = null; 
		try {
			// Luodaan yhteys
			conn = getConnection();
			// Luodaan komento: haetaan kaikki rivit henkilo-taulusta
			String sqlSelect = "SELECT asiakas_id, etunimi, sukunimi, email, puhelin_nro, katuosoite, postinumero, kayttajatunnus, salasana FROM Asiakas;";
			// Valmistellaan komento:
			stmt = conn.prepareStatement(sqlSelect);
			// Lähetetään komento:
			rs = stmt.executeQuery(sqlSelect);
			// Käydään tulostaulun rivit läpi ja luetaan readHenkilo()-metodilla:
			while (rs.next()) {
				asiakas = readAsiakas(rs);
				// lisätään henkilö listaan
				asiakkaat.add(asiakas);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(rs, stmt, conn); // Suljetaan
		}
	
		return asiakkaat;
	}
	
	
	
	/**
	 * Lukee tietokannasta taulusta yhden tietorivin (henkilon tiedot). Luo ja palauttaa tietojen perusteella Henkilo-tyyppisen olion
	 * 
	 * @param rs tietokannasta kyselyllä haettu tulostaulu
	 *            
	 * @return Henkilo henkilo-olio
	 */
	private Asiakas readAsiakas(ResultSet rs) {	
		try {
			// Haetaan yhden henkilön tiedot kyselyn tulostaulun (ResultSet-tyyppinen rs-olion) aktiiviselta tietoriviltä
			int asiakas_id = rs.getInt("asiakas_id");
			String etunimi = rs.getString("etunimi");
			String sukunimi = rs.getString("sukunimi");
			String email = rs.getString("email");
			String puhelin_nro = rs.getString("puhelin_nro");
			String katuosoite = rs.getString("katuosoite");
			String postinumero = rs.getString("postinumero");
			String kayttajatunnus = rs.getString("kayttajatunnus");
			String salasana = rs.getString("salasana");
			
			
		
			//  Luodaan ja palautetaan uusi henkilo
			return new Asiakas(asiakas_id, etunimi, sukunimi,
					email, puhelin_nro, katuosoite,
					postinumero, kayttajatunnus, salasana);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Tässä tarkistetaan löytyykö asiakas tietokannasta ja onko sen tunnus ja salasana täsmä
	 * @param asiakas
	 * @return
	 */
	public static Asiakas login(Asiakas asiakas){
		
		Statement stmt = null;
		ResultSet rs = null;
		Connection conn = null;
		String tunnus = asiakas.getKayttajatunnus();
		String salasana = asiakas.getSalasana();
		
		String searchQuery = "select * from Asiakas where kayttajatunnus='"+tunnus+"'AND salasana='"+salasana+"'";
		
		System.out.println("Tunnuksesi on "+tunnus);
		System.out.println("Salasanasi on "+salasana);
		System.out.println(searchQuery);
		
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(searchQuery);
			boolean more = rs.next();
			if(!more){
				System.out.println("Sorry, you are not a registered user!");
				asiakas.setValid(false);
			}else if(more){
				String nimi = rs.getString("etunimi");
				System.out.println("Welcome "+nimi);
				asiakas.setValid(true);
			}
			
		}catch(Exception ex){
			System.out.println("Log in failed: An Exception has occured!"+ex);
		}finally{
			if(rs!=null){
				try{
					rs.close();
				}catch(Exception e){
					rs=null;
				}
			}
			if(stmt!=null){
				try{
					stmt.close();
				}catch(Exception e){
					stmt=null;
				}
			}
			if (conn!=null){
				try{
					conn.close();
				}catch(Exception e){
					conn=null;
				}
			}
		}
		
		return asiakas;
		
	}

}