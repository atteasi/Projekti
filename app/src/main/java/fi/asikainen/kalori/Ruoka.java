package fi.asikainen.kalori;

import java.util.ArrayList;
/**
 * A class that has a constructor for Ruoka-type entries and a toString method to print the wanted outcome to its assigned ListView
 */
public class Ruoka{

    private int kalorit;
    private String nimi;

    /**
     * The constructor for the Ruoka-class
     * @param kalorit The amount of calories in the consumed food
     * @param nimi The name of the dish/food item
     */
    public Ruoka(int kalorit, String nimi) {
        this.nimi = nimi;
        this.kalorit = kalorit;
    }

    public String toString(){
        String kaloreita = Integer.toString(this.kalorit);
        return this.nimi + ",                " + kaloreita + " kaloria";
    }
}
