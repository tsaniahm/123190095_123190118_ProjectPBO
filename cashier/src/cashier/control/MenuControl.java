package cashier.control;

import cashier.model.MenuModel;
import cashier.view.MenuView;

public class MenuControl {
    
    public void viewMenu() {
        MenuView menu = new MenuView();
        menu.viewMenu();
    }
    
    public Object[] searchProduct(String code, int qty) {
        MenuModel search = new MenuModel();
        return search.searchProduct(code, qty);
    }

}
