package nfc;

import android.content.Context;
import android.nfc.Tag;
import android.os.AsyncTask;

import database.KidActivity;
import repositories.KidActivityRepository;

/**
 * Created by wookie on 4/22/16.
 */
public class NdefReaderTask extends AsyncTask<Tag, Void, Integer> {

    private Context context;

    public NdefReaderTask(Context context) {
        this.context = context;
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
    }
}