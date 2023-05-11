package ch.fhnw.galacticenergies.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SelectTest {

    public static void main (String[] args) {
        SelectTest s = new SelectTest();
        s.selectStuff();
    }

    public void selectStuff () {
        DBConnection c = new DBConnection();
        Connection conn = c.getConnection();
        String sqlSelectAllPersons = "SELECT * FROM energydata";
        try {
            PreparedStatement ps = conn.prepareStatement(sqlSelectAllPersons);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                long id = rs.getLong("idenergydata");
                String name = rs.getString("devicename");
                String lastName = rs.getString("power");
            }
            // do something with the extracted data...

        } catch (Exception e) {
            System.err.println(e);
        }
    }
}

