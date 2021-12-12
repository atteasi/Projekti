package fi.asikainen.kalori;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CalAddDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(CalAdd calAdd);

    @Delete
    void delete(CalAdd calAdd);

    @Query("SELECT * FROM calories_added")
    LiveData<List<CalAdd>> getAllCalAdd();

    @Query("SELECT * FROM calories_added WHERE userID = :userID ")
    LiveData<List<CalAdd>> getUsersCalAdds(int userID);
}
