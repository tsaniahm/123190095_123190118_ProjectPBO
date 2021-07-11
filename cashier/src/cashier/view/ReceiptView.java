package cashier.view;

import javax.swing.*;
import javax.swing.table.*;

public class ReceiptView extends JFrame {
    
    private int tprice = 0; Object[][] d = new Object[999][3];
    
    JLabel title = new JLabel("RECEIPT");
    JLabel total = new JLabel("total");
    JLabel price = new JLabel();
      
    String[] header = {"qty", "product name", "price"};
    Object[][] data = new Object[999][3];
    JTable product = new JTable(new DefaultTableModel(header, 0));
    DefaultTableModel tablep  = new DefaultTableModel() {
        @Override
         public boolean isCellEditable(int row, int column) {
             return false;
         }
    };
    JScrollPane scrollpane = new JScrollPane(product);
    
    public void viewReceipt(Object[][] data, int tdata) {
        setSize(350, 350);
        setLayout(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
       
        add(title);
        add(scrollpane);
        add(total);
        add(price);
        
        title.setBounds(145, 30, 100, 20);
        total.setBounds(165, 280, 100, 20);
        price.setBounds(200, 280, 100, 20);
        scrollpane.setBounds(50, 70, 240, 200); 
        product.getColumnModel().getColumn(0).setPreferredWidth(20);
        tablep = (DefaultTableModel) product.getModel();
        
        for(int i=0; i<tdata; i++) {
            d[i][0] = data[i][4];
            d[i][1] = data[i][1];
            d[i][2] = data[i][2];
            tablep.addRow(d[i]);
            tprice+=(int)data[i][2];
        }
        price.setText(String.valueOf(tprice));
    }
}
