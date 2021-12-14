package fi.asikainen.kalori;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

/**
 * @author Ricardo Nunes
 */
/**
 * Data Access Object Type Room component for accessing the database. DAO
 */
@Dao
public interface CalSubDAO {
    /**
     * Inserts given CalSub object to the database
     * @param calSub
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(CalSub calSub);

    /**
     * Deletes given CalSub object from the database
     * @param calSub
     */
    @Delete
    void delete(CalSub calSub);

    /**
     * Queries Database for all CalSub objects
     * @return LiveData List of all CalSub objects in the database
     */
    @Query("SELECT * FROM calories_subtracted")
    LiveData<List<CalSub>> getAllCalSub();

    /**
     * Queries Database for CalSub objects with given User identifier
     * @param userID int type User Identifier
     * @return LiveData List of given Users CalSubs
     */
    @Query("SELECT * FROM calories_subtracted WHERE userID = :userID ")
    LiveData<List<CalSub>> getUsersCalSubs(int userID);

}
