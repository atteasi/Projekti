package fi.asikainen.kalori;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class GraphFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.bmi_fragment, container, false);

        SharedPreferences share = getActivity().getSharedPreferences("SharedPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = share.edit();
        Button clear = (Button) v.findViewById(R.id.clearaus);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit.remove("ruuat");
                edit.remove("liikunnat");
                edit.commit();
            }
        });
        return v;
    }
}

