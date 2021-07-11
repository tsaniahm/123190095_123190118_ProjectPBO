package cashier.view;

import cashier.control.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

public class MenuView extends JFrame implements ActionListener {
    
    private String code; int qty, tdata = 0, tprice = 0;
    
    JLabel title = new JLabel("CASHIER");
    JLabel codep = new JLabel("code product");
    JLabel quantityp = new JLabel("product quantity");
    JLabel total = new JLabel("total");
    JLabel price = new JLabel();
    
    JButton add = new JButton("add");
    JButton products = new JButton("products");
    JButton history = new JButton("history");
    JButton pay = new JButton("pay");
    JButton cancel = new JButton("cancel");
    
    JTextField incode = new JTextField();
    JTextField inqty = new JTextField();
    
    String[] header = {"qty", "product name", "price"};
    Object[][] data = new Object[999][5];
    JTable product = new JTable((new DefaultTableModel(header, 0)));
    DefaultTableModel tablep  = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };   
    JScrollPane scrollpane = new JScrollPane(product);
    
    public void viewMenu() {
        setSize(350, 500);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        
        add(title);
        add(codep);
        add(incode);
        add(quantityp);
        add(inqty);
        add(add);
        add(products);
        add(scrollpane);
        add(total);
        add(price);
        add(history);
        add(pay);
        add(cancel);
        
        title.setBounds(145, 30, 100, 20);
        codep.setBounds(50, 80, 100, 20);
        incode.setBounds(160, 80, 130, 20);
        quantityp.setBounds(50, 110, 100, 20);
        inqty.setBounds(160, 110, 130, 20);
        products.setBounds(50, 145, 100, 20);
        add.setBounds(190, 145, 100, 20);
        total.setBounds(165, 335, 100, 20);
        price.setBounds(200, 335, 100, 20);
        history.setBounds(125, 400, 100, 20);
        cancel.setBounds(50, 360, 100, 20);
        pay.setBounds(190, 360, 100, 20);
        scrollpane.setBounds(50, 180, 240, 150);
        
        add.addActionListener(this);
        products.addActionListener(this);
        history.addActionListener(this);
        pay.addActionListener(this);
        cancel.addActionListener(this);
        tablep = (DefaultTableModel) product.getModel();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == add) {
            Object[][] d = new Object[1][3];
            product.getColumnModel().getColumn(0).setPreferredWidth(20);
            try{
                code = incode.getText();
                qty = Integer.valueOf(inqty.getText());
                if (qty<=0) throw new Exception("positive numbers only");
                MenuControl menu = new MenuControl(); 
                data[tdata] = menu.searchProduct(code, qty);
                if (data[tdata] == null) throw new Exception("product is not found");
                tprice+=(int)data[tdata][2];
                price.setText(String.valueOf(tprice));
                if(sameProduct() == false) {
                    d[0][0] = data[tdata][4];
                    d[0][1] = data[tdata][1];
                    d[0][2] = data[tdata][2];
                    tablep.addRow(d[0]);
                    tdata++;
                }                
            }catch(Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        }
        else if (e.getSource() == pay) {
            try{
                if (tprice == 0) throw new IllegalArgumentException("payment failed");
                int paid;
                paid = Integer.valueOf(JOptionPane.showInputDialog(null, "total : " + tprice));
                if(paid<tprice) throw new IllegalArgumentException("payment failed");
                else if(paid>tprice) {
                    int ex = paid-tprice;
                    throw new Exception("payment success. exchange : " + ex);
                }else if(tprice == paid) throw new Exception("payment success");
            }catch(IllegalArgumentException er) {
                JOptionPane.showMessageDialog(this, er.getMessage());
            }catch(Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
                ReceiptControl insert = new ReceiptControl();
                insert.insertReceipt(data, tdata);
                reset();
            }
        }
        else if(e.getSource() ==  products){
            try{
                ProductControl product = new ProductControl();
                product.readDataProducts();
            }catch(Exception ex){
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        }
        else if(e.getSource() ==  history){
            try{
                HistoryControl history = new HistoryControl();
                history.readDataHistory();
            }catch(Exception ex){
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        }
        else if (e.getSource() == cancel) reset();
    }
    
    public boolean sameProduct() {
       boolean same;
       same = false;
       for(int i=0; i<tdata; i++) {
           if(data[i][0].equals(data[tdata][0])) {
               int qty1 = (int) data[i][4], qty2 = (int) data[tdata][4];
               data[i][4] = qty1 + qty2;
               int price1 = (int) data[i][2], price2 = (int) data[tdata][2];
               data[i][2] = price1 + price2;
               same = true;
               tablep.setValueAt(data[i][4], i, 0);
               tablep.setValueAt(data[i][2], i, 2);
           }
       }
       return same;
    }
    
    public void reset() {
        tprice = 0; tdata = 0;
        incode.setText("");
        inqty.setText("");
        price.setText("");
        int row = tablep.getRowCount();
        for(int i=row-1;i>=0;i--) tablep.removeRow(i);
    }
    
}
