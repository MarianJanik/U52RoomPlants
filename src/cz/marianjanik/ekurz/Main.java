package cz.marianjanik.ekurz;

import java.io.FileNotFoundException;
import java.time.LocalDate;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        final String FILE1 = "kvetiny.txt";
        final String FILE2 = "vystup.txt";

        System.out.println("\n--------------------------------------- Informace o zálivce:");
        PlantList myRegisterPlant;
        myRegisterPlant = PlantList.readDatabaseFromTxt(FILE1);
        myRegisterPlant.printInfoWatering();

        System.out.println("\n--------------------------------------- Vložení dalších dvou květin:");
        Plant kaktus = new Plant("Ferocactus stainesii na WC","Zalévá se 1x/rok",
                LocalDate.parse("2016-08-15"),LocalDate.parse("2020-08-15"),365);
        Plant orchidea = new Plant("Phalaenopsis v obýváku","Růžové květy",
                LocalDate.parse("2016-08-15"),LocalDate.parse("2020-08-15"),10);
        myRegisterPlant.addPlant(kaktus);
        myRegisterPlant.addPlant(orchidea);
        myRegisterPlant.printInfoWatering();

        System.out.println("\n--------------------------------------- Odstranění vánoční hvězdy v srpnu:");
        myRegisterPlant.remove(myRegisterPlant.getPlantInOrder(1));
        myRegisterPlant.printInfoWatering();

        //System.out.println("\n--------------------------------------- Zápis do souboru:");
        myRegisterPlant.writeDatabaseToTxt(FILE2);

        System.out.println("\n--------------------------------------- Opětovné načtení vygenerovaného souboru:");
        PlantList myRegisterPlant2;
        myRegisterPlant2 = PlantList.readDatabaseFromTxt(FILE2);
        myRegisterPlant2.printInfoWatering();

    }
}
