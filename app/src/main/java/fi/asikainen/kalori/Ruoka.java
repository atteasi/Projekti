package fi.asikainen.kalori;

import java.util.ArrayList;

public class Ruoka{

    private String kalorit;
    private String nimi;
    public Ruoka(String kalorit, String nimi) {
        this.nimi = nimi;
        this.kalorit = kalorit;
    }

    public String toString(){
        return this.nimi;
    }
}
