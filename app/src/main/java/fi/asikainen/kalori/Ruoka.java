package fi.asikainen.kalori;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.zip.DataFormatException;

/**
 * A class that has a constructor for Ruoka-type entries and a toString method to print the wanted outcome to its assigned ListView
 */
public class Ruoka{

    private LocalDate submissionDate;
    private int kalorit;
    private String nimi;

    /**
     * The constructor for the Ruoka-class
     * @param kalorit The amount of calories in the consumed food
     * @param nimi The name of the dish/food item
     * @param paiva
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public Ruoka(int kalorit, String nimi, LocalDate paiva) {
        this.nimi = nimi;
        this.kalorit = kalorit;
        this.submissionDate = paiva;
    }

    public String toString(){
        String kaloreita = Integer.toString(this.kalorit);
        return this.nimi + ",                " + kaloreita + " kaloria";
    }

    public int getKalorit() {
        return this.kalorit;
    }

    public LocalDate getSubmissionDate() {
        return submissionDate;
    }
}
