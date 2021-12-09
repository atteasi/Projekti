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

public class KaloriPlusFragment extends Fragment {
    private ArrayAdapter<Ruoka> listAdapter;
    private ArrayList<Ruoka> ruuat = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.kalori_plus_fragment, container, false);
        Button add = (Button) v.findViewById(R.id.button);
        EditText ruoka = v.findViewById(R.id.ruoka);
        EditText kalorit = v.findViewById(R.id.kalorit);
        ListView ruokalista = v.findViewById(R.id.ruokalista);
        this.listAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, ruuat);
        ruokalista.setAdapter(listAdapter);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String addRuoka = ruoka.getText().toString();
                String addKalorit = kalorit.getText().toString();
                ruuat.add(new Ruoka(addKalorit, addRuoka));
                ruoka.setText("");
                kalorit.setText("");
                listAdapter.notifyDataSetChanged();
            }
        });
        return v;
    }
}