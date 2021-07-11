
package cashier.model;

import cashier.dbconnect.DBConnect;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ProductModel {
    
    DBConnect connect = new DBConnect();
    String data[][] = new String[500][3];
    
    public void createData(String[] data){
        try {
            String query = "INSERT INTO `products`(`id_product`, `product_name`, `price`) VALUES ('"+data[0]+"','"+data[1]+"','"+data[2]+"')";

            connect.statement = connect.connection.createStatement();
            connect.statement.executeUpdate(query);
                        
            System.out.println("Insert Successful");
            JOptionPane.showMessageDialog(null,"Insert successfull !!");            
           } catch (Exception ex){
               System.out.println(ex.getMessage());
           }
    }
    
    public int countData(){ 
        int jmlData = 0; 
        try{
            connect.statement = connect.connection.createStatement();
            String query = "Select * from `products`";
            ResultSet resultSet = connect.statement.executeQuery(query); 
            while(resultSet.next()){ 
                jmlData++;
            }
            return jmlData;
        }catch(SQLException e){
            System.out.println(e.getMessage());
            System.out.println("SQL Error");
            return 0;
        }
    }
        
    public String[][] readData(){ 
        try{
            int jmlData = 0; 
            String data[][] = new String[countData()][3];
            String query = "Select * from `products`"; 
            ResultSet resultSet = connect.statement.executeQuery(query); 
            while(resultSet.next()){
                data[jmlData][0] = resultSet.getString("id_product"); 
                data[jmlData][1] = resultSet.getString("product_name"); 
                data[jmlData][2] = resultSet.getString("price");
                jmlData++;
            }
            return data;
        }catch(SQLException e){
            System.out.println(e.getMessage());
            System.out.println("SQL Error");
            return null;
        }
    }
    
    public String[][] readData(String id){ 
        try{
            int jmlData = 0; 
            String data[][] = new String[countData()][3];
            String query = "Select * from `products` where id_product = '"+id+"'"; 
            ResultSet resultSet = connect.statement.executeQuery(query); 
            while(resultSet.next()){
                data[jmlData][0] = resultSet.getString("id_product"); 
                data[jmlData][1] = resultSet.getString("product_name"); 
                data[jmlData][2] = resultSet.getString("price");
                jmlData++; 
            }
            return data;
        }catch(SQLException e){
            System.out.println(e.getMessage());
            System.out.println("SQL Error");
            return null;
        }
    }
    
    public void updateData(String [] data){
        try {
             String query = "UPDATE `products` set `product_name` = '"+data[1]+"', `price` = '"+data[2]+"' where `id_product` = '"+data[0]+"'";

             connect.statement = connect.connection.createStatement();
             connect.statement.executeUpdate(query);
                        
             System.out.println("Update Successful");
             JOptionPane.showMessageDialog(null,"Update Successful !!");         
            } catch (Exception ex){
                System.out.println(ex.getMessage());
            } 
    }
    
    public void deleteData(String data){
        try {
            String query = "DELETE FROM `products` WHERE `id_product` = '"+data+"'";

            connect.statement = connect.connection.createStatement();
            connect.statement.executeUpdate(query);

            System.out.println("Delete Successful!");
                JOptionPane.showMessageDialog(null,"Delete Successful!");
            } catch (Exception ex){
                System.out.println(ex.getMessage());
            }
    }
}
