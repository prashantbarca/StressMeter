package at.prashant.prashantstress;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
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

        String time = String.valueOf(System.currentTimeMillis()/1000);

        try
        {
            File root = Environment.getExternalStorageDirectory();
            File file = new File(root, "stress.csv");
            if(!file.exists())
            {
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file, true);
            fileWriter.write(time +","+stressLevel + "\n");
            fileWriter.close();
        }
        catch(IOException ioe)
        {
            ioe.printStackTrace();
        }
        setResult(Activity.RESULT_OK, null); // Setting the result value
        finish();
    }
}
