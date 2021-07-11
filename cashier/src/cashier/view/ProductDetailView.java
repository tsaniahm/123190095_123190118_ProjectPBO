
package cashier.view;

import cashier.control.ProductControl;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.Color;

public class ProductDetailView extends JFrame implements ActionListener{
    JLabel title, lcode, lname, lprice;
    JTextField incode, inname, inprice;
    JButton back, update, delete;

    public ProductDetailView(String[][] data){
        title = new JLabel("INPUT PRODUCTS");
        lcode = new JLabel("id product");
        lname = new JLabel("product name");
        lprice = new JLabel("price");

        incode = new JTextField(data[0][0]);
        inname = new JTextField(data[0][1]);
        inprice = new JTextField(data[0][2]);

        back = new JButton("Back");
        update = new JButton("Update product");
        delete = new JButton("Delete Product");

        setLayout(null);
        setSize(400,300);
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);

        add(title);
        add(lcode);
        add(lname);
        add(lprice);
        add(incode);
        add(inname);
        add(inprice);
        add(back);
        add(update);
        add(delete);
    
        title.setBounds(130, 30,130,25);
        lcode.setBounds(80, 70, 130,25);
        lname.setBounds(80, 100, 130,25);
        lprice.setBounds(80, 130, 130,25);
        incode.setBounds(160, 70, 135,25);
        inname.setBounds(160,100,135,25);
        inprice.setBounds(160,130,135,25);
        
        update.setBounds(50, 170, 135, 25);
        delete.setBounds(200, 170, 135, 25);
        back.setBounds(20,220,350,25);
        
        update.setBackground(Color.blue);
        delete.setBackground(Color.red);
        update.setForeground(Color.white);
        delete.setForeground(Color.white);
        
        back.addActionListener(this);
        update.addActionListener(this);
        delete.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()== back){
            try {     
                  ProductControl product = new ProductControl();
                  product.readDataProducts();
                  dispose();
                } catch (Exception ex){
                    System.out.println(ex.getMessage());
                }
        }
        else if(e.getSource()== update){
            if (incode.getText().equals("") || inname.getText().equals("") || inprice.getText().equals("")) {
                JOptionPane.showMessageDialog(null,"Input is Required!!");
            }
            else{
            try {
                 String[] dataUpdate = {
                    incode.getText(), inname.getText(), inprice.getText()
                 };
                  ProductControl product = new ProductControl();
                  product.updateDataProducts(dataUpdate);
                  dispose();
                } catch (Exception ex){
                    System.out.println(ex.getMessage());
                }
            }
        }
        else if(e.getSource()== delete){
            if (incode.getText().equals("") || inname.getText().equals("") || inprice.getText().equals("")) {
                JOptionPane.showMessageDialog(null,"Input is Required!!");
            }
            else{
            try{
                int opsi = JOptionPane.showConfirmDialog(null, "Benarkah anda ingin menghapus data ini ?");
                switch(opsi){
                    case JOptionPane.YES_OPTION -> {
                        String dataDelete = incode.getText();
                        ProductControl product = new ProductControl();
                        product.deleteDataProducts(dataDelete);
                        dispose();
                    }
                    default -> {
                    }
                 }
            }catch(HeadlessException ex) {
                System.out.println(ex.getMessage());
            }
           }
        }
    }  
}

