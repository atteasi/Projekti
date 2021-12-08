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

import java.util.ArrayList;

public class KaloriPlusFragment extends Fragment {
    final Button add = getView().findViewById(R.id.button);
    private static ArrayList<Ruoka> ruuat = new ArrayList<>();
    private ArrayAdapter<Ruoka> listAdapter;
    ListView ruokalista = getView().findViewById(R.id.ruokalista);
    EditText ruoka = getView().findViewById(R.id.ruoka);
    EditText kalorit = getView().findViewById(R.id.kalorit);

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.kalori_plus_fragment, container, false);
    }


/*
    public void onClick(){
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String addRuoka = ruoka.getText().toString();
                String addKalorit = kalorit.getText().toString();
                ruuat.add(new Ruoka(addKalorit, addRuoka));
                ruoka.setText("");
                kalorit.setText("");
            }
        });
    }*/
}

