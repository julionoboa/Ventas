import dao.AireDAO;
import dao.impl.AireDAOImplementation;
import dao.impl.VentaDAOImplementation;
import model.Aire;
import model.Venta;
import service.AireService;
import service.VentaService;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        AireService aireService = new AireService(new AireDAOImplementation());
        VentaService ventaService = new VentaService(new VentaDAOImplementation());
        Aire aire = new Aire();
//        List<Aire> aireList = aireService.showAll(aire);
        Venta venta = new Venta();
        ventaService.delete(venta);
//        aireService.insert(aire);
//        List<Venta> ventaList = ventaService.showAll(venta);
//        aire = aireService.getByID(2);
//        System.out.println(aire.marcaAire);

//        venta.marcaVenta = "Samsung";

//        aire.marcaAire = "Samsung";
//        aire.tipoAire = "Split";
//        aire.cantidadAire = 500;
//        aire.precioAire = 1000.99;
//
//        aireService.insert(aire);
//        aireService.delete(aire);
//        aireService.update(aire);
//
//
//        for (Aire i : aireList){
//            System.out.println("-------------------------------");
//            System.out.println("ID: " + i.idAire);
//            System.out.println("Marca: " + i.marcaAire);
//            System.out.println("Tipo: " + i.tipoAire);
//            System.out.println("Cantidad: " + i.cantidadAire);
//            System.out.println("Precio: " + i.precioAire);
//            System.out.println("-------------------------------");
//        }


//        Aire aireFromDB = aireService.getByID(1);
//        System.out.println("ID: " + aireFromDB.idAire);
//        System.out.println("Marca: " + aireFromDB.marcaAire);
//        System.out.println("Tipo: " + aireFromDB.tipoAire);
//        System.out.println("Cantidad: " + aireFromDB.cantidadAire);
//        System.out.println("Precio: " + aireFromDB.precioAire);



        }
    }
