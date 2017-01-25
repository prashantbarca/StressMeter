package at.prashant.prashantstress;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Map;


public class ResultsFragment extends Fragment {
    public ResultsFragment() {

        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        SharedPreferences keyValues = getContext().getSharedPreferences("stress_meter", getContext().MODE_PRIVATE);

        // For debugging purposes -- Check if all the stress values were entered
        for (Map.Entry<String, ?> entry : keyValues.getAll().entrySet())
        {
            Log.d("1",entry.getKey() + "/" + entry.getValue());
        }

        View view = inflater.inflate(R.layout.fragment_results, container, false);
        MainActivity.stopAlert();
        return view;
    }
}
