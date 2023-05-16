
import ch.fhnw.galacticenergies.data.DBConnection;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testing of the Database
 * @version 1.0
 */
public class dbTest {
    Connection conn;

    /**
     * Setup of the DB connection
     */
    @BeforeEach
    public void init(){
        DBConnection c = new DBConnection();
        conn = c.getConnection();
    }

    /**
     * Testing the Edge cases and a default case of the EnergyData table
     * @throws SQLException in case of an error
     */
    @Test
    public void testEnergydata() throws SQLException {
        ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM energydata WHERE idenergydata = 15");
        ResultSet rs2 = conn.createStatement().executeQuery("SELECT * FROM energydata WHERE idenergydata = 20");
        ResultSet rs3 = conn.createStatement().executeQuery("SELECT * FROM energydata WHERE idenergydata = 31");

        while (rs.next()) {
            assertEquals("Smartphone", rs.getString("devicename"));
            assertEquals(20, rs.getInt("power"));
        }
        while (rs2.next()) {
            assertEquals("Dishwasher", rs2.getString("devicename"));
            assertEquals(1250, rs2.getInt("power"));
        }
        while (rs3.next()) {
            assertEquals("use WhatsApp", rs3.getString("devicename"));
            assertEquals(1, rs3.getInt("power"));
        }

    }

    /**
     * Testing of the totalpower table
     * @throws SQLException in case of an Error
     */
    @Test
    public void testTotalpower() throws SQLException {
        double power = 1000;
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO totalpower(producedpower, date) VALUES (?,?)");
        stmt.setDouble(1, power);
        stmt.setDate(2, Date.valueOf(LocalDate.now()));
        stmt.executeUpdate();

        ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM totalpower WHERE producedpower = " + power);

        while (rs.next()) {
            assertEquals(power, rs.getDouble("producedpower"));
            assertEquals(Date.valueOf(LocalDate.now()), rs.getDate("date"));
        }

        PreparedStatement stmtDel = conn.prepareStatement("DELETE FROM totalpower WHERE producedpower = " + power);
        stmtDel.executeUpdate();



    }

    @AfterEach
    public void teardown() throws SQLException {
        conn.close();
    }
}
