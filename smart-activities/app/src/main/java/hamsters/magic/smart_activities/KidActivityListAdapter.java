package hamsters.magic.smart_activities;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import database.KidActivity;
import repositories.KidActivityRepository;

/**
 * Created by Gosia on 2016-04-22.
 */
public class KidActivityListAdapter extends ArrayAdapter<KidActivity> {


    private final Context context;
    private final List<KidActivity> kidActivityList;

    public KidActivityListAdapter(Context context){
        super(context, -1, new ArrayList<KidActivity>());
        this.context = context;
        this.kidActivityList = KidActivityRepository.getAllKidsActivities(context);
    }

    @Override
    public View getView(int position, View rowView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        rowView = inflater.inflate(R.layout.activity_list_element, parent, false);
        TextView name = (TextView)rowView.findViewById(R.id.name);
        ImageView img = (ImageView)rowView.findViewById(R.id.picture);

        KidActivity kidActivityOnPosition  = kidActivityList.get(position);
        name.setText(kidActivityOnPosition.getName());
        InputStream stream = null;
        try {
            stream = context.getResources().getAssets().open(kidActivityOnPosition.getImgUrl());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Drawable myDrawable = Drawable.createFromStream(stream, null);
        img.setImageDrawable(myDrawable);
        return rowView;
    }

    @Override
    public int getCount(){
        return this.kidActivityList.size();
    }


}
