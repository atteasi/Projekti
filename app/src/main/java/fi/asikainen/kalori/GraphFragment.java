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

/**
 * @Author Atte Asikainen
 */
/**
 * The fragment used to display graphs based on the user inputted data and delete the inputted data
 */
public class GraphFragment extends Fragment {

    Gson gson = new Gson();
    private ArrayList<Liikunta> liikunnat= new ArrayList<>();
    private ArrayList<Ruoka> ruuat = new ArrayList<>();

    /**
     * The onCreate method of this fragment, handling the layout items, graph data point crations and displaying the graphs
     * @return The view that os created
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.graphs_fragment, container, false);

        SharedPreferences share = getActivity().getSharedPreferences("SharedPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = share.edit();
        //Setting up the graphs
        GraphView ruokaGraph = (GraphView) v.findViewById(R.id.ruoka_graph);
        GraphView liikuntaGraph = (GraphView) v.findViewById(R.id.liikunta_graph);
        //Retrieving the ruuat- and liikunnat-ArrayLists
        String liikunta = share.getString("liikunnat", null);
        String ruoka = share.getString("ruuat", null);
        Type liikuntaTyyppi = new TypeToken<ArrayList<Liikunta>>() {}.getType();
        Type ruokaTyyppi = new TypeToken<ArrayList<Ruoka>>() {}.getType();
        liikunnat = gson.fromJson(liikunta, liikuntaTyyppi);
        ruuat = gson.fromJson(ruoka, ruokaTyyppi);
        //If ruuat is null, a new ArrayList is created instead
        if(ruuat == null){
            ruuat = new ArrayList<>();
        }
        //Creating the data points for the graph
        if(ruuat.size() > 0) {
            LineGraphSeries<DataPoint> ruokaSetti = new LineGraphSeries<>();
            for(int i = 0; i < ruuat.size(); i++){
                DataPoint ruokaPointti = new DataPoint(i, ruuat.get(i).getKalorit());
                ruokaSetti.appendData(ruokaPointti, true, ruuat.size());
            }
            //Adding the datapoints to the graph
            ruokaGraph.addSeries(ruokaSetti);
        }
        //If liikunnat is null, a new ArrayList is created instead
        if (liikunnat == null){
            liikunnat = new ArrayList<>();
        }
        //Creating the data points for the graph
        if (liikunnat.size() > 0){
            LineGraphSeries<DataPoint> liikuntaSetti = new LineGraphSeries<>();
            for(int i = 0; i < liikunnat.size(); i++){
                DataPoint liikuntaPointti = new DataPoint(i, liikunnat.get(i).getKalorit());
                liikuntaSetti.appendData(liikuntaPointti,true, liikunnat.size());
            }
            //Adding the datapoints to the graph
            liikuntaGraph.addSeries(liikuntaSetti);
        }
        //Setting up the button
        Button clear = (Button) v.findViewById(R.id.clearaus);
        //The button functionality: Clears all the data that has been inputted and clears the graphs
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit.remove("ruuat");
                edit.remove("liikunnat");
                edit.commit();
                ruokaGraph.removeAllSeries();
                liikuntaGraph.removeAllSeries();
            }
        });
        return v;
    }
}

