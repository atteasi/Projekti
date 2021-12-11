package fi.asikainen.kalori;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.List;

public class ADBViewModel extends AndroidViewModel {

    private ADBRepository repository;
    private final LiveData<List<User>> userList;
    private final LiveData<List<Weight>> weightList;
    //private User getNamedUser;

    public ADBViewModel(Application application){
        super(application);
        repository = new ADBRepository(application);

        userList = repository.getUser();
        weightList = repository.getAllWeights();
        //getNamedUser = repository.getNamedUser(getNamedUser.nameFirst, getNamedUser.nameLast);

    }

    public void insert(User user) {repository.insert(user); }

    User getNamedUser(String first, String last){
        return repository.getNamedUser(first, last);
    }

    LiveData<List<User>> getAllUsers(){
        return userList;
    }

    LiveData<List<Weight>> getAllWeights(){return weightList;}

}
