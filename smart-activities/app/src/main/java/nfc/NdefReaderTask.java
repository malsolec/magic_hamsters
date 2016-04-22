package nfc;

import android.content.Context;
import android.content.Intent;
import android.nfc.Tag;
import android.os.AsyncTask;

import android.speech.tts.TextToSpeech;
import database.KidActivity;
import hamsters.magic.smart_activities.ActionActivity;
import hamsters.magic.smart_activities.MainActivity;
import repositories.KidActivityRepository;

/**
 * Created by wookie on 4/22/16.
 */
public class NdefReaderTask extends AsyncTask<Tag, Void, Integer> {

    private Context context;
    private KidActivity topActivity;
    private TextToSpeech t1;

    public NdefReaderTask(Context context, KidActivity topActivity) {
        this.context = context;
        this.topActivity = topActivity;
    }

    @Override
    protected Integer doInBackground(Tag... params) {
        Tag tag = params[0];
        return countTagId(tag);
    }

    private Integer countTagId(Tag tag) {
        Integer id = 0;
        for(int i=0; i<7; i++)
            id += tag.getId()[i];

        return id;
    }

    @Override
    protected void onPostExecute(Integer result) {
        KidActivity detectedActivity = KidActivityRepository.getKidActivityByNFCDeviceId(context, result);

        if(detectedActivity.getId().equals(topActivity.getId())) {
            Intent intent = new Intent(context, ActionActivity.class);
            intent.putExtra("ACTIVITY_ID", detectedActivity.getId());
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } else {
            t1=new TextToSpeech(context, new TextToSpeech.OnInitListener() {
                @Override
                public void onInit(int status) {
                    if(status != TextToSpeech.ERROR) {
                        //t1.setLanguage(Locale.UK);
                        t1.speak("Zła czynność. Teraz czas na " + topActivity.getName(), TextToSpeech.QUEUE_FLUSH, null);
                    }
                }
            });
        }
    }
}