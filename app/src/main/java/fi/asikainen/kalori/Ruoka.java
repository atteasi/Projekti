package fi.asikainen.kalori;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @Author Atte Asikainen
 */
/**
 * A class that has a constructor for Ruoka-type entries and a toString method to print the wanted outcome to its assigned ListView
 */
@RequiresApi(api = Build.VERSION_CODES.O)
public class Ruoka{

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/YYYY");
    private String submissionDate;
    private int kalorit;
    private String nimi;
    /**
     * The constructor for the Ruoka-class
     * @param kalorit The amount of calories in the consumed food
     * @param nimi The name of the dish/food item
     * @param paiva The date when the dish was added
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public Ruoka(int kalorit, String nimi, LocalDate paiva) {
        this.nimi = nimi;
        this.kalorit = kalorit;
        this.submissionDate = dtf.format(paiva);
    }

    /**
     * The toString method for the Ruoka-class
     * @return form of the print that shows up in a ListView
     */
    public String toString(){
        String kaloreita = Integer.toString(this.kalorit);
        return this.submissionDate + " " + this.nimi + ", " + kaloreita + " kaloria";
    }

    /**
     * Used to get the calories inputted in a certain entry
     * @return the calories of a certain entry
     */
    public int getKalorit() {
        return this.kalorit;
    }

    /**
     * Used to return the name of a certain entry
     * @return calories of a certain entry
     */
    public String getNimi() {
        return nimi;
    }

    /**
     * Used to return the date the entry was added
     * @return the date the certain entry was added
     */
    public String getSubmissionDate() {
        return this.submissionDate;
    }
}
