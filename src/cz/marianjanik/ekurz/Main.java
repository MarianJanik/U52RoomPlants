package cz.marianjanik.ekurz;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Main {

    public static void main(String[] args){
        final String FILE1 = "kvetiny.txt";
        final String FILE2 = "vystup.txt";
        final String ERROR_MESSAGE = "Zadaná chybná hodnota: ";

        System.out.println("\n--------------------------------------- Informace o zálivce:");
        PlantList myRegisterPlant = new PlantList();
        try {
            myRegisterPlant = PlantList.readDatabaseFromTxt(FILE1);
            System.out.println(myRegisterPlant.getAllInfoWatering());
        } catch (FileNotFoundException e) {
            System.err.println("Soubor s daty " + FILE1 + " nebyl nalezen.\nProgram pokračuje bez načtení souboru.");
        } catch (PlantException e) {
            System.err.println(ERROR_MESSAGE + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println(ERROR_MESSAGE + e.getMessage());
        } catch (DateTimeParseException e) {
            System.err.println(ERROR_MESSAGE + e.getMessage());
        }

        System.out.println("\n--------------------------------------- Vložení dalších dvou květin:");

        Plant kaktus = null;
        Plant orchidea = null;
        try {
            kaktus = new Plant("Ferocactus stainesii","Zalévá se 1x/rok, na WC",
                    LocalDate.parse("2016-08-15"),LocalDate.parse("2020-08-15"),365);
            orchidea = new Plant("Phalaenopsis","Růžové květy, v obýváku",
                    LocalDate.parse("2016-08-15"),LocalDate.parse("2020-08-15"),10);
        } catch (PlantException e) {
            System.err.println(ERROR_MESSAGE + e.getMessage());
        }
        myRegisterPlant.addPlant(kaktus);
        myRegisterPlant.addPlant(orchidea);
        System.out.println(myRegisterPlant.getAllInfoWatering());

        System.out.println("\n--------------------------------------- Odstranění vánoční hvězdy v srpnu:");
        myRegisterPlant.remove(myRegisterPlant.getPlantInOrder(1));
        System.out.println(myRegisterPlant.getAllInfoWatering());

        //System.out.println("\n--------------------------------------- Zápis do souboru:");
        try {
            myRegisterPlant.writeDatabaseToTxt(FILE2);
        } catch (FileNotFoundException e) {
            System.err.println("Soubor nebylo možné uložit.");
        }

        System.out.println("\n--------------------------------------- Opětovné načtení vygenerovaného souboru:");
        PlantList myRegisterPlant2 = null;
        try {
            myRegisterPlant2 = PlantList.readDatabaseFromTxt(FILE2);
        } catch (FileNotFoundException e) {
            System.err.println("Soubor s daty " + FILE2 + " nebyl nalezen.");
        } catch (PlantException e) {
            System.err.println(ERROR_MESSAGE + e.getMessage());
        }
        System.out.println(myRegisterPlant2.getAllInfoWatering());
    }
}
