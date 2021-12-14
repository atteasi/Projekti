package fi.asikainen.kalori;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author Ricardo Nunes
 */
/**
 * Android architecture Repository class to abstract access to data sources.
 */
//ADBRepository Application DataBase Repository
class ADBRepository {

    private UserDAO userDao; CalAddDAO calAddDao; CalSubDAO calSubDao; WeightDAO weightDao;
    private LiveData<List<User>> allUsers; LiveData<List<Weight>> allWeights;
            LiveData<List<CalAdd>> allCalAdds; LiveData<List<CalSub>> allCalSubs;
    private LiveData<List<Weight>> allUserWeights; LiveData<List<CalAdd>> allUserCalAdds;
            LiveData<List<CalSub>> allUserCalSubs;
    private User getNamedUser;

    /**
     * @param application gets the global state for managing the application context
     */
    ADBRepository(Application application){
        AppRoomDatabase database = AppRoomDatabase.getDatabase(application);
        userDao = database.userDAO(); calAddDao = database.calAddDAO(); calSubDao = database.calSubDAO();
        weightDao = database.weightDAO();

        allUsers = userDao.getAllUsers();
        allWeights = weightDao.getAllWeights();
        allCalAdds = calAddDao.getAllCalAdd();
        allCalSubs = calSubDao.getAllCalSub();
    }

    /**
     * Inserts given User type object into Database.
     * @param user User Entity object
     */
    void insert(User user) {
        AppRoomDatabase.databaseWriteExecutor.execute(() -> {
            userDao.insert(user);
        });
    }

    /**
     * Deletes given User type object from Database.
     * @param user User Entity object
     */
    void delete(User user) {
        AppRoomDatabase.databaseWriteExecutor.execute(() -> {
            userDao.delete(user);
        });
    }

    /**
     * Inserts given Weight type object into Database.
     * @param weight Weight Entity object
     */
    void insert(Weight weight) {
        AppRoomDatabase.databaseWriteExecutor.execute(() -> {
            weightDao.insert(weight);
        });
    }

    /**
     * Deletes given User type object from Database.
     * @param weight Weight Entity object
     */
    void delete(Weight weight) {
        AppRoomDatabase.databaseWriteExecutor.execute(() -> {
            weightDao.delete(weight);
        });
    }

    /**
     * Inserts given Calories Added/CalAdd type object into Database.
     * @param calAdd CalAdd Entity object
     */
    void insert(CalAdd calAdd) {
        AppRoomDatabase.databaseWriteExecutor.execute(() -> {
            calAddDao.insert(calAdd);
        });
    }

    /**
     * Deletes given Calories Added/CalAdd type object from Database.
     * @param calAdd CalAdd Entity Object
     */
    void delete(CalAdd calAdd) {
        AppRoomDatabase.databaseWriteExecutor.execute(() -> {
            calAddDao.delete(calAdd);
        });
    }

    /**
     * Inserts given Calories Subtracted/CalSub type object from Database.
     * @param calSub CalSub Entity Object
     */
    void insert(CalSub calSub) {
        AppRoomDatabase.databaseWriteExecutor.execute(() -> {
            calSubDao.insert(calSub);
        });
    }

    /**
     * Deletes given Calories Subtracted/CalSub type object from Database
     * @param calSub CalSub Entity Object
     */
    void delete(CalSub calSub) {
        AppRoomDatabase.databaseWriteExecutor.execute(() -> {
            calSubDao.delete(calSub);
        });
    }

    /**
     * Query database for all User type Entities
     * @return LiveData List of all Users
     */
    LiveData<List<User>> getAllUsers(){return allUsers;}

    /**
     * Query database for all Weight type Entities
     * @return LiveData List of all Weights
     */
    LiveData<List<Weight>> getAllWeights(){return allWeights;}

    /**
     * Query database for all CalAdd type Entities
     * @return LiveData List of all CalAdds
     */
    LiveData<List<CalAdd>> getAllCalAdds(){return allCalAdds;}

    /**
     * Query database for all CalSub type Entities
     * @return LiveData List of all CalSubs
     */
    LiveData<List<CalSub>> getAllCalSubs(){return allCalSubs;}

    /**
     * Query database for specific User
     * @param first "UserFirstName" as a String
     * @param last "UserLastName" as a String
     * @return User Who matched the name
     */
    User getNamedUser(String first, String last){
        Future<User> futureUser = AppRoomDatabase.databaseReadExecutor.submit(()-> userDao.getNamedUser(first, last));
        try {
            getNamedUser = futureUser.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return getNamedUser;
    }

    /**
     * Query database for specific User's Weights
     * @param userID int type user identifier
     * @return LiveData List of given users Weights
     */
    LiveData<List<Weight>> getUsersWeights(int userID){
        Future<LiveData<List<Weight>>> futureUserWeights = AppRoomDatabase.databaseReadExecutor.submit(()-> weightDao.getUsersWeights(userID));
        try {
            allUserWeights = futureUserWeights.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return allUserWeights;
    }

    /**
     * Query database for specific User's CalAdds
     * @param userID int type user identifier
     * @return LiveData List of given users CalAdds
     */
    LiveData<List<CalAdd>> getUsersCalAdds(int userID){
        Future<LiveData<List<CalAdd>>> futureUserCalAdds = AppRoomDatabase.databaseReadExecutor.submit(()-> calAddDao.getUsersCalAdds(userID));
        try {
            allUserCalAdds = futureUserCalAdds.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return allUserCalAdds;
    }

    /**
     * Query database for specific User's CalSubs
     * @param userID int type user identifier
     * @return LiveData List of given users CalSubs
     */
    LiveData<List<CalSub>> getUsersCalSubs(int userID){
        Future<LiveData<List<CalSub>>> futureUserCalSubs = AppRoomDatabase.databaseReadExecutor.submit(()-> calSubDao.getUsersCalSubs(userID));
        try {
            allUserCalSubs = futureUserCalSubs.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return allUserCalSubs;
    }

}
