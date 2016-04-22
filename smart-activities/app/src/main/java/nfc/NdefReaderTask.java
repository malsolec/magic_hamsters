package nfc;

import android.content.Context;
import android.content.Intent;
import android.nfc.Tag;
import android.os.AsyncTask;

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
            context.startActivity(intent);
        } else {
            // TODO: wrong nfc tag
        }
    }
}