package hamsters.magic.smart_activities;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gosia on 2016-04-22.
 */
public class KidActivityListAdapter extends ArrayAdapter<KidActivity> {


    private final Context context;
    private final List<KidActivity> kidActivityList;

    public KidActivityListAdapter(Context context){
        super(context, -1, new ArrayList<KidActivity>());
        this.context = context;
        this.kidActivityList = new ArrayList<KidActivity>(){{
            add(new KidActivity("ACTIVITY_1", "img/adr"));
            add(new KidActivity("ACTIVITY_2", "img/adr"));
        }};
    }

    @Override
    public View getView(int position, View rowView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        rowView = inflater.inflate(R.layout.activity_list_element, parent, false);
        TextView description = (TextView)rowView.findViewById(R.id.description);
        ImageView img = (ImageView)rowView.findViewById(R.id.picture);

        KidActivity kidActivityOnPosition  = kidActivityList.get(position);
        description.setText(kidActivityOnPosition.description);
        img.setImageResource(R.drawable.abc);
        return rowView;
    }

    @Override
    public int getCount(){
        return this.kidActivityList.size();
    }


}
