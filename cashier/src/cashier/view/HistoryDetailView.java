
package cashier.view;

import cashier.control.HistoryControl;
import cashier.dbconnect.DBConnect;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;

public class HistoryDetailView extends JFrame implements ActionListener{
    DBConnect connect = new DBConnect();
    
    private final JLabel lid, idreceipt, ltotal, total;
    private final JButton back;
    private final JTable table;
    
    JScrollPane scrollPane;
    Object namaKolom[] = {"id product","Product Name", "quantity","price"};
    String [][] dataa;
    int totall,i=0;
    String id;
    
    public HistoryDetailView(String[][] data){
        
        id = data[0][4];
        dataa = data;

        setLayout(null);
        setSize(450,350);
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);

        lid = new JLabel("ID RECEIPT : ");
        ltotal = new JLabel("Total : Rp. ");
        total = new JLabel(Integer.toString(count()));
        idreceipt = new JLabel(data[0][4]);
        
        table = new JTable(data,namaKolom);
        scrollPane = new JScrollPane(table);
        back = new JButton ("Back");
    
        add(lid);
        add(idreceipt);
        add(scrollPane);
        add(ltotal);
        add(total);
        add(back);
        lid.setBounds(30, 10, 130, 25);
        idreceipt.setBounds(110, 10, 130, 25);
        scrollPane.setBounds(30, 40, 360, 150);
        ltotal.setBounds(30, 200, 125, 25);
        total.setBounds(100, 200, 125, 25);
        back.setBounds(30, 250, 125, 25);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        back.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == back){
            dispose();
           HistoryControl history = new HistoryControl();
           history.readDataHistory();
        }
    }
    
    private int count(){
        while(i < countData()){
            totall = totall +  (Integer.valueOf(dataa[i][3])*Integer.valueOf(dataa[i][2]));
            i++;
        }
        return totall;
    }

    private int countData() {
        int jmlData = 0; 
        try{
            connect.statement = connect.connection.createStatement();
            String query = "Select * from `receipt` where id_receipt = '"+id+"'";
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
}
