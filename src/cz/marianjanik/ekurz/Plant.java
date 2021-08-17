package cz.marianjanik.ekurz;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Třída pro ukládání informací o pokojových rostlinách (plant).
 */

public class Plant {
    private String name;
    private String notes;
    private LocalDate planted;
    private LocalDate watering;
    private int frequencyOfWatering;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.M.yy");
    /**
     * Následují 3 konstruktory.
     * Construktor 1.
     * @param name název,
     * @param notes poznámky,
     * @param planted datum, kdy byla zasazena,
     * @param watering datum poslední zálivky,
     * @param frequencyOfWatering běžná frekvence zálivky ve dnech.
     */
    public Plant(String name, String notes, LocalDate planted, LocalDate watering,
                 int frequencyOfWatering) throws PlantException {
        this.name = name;
        this.notes = notes;
        this.planted = planted;
        this.watering = watering;
        if (watering.isBefore(planted)){
            throw new PlantException("Datum zálivky musí být po datumu zasazení rostliny. "
                    + "\nU rostliny \"" + name + "\" bylo zadáno: "
                    + "\ndatum zasazení: " + formatter.format(planted)
                    + "\ndatum zálivky: " + formatter.format(watering));// TODO tady Java-třída DateTimeFormatter
            // TODO podle mně dělá chybu: (2.1.21 převádí na 2.1.20 a 1.1.21 převádí na 1.1.20 - viz Fialka 1).
        }
        this.frequencyOfWatering = frequencyOfWatering;
        if (frequencyOfWatering<=0) {
            throw new PlantException("Frekvence zálivky musí být nenulové kladné celé číslo." +
                    "\nU rostliny \"" + name + "\" bylo zadáno: "
                    + frequencyOfWatering);
        }


    }

    /**
     * Construktor 2.
     */
    public Plant(String name, LocalDate planted, int frequencyOfWatering) throws PlantException {
        this(name,"",planted,LocalDate.now(),frequencyOfWatering);
    }

    /**
     * Construktor 3
     */
    public Plant(String name) throws PlantException {
        this(name,LocalDate.now(),7);
    }

    /**
     * getry a setry ke každé proměnné
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public LocalDate getPlanted() {
        return planted;
    }

    public void setPlanted(LocalDate planted) {
        this.planted = planted;
    }

    public LocalDate getWatering() {
        return watering;
    }

    public void setWatering(LocalDate watering) {
        this.watering = watering;
    }

    public int getFrequencyOfWatering() {
        return frequencyOfWatering;
    }

    public void setFrequencyOfWatering(int frequencyOfWatering) {
        this.frequencyOfWatering = frequencyOfWatering;
    }

    /**
     * Metoda vrátí ve formě textu název květiny, datum poslední zálivky a datum doporučené další zálivky.
     * @return text.
     */
    public String getWateringInfo() {
        String text = this.name + ", " + formatter.format(this.watering) + ", "
                +  formatter.format(this.watering.plusDays(this.frequencyOfWatering));
        return text;
    }
}
