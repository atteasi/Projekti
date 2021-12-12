package fi.asikainen.kalori;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class BMIFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.bmi_fragment, container, false);

        EditText pituus = v.findViewById(R.id.pituus);
        EditText paino = v.findViewById(R.id.paino);
        Button nappi = (Button) v.findViewById(R.id.nappi);
        TextView bmiText = v.findViewById(R.id.bmi);

        nappi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String height = pituus.getText().toString();
                int pituusInt = Integer.parseInt(height);
                String weight = paino.getText().toString();
                int painoInt = Integer.parseInt(weight);
                float bmi = (float) painoInt / (pituusInt * pituusInt);
                bmiText.setText(String.valueOf(bmi));
            }
        });
        return v;
    }
}
