package hamsters.magic.smart_activities;

import android.content.Context;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import database.Action;
import database.KidActivity;
import repositories.ActionRepository;
import repositories.KidActivityRepository;
import repositories.NFCDeviceRepository;
import repositories.Utils;

/**
 * Created by Gosia on 2016-04-22.
 */
public class SyncManager {

    private Context context;

    public SyncManager(Context applicationContext) {
        this.context = applicationContext;
    }

    public ReceiveModel receiveReceiveModel() {
        String url = "http://10.0.0.0/ws/rest/getAllData";
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        ReceiveModel receiveModel = restTemplate.getForObject(url, ReceiveModel.class);
        return receiveModel;
    }

    public void updateDatabaseAfterReceive(ReceiveModel receiveSyncModel) {
        Utils.cleanUpDatabase(this.context);
        KidActivityRepository.insertKidActivitiesFromModel(context, receiveSyncModel.kidActivities);
        NFCDeviceRepository.insertNfcDeviceFromModel(context,receiveSyncModel.nfcDevices);
        ActionRepository.insertActionsFromModel(context,receiveSyncModel.actions);
    }
}
