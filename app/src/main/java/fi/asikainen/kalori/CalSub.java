package fi.asikainen.kalori;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

/**
 * @author Ricardo Nunes
 */
/**
 * Entity Class component of Room used to define Entity Objects that will populate single row in their table
 * and columns defined
 * Calories Subtracted entity to store row ID, UserID, Insertion Date, Description of the activity, Calorie Amount
 */
@Entity(tableName = "calories_subtracted")
public class CalSub {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    public int id;

    @ColumnInfo(name = "userID")
    public int userID;

    @NonNull
    @ColumnInfo(name = "creation_date")
    public Date insertDate;

    @ColumnInfo(name = "activity_description")
    public String description;

    @NonNull
    @ColumnInfo(name = "calorie_amount")
    public int calories;

    /**
     *
     * @param userID userID int number to identify the user adding the entry
     * @param insertDate insertDate system fetched insert date
     * @param description description description of the activity e.g "Jogging"
     * @param calories calories int number of the amount of calories subtracted
     */
    public CalSub(@NonNull int userID, @NonNull Date insertDate, String description, @NonNull int calories) {
        this.userID = userID;
        this.insertDate = insertDate;
        this.description = description;
        this.calories = calories;
    }

    public int getId() {return id;}

    public int getUserID() {return userID;}

    public Date getInsertDate() {return insertDate;}

    public String getDescription() {return description;}

    public  int getCalories() {return calories;}

}
