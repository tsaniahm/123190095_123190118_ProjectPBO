package cashier.model;

import cashier.dbconnect.DBConnect;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataModel {
    
    DBConnect connect = new DBConnect();
    
    public int countReceipt() {
        int count = 0;
        try {
            connect.statement = connect.connection.createStatement();
            String query = "SELECT * FROM `receipt`";
            ResultSet resultSet = connect.statement.executeQuery(query);
            while(resultSet.next()) {
                count = resultSet.getInt("id_receipt");
            }
        }catch (SQLException ex) {
            Logger.getLogger(DataModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    } 
}
