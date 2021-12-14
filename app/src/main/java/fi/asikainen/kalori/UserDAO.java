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
public interface UserDAO {
    /**
     * Insert given User type object to the database
     * @param user
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(User user);

    /**
     * Delete given user type object from the database
     * @param user
     */
    @Delete
    void delete(User user);

    /**
     * Query the database for all User type objects
     * @return LiveData List of all Users
     */
    @Query("SELECT * FROM user")
    LiveData<List<User>> getAllUsers();

    /**
     * Query the database for user of given name
     * @param first String type first name
     * @param last String type last name
     * @return User object of matching name
     */
    @Query("SELECT * FROM user WHERE user_firstname = :first AND user_lastname = :last")
    User getNamedUser(String first, String last);

}
