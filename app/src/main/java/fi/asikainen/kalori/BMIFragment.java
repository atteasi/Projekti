package fi.asikainen.kalori;

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

import java.text.DecimalFormat;

/**
 * @Author Atte Asikainen
 */
/**
 * The fragment that calculates the users BMI
 */
public class BMIFragment extends Fragment {

    /**
     * The onCreate method of this fragment, handling all the layout items, calculating the BMI based on input data and the button functionality
     * @return the view that is created
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.bmi_fragment, container, false);
        //A formatter for the BMI
        DecimalFormat df = new DecimalFormat("0.00");
        //Assigning the layout items
        EditText pituus = v.findViewById(R.id.pituus);
        EditText paino = v.findViewById(R.id.paino);
        Button nappi = (Button) v.findViewById(R.id.lasku_nappi);
        TextView bmiText = v.findViewById(R.id.bmi);
        //The button functionality, takes the inputted height and weight data and calculates the BMI from them
        nappi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String height = pituus.getText().toString();
                float pituusInt = (float) (Double.parseDouble(height) / 100);
                String weight = paino.getText().toString();
                int painoInt = Integer.parseInt(weight);
                if (painoInt > 0 && pituusInt > 0) {
                    float bmi = (float) painoInt / (pituusInt * pituusInt);
                    String formattedBmi = df.format(bmi);
                    bmiText.setText(formattedBmi);
                }
            }
        });
        return v;
    }
}
