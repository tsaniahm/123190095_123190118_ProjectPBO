
package cashier.view;

import cashier.control.ProductControl;
import cashier.dbconnect.DBConnect;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ProductView implements ActionListener{
    public String id_product, product_name, price, id;
    int jmlData;
    String data[][] = new String[500][3];
    DBConnect connect = new DBConnect();    

    JFrame window = new JFrame("PRODUCTS");
    JTable tabel;
    
    JLabel title = new JLabel("INPUT PRODUCTS");
    JLabel warning = new JLabel("*Click product on table to edit or delete");
    JLabel lcode = new JLabel("id product");
    JLabel lname = new JLabel("product name");
    JLabel lprice = new JLabel("price");
    
    JTextField incode = new JTextField();
    JTextField inname = new JTextField();
    JTextField inprice = new JTextField();
   
    DefaultTableModel tableModel;
    JScrollPane scrollPane;
    Object namaKolom[] = {"id product","product name","price"};
    
    JButton add = new JButton("Add New Product");
    JButton back = new JButton("Back");
    
    public  ProductView(String[][] data){
        id = data[0][0];
        window.setLayout(null);
        window.setSize(600,600);
        window.setVisible(true);
        window.setLocationRelativeTo(null);
        window.setResizable(false);
        
        tabel = new JTable(data,namaKolom); 
        scrollPane = new JScrollPane(tabel);
        window.add(scrollPane);
        window.add(title);
        window.add(warning);
        window.add(lcode);
        window.add(lname);
        window.add(lprice);
        window.add(incode);
        window.add(inname);
        window.add(inprice);
        window.add(add);
        window.add(back);
        
        scrollPane.setBounds(50, 35, 500, 250);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        
        warning.setBounds(50, 290, 400, 25);
        title.setBounds(230, 340,130,25);
        lcode.setBounds(180, 370, 130,25);
        lname.setBounds(180, 400, 130,25);
        lprice.setBounds(180, 430, 130,25);
        incode.setBounds(280, 370, 135,25);
        inname.setBounds(280,400,135,25);
        inprice.setBounds(280,430,135,25);
        
        add.setBackground(Color.blue);
        add.setForeground(Color.white);
        
        add.setBounds(230, 470, 135, 25);
        back.setBounds(230, 510, 135, 25);
        

        tabel.setCursor((Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)));
        tabel.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
               
                public void valueChanged(ListSelectionEvent event) {
                    window.dispose();
                    id = tabel.getValueAt(tabel.getSelectedRow(), 0).toString();
                    ProductControl product = new ProductControl();
                    product.readDataProducts(id);
                }
        });
        add.addActionListener(this);
        back.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()== add){
            if (incode.getText().equals("") || inname.getText().equals("") || inprice.getText().equals("")) {
                JOptionPane.showMessageDialog(null,"Input is Required!!");
            }
            else{
            try {
                 String[] dataInput = {
                    incode.getText(), inname.getText(), inprice.getText()
                 };
                        
                  ProductControl product = new ProductControl();
                  product.createDataProducts(dataInput);
                  window.dispose();
                } catch (Exception ex){
                    System.out.println(ex.getMessage());
                }
            }
        }else if(e.getSource() == back){
            window.dispose();
        }
    }  
}
