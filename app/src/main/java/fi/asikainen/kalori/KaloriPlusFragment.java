package fi.asikainen.kalori;

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
import androidx.fragment.app.FragmentActivity;
import java.util.ArrayList;

public class KaloriPlusFragment extends Fragment {
    final Button add = getView().findViewById(R.id.button);
    private ArrayList<Ruoka> ruuat = new ArrayList<>();
    private ArrayAdapter<Ruoka> listAdapter;
    ListView ruokalista = getView().findViewById(R.id.ruokalista);

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.kalori_plus_fragment, container, false);
        this.listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, ruuat);
        ruokalista.setAdapter(listAdapter);
    }



    public void onClick(){
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText ruoka = getView().findViewById(R.id.ruoka);
                EditText kalorit = getView().findViewById(R.id.kalorit);
                String addRuoka = ruoka.getText().toString();
                String addKalorit = kalorit.getText().toString();
                ruuat.add(new Ruoka(addKalorit, addRuoka));
            }
        });
    }
}

