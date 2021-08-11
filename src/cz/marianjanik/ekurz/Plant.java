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

    /**
     * Následují 3 konstruktory.
     * Construktor 1.
     * @param name název,
     * @param notes poznámky,
     * @param planted datum, kdy byla zasazena,
     * @param watering datum poslední zálivky,
     * @param frequencyOfWatering běžná frekvence zálivky ve dnech.
     */
    public Plant(String name, String notes, LocalDate planted, LocalDate watering, int frequencyOfWatering) {
        this.name = name;
        this.notes = notes;
        this.planted = planted;
        this.watering = watering;
        this.frequencyOfWatering = frequencyOfWatering;
    }

    /**
     * Construktor 2.
     */
    public Plant(String name, LocalDate planted, int frequencyOfWatering) {
        this(name,"",planted,LocalDate.now(),frequencyOfWatering);
    }

    /**
     * Construktor 3
     */
    public Plant(String name) {
        this(name,"",LocalDate.now(),LocalDate.now(),7);
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.M.YY");
        String text = this.name + ", " + formatter.format(this.watering) + ", "
                +  formatter.format(this.watering.plusDays(this.frequencyOfWatering));
        return text;
    }
}
