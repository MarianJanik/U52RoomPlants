package cz.marianjanik.ekurz;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Třídu pro ukládání seznamu pokojových květin. Jako atribut bude mít kolekci uchovávající květiny.
 * Třída nemá tisk na konzoli, odesílá text (chyby, getAllInfoWatering).
 */
public class PlantList {
    private ArrayList <Plant> listOfPlants = new ArrayList<>();


    /**
     * Načtení databáze z textového souboru fileName.
     * @param fileName čtený textový soubor,
     * @return seznam rostlin,
     * @throws FileNotFoundException - chyba ošetřena v Main,
     * @throws PlantException - chyba ošetřena v Main.
     */
    public static PlantList readDatabaseFromTxt(String fileName) throws FileNotFoundException,
            NumberFormatException, DateTimeParseException, PlantException {
        PlantList listOfPlants = new PlantList();
        String [] parseInfoPlant = null;
        int row = 0;
        try (Scanner scanner = new Scanner(new FileInputStream(fileName))){
            while (scanner.hasNextLine()){
                row++;
                String inputLine = scanner.nextLine();
                parseInfoPlant = inputLine.split("\t");
                int number = Integer.valueOf(parseInfoPlant[2]);
                Plant plant = new Plant(parseInfoPlant[0],parseInfoPlant[1],
                        LocalDate.parse(parseInfoPlant[4]),
                        LocalDate.parse(parseInfoPlant[3]),
                        number);
                listOfPlants.addPlant(plant);
            }
        } catch (NumberFormatException e) {
            throw new PlantException("Došlo k chybě při čtení \"frekvence zálivky\" na řádku " + row + ", bylo zadáno: \"" + parseInfoPlant[2] + "\".");
//            e.printStackTrace();
        } catch (DateTimeParseException e) {
            throw new PlantException("Došlo k chybě při čtení \"datumu\" na řádku " + row + ".");
        } catch (PlantException e) {
        throw new PlantException(e.getLocalizedMessage() + ", na řádku " + row + ".");
        }
        return listOfPlants;
    }

    /**
     * Zápis databáze do textového souboru (oddělovač-tabulátor).
     * @param fileName název textového souboru,
     * @throws FileNotFoundException výjimka - neřešená, soubor se vždy znovu vytváří.
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
     * Výstupní text kolekce rostlin.
     */
    public String getAllInfoWatering(){
        StringBuilder text = new StringBuilder();
        for (Plant plant:listOfPlants) {
            text.append(plant.getWateringInfo()+"\n");
        }
        return text.toString();
    }
}
