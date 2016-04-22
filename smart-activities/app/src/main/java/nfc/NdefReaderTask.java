package nfc;

import android.nfc.Tag;
import android.os.AsyncTask;

/**
 * Created by wookie on 4/22/16.
 */
public class NdefReaderTask extends AsyncTask<Tag, Void, String> {

    @Override
    protected String doInBackground(Tag... params) {
        Tag tag = params[0];
        return countTagId(tag).toString();
    }

    private Integer countTagId(Tag tag) {
        Integer id = 0;
        for(int i=0; i<7; i++)
            id += tag.getId()[i];

        return id;
    }

    @Override
    protected void onPostExecute(String result) {
        // TODO: run new activity
    }
}