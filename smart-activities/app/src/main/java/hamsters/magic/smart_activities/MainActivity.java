package hamsters.magic.smart_activities;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import android.widget.Toast;
import junit.framework.Test;
import model.NfcConstants;
import nfc.NdefReaderTask;

import java.util.List;

import database.Action;
import database.KidActivity;
import repositories.KidActivityRepository;

public class MainActivity extends AppCompatActivity {

    private NfcAdapter mNfcAdapter;
    private KidActivityListAdapter kidActivityListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setUpActivitiesListContent();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        initNfcAdapter();
        handleIntent(getIntent());
    }

    private void setUpActivitiesListContent() {
        final ListView activityListView  = (ListView) findViewById(R.id.activities_list);
        kidActivityListAdapter = new KidActivityListAdapter(this.getApplicationContext());
        activityListView.setAdapter(kidActivityListAdapter);

        activityListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                getIntent().setAction(null);
                Intent intent = new Intent(MainActivity.this, ActionActivity.class);
                KidActivity selectedAction = (KidActivity) activityListView.getAdapter().getItem(position);
                intent.putExtra("ACTIVITY_ID", selectedAction.getId());
                MainActivity.this.startActivity(intent);
            }
        });
    }

    private void initNfcAdapter() {
        mNfcAdapter = NfcAdapter.getDefaultAdapter(this);
        if (mNfcAdapter == null) {
            // Stop here, we definitely need NFC
            Toast.makeText(this, "This device doesn't support NFC.", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        setupForegroundDispatch(this, mNfcAdapter);
    }

    @Override
    protected void onPause() {
        stopForegroundDispatch(this, mNfcAdapter);
        super.onPause();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        String action = intent.getAction();
        Log.d(NfcConstants.TAG, action);
        if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(action)) {
            String type = intent.getType();
            if (NfcConstants.MIME_TEXT_PLAIN.equals(type)) {
                Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
                new NdefReaderTask(MainActivity.this.getApplicationContext(), kidActivityListAdapter.getItem(0)).execute(tag);
            } else {
                Log.d(NfcConstants.TAG, "Wrong mime type: " + type);
            }
        } else if (NfcAdapter.ACTION_TECH_DISCOVERED.equals(action) || NfcAdapter.ACTION_TAG_DISCOVERED.equals(action)) {
            // In case we would still use the Tech Discovered Intent
            Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
            new NdefReaderTask(MainActivity.this.getApplicationContext(), kidActivityListAdapter.getItem(0)).execute(tag);
        }
    }

    public static void setupForegroundDispatch(final Activity activity, NfcAdapter adapter) {
        final Intent intent = new Intent(activity, activity.getClass());
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

        final PendingIntent pendingIntent = PendingIntent.getActivity(activity, 0, intent, 0);

        if (adapter != null)
            adapter.enableForegroundDispatch(activity, pendingIntent, null, null);
    }

    public static void stopForegroundDispatch(final Activity activity, NfcAdapter adapter) {
        adapter.disableForegroundDispatch(activity);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_refresh) {
            refreshActivities();
        }

        return super.onOptionsItemSelected(item);
    }

    private void refreshActivities() {
        KidActivityRepository.undoneAllKidActivities(this.getApplicationContext());
    }
}
