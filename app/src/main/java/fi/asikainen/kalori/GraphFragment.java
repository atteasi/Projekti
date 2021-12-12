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

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.lang.reflect.Type;
import java.util.ArrayList;


public class GraphFragment extends Fragment {

    Gson gson = new Gson();
    private ArrayList<Liikunta> liikunnat= new ArrayList<>();
    private ArrayList<Ruoka> ruuat = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.graphs_fragment, container, false);

        SharedPreferences share = getActivity().getSharedPreferences("SharedPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = share.edit();

        GraphView graph = (GraphView) v.findViewById(R.id.graph);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(0, 1),
                new DataPoint(1, 5),
                new DataPoint(2, 3),
                new DataPoint(3, 2),
                new DataPoint(4, 6)
        });

        String liikunta = share.getString("liikunnat", null);
        String ruoka = share.getString("ruuat", null);
        Type liikuntaTyyppi = new TypeToken<ArrayList<Liikunta>>() {}.getType();
        Type ruokaTyyppi = new TypeToken<ArrayList<Ruoka>>() {}.getType();
        liikunnat = gson.fromJson(liikunta, liikuntaTyyppi);
        ruuat = gson.fromJson(ruoka, ruokaTyyppi);

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

