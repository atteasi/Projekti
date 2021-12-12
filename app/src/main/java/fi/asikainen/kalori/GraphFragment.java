package fi.asikainen.kalori;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
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
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.graphs_fragment, container, false);

        SharedPreferences share = getActivity().getSharedPreferences("SharedPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = share.edit();

        GraphView ruokaGraph = (GraphView) v.findViewById(R.id.graph);
        if(ruuat.size() > 0) {
            LineGraphSeries<DataPoint> series = new LineGraphSeries<>();
                for(int i = 0; i < ruuat.size(); i++){
                    DataPoint pointti = new DataPoint(i, ruuat.get(i).getKalorit());
                    series.appendData(pointti, true, ruuat.size());
            }
            ruokaGraph.addSeries(series);
        }
        String liikunta = share.getString("liikunnat", null);
        String ruoka = share.getString("ruuat", null);
        Type liikuntaTyyppi = new TypeToken<ArrayList<Liikunta>>() {}.getType();
        Type ruokaTyyppi = new TypeToken<ArrayList<Ruoka>>() {}.getType();
        liikunnat = gson.fromJson(liikunta, liikuntaTyyppi);
        ruuat = gson.fromJson(ruoka, ruokaTyyppi);
        if(ruuat == null){
            ruuat = new ArrayList<>();
        }
        if(ruuat.size() > 0) {
            LineGraphSeries<DataPoint> ruokaSetti = new LineGraphSeries<>();
            for(int i = 0; i < ruuat.size(); i++){
                DataPoint ruokaPointti = new DataPoint(i, ruuat.get(i).getKalorit());
                ruokaSetti.appendData(ruokaPointti, true, ruuat.size());
            }
            ruokaGraph.addSeries(ruokaSetti);
        }
        if (liikunnat.size() > 0){
            LineGraphSeries<DataPoint> liikuntaSetti = new LineGraphSeries<>();
            for(int i = 0; i < liikunnat.size(); i++){
                DataPoint liikuntaPointti = new DataPoint(i, liikunnat.get(i).getKalorit());
                liikuntaSetti.appendData(liikuntaPointti,true, liikunnat.size());
            }
        }
        Button clear = (Button) v.findViewById(R.id.clearaus);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit.remove("ruuat");
                edit.remove("liikunnat");
                edit.commit();
                ruokaGraph.removeAllSeries();
            }
        });
        return v;
    }
}

