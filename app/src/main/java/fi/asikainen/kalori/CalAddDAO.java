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
public interface CalAddDAO {
    /**
     * Insert given CalAdd object to the database
     * @param calAdd
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(CalAdd calAdd);

    /**
     * Delete given CalAdd object from the database
     * @param calAdd
     */
    @Delete
    void delete(CalAdd calAdd);

    /**
     * Queries the database for all CalAdd objects
     * @return LiveData List of all CalAdd objects
     */
    @Query("SELECT * FROM calories_added")
    LiveData<List<CalAdd>> getAllCalAdd();

    /**
     * Queries the database for given users CalAdd objects
     * @param userID int type User identifier
     * @return LiveData List of given
     */
    @Query("SELECT * FROM calories_added WHERE userID = :userID ")
    LiveData<List<CalAdd>> getUsersCalAdds(int userID);
}
