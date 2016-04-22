package com.smartactivities;

import java.util.List;

/**
 * Created by Gosia on 2016-04-22.
 */
public class ReceiveModel {

    List<ManagerAction> actions;
    List<ManagerKidActivity> kidActivities;
    List<ManagerNFCDevice> nfcDevices;

    public ReceiveModel(){
        this.actions = null;
        this.kidActivities = null;
        this.nfcDevices = null;
    }


    public ReceiveModel(List<ManagerAction> actions, List<ManagerKidActivity> kidActivities, List<ManagerNFCDevice>nfcDevices){
        this.actions = actions;
        this.kidActivities = kidActivities;
        this.nfcDevices = nfcDevices;
    }


}
