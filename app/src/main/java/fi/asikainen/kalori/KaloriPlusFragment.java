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
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * @Author Atte Asikainen
 */
/**
 * The fragment used to log the intake of calories
*/
@RequiresApi(api = Build.VERSION_CODES.O)
public class KaloriPlusFragment extends Fragment {
    // The ArrayAdapter of the ListView used in this fragment, ArrayList that has the info about the entries the user gives,
    // and the Gson used in the fragment
    private ArrayAdapter<Ruoka> listaaja;
    private ArrayList<Ruoka> ruuat = new ArrayList<>();
    Gson gson = new Gson();

    /**
     * The method that dictates what happens when the view is created
     * @return The view that is created when selecting Kalori+-fragment
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //The view that is created when selecting the Kalori+-fragment
        View v = inflater.inflate(R.layout.kalori_plus_fragment, container, false);
        LocalDate date = LocalDate.now();
        //Initialization of the SharedPreferences and the retrieval of the ruuat-ArrayList
        SharedPreferences share = getActivity().getSharedPreferences("SharedPref", Context.MODE_PRIVATE);
        String json = share.getString("ruuat", null);
        Type tyyppi = new TypeToken<ArrayList<Ruoka>>() {}.getType();
        ruuat = gson.fromJson(json,tyyppi);
        //In case the retrieval provides null, a ruuat-arraylist is created
        if(ruuat == null){
            ruuat = new ArrayList<>();
        }
        //Initialization of the different UI-items in the layout of the fragment
        Button add = (Button) v.findViewById(R.id.button);
        EditText ruoka = v.findViewById(R.id.ruoka);
        EditText kalorit = v.findViewById(R.id.kalorit);
        ListView ruokalista = v.findViewById(R.id.ruokalista);
        //Setting the adapter for the layouts ListView
        this.listaaja = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, ruuat);
        ruokalista.setAdapter(listaaja);
        //The button functionality: It saves the logged entry and clears the EditText fields ready for a new entry. At the end, updates the list
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String addRuoka = ruoka.getText().toString();
                String kaloriValue = kalorit.getText().toString();
                int addKalorit = Integer.parseInt(kaloriValue);
                // If the calories of the given data are above 0, the data is added and the EditText-fields are cleared
                if(addKalorit > 0) {
                    ruuat.add(new Ruoka(addKalorit, addRuoka, date));
                    ruoka.setText("");
                    kalorit.setText("0");
                    listaaja.notifyDataSetChanged();
                }
            }
        });
        return v;
    }

    /**
     * The onPause method makes sure the app saves the ruuat-ArrayList when putting the Kalori+ -fragment on pause
     */
    @Override
    public void onPause() {
        super.onPause();
        SharedPreferences share = getActivity().getSharedPreferences("SharedPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = share.edit();
        String json = gson.toJson(ruuat);
        edit.putString("ruuat", json);
        edit.commit();
    }
}