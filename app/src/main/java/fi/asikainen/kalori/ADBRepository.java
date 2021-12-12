package fi.asikainen.kalori;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**ADBRepository Application DataBase Repository */
class ADBRepository {

    private UserDAO userDao; CalAddDAO calAddDao; CalSubDAO calSubDao; WeightDAO weightDao;
    private LiveData<List<User>> allUsers; LiveData<List<Weight>> allWeights;
            LiveData<List<CalAdd>> allCalAdds; LiveData<List<CalSub>> allCalSubs;
    private LiveData<List<Weight>> allUserWeights; LiveData<List<CalAdd>> allUserCalAdds;
            LiveData<List<CalSub>> allUserCalSubs;
    private User getNamedUser;

    ADBRepository(Application application){
        AppRoomDatabase database = AppRoomDatabase.getDatabase(application);
        userDao = database.userDAO(); calAddDao = database.calAddDAO(); calSubDao = database.calSubDAO();
        weightDao = database.weightDAO();

        allUsers = userDao.getAllUsers();
        allWeights = weightDao.getAllWeights();
        allCalAdds = calAddDao.getAllCalAdd();
        allCalSubs = calSubDao.getAllCalSub();
    }

    void insert(User user) {
        AppRoomDatabase.databaseWriteExecutor.execute(() -> {
            userDao.insert(user);
        });
    }

    void delete(User user) {
        AppRoomDatabase.databaseWriteExecutor.execute(() -> {
            userDao.delete(user);
        });
    }

    void insert(Weight weight) {
        AppRoomDatabase.databaseWriteExecutor.execute(() -> {
            weightDao.insert(weight);
        });
    }

    void delete(Weight weight) {
        AppRoomDatabase.databaseWriteExecutor.execute(() -> {
            weightDao.delete(weight);
        });
    }

    void insert(CalAdd calAdd) {
        AppRoomDatabase.databaseWriteExecutor.execute(() -> {
            calAddDao.insert(calAdd);
        });
    }

    void delete(CalAdd calAdd) {
        AppRoomDatabase.databaseWriteExecutor.execute(() -> {
            calAddDao.delete(calAdd);
        });
    }

    void insert(CalSub calSub) {
        AppRoomDatabase.databaseWriteExecutor.execute(() -> {
            calSubDao.insert(calSub);
        });
    }

    void delete(CalSub calSub) {
        AppRoomDatabase.databaseWriteExecutor.execute(() -> {
            calSubDao.delete(calSub);
        });
    }

    LiveData<List<User>> getAllUsers(){return allUsers;}

    LiveData<List<Weight>> getAllWeights(){return allWeights;}

    LiveData<List<CalAdd>> getAllCalAdds(){return allCalAdds;}

    LiveData<List<CalSub>> getAllCalSubs(){return allCalSubs;}

    User getNamedUser(String first, String last){
        Future<User> futureUser = AppRoomDatabase.databaseReadExecutor.submit(()-> userDao.getNamedUser(first, last));
        try {
            getNamedUser = futureUser.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return getNamedUser;
    }

    LiveData<List<Weight>> getUsersWeights(int userID){
        Future<LiveData<List<Weight>>> futureUserWeights = AppRoomDatabase.databaseReadExecutor.submit(()-> weightDao.getUsersWeights(userID));
        try {
            allUserWeights = futureUserWeights.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return allUserWeights;
    }

    LiveData<List<CalAdd>> getUsersCalAdds(int userID){
        Future<LiveData<List<CalAdd>>> futureUserCalAdds = AppRoomDatabase.databaseReadExecutor.submit(()-> calAddDao.getUsersCalAdds(userID));
        try {
            allUserCalAdds = futureUserCalAdds.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return allUserCalAdds;
    }

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
