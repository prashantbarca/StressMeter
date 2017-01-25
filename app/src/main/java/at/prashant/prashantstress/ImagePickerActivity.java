package at.prashant.prashantstress;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

/*
 * Importing chart libraries
 */
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.ChartData;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.view.Chart;
import lecho.lib.hellocharts.view.LineChartView;

public class ImagePickerActivity extends AppCompatActivity {

    /*
    * Inspired from here to use shared preferences.
    http://stackoverflow.com/questions/3876680/is-it-possible-to-add-an-array-or-object-to-
    sharedpreferences-on-android
     */

    int grid; // To fetch
    int position; // Position in grid
    int stressLevel; // Stress level variable

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        MainActivity.stopAlert();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_picker);
        Intent intent = getIntent();
        grid = intent.getIntExtra("GRID",5);
        position = intent.getIntExtra("POS", 5);
        ImageView imageView = (ImageView) findViewById(R.id.imageview);
        imageView.setImageResource(PSM.getGridById(grid)[position]);
    }
    public void onCancel(View view)
    {
        finish();
    }

    public void onSave(View view)
    {
        switch(position)
        {
            case 1:
                stressLevel = 6;
                break;
            case 2:
                stressLevel = 8;
                break;
            case 3:
                stressLevel = 14;
                break;
            case 4:
                stressLevel = 16;
                break;
            case 5:
                stressLevel = 5;
                break;
            case 6:
                stressLevel = 7;
                break;
            case 7:
                stressLevel = 13;
                break;
            case 8:
                stressLevel = 15;
                break;
            case 9:
                stressLevel = 2;
                break;
            case 10:
                stressLevel = 4;
                break;
            case 11:
                stressLevel = 10;
                break;
            case 12:
                stressLevel = 12;
                break;
            case 13:
                stressLevel = 1;
                break;
            case 14:
                stressLevel = 3;
                break;
            case 15:
                stressLevel = 9;
                break;
            case 16:
                stressLevel = 11;
                break;
            default:break;
        }
        /* Shared preferences to save the value of the stress clicked */
        SharedPreferences keyValues = getSharedPreferences("stress_meter", MODE_PRIVATE);
        SharedPreferences.Editor keyValuesEditor = keyValues.edit();
        String time = String.valueOf(System.currentTimeMillis()/1000);
        keyValuesEditor.putString(time, String.valueOf(stressLevel));
        keyValuesEditor.commit();
        /* Shared preferences end here */
        setResult(Activity.RESULT_OK, null); // Setting the result value
        finish();
    }
}
