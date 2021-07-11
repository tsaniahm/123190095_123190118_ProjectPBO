
package cashier.view;

import cashier.control.HistoryControl;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class HistoryView  extends JFrame implements ActionListener{
    
    private JTable table;
    private JButton back;
    
    private String id;
    private String[] tableTitle = {"id_receipt"};
    
    public HistoryView(String [][] data){
        try{
            id = data[0][0];
           
            setTitle("HISTORY");
            setSize(300,450);
            setLocationRelativeTo(null);
            setVisible(true);
            setLayout(null);
            setResizable(false);

            back = new JButton("Back");
            table = new JTable(data,tableTitle);

            JScrollPane scroll = new JScrollPane(table);
            add(scroll);
            add(back);
        
            scroll.setBounds(50, 35, 200, 300);
            scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            back.setBounds(60, 350, 150,35);
            
            table.setCursor((Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)));
            table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
               
                public void valueChanged(ListSelectionEvent event) {
                    dispose();
                    id = table.getValueAt(table.getSelectedRow(), 0).toString();
                    HistoryControl barang = new HistoryControl();
                    barang.readDataHistoryy(id);
                }
            });
            
             back.addActionListener(this);
        }
        catch (Exception e){
            System.out.println("Error : " + e);
        }      
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==back){
            dispose();
        }
    }
}
