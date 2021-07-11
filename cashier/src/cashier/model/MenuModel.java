package cashier.model;

import cashier.dbconnect.DBConnect;
import java.sql.*;
import java.util.logging.*;

public class MenuModel {
    
    DBConnect connect = new DBConnect();
    
    public Object[] searchProduct(String code, int qty) {
        boolean found = false; String scode; Object[] data = new Object[5];
        try {
            String qsproduct = "SELECT * FROM `products`";
            connect.statement = connect.connection.createStatement();
            ResultSet resultSet = connect.statement.executeQuery(qsproduct);
            while(resultSet.next() && !found){
                scode = resultSet.getString("id_product");
                if(code.equals(scode)) {
                    data[0] = code;
                    data[1] = resultSet.getString("product_name");
                    data[2] = qty * resultSet.getInt("price");
                    data[3] = resultSet.getInt("price");
                    data[4] = qty;
                    found = true;
                }
            }
            if(found) return data;
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(MenuModel.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
}
