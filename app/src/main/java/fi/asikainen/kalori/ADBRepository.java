package fi.asikainen.kalori;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**ADBRepository Application DataBase Repository */
class ADBRepository {

    private UserDAO userDao;
    private WeightDAO weightDao;
    private LiveData<List<User>> allUsers;
    private LiveData<List<Weight>> allWeights;
    private User getNamedUser;

    ADBRepository(Application application){
        AppRoomDatabase database = AppRoomDatabase.getDatabase(application);
        userDao = database.userDAO();

        //getNamedUser = userDao.getNamedUser(userDao.);
        allUsers = userDao.getAllUsers();
        allWeights = weightDao.getAllWeights();

    }

    void insert(User user) {
        AppRoomDatabase.databaseWriteExecutor.execute(() -> {
            userDao.insert(user);
        });
    }

    User getNamedUser(String first, String last){
        Future<User> futureUser = AppRoomDatabase.databaseReadExecutor.submit(()-> userDao.getNamedUser(first, last));
        try {
            getNamedUser = futureUser.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return getNamedUser;
    }

    void insert(Weight weight) {
        AppRoomDatabase.databaseWriteExecutor.execute(() -> {
            weightDao.insert(weight);
        });
    }

    LiveData<List<User>> getUser(){
        return allUsers;
    }

    LiveData<List<Weight>> getAllWeights(){return allWeights;}



}
