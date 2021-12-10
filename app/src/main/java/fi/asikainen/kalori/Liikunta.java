package fi.asikainen.kalori;

/**
 * A class that has a constructor for Liikunta-type entries and a toString method to print the wanted outcome to its assigned ListView
 */

public class Liikunta {

    private String muoto;
    private String kalorit;

    /**
     * The constuctor for Liikunta-type entries
     * @param muoto The form of exercise the user logs
     * @param kalorit The amount of calories burned
     */
    public Liikunta(String muoto, String kalorit) {
        this.muoto = muoto;
        this.kalorit = kalorit;
    }

    /**
     * The toString method for the Liikunta-class
     * @return form of the print that shows up in a ListView
     */
    public String toString(){

        return this.muoto + ",                " + this.kalorit + " kulutettua kaloria";
    }
}
