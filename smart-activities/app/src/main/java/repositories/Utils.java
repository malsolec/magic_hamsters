package repositories;

/*
 * Smart Activities
 *     Copyright (C) 2016 magic-hamsters-team
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import database.Action;
import database.ActionDao;
import database.DaoMaster;
import database.DaoSession;
import database.KidActivity;
import database.KidActivityDao;
import database.NFCDevice;
import database.NFCDeviceDao;

public class Utils {

    public static DaoSession getDaoSession(Context context) {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, "mroza-db", null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        return daoMaster.newSession();
    }

    public static void cleanUpDatabase(Context context) {
        DaoSession daoSession = getDaoSession(context);
        daoSession.getActionDao().deleteAll();
        daoSession.getNFCDeviceDao().deleteAll();
        daoSession.getKidActivityDao().deleteAll();
    }

    public static void setUpExamples(Context context){
        List<KidActivity> kidActivities = new ArrayList<>();
        List<NFCDevice> nfcDevices = new ArrayList<>();
        List<Action> actions = new ArrayList<>();
        KidActivityDao kidActivityDao = getDaoSession(context).getKidActivityDao();
        NFCDeviceDao nfcDeviceDao = getDaoSession(context).getNFCDeviceDao();
        ActionDao actionDao = getDaoSession(context).getActionDao();


        kidActivities.add(createKidActivity("Mycie zębów", "./img/mycie_zebow.jpg", 1));
        kidActivities.add(createKidActivity("Zabawa z misiem", "./img/zabawa_z_misiem.jpg", 2));

        for (KidActivity kidActivity : kidActivities) {
            kidActivityDao.insertOrReplace(kidActivity);
        }


        nfcDevices.add(createNFCDevice("ABC", kidActivities.get(0).getId()));
        nfcDevices.add(createNFCDevice("DEF", kidActivities.get(1).getId()));

        for (NFCDevice nfcDevice : nfcDevices) {
            nfcDeviceDao.insertOrReplace(nfcDevice);
        }

        actions.add(createAction("Czynność 1", "./img/czynnosc_1", 1, kidActivities.get(0).getId()));

        for (Action action : actions) {
            actionDao.insertOrReplace(action);
        }
    }

    public static KidActivity createKidActivity(String name, String imgUrl, Integer orderNumber) {
        KidActivity kidActivity = new KidActivity();
        kidActivity.setName(name);
        kidActivity.setImgUrl(imgUrl);
        kidActivity.setOrderNumber(orderNumber);
        return kidActivity;
    }

    public static NFCDevice createNFCDevice(String deviceId, Long kidActivityId) {
        NFCDevice nfcDevice = new NFCDevice();
        nfcDevice.setDeviceId(deviceId);
        nfcDevice.setKidActivityId(kidActivityId);
        return nfcDevice;
    }

    public static Action createAction(String name, String imgUrl, Integer orderNumber, Long kidActivityId) {
        Action action = new Action();
        action.setName(name);
        action.setImgUrl(imgUrl);
        action.setOrderNumber(orderNumber);
        action.setKidActivityId(kidActivityId);
        return action;
    }
}
