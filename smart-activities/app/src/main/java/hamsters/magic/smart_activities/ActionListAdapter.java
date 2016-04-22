package hamsters.magic.smart_activities;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import database.Action;
import repositories.ActionRepository;

/**
 * Created by Gosia on 2016-04-22.
 */
public class ActionListAdapter extends ArrayAdapter<Action> {

    private final Context context;
    private List<Action> actionList;

    public ActionListAdapter(Context context, Long activityId) {
        super(context, -1);
        this.context = context;
        actionList = ActionRepository.getActionsByKidActivityId(context,activityId);
    }

    @Override
    public View getView(int position, View rowView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        rowView = inflater.inflate(R.layout.action_list_element, parent, false);
        TextView name = (TextView)rowView.findViewById(R.id.action_name);
        ImageView img = (ImageView)rowView.findViewById(R.id.action_picture);

        Action actionOnPosition  = actionList.get(position);
        name.setText(actionOnPosition.getName());
        InputStream stream = null;
        try {
            stream = context.getResources().getAssets().open(actionOnPosition.getImgUrl());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Drawable myDrawable = Drawable.createFromStream(stream, null);
        img.setImageDrawable(myDrawable);
        return rowView;
    }

    @Override
    public int getCount(){
        return this.actionList.size();
    }
}
