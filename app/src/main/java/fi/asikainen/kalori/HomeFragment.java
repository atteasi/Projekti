package fi.asikainen.kalori;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * @Author Atte Asikainen
 */
/**
 * The Home Screen fragment, shows the added calory - data for today and the progressbar that shows that how much of your daily
 * calories are consumed
 */
public class HomeFragment extends Fragment {


    Gson gson = new Gson();
    private ArrayList<Ruoka> ruuat = new ArrayList<>();
    private ArrayList<Ruoka> todaysRuuat = new ArrayList<>();
    private ArrayAdapter<Ruoka> listaaja;
    private int progress;

    /**
     * The onCreate method of this fragment, handling all the layout assignments, listing todays added calory-data and the progress bars progress
     * @return v variable with the View
     */
    @RequiresApi(api = Build.VERSION_CODES.O)

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Variable of todays date and a formatter to format the date into a String
        LocalDate date = LocalDate.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/YYYY");

        View v =  inflater.inflate(R.layout.home_fragment, container, false);
        //Assigning the layout items
        ProgressBar dailyKalorit = (ProgressBar) v.findViewById(R.id.progressBar);
        ListView list = v.findViewById(R.id.list);

        SharedPreferences share = getActivity().getSharedPreferences("SharedPref", Context.MODE_PRIVATE);
        String json = share.getString("ruuat", null);
        Type tyyppi = new TypeToken<ArrayList<Ruoka>>() {}.getType();
        ruuat = gson.fromJson(json, tyyppi);
        //If the ruuat-ArrayList return a null, a new ArrayList is created instead
        if(ruuat == null){
            ruuat = new ArrayList<>();
        }
        //Creating the ArrayList that only contains todays added calory-data
        for(int i = 0; i < ruuat.size();i++){
           if(ruuat.get(i).getSubmissionDate().compareTo(dtf.format(date)) == 0) {
                todaysRuuat.add(new Ruoka(ruuat.get(i).getKalorit(), ruuat.get(i).getNimi(), date));
                progress += ruuat.get(i).getKalorit();
            }
        }
        //Setting the adapter for the listview
        this.listaaja = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, todaysRuuat);
        list.setAdapter(listaaja);
        //Sets the progress amount for the progress bar
        dailyKalorit.setProgress(progress);

        return v;
    }
}
