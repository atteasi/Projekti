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
 * @Author Ricardo Nunes
 */
@Dao
public interface WeightDAO {
    /**
     * Insert given Weight object to database
     * @param weight
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Weight weight);

    /**
     * Delete given Weight object from database
     * @param weight
     */
    @Delete
    void delete(Weight weight);

    /**
     * Query database for all Weights
     * @return returns LiveData List of all the Weights
     */
    @Query("SELECT * FROM weight")
    LiveData<List<Weight>> getAllWeights();

    /**
     * Query database for all Weights with given User Identifier
     * @param userID int type User Identifier
     * @return LiveData List of all users Weights
     */
    @Query("SELECT * FROM weight WHERE userID = :userID ")
    LiveData<List<Weight>> getUsersWeights(int userID);

}
