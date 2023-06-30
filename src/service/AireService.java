package service;

import dao.AireDAO;
import model.Aire;

import java.math.BigInteger;
import java.util.List;
import java.util.Scanner;

public class AireService {
    Scanner sc = new Scanner(System.in);
    public AireService(AireDAO aireDAO){
        this.aireDAO = aireDAO;
    }
    private AireDAO aireDAO;

    public void insert(Aire aire){
        if (validateBrandAndType(aire.marcaAire, aire.tipoAire)) {
            if (validateNull(aire.idAire) && validateNull(aire.cantidadAire) &&
                    validateNull(aire.precioAire) && validateNull(aire.marcaAire) &&
                    validateNull(aire.tipoAire) && validateNull(aire.cantidadAire)) {
                aireDAO.insert(aire);
            }
        }
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

    public boolean validateBrandAndType(String marcaAire, String tipoAire){
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

    public void checkAC(){
        List<Aire> aireList = aireDAO.showAll();
        for (Aire i: aireList){
            showAC(i);
            }
        }


    public <T> boolean validateNull(T aire){

        if (aire == null){

            System.out.println("No puede contener valores nulos.");
            return false;
        }
        else if(aire instanceof String && ((String) aire).isEmpty()) {

            System.out.println("Ningún campo puede estar vacío.");
            return false;

        }

        return true;
    }

    public boolean validateNumber(String number) {
        try {
            BigInteger.valueOf(Long.parseLong(number));
        } catch (Exception e) {
            System.out.println("Debe introducir un número válido");
            return false;
        }
        return true;
    }

    public int convertToNumber(String number){
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

    public int validation(){
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

    public int yesOrNot(Scanner sc) {
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

}
