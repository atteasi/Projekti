package fi.asikainen.kalori;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class HomeFragment extends Fragment {


    Gson gson = new Gson();
    private ArrayList<Ruoka> ruuat = new ArrayList<>();
    private ArrayList<Ruoka> todaysRuuat = new ArrayList<>();
    private int progress;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDate today = LocalDate.now();

        View v =  inflater.inflate(R.layout.home_fragment, container, false);
        ProgressBar dailyKalorit = (ProgressBar) v.findViewById(R.id.progressBar);
        SharedPreferences share = getActivity().getSharedPreferences("SharedPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = share.edit();
        String ruokaJson = share.getString("ruuat", null);
        Type tyyppi = new TypeToken<ArrayList<Ruoka>>() {}.getType();
        ruuat = gson.fromJson(ruokaJson, tyyppi);

        for(int i = 0; i < ruuat.size();i++){
            if(ruuat.get(i).getSubmissionDate() == today) {
                todaysRuuat.add(ruuat.get(i));
                progress += ruuat.get(i).getKalorit();
            }
        }
        dailyKalorit.setProgress(progress);

        return v;
    }
}
