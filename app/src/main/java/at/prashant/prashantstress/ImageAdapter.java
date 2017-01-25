package at.prashant.prashantstress;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;

/**
 * Created by prashant on 1/24/17.
 */
public class ImageAdapter extends BaseAdapter {
    Context context;
    int gridNumber;
    public ImageAdapter(Context context, int gridNumber) {
        this.context = context;
        this.gridNumber = gridNumber;
    }

    @Override
    public int getCount()
    {
        return PSM.getGridById(gridNumber).length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if(convertView == null){
            imageView = new ImageView(context);
        }
        else
        {
            imageView = (ImageView) convertView;
        }
        imageView.setLayoutParams(new GridView.LayoutParams(300, 300));
        // Since all images seem to be of this size
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageResource(PSM.getGridById(gridNumber)[position]);
        return imageView;
    }
}
