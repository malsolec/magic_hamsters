package hamsters.magic.smart_activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

/**
 * Created by Gosia on 2016-04-22.
 */
public class ActionActivity extends AppCompatActivity {

    private Long kidActivityId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        this.kidActivityId = extras.getLong("ACTIVITY_ID");
        setContentView(R.layout.action_list);
        setUpActionList();
    }

    private void setUpActionList() {
        final ListView activityListView  = (ListView) findViewById(R.id.action_list);
        ActionListAdapter actionListAdapter = new ActionListAdapter(this.getApplicationContext(),this.kidActivityId);
        activityListView.setAdapter(actionListAdapter);

    }

}
