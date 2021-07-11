
package cashier.model;

import cashier.dbconnect.DBConnect;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HistoryModel {

    DBConnect connect =  new DBConnect();
    public int countData(){ 
        int jmlData = 0; 
        try{
            connect.statement = connect.connection.createStatement();
            String query = "Select * from `receipt`";
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
            String data[][] = new String[countData()][1];
            String query = "Select * from `receipt`"; 
            ResultSet resultSet = connect.statement.executeQuery(query);
            while(resultSet.next()){ 
                
                String a = resultSet.getString("id_receipt"); 
                if(jmlData == 0 ){
                    data[jmlData][0] = a; 
                    jmlData++; 
                }else{
                if( !a.equals(data[jmlData-1][0])){ 
                     data[jmlData][0] = a;
                     jmlData++; 
                }
                }  
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
            String data[][] = new String[countData()][6];
            String query = "select r.id_receipt, r.id_product, r.quantity, p.product_name, p.price from receipt r left join products p on r.id_product = p.id_product where r.id_receipt ='"+ id+"'"; 
             
            ResultSet resultSet = connect.statement.executeQuery(query);
            while(resultSet.next()){ 
                data[jmlData][0] = resultSet.getString("id_product"); 
                data[jmlData][1] = resultSet.getString("product_name"); 
                data[jmlData][2] = resultSet.getString("quantity");
                data[jmlData][3] = resultSet.getString("price");
                data[jmlData][4] = resultSet.getString("id_receipt");
                jmlData++; 
            }
            return data;
        }catch(SQLException e){
            System.out.println(e.getMessage());
            System.out.println("SQL Error");
            return null;
        }
    }
}
