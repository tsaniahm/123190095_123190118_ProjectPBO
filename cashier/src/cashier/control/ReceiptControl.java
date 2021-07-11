package cashier.control;

import cashier.model.ReceiptModel;
import cashier.view.ReceiptView;

public class ReceiptControl {
    
    public void insertReceipt(Object[][] data, int tdata) {
        ReceiptModel insert = new ReceiptModel();
        insert.insertReceipt(data, tdata);
        System.out.println(tdata);
        ReceiptView view = new ReceiptView();
        view.viewReceipt(data, tdata);
    }
}
