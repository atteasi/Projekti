package fi.asikainen.kalori;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * @Author Atte Asikainen
 */
/**
 * The fragment used to log exerices that burn calories
 */

public class KaloriMinusFragment extends Fragment {

    private ArrayAdapter<Liikunta> listaaja;
    private ArrayList<Liikunta> liikunnat = new ArrayList<>();
    Gson gson = new Gson();
    /**
     * The onCreate method of this fragment, handling all the layout assignments, listing of the previously added data and the button functionality
     * @return the view that is created
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.kalori_minus_fragment, container, false);
        //Gets todays date and saves it as a LocalDate variable
        LocalDate date = LocalDate.now();
        SharedPreferences share = getActivity().getSharedPreferences("SharedPref", Context.MODE_PRIVATE);
        String json = share.getString("liikunnat", null);
        Type tyyppi = new TypeToken<ArrayList<Liikunta>>() {}.getType();
        liikunnat = gson.fromJson(json, tyyppi);
        // If the retrieved ArrayList is null, a new one is created instead
        if(liikunnat == null){
            liikunnat = new ArrayList<>();
        }
        //Setting up the layout items
        Button add = (Button) v.findViewById(R.id.nappi);
        EditText liikunta = v.findViewById(R.id.liikunta_edit);
        EditText kalorit = v.findViewById(R.id.kalorit_edit);
        ListView liikuntaLista = v.findViewById(R.id.liikuntalista);
        // Setting up the ArrayAdapter for the ListView
        this.listaaja = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, liikunnat);
        liikuntaLista.setAdapter(listaaja);
        //The button functionality: Adds the name of the entry and burned calories to the ArrayList and clears the EditTexts
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String addLiikunta = liikunta.getText().toString();
                String kaloriValue = kalorit.getText().toString();
                int addKalorit = Integer.parseInt(kaloriValue);
                //If the addKalorit value is more than 0, the user inputted data is saved into the ArrayList
                if (addKalorit > 0) {
                    liikunnat.add(new Liikunta(addLiikunta, addKalorit, date));
                    liikunta.setText("");
                    kalorit.setText("0");
                    listaaja.notifyDataSetChanged();
                }
            }
        });
        return v;
    }

    /**
     * Saves the data of liikunnat-ArrayList when the fragment goes on pause
     */
    public void onPause() {
        super.onPause();
        SharedPreferences share = getActivity().getSharedPreferences("SharedPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = share.edit();
        // Saves the liikunnat-ArrayList
        String json = gson.toJson(liikunnat);
        edit.putString("liikunnat", json);
        edit.commit();
    }

}

