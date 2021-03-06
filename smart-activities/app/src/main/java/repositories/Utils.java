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


        kidActivities.add(createKidActivity("Mycie zębów", "szczoteczka", 1));
        kidActivities.add(createKidActivity("Zabawa z misiem", "mis", 2));
        kidActivities.add(createKidActivity("Kodowanie", "koduj", 3));

        for (KidActivity kidActivity : kidActivities) {
            kidActivityDao.insertOrReplace(kidActivity);
        }


        nfcDevices.add(createNFCDevice(28, kidActivities.get(0).getId()));
        nfcDevices.add(createNFCDevice(4, kidActivities.get(1).getId()));
        nfcDevices.add(createNFCDevice(-193, kidActivities.get(2).getId()));

        for (NFCDevice nfcDevice : nfcDevices) {
            nfcDeviceDao.insertOrReplace(nfcDevice);
        }

        actions.add(createAction("Weź szczoteczkę", "szczoteczka_duza", 1, kidActivities.get(0).getId()));
        actions.add(createAction("Nałóż pastę", "szczoteczka_duza", 2, kidActivities.get(0).getId()));
        actions.add(createAction("Szczotkuj zęby", "szczoteczka_duza", 3, kidActivities.get(0).getId()));
        actions.add(createAction("Wypluj pastę", "szczoteczka_duza", 4, kidActivities.get(0).getId()));
        actions.add(createAction("Wypłucz usta", "szczoteczka_duza", 5, kidActivities.get(0).getId()));
        actions.add(createAction("Umyj i odłóż szczoteczkę", "szczoteczka_duza", 6, kidActivities.get(0).getId()));

        actions.add(createAction("Zapytaj misia: czy dobrze się czujesz?", "mis_duzy", 1, kidActivities.get(1).getId()));
        actions.add(createAction("Miś odpowiada: boli mnie łapka", "mis_duzy", 2, kidActivities.get(1).getId()));
        actions.add(createAction("Przyklej misiowi plaster", "mis_duzy", 3, kidActivities.get(1).getId()));
        actions.add(createAction("Zapytaj misia: czy teraz jest lepiej?", "mis_duzy", 4, kidActivities.get(1).getId()));
        actions.add(createAction("Miś odpowiada: Tak. Dziękuję.", "mis_duzy", 5, kidActivities.get(1).getId()));
        actions.add(createAction("Przytul misia", "mis_duzy", 6, kidActivities.get(1).getId()));

        actions.add(createAction("Włącz komputer", "koduj_duzy", 1, kidActivities.get(2).getId()));
        actions.add(createAction("Odpal IDE", "koduj_duzy", 2, kidActivities.get(2).getId()));
        actions.add(createAction("Uratuj świat", "koduj_duzy", 3, kidActivities.get(2).getId()));
        actions.add(createAction("Możesz włączyć koty na youtube", "koduj_duzy", 4, kidActivities.get(2).getId()));

        for (Action action : actions) {
            actionDao.insertOrReplace(action);
        }
    }

    public static KidActivity createKidActivity(String name, String imgUrl, Integer orderNumber) {
        KidActivity kidActivity = new KidActivity();
        kidActivity.setName(name);
        kidActivity.setImgUrl(imgUrl);
        kidActivity.setOrderNumber(orderNumber);
        kidActivity.setIsDone(false);
        return kidActivity;
    }

    public static NFCDevice createNFCDevice(Integer deviceId, Long kidActivityId) {
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
