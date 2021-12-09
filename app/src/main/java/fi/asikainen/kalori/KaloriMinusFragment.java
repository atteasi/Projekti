package fi.asikainen.kalori;

import static fi.asikainen.kalori.Ruoka.ruuat;

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

import java.util.ArrayList;

public class KaloriMinusFragment extends Fragment {

    private ArrayAdapter<Liikunta> listaaja;
    private ArrayList<Liikunta> liikunnat = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.kalori_plus_fragment, container, false);
        Button add = (Button) v.findViewById(R.id.button);
        EditText liikunta = v.findViewById(R.id.liikunta_edit);
        EditText kalorit = v.findViewById(R.id.kalorit_edit);
        ListView liikuntaLista = v.findViewById(R.id.ruokalista);
        this.listaaja = new ArrayAdapter<Liikunta>(getContext(), android.R.layout.simple_list_item_1, liikunnat);
        liikuntaLista.setAdapter(listaaja);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String addLiikunta = liikunta.getText().toString();
                String addKalorit = kalorit.getText().toString();
                if(addLiikunta != "" && addKalorit != "") {
                    new Ruoka(addLiikunta, addKalorit);
                    liikunta.setText("");
                    kalorit.setText("");
                    listaaja.notifyDataSetChanged();
                }
            }
        });
        return v;
    }
    }
}
