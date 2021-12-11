package fi.asikainen.kalori;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;


import java.util.List;

@Dao
public interface UserDAO {

    //(onConflict = OnConflictStrategy.REPLACE)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(User user);

    @Delete
    void delete(User user);

    @Query("SELECT * FROM user")
    LiveData<List<User>> getAllUsers();

    @Query("SELECT * FROM user WHERE user_firstname = :first AND user_lastname = :last")
    User getNamedUser(String first, String last);


    //@Query("SELECT * FROM User")
    //public User[] loadAllUsers();


    /*@Query("SELECT * From User WHERE nameFirst LIKE :search")
    public List<User> findUserWithName(String search);*/

}
