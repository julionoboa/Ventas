package service;

import dao.AireDAO;
import model.Aire;

import java.math.BigInteger;
import java.util.List;
import java.util.Scanner;

public class AireService {
    Scanner sc = new Scanner(System.in);
    int repetir = -1;
    public AireService(AireDAO aireDAO){
        this.aireDAO = aireDAO;
    }
    private AireDAO aireDAO;

    public void insert(Aire aire){

        while (repetir < 0) {

            System.out.println("Ingrese la marca del aire a crear:");
            aire.marcaAire = sc.nextLine();

            while (!validateNull(aire.marcaAire)) {
                System.out.println("Ingrese la marca del aire a crear:");
                aire.marcaAire = sc.nextLine();
            }

            System.out.println("Ingrese el tipo de aire:");
            aire.tipoAire = sc.nextLine();


            while (!validateNull(aire.tipoAire)) {
                System.out.println("Ingrese el tipo de aire:");
                aire.tipoAire = sc.nextLine();
            }

            while (!validateBrandAndType(aire.marcaAire, aire.tipoAire)) {
                System.out.println("El tipo y la marca de aire ya existen, en caso de querer modificar debe utilizar");
                System.out.println("la opción correspondiente.");

                System.out.println("Ingrese la marca del aire a crear (o escriba 0 para salir):");
                aire.marcaAire = sc.nextLine();
                if (aire.tipoAire == "0") break;
                ;

                while (!validateNull(aire.marcaAire)) {
                    System.out.println("Ingrese la marca del aire a crear:");
                    aire.marcaAire = sc.nextLine();
                    if (aire.marcaAire == "0") break;
                }

                System.out.println("Ingrese el tipo de aire (o escriba 0 para salir):");
                aire.tipoAire = sc.nextLine();
                if (aire.tipoAire == "0") break;

                while (!validateNull(aire.tipoAire)) {
                    System.out.println("Ingrese el tipo de aire:");
                    aire.tipoAire = sc.nextLine();
                    if (aire.tipoAire == "0") break;
                }
            }

            System.out.println("Ingrese la cantidad de aires que habrán en existencia:");
            String cantidadAire = sc.nextLine();

            while (!validateNull(cantidadAire) || !validateNumber(cantidadAire)) {
                System.out.println("Ingrese la cantidad de aires que habrán en existencia (No debe ser 0):");
                cantidadAire = sc.nextLine();
            }

            aire.cantidadAire = Integer.parseInt(cantidadAire);

            while (aire.cantidadAire <= 0){
                System.out.println("La cantidad no puede ser 0 o menor a 0, favor ingresar cantidad nuevamente:");
                cantidadAire = sc.nextLine();
                while (!validateNull(cantidadAire) || !validateNumber(cantidadAire)) {
                    System.out.println("Ingrese la cantidad de aires que habrán en existencia (No debe ser 0):");
                    cantidadAire = sc.nextLine();
                }
                aire.cantidadAire = Integer.parseInt(cantidadAire);
            }

            System.out.println("Ingrese el precio unitario que tendrá el aire:");
            String precioAire = sc.nextLine();

            while (!validateDouble(precioAire) || !validateNull(precioAire)) {
                System.out.println("Ingrese el precio unitario que tendrá el aire:");
                precioAire = sc.nextLine();
            }

            aire.precioAire = Double.parseDouble(precioAire);

            while (aire.precioAire <= 0){
                System.out.println("La cantidad no puede ser 0 o menor a 0, favor ingresar cantidad nuevamente:");
                precioAire = sc.nextLine();
                while (!validateNull(precioAire) || !validateDouble(precioAire)) {
                    System.out.println("Ingrese la cantidad de aires que habrán en existencia (No debe ser 0):");
                    precioAire = sc.nextLine();
                }
                aire.precioAire = Double.parseDouble(precioAire);
            }

            aireDAO.insert(aire);
            repetir = 0;
        }
        repetir = -1;

    }

    public void update(Aire aire){
        int idAireInt = validation();
        aire = getByID(idAireInt);
        showAC(aire);
        System.out.println("Solo se puede actualizar la cantidad. Ingrese nueva cantidad:");
        String cantidadAire = sc.nextLine();
        while (!validateNumber(cantidadAire)){
            System.out.println("Introduzca un número correcto");
            cantidadAire = sc.nextLine();
        };
        aire.cantidadAire = Integer.parseInt(cantidadAire);
        aire.idAire = idAireInt;
        aireDAO.update(aire);
    }

    public void delete(Aire aire){
        int idAireInt = validation();
        aire = getByID(idAireInt);
        showAC(aire);
        System.out.println("¿Desea borrar este aire?");
        int klk = yesOrNot(sc);
        while(klk == 0){
            klk = yesOrNot(sc);
        };
        if (klk ==1) aireDAO.delete(aire);

    }

    public List<Aire> showAll(Aire aire){
        return aireDAO.showAll();
    }

    public Aire getByID(int idAire){
        return aireDAO.getByID(idAire);
    }

    private boolean validateBrandAndType(String marcaAire, String tipoAire){
        List<Aire> aireList = aireDAO.showAll();
        for (Aire i: aireList){
            if (i.marcaAire.equals(marcaAire)){
                if (i.tipoAire.equals(tipoAire)){
                System.out.println("El aire ya existe en la base de datos.");
                return false;
            }
        }
        }
        return true;
    }

    private void checkAC(){
        List<Aire> aireList = aireDAO.showAll();
        for (Aire i: aireList){
            showAC(i);
            }
        }


    private boolean validateNull(String aire){

        if (aire == null){

            System.out.println("No puede contener valores nulos.");
            return false;

        }

        else if(aire instanceof String && ((String) aire).isBlank()) {

            System.out.println("Ningún campo puede estar vacío.");
            return false;

        }

        return true;
    }

    public boolean validateNumber(String number) {
        try {
            BigInteger.valueOf(Long.parseLong(number));
        } catch (NumberFormatException e) {
            System.out.println("Debe introducir un número válido");
            return false;
        }
        return true;
    }

    private int convertToNumber(String number){
        int newNumber = 0;
        try{
            newNumber = Integer.parseInt(number);
        } catch (Exception e){
            System.out.println("Debe introducir un número válido");
        }
        return newNumber;
    }

    public Aire showAC(Aire i){
        System.out.println("-------------------------------");
        System.out.println("ID: " + i.idAire);
        System.out.println("Marca: " + i.marcaAire);
        System.out.println("Tipo: " + i.tipoAire);
        System.out.println("Cantidad: " + i.cantidadAire);
        System.out.println("Precio: " + i.precioAire);
        System.out.println("-------------------------------");
        return null;
    }

    private int validation(){
        checkAC();
        System.out.println("Seleccione el ID del aire:");
        String idAire = sc.nextLine();
        while (!validateNumber(idAire)){
            System.out.println("Introduzca un número correcto");
            idAire = sc.nextLine();
        };
        int idAireInt = convertToNumber(idAire);

        while (getByID(idAireInt) == null){
            System.out.println("El ID introducido no existe, favor introducir ID válido");
            idAire = sc.nextLine();
            while (!validateNumber(idAire)){
                System.out.println("Introduzca un número correcto");
                idAire = sc.nextLine();
            };
            idAireInt = convertToNumber(idAire);
        }
        return idAireInt;
    }

    private int yesOrNot(Scanner sc) {
        String entrada;
        System.out.println("Si(Y)");
        System.out.println("No(N)");


            entrada = sc.nextLine();
            entrada = entrada.toUpperCase();
            if (entrada.equalsIgnoreCase("Y")){
                return 1;
            }
            else if (entrada.equalsIgnoreCase("N")){
                return 2;
            }
            else {
                System.out.println("Debe ser Y o N");
            }
        return 0;
    }

    private boolean validateDouble(String number){
        try {
            Double.parseDouble(number);
        } catch (NumberFormatException e){
            System.out.println("Debe introducir un número válido");
            return false;
        }
        return true;
    }

}
