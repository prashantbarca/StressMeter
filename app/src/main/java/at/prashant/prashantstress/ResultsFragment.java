package at.prashant.prashantstress;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Environment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * Importing chart libraries
 */
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.ChartData;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.view.Chart;
import lecho.lib.hellocharts.view.LineChartView;


public class ResultsFragment extends Fragment {
    public ResultsFragment() {

        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /*
     * File Reading and returning an Map of Strings
     */
    Map<String, String> read()
    {
        File f = new File(Environment.getExternalStorageDirectory(),"stress.csv");
        FileInputStream fileInputStream;
        BufferedReader reader;
        String line;
        Map<String, String> map = new HashMap<>();
        try {
            fileInputStream = new FileInputStream(f);
            reader = new BufferedReader(new InputStreamReader(fileInputStream));
            while((line = reader.readLine())!=null)
            {
                map.put(line.split(",")[0],line.split(",")[1]);
            }
        }
        catch(Exception fnfe)
        {
            fnfe.printStackTrace();
        }
        return map;
    }

    /*
     * This method was inspired from the github documentation of the library.
     * https://github.com/lecho/hellocharts-android
     *
     */
    void drawGraph(View view)
    {
        LineChartView chart = (LineChartView) view.findViewById(R.id.chart);
        Axis xAxis = new Axis();
        Axis yAxis = new Axis().setHasLines(true);
        xAxis.setName("Instances");
        yAxis.setName("Stress level");
        LineChartData data = new LineChartData();
        data.setAxisXBottom(xAxis);
        data.setAxisYLeft(yAxis);
        List<PointValue> points = new ArrayList<>();
        int i = 0;
        for(String s: read().keySet()) // read() returns a Map<String, String>
        {
            points.add(new PointValue(i, Integer.parseInt(read().get(s))));
            i = i + 1;
        }
        Line line = new Line(points).setColor(Color.BLUE).setCubic(true);
        line.setFilled(true);
        List<Line> lines = new ArrayList<Line>();
        lines.add(line);
        data.setLines(lines);
        chart.setLineChartData(data);
    }

    void populateTable(View view)
    {
        TableLayout tableLayout = (TableLayout) view.findViewById(R.id.table1);
        int i = 0;
        for(String key: read().keySet())
        {
            Log.d(key, read().get(key));
            TableRow row = new TableRow(getActivity());
            TextView v1 = new TextView(getActivity());
            TextView v2 = new TextView(getActivity());
            v1.setText(key);
            v1.setPadding(0,0,400,0); // Padding between the two columns
            v2.setText(read().get(key));
            v2.setGravity(Gravity.RIGHT);
            v1.setTypeface(null,Typeface.BOLD);
            v2.setTypeface(null,Typeface.BOLD);
            if(i%2 == 0)
            {
                row.setBackgroundColor(Color.CYAN);
            }
            else
            {
                row.setBackgroundColor(Color.YELLOW);
            }
            row.addView(v1);
            row.addView(v2);
            tableLayout.addView(row);
            i = i + 1; // Used to alternate color
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_results, container, false);
        MainActivity.stopAlert(); // Shut the sound off
        populateTable(view);
        drawGraph(view);
        return view;
    }
}
