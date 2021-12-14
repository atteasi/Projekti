package fi.asikainen.kalori;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ricardo Nunes
 */
/**
 * Viewmodel Class to wrap and call for the Repository Class.
 */
public class ADBViewModel extends AndroidViewModel {

    private ADBRepository repository;
    private final LiveData<List<User>> userList; LiveData<List<Weight>> weightList;
                  LiveData<List<CalAdd>> calAddList; LiveData<List<CalSub>> calSubList;


    public ADBViewModel(Application application){
        super(application);
        repository = new ADBRepository(application);

        userList = repository.getAllUsers();
        weightList = repository.getAllWeights();
        calAddList = repository.getAllCalAdds();
        calSubList = repository.getAllCalSubs();
    }

    public void insert(User user) {repository.insert(user); }

    public void delete(User user) {repository.delete(user);}

    public void insert(Weight weight) {repository.insert(weight);}

    public void delete(Weight weight) {repository.delete(weight);}

    public void insert(CalAdd calAdd) {repository.insert(calAdd);}

    public void delete(CalAdd calAdd) {repository.delete(calAdd);}

    public void insert(CalSub calSub) {repository.insert(calSub);}

    public void delete(CalSub calSub) {repository.delete(calSub);}

    LiveData<List<User>> getAllUsers(){return userList;}

    LiveData<List<Weight>> getAllWeights(){return weightList;}

    LiveData<List<CalAdd>> getAllCalAdds(){return calAddList;}

    LiveData<List<CalSub>> getAllCalSubs(){return calSubList;}

    User getNamedUser(String first, String last){return repository.getNamedUser(first, last);}

    LiveData<List<Weight>> getUsersWeights(int userID){return repository.getUsersWeights(userID);}

    LiveData<List<CalAdd>> getUsersCalAdds(int userID){return repository.getUsersCalAdds(userID);}

    LiveData<List<CalSub>> getUsersCalSubs(int UserID){return repository.getUsersCalSubs(UserID);}

}
