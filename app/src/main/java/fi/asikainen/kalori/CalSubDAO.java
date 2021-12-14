package fi.asikainen.kalori;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

/**
 * Data Access Object Type Room component for accessing the database. DAO
 */
@Dao
public interface CalSubDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(CalSub calSub);

    @Delete
    void delete(CalSub calSub);

    @Query("SELECT * FROM calories_subtracted")
    LiveData<List<CalSub>> getAllCalSub();

    @Query("SELECT * FROM calories_subtracted WHERE userID = :userID ")
    LiveData<List<CalSub>> getUsersCalSubs(int userID);

}
