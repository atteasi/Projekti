package fi.asikainen.kalori;

import java.util.ArrayList;

public class Ruoka{

    private String kalorit;
    private String nimi;
    public static ArrayList<Ruoka> ruuat = new ArrayList<>();
    public Ruoka(String kalorit, String nimi) {
        this.nimi = nimi;
        this.kalorit = kalorit;
        ruuat.add(new Ruoka(this.kalorit, this.nimi));
    }

    public String toString(){
        return this.nimi;
    }

    public String getKalorit(){
        return this.kalorit;
    }
}
