package hamsters.magic.smart_activities;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
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
import repositories.KidActivityRepository;

/**
 * Created by Gosia on 2016-04-22.
 */
public class ActionActivity extends AppCompatActivity {

    private Long kidActivityId;
    private List<Action> actionList;
    private Action actualAction;
    private ListIterator<Action> listIterator;
    private TextToSpeech t1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        this.kidActivityId = extras.getLong("ACTIVITY_ID");
        setContentView(R.layout.action_list);
        actionList = ActionRepository.getActionsByKidActivityId(this, kidActivityId);
        listIterator = actionList.listIterator();
        initTextToSpeech();
        handleActionBehaviour();
        ImageButton button = (ImageButton)findViewById(R.id.button_next);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                handleActionBehaviour();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        new BackgroundTask(this).execute();
    }

    private void initTextToSpeech() {
        t1=new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {}
            }
        });
    }

    private void handleActionBehaviour() {
        if (listIterator.hasNext()) {
            actualAction = listIterator.next();
            TextView name = (TextView)findViewById(R.id.action_name);
            ImageView img = (ImageView)findViewById(R.id.action_picture);

            name.setText(actualAction.getName());
            Resources resources = getResources();
            int resourceId = resources.getIdentifier(actualAction.getImgUrl(), "drawable", getPackageName());
            img.setImageResource(resourceId);
            getWindow().getDecorView().findViewById(android.R.id.content).invalidate();

            t1.speak(actualAction.getName(), TextToSpeech.QUEUE_FLUSH, null);
        }
        else {
            getIntent().setAction(null);
            Intent intent = new Intent(ActionActivity.this, MainActivity.class);
            ActionActivity.this.startActivity(intent);
        }

    }

    private class BackgroundTask extends AsyncTask<Void, Void, String> {

        private Context context;

        public BackgroundTask(Context context) {
            this.context = context;
        }

        @Override
        protected String doInBackground(Void... params) {
            return null;
        }

        @Override
        protected void onPostExecute(String param) {
            t1 = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
                @Override
                public void onInit(int status) {
                    if(status != TextToSpeech.ERROR) {
                        //t1.setLanguage(Locale.UK);
                        t1.speak(actualAction.getName(), TextToSpeech.QUEUE_FLUSH, null);
                    }
                }
            });
        }
    }
}
