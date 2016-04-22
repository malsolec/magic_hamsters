package hamsters.magic.smart_activities;

import java.util.List;

import database.Action;
import database.KidActivity;
import database.NFCDevice;
import repositories.ManagerAction;
import repositories.ManagerKidActivity;
import repositories.ManagerNFCDevice;

/**
 * Created by Gosia on 2016-04-22.
 */
public class ReceiveModel {

    List<ManagerAction> actions;
    List<ManagerKidActivity> kidActivities;
    List<ManagerNFCDevice> nfcDevices;


    public ReceiveModel(List<ManagerAction> actions, List<ManagerKidActivity> kidActivities, List<ManagerNFCDevice>nfcDevices){
        this.actions = actions;
        this.kidActivities = kidActivities;
        this.nfcDevices = nfcDevices;
    }


}
