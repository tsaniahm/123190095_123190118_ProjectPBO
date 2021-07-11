
package cashier.control;

import cashier.model.HistoryModel;
import cashier.view.HistoryDetailView;
import cashier.view.HistoryView;
import cashier.view.ReceiptView;

public class HistoryControl {
    
    public void readDataHistory(){
        HistoryModel model = new HistoryModel();
        String [][] data = model.readData();
        if(data == null)
            new ReceiptView();
        else
           new HistoryView(data);
    }
    
    public void readDataHistoryy(String id){
        HistoryModel model = new HistoryModel();
        String [][] data = model.readData(id);
        HistoryDetailView history = new HistoryDetailView(data);
    }
}
