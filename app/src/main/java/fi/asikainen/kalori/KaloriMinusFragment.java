package fi.asikainen.kalori;

import static fi.asikainen.kalori.Ruoka.ruuat;

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

public class KaloriMinusFragment extends Fragment {

    private ArrayAdapter<Liikunta> listaaja;
    private ArrayList<Liikunta> liikunnat = new ArrayList<>();
    Gson gson = new Gson();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.kalori_minus_fragment, container, false);
        SharedPreferences share = getActivity().getSharedPreferences("SharedPref", Context.MODE_PRIVATE);
        String json = share.getString("liikunta", null);
        Type tyyppi = new TypeToken<ArrayList<Liikunta>>() {}.getType();
        liikunnat = gson.fromJson(json,tyyppi);
        Button add = (Button) v.findViewById(R.id.lisäys_nappi);
        EditText liikunta = v.findViewById(R.id.liikunta_edit);
        EditText kalorit = v.findViewById(R.id.kalorit_edit);
        ListView liikuntaLista = v.findViewById(R.id.liikuntalista);

        this.listaaja = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, liikunnat);
        liikuntaLista.setAdapter(listaaja);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String addLiikunta = liikunta.getText().toString();
                String addKalorit = kalorit.getText().toString();
                    liikunnat.add(new Liikunta(addLiikunta, addKalorit));
                    liikunta.setText("");
                    kalorit.setText("");
                    listaaja.notifyDataSetChanged();
            }
        });
        return v;
    }

    public void onPause() {
        super.onPause();
        String json = gson.toJson(liikunnat);
        SharedPreferences share = getActivity().getSharedPreferences("SharedPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = share.edit();
        edit.putString("liikunnat", json);
        edit.commit();
    }

}

