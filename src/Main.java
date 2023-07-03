import dao.AireDAO;
import dao.impl.AireDAOImplementation;
import dao.impl.VentaDAOImplementation;
import menu.AireMenu;
import model.Aire;
import model.Venta;
import service.AireService;
import service.VentaService;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        AireMenu aireMenu = new AireMenu();
        aireMenu.menu();
    }
}
