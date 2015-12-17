package pizzicato_ibizza.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
 
 
import pizzicato_ibizza.model.Tayte;
import pizzicato_ibizza.model.dao.DataAccessObject;
import pizzicato_ibizza.model.dao.TayteDAO;
 
 
 
 
 
public class TayteDAO extends DataAccessObject {
 
        /**
         * lisää Henkilön tiedot tietokantaan
         *
         * @param henkilo Henkilo-luokan olio
         * @throws SQLException
         */
        public void addTayte(Tayte Tayte) throws SQLException {
                Connection connection = null;
                PreparedStatement stmtInsert = null;
       
 
                try {
                        // Luodaan yhteys ja aloitetaan transaktio:
                        connection = getConnection();
                        //Luodaan uusi henkilo tietokantaan:
                        String sqlInsert = "INSERT INTO Tayte(tayte_nimi, hinta) VALUES (?,?)";
                        stmtInsert = connection.prepareStatement(sqlInsert);
                        stmtInsert.setString(1, Tayte.getTayte_nimi());
                        stmtInsert.setDouble(2, Tayte.getHinta());
                        stmtInsert.executeUpdate();
                       
                } catch (SQLException e) {
                        throw new RuntimeException(e);
                } finally {
                        close(stmtInsert, connection); // Suljetaan statement ja yhteys
                }
        }
 
 
       /**
        * Haetaan kaikki täytteet tietokannasta
        * @return
        */
 
        public ArrayList<Tayte> findAll() {    
                Connection conn = null;
                PreparedStatement stmt = null;
                ResultSet rs = null;
                ArrayList<Tayte> Taytteet = new ArrayList<Tayte>();
                Tayte Tayte = null;
                try {
                        // Luodaan yhteys
                        conn = getConnection();
                        // Luodaan komento: haetaan kaikki rivit henkilo-taulusta
                        String sqlSelect = "SELECT tayte_id, tayte_nimi, hinta, status FROM Tayte;";
                        // Valmistellaan komento:
                        stmt = conn.prepareStatement(sqlSelect);
                        // Lähetetään komento:
                        rs = stmt.executeQuery(sqlSelect);
                        // Käydään tulostaulun rivit läpi ja luetaan readHenkilo()-metodilla:
                        while (rs.next()) {
                                Tayte = readTayte(rs);
                                // lisätään henkilö listaan
                                Taytteet.add(Tayte);
                        }
                } catch (SQLException e) {
                        throw new RuntimeException(e);
                } finally {
                        close(rs, stmt, conn); // Suljetaan
                }
       
                return Taytteet;
        }
       
       
       
        /**
         * Lukee tietokannasta taulusta yhden tietorivin (henkilon tiedot). Luo ja palauttaa tietojen perusteella Henkilo-tyyppisen olion
         *
         * @param rs tietokannasta kyselyllä haettu tulostaulu
         * @return Henkilo henkilo-olio
         */
        private Tayte readTayte(ResultSet rs) {
                try {
                        // Haetaan yhden henkilön tiedot kyselyn tulostaulun (ResultSet-tyyppinen rs-olion) aktiiviselta tietoriviltä
                        int tayte_id = rs.getInt("tayte_id");
                        String tayte_nimi = rs.getString("tayte_nimi");
                        double hinta = rs.getDouble("hinta");
                        boolean status = rs.getBoolean("status");
                       
               
                        //  Luodaan ja palautetaan uusi henkilo
                        return new Tayte(tayte_id, tayte_nimi, hinta, status);
                } catch (SQLException e) {
                        throw new RuntimeException(e);
                }
        }
        /**
         * Poistetaan täyte id:lla
         * @param tayte_id
         */
        public void deleteTayte(int tayte_id) {
                Connection conn = null;
                PreparedStatement stmt = null;
       
        try {
                conn = getConnection();
                String sqlInsert = "delete from Tayte where tayte_id=?";
            stmt = conn.prepareStatement(sqlInsert);
            // Parameters start with 1
            stmt.setInt(1, tayte_id);
            stmt.executeUpdate();
 
                } catch (SQLException e) {
                        throw new RuntimeException(e);
                } finally {
                        close(stmt, conn); // Suljetaan statement ja yhteys
                }
    }
    /**
     * Päivitetään tietyn täytteen tietoja  
     * @param Tayte
     */
    public void updateTayte(Tayte Tayte) {
                Connection conn = null;
                PreparedStatement stmt = null;
        try {
                conn = getConnection();
                String sqlInsert = "UPDATE Tayte set tayte_nimi=?, hinta=? WHERE tayte_id=?";
                stmt = conn.prepareStatement(sqlInsert);
            // Parameters start with 1
           
                stmt.setString(1, Tayte.getTayte_nimi());
            stmt.setDouble(2, Tayte.getHinta());
            stmt.setInt(3, Tayte.getTayte_id());
           
 
            stmt.executeUpdate();
 
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
                close(stmt, conn);
        }
       
    }
    /**
     * Päivitetään tietyn täytteen statusta
     * @param tayte
     */
    public void updateTayteStatus(Tayte tayte) {
                Connection conn = null;
                PreparedStatement stmt = null;
        try {
                conn = getConnection();
                String sqlInsert = "update Tayte set status=? where tayte_id=?";
           stmt = conn.prepareStatement(sqlInsert);
            // Parameters start with 1
           
                stmt.setBoolean(1, tayte.getStatus());
            stmt.setInt(2, tayte.getTayte_id());
 
           
            stmt.executeUpdate();
 
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
                close(stmt, conn);
        }// Suljetaan
       
    }
   
    	/**
    	 * Haetaan täyte id numerolla
    	 * @param tayte_id
    	 * @return
    	 */
        public Tayte getTayteById(int tayte_id) {
       
                Connection conn = null;
                PreparedStatement stmt = null;
       
                String tayte_nimi = null;
                double hinta = 0;
                boolean status = true;
                Tayte tayte = new Tayte(tayte_id, tayte_nimi, hinta, status);
       
        try {
                conn = getConnection();
            stmt = conn.
                    prepareStatement("select * from Tayte where tayte_id=?");
            stmt.setInt(1, tayte_id);
            ResultSet rs = stmt.executeQuery();
 
            if (rs.next()) {
                tayte.setTayte_id(rs.getInt("tayte_id"));
                tayte.setTayte_nimi(rs.getString("tayte_nimi"));
                tayte.setHinta(rs.getDouble("hinta"));
                tayte.setStatus(rs.getBoolean("status"));
               
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
                close(stmt, conn);
        }
 
        return tayte;
    }
       
 
}