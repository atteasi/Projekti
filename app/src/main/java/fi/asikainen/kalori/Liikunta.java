package fi.asikainen.kalori;

/**
 * A class that has a constructor for Liikunta-type entries and a toString method
 */

public class Liikunta {
    private String muoto;
    private String kalorit;

    public Liikunta(String muoto, String kalorit) {
        this.muoto = muoto;
        this.kalorit = kalorit;
    }

    public String toString(){
        return this.muoto + ",                " + this.kalorit + " kulutettua kaloria";
    }
}
