
package cashier.control;

import cashier.model.ProductModel;
import cashier.view.ProductDetailView;
import cashier.view.ProductView;
import cashier.view.ReceiptView;

public class ProductControl {
    
    public void readDataProducts(){
        ProductModel product = new ProductModel();
        String[][] data = product.readData();
        if(data == null)
            new ReceiptView();
        else
           new ProductView(data);
    }
    
    public void readDataProducts(String id){
        ProductModel product = new ProductModel();
        String[][] data = product.readData(id);
        if(data == null)
            new ReceiptView();
        else
           new ProductDetailView(data);
    }
    
    public void createDataProducts(String [] data){
        ProductModel product = new ProductModel();
        product.createData(data);
        
        ProductModel products = new ProductModel();
        String[][] dataa = product.readData();
        if(dataa == null)
            new ReceiptView();
        else
           new ProductView(dataa);
    }
    
    public void updateDataProducts(String [] data){
        ProductModel product = new ProductModel();
        product.updateData(data);
        
        ProductModel products = new ProductModel();
        String[][] dataa = product.readData();
        if(dataa == null)
            new ReceiptView();
        else
           new ProductView(dataa);
    }
    
    public void deleteDataProducts(String data){
        ProductModel product = new ProductModel();
        product.deleteData(data);
        
        ProductModel products = new ProductModel();
        String[][] dataa = product.readData();
        if(dataa == null)
            new ReceiptView();
        else
           new ProductView(dataa);
    }   
}
