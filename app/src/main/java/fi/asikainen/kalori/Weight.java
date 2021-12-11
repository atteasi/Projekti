package fi.asikainen.kalori;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "weight")
public class Weight {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    public int id;

    @ColumnInfo(name = "userID")
    public int userID;

    @NonNull
    @ColumnInfo(name = "creation_date")
    public Date insertDate;

    @NonNull
    @ColumnInfo(name = "measured_weight")
    public int weight;

    public Weight(int userID, Date insertDate, int weight) {
        this.userID = userID;
        this.insertDate = insertDate;
        this.weight = weight;
    }

    public int getID() {return id;}

    public Date getInsertDate() {return insertDate;}

    public int getWeight() {return weight;}

}
