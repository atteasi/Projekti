package fi.asikainen.kalori;

import java.util.ArrayList;
/**
 * A class that has a constructor for Ruoka-type entries and a toString method to print the wanted outcome to its assigned ListView
 */
public class Ruoka{

    private String kalorit;
    private String nimi;

    /**
     * The constructor for the Ruoka-class
     * @param kalorit The amount of calories in the consumed food
     * @param nimi The name of the dish/food item
     */
    public Ruoka(String kalorit, String nimi) {
        this.nimi = nimi;
        this.kalorit = kalorit;
    }

    public String toString(){
        return this.nimi + ",                " + this.kalorit + " kaloria";
    }
}
