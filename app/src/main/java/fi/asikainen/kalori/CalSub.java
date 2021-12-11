package fi.asikainen.kalori;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

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

    @NonNull
    @ColumnInfo(name = "calorie_amount")
    public int calories;

    public CalSub(@NonNull int userID, @NonNull int calories, @NonNull Date insertDate) {
        this.userID = userID;
        this.insertDate = insertDate;
        this.calories = calories;
    }

    public int getId() {return id;}

    public int getUserID() {return userID;}

    public Date getInsertDate() {return insertDate;}

    public  int getCalories() {return calories;}

}
