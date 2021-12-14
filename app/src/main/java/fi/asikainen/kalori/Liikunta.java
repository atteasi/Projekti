package fi.asikainen.kalori;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @Author Atte Asikainen
 */
/**
 * A class that has a constructor for Liikunta-type entries and a toString method to print the wanted outcome to its assigned ListView
 */

@RequiresApi(api = Build.VERSION_CODES.O)

public class Liikunta {

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/YYYY");
    private String submissionDate;
    private String muoto;
    private int kalorit;

    /**
     * The constuctor for Liikunta-type entries
     * @param muoto The form of exercise the user logs
     * @param kalorit The amount of calories burned
     * @param paiva The date this entry was created
     */
    public Liikunta(String muoto, int kalorit, LocalDate paiva) {
        this.muoto = muoto;
        this.kalorit = kalorit;
        this.submissionDate = dtf.format(paiva);
    }

    /**
     * The toString method for the Liikunta-class
     * @return form of the print that shows up in a ListView
     */
    public String toString(){
        String kaloreita = Integer.toString(this.kalorit);
        return this.submissionDate + " " + this.muoto + ", " + kaloreita + " kaloria";
    }

    /**
     * Used to get the calories burned in a certain exercise
     * @return the calories burned by a certain entry
     */
    public int getKalorit(){

        return this.kalorit;
    }
}
