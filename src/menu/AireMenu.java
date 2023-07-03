package menu;

import dao.impl.AireDAOImplementation;
import dao.impl.VentaDAOImplementation;
import model.Aire;
import model.Venta;
import service.AireService;
import service.VentaService;

import java.util.List;
import java.util.Scanner;

public class AireMenu {
    public void menu() {

        AireService aireService = new AireService(new AireDAOImplementation());
        VentaService ventaService = new VentaService(new VentaDAOImplementation());
        List<Venta> ventaList;
        List<Aire> aireList;
        Aire aire = new Aire();
        Venta venta = new Venta();
        Scanner sc = new Scanner(System.in);
        int repetir = -1;

        while (repetir < 0){
            System.out.println("Favor ingrese opción a realizar:");
            System.out.println("1. Ventas");
            System.out.println("2. Inventario");
            System.out.println("0. Salir");
            int option = validate(sc);

                while (option < 0 || option > 2) {
                    System.out.println("Introduzca un número del 0 al 2");
                    option = validate(sc);
                }

            switch (option){
                case 1:
                    System.out.println("***VENTAS***");
                    System.out.println("Ingrese una de las siguientes opciones:");
                    System.out.println("1. Realizar venta");
                    System.out.println("2. Actualizar venta");
                    System.out.println("3. Borrar venta");
                    System.out.println("4. Mostrar ventas");
                    System.out.println("0. Volver atrás");
                    option = validate(sc);

                    while (option < 0 || option > 4) {
                        System.out.println("Introduzca un número del 0 al 2");
                        option = validate(sc);
                    }

                    switch (option){
                        case 1:
                            ventaService.insert(venta);
                            break;
                        case 2:
                            ventaService.update(venta);
                            break;
                        case 3:
                            ventaService.delete(venta);
                            break;
                        case 4:
                            ventaList = ventaService.showAll(venta);
                            for (Venta i : ventaList){
                                ventaService.showSales(i);
                            }
                            break;
                    }
                    break;
                case 2:
                    System.out.println("***INVENTARIO***");
                    System.out.println("Ingrese una de las siguientes opciones:");
                    System.out.println("1. Registrar aire");
                    System.out.println("2. Actualizar aire");
                    System.out.println("3. Borrar aire");
                    System.out.println("4. Mostrar aires");
                    System.out.println("0. Volver atrás");
                    option = validate(sc);

                    while (option < 0 || option > 4) {
                        System.out.println("Introduzca un número del 0 al 2");
                        option = validate(sc);
                    }

                    switch (option){
                        case 1:
                            aireService.insert(aire);
                            break;
                        case 2:
                            aireService.update(aire);
                            break;
                        case 3:
                            aireService.delete(aire);
                            break;
                        case 4:
                            aireList = aireService.showAll(aire);
                            for (Aire i : aireList){
                                aireService.showAC(i);
                            }
                            break;
                    }
                    break;
            }

            System.out.println("¿Desea salir?");
            int out = yesOrNot(sc);

            while (out == 0) out = yesOrNot(sc);

            if (out == 1) repetir = 0;
        }

    }

    public int validate(Scanner sc) {
        int i = -1;

        while (i < 0 ) {

            String optionMenu = sc.nextLine();

            try {
                i = Integer.valueOf(optionMenu);
            }
            catch (Exception e){
                System.out.println("Debe introducir un número válido");
                i = -1;
            }

        }

        return i;
    }

    private int yesOrNot(Scanner sc) {
        String entrada;
        System.out.println("Si(Y) [Cierra el programa]");
        System.out.println("No(N) [Vuelve al menú principal]");

        entrada = sc.nextLine();
        entrada = entrada.toUpperCase();
        if (entrada.equalsIgnoreCase("Y")) {
            return 1;
        } else if (entrada.equalsIgnoreCase("N")) {
            return 2;
        } else {
            System.out.println("Debe ser Y o N");
        }
        return 0;
    }

}
