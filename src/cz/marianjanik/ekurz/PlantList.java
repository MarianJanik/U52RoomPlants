package cz.marianjanik.ekurz;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Třídu pro ukládání seznamu pokojových květin. Jako atribut bude mít kolekci uchovávající květiny.
 */
public class PlantList {
    private ArrayList <Plant> listOfPlants = new ArrayList<>();


    /**
     * Načtení databáze z textového souboru fileName.
     * @param fileName čtený textový soubor,
     * @return seznam rostlin.
     * @throws FileNotFoundException chyba - ZATÍM NEŘEŠENÁ.
     */
    public static PlantList readDatabaseFromTxt(String fileName) throws FileNotFoundException {
        PlantList listOfPlants = new PlantList();
        try (Scanner scanner = new Scanner(new FileInputStream(fileName))){
            while (scanner.hasNextLine()){
                String inputLine = scanner.nextLine();
                String [] parseInfoPlant = inputLine.split("\t");
                int number = Integer.valueOf(parseInfoPlant[2]);
                Plant plant = new Plant(parseInfoPlant[0],parseInfoPlant[1],
                        LocalDate.parse(parseInfoPlant[4]),
                        LocalDate.parse(parseInfoPlant[3]),
                        number);
                listOfPlants.addPlant(plant);
            }
        }
        return listOfPlants;
    }

    /**
     * Zápis databáze do textového souboru (oddělovač-tabulátor).
     * @param fileName název textového souboru,
     * @throws FileNotFoundException výjimka - ZATÍM NEŘEŠENÁ.
     */
    public void writeDatabaseToTxt(String fileName) throws FileNotFoundException {
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(fileName))){
            for (Plant plant:this.listOfPlants) {
                writer.println(plant.getName() + "\t"
                + plant.getNotes() + "\t"
                + plant.getFrequencyOfWatering() + "\t"
                + plant.getWatering() + "\t"
                + plant.getPlanted());
            }
        }
    }

    /**
     * Přidání kvědiny do kolekce.
     * @param plant adresa květiny, kterou chceme přidat.
     */
    public void addPlant(Plant plant){
        listOfPlants.add(plant);
    }

    /**
     * Získaní květiny z kolekce.
     * @param index pořadí objektu v kolekci,
     * @return oblekt, adresa rostliny.
     */
    public Plant getPlantInOrder(int index){
        return listOfPlants.get(index);
    }

    /**
     * Vymazání rostliny ze seznamu.
     * @param plant adresa rostliny.
     */
    public void remove(Plant plant){
        listOfPlants.remove(plant);
    }

    /**
     * Tisk kolekce rostlin.
     */
    public void printInfoWatering(){
        for (Plant plant:listOfPlants) {
            System.out.println(plant.getWateringInfo());
        }
    }
}
