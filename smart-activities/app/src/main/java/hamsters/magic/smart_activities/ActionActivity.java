package hamsters.magic.smart_activities;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import database.Action;
import database.KidActivity;
import repositories.ActionRepository;

/**
 * Created by Gosia on 2016-04-22.
 */
public class ActionActivity extends AppCompatActivity {

    private Long kidActivityId;
    private List<Action> actionList;
    private Action actualAction;
    private ListIterator<Action> listIterator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        this.kidActivityId = extras.getLong("ACTIVITY_ID");
        setContentView(R.layout.action_list);
        actionList = ActionRepository.getActionsByKidActivityId(this, kidActivityId);
        listIterator = actionList.listIterator();
        handleActionBehaviour();
        ImageButton button = (ImageButton)findViewById(R.id.button_next);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                handleActionBehaviour();
            }
        });

    }

    private void handleActionBehaviour() {

        if(listIterator.hasNext()) {

            actualAction = listIterator.next();
            TextView name = (TextView)findViewById(R.id.action_name);
            ImageView img = (ImageView)findViewById(R.id.action_picture);

            name.setText(actualAction.getName());
            Resources resources = getResources();
            int resourceId = resources.getIdentifier(actualAction.getImgUrl(), "drawable", getPackageName());
            img.setImageResource(resourceId);
            getWindow().getDecorView().findViewById(android.R.id.content).invalidate();
        }
        else{
            finish();
        }

    }


}
