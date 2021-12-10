package fi.asikainen.kalori;

import android.content.Context;
import android.content.SharedPreferences;
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
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class KaloriPlusFragment extends Fragment {
    private ArrayAdapter<Ruoka> listaaja;
    private ArrayList<Ruoka> ruuat = new ArrayList<>();
    Gson gson = new Gson();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.kalori_plus_fragment, container, false);
        SharedPreferences share = getActivity().getSharedPreferences("SharedPref", Context.MODE_PRIVATE);
        String json = share.getString("ruuat", null);
        Type tyyppi = new TypeToken<ArrayList<Ruoka>>() {}.getType();
        ruuat = gson.fromJson(json,tyyppi);
        Button add = (Button) v.findViewById(R.id.button);
        EditText ruoka = v.findViewById(R.id.ruoka);
        EditText kalorit = v.findViewById(R.id.kalorit);
        ListView ruokalista = v.findViewById(R.id.ruokalista);
        this.listaaja = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, ruuat);
        ruokalista.setAdapter(listaaja);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String addRuoka = ruoka.getText().toString();
                String addKalorit = kalorit.getText().toString();
                ruuat.add(new Ruoka(addKalorit, addRuoka));
                ruoka.setText("");
                kalorit.setText("");
                listaaja.notifyDataSetChanged();
            }
        });
        return v;
    }

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