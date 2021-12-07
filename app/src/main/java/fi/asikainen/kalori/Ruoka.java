package fi.asikainen.kalori;

import java.util.ArrayList;

public class Ruoka{

    private String kalorit;
    private String nimi;
    private static ArrayList<Ruoka> ruuat = new ArrayList<>();
    private static final Ruoka ourInstance = new Ruoka;
    public Ruoka(String kalorit, String nimi) {
        this.nimi = nimi;
        this.kalorit = kalorit;
    }

    public static Ruoka getOurInstance() {
        return ourInstance;
    }


    public String toString(){
        return this.nimi;
    }
}
