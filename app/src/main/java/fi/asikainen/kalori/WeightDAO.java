package fi.asikainen.kalori;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface WeightDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Weight weight);

    @Delete
    void delete(Weight weight);

    @Query("SELECT * FROM weight")
    LiveData<List<Weight>> getAllWeights();

    /* @Query("SELECT * FROM weight WHERE user_firstname = :first AND user_lastname = :last")
    User getNamedUser(String first, String last); */

}
