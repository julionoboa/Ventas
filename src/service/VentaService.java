package service;

import dao.AireDAO;
import dao.VentaDAO;
import dao.impl.AireDAOImplementation;
import model.Aire;
import model.Venta;

import java.math.BigInteger;
import java.util.List;
import java.util.Scanner;

public class VentaService {
    Scanner sc = new Scanner(System.in);
    int repetir = -1;

    public VentaService(VentaDAO ventaDAO) {
        this.ventaDAO = ventaDAO;

    }

    private VentaDAO ventaDAO;
    private AireDAO aireDAO = new AireDAOImplementation();

    public void insert(Venta venta) {
        Aire aire;
        checkAC();
        System.out.println("Inserte el ID del aire a vender:");
        String idAire = sc.nextLine();
        while (!validateNumber(idAire)) {
            System.out.println("Debe ser un número");
            idAire = sc.nextLine();
        }
        int idAireInt = convertToNumber(idAire);
        aire = aireDAO.getByID(idAireInt);
        showAC(aire);

        System.out.println("Ingrese la cantidad de aires a vender: (No debe ser mayor a la cantidad existente):");

        String cantidadVenta = sc.nextLine();
        while (!validateNumber(cantidadVenta)) {
            System.out.println("Debe ser un número");
            cantidadVenta = sc.nextLine();
        }

        int cantidadVentaInt = convertToNumber(cantidadVenta);

        while (cantidadVentaInt == 0) {
            System.out.println("La cantidad no puede ser 0, ingrese la cantidad de aires a vender (No debe ser mayor a la cantidad existente):");
            cantidadVenta = sc.nextLine();
            while (!validateNumber(cantidadVenta)) {
                System.out.println("Debe ser un número");
                cantidadVenta = sc.nextLine();
            }
        }

        cantidadVentaInt = convertToNumber(cantidadVenta);
        venta.marcaVenta = aire.marcaAire;
        venta.tipoVenta = aire.tipoAire;
        venta.idAire = aire.idAire;
        venta.cantidadVenta = cantidadVentaInt;

        if (!validateExistence(venta.marcaVenta, venta.tipoVenta)) {

            System.out.println("El aire a vender no existe. Favor intente nuevamente.");
        } else if (venta.cantidadVenta > aire.cantidadAire) {

            System.out.println("La cantidad a vender es mayor a la existencia. Favor intente nuevamente.");
        } else if (validateNull(venta.idVenta) && validateNull(venta.cantidadVenta) &&
                validateNull(venta.totalVenta) && validateNull(venta.marcaVenta) &&
                validateNull(venta.tipoVenta) && validateNull(venta.cantidadVenta)) {

            venta.totalVenta = aire.precioAire * venta.cantidadVenta;
            ventaDAO.insert(venta);
            System.out.println("¡Venta insertada con éxito!");

        }
    }


    public void update(Venta venta) {

        while (repetir < 0) {
            int idVentaInt = validation();

            venta.idVenta = idVentaInt;

            Aire aire = new Aire();

            venta = getByID(idVentaInt);

            int idAireInt = venta.idAire;

            aire = aireDAO.getByID(idAireInt);


            showSales(venta);
            System.out.println("Solo se puede actualizar la cantidad. Ingrese nueva cantidad:");

            venta.idAire = aire.idAire;

            String cantidadVenta = sc.nextLine();
            while (!validateNumber(cantidadVenta)) {
                System.out.println("Introduzca un número correcto");
                cantidadVenta = sc.nextLine();
            }

            int cantidadVentaInt = Integer.parseInt(cantidadVenta);

            while (cantidadVentaInt <= 0) {
                System.out.println("La cantidad no puede ser 0, favor ingrese la cantidad nuevamente:");
                cantidadVenta = sc.nextLine();

                while (!validateNumber(cantidadVenta)) {
                    System.out.println("Introduzca un número correcto");
                    cantidadVenta = sc.nextLine();
                }
                cantidadVentaInt = Integer.parseInt(cantidadVenta);
            }

            if (cantidadVentaInt > (venta.cantidadVenta + aire.cantidadAire)) {
                System.out.println("La cantidad a actualizar es mayor a la existencia. Favor intentar nuevamente.");
                repetir = 0;
                break;
            } else if (cantidadVentaInt > venta.cantidadVenta) {
                int restarAires = venta.cantidadVenta - cantidadVentaInt;
                ventaDAO.refresh(venta, restarAires);
            } else if (cantidadVentaInt < venta.cantidadVenta) {
                int sumarAires = venta.cantidadVenta - cantidadVentaInt;
                ventaDAO.refresh(venta, sumarAires);
            }
            venta.cantidadVenta = cantidadVentaInt;

            ventaDAO.update(venta);
            repetir = 0;
            System.out.println("¡Venta actualizada con éxito!");
        }
        repetir = -1;

    }

    public void delete(Venta venta) {
        int idVentaInt = validation();
        venta = getByID(idVentaInt);
        showSales(venta);
        System.out.println("¿Desea borrar esta venta?");
        int confirmation = yesOrNot(sc);
        while (confirmation == 0) {
            confirmation = yesOrNot(sc);
        }
        ;
        if (confirmation == 1) {
            ventaDAO.refresh(venta, venta.cantidadVenta);
            ventaDAO.delete(venta);
            System.out.println("¡Venta borrada con éxito!");
        }

    }

    public List<Venta> showAll(Venta venta) {
        return ventaDAO.showAll();
    }

    public Venta getByID(int idVenta) {
        return ventaDAO.getByID(idVenta);
    }

    private boolean validateExistence(String marcaVenta, String tipoVenta) {
        List<Aire> aireList = aireDAO.showAll();
        for (Aire i : aireList) {
            if (i.marcaAire.equals(marcaVenta)) {
                if (i.tipoAire.equals(tipoVenta)) {
                    return true;
                }
            }
        }
        return false;
    }


    private void checkSales() {
        List<Venta> ventaList = ventaDAO.showAll();
        for (Venta i : ventaList) {
            showSales(i);
        }
    }


    private <T> boolean validateNull(T venta) {

        if (venta == null) {

            System.out.println("No puede contener valores nulos.");
            return false;
        } else if (venta instanceof String && ((String) venta).isEmpty()) {

            System.out.println("Ningún campo puede estar vacío.");
            return false;

        }

        return true;
    }

    private boolean validateNumber(String number) {
        try {
            BigInteger.valueOf(Long.parseLong(number));
        } catch (Exception e) {
            System.out.println("Debe introducir un número válido");
            return false;
        }
        return true;
    }

    private int convertToNumber(String number) {
        int newNumber = 0;
        try {
            newNumber = Integer.parseInt(number);
        } catch (Exception e) {
            System.out.println("Debe introducir un número válido");
        }
        return newNumber;
    }

    public Venta showSales(Venta i) {
        System.out.println("-------------------------------");
        System.out.println("ID: " + i.idVenta);
        System.out.println("Marca: " + i.marcaVenta);
        System.out.println("Tipo: " + i.tipoVenta);
        System.out.println("Cantidad: " + i.cantidadVenta);
        System.out.println("Precio: " + i.totalVenta);
        System.out.println("ID Aire: " + i.idAire);
        System.out.println("-------------------------------");
        return null;
    }

    private int validation() {
        checkSales();
        System.out.println("Seleccione el ID de venta:");
        String idVenta = sc.nextLine();
        while (!validateNumber(idVenta)) {
            System.out.println("Introduzca un número correcto");
            idVenta = sc.nextLine();
        }
        ;
        int idVentaInt = convertToNumber(idVenta);

        while (getByID(idVentaInt) == null) {
            System.out.println("El ID introducido no existe, favor introducir ID válido");
            idVenta = sc.nextLine();
            while (!validateNumber(idVenta)) {
                System.out.println("Introduzca un número correcto");
                idVenta = sc.nextLine();
            }
            ;
            idVentaInt = convertToNumber(idVenta);
        }
        return idVentaInt;
    }

    private int yesOrNot(Scanner sc) {
        String entrada;
        System.out.println("Si(Y)");
        System.out.println("No(N)");


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

    private void checkAC() {
        List<Aire> aireList = aireDAO.showAll();
        for (Aire i : aireList) {
            showAC(i);
        }
    }

    public Aire showAC(Aire i) {
        System.out.println("-------------------------------");
        System.out.println("ID: " + i.idAire);
        System.out.println("Marca: " + i.marcaAire);
        System.out.println("Tipo: " + i.tipoAire);
        System.out.println("Cantidad: " + i.cantidadAire);
        System.out.println("Precio: " + i.precioAire);
        System.out.println("-------------------------------");
        return null;
    }

}
