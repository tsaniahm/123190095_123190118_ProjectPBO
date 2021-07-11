package cashier.model;

import cashier.dbconnect.DBConnect;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReceiptModel extends DataModel {
    
    DBConnect connect = new DBConnect();
    
    public void insertReceipt(Object[][] data, int tdata) {
        int count = super.countReceipt() + 1;
        for(int i=0;i<tdata;i++) {
            try {
                String query = "INSERT INTO `receipt`(`id_receipt`, `id_product`, `quantity`) VALUES ('"+count+"','"+data[i][0]+"','"+data[i][4]+"')";
                connect.statement = connect.connection.createStatement();
                connect.statement.executeUpdate(query);
            }catch (SQLException ex) {
                Logger.getLogger(ReceiptModel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }    
    }
}
