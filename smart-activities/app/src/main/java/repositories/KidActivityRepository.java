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

import java.util.List;

import database.DaoSession;
import database.KidActivity;
import database.KidActivityDao;
import database.NFCDevice;
import database.NFCDeviceDao;
import de.greenrobot.dao.query.QueryBuilder;

public class KidActivityRepository {

    public static KidActivity getKidActivityById(Context context, Long kidActivityId) {
        DaoSession daoSession = Utils.getDaoSession(context);
        KidActivityDao kidActivityDao = daoSession.getKidActivityDao();
        return kidActivityDao.load(kidActivityId);
    }

    public static List<KidActivity> getAllKidsActivities(Context context) {
        DaoSession daoSession = Utils.getDaoSession(context);
        KidActivityDao kidActivityDao = daoSession.getKidActivityDao();
        return kidActivityDao.loadAll();
    }

    public static List<KidActivity> getAllNotDoneKidsActivities(Context context) {
        DaoSession daoSession = Utils.getDaoSession(context);
        KidActivityDao kidActivityDao = daoSession.getKidActivityDao();
        QueryBuilder qb = kidActivityDao.queryBuilder();
        qb.where(KidActivityDao.Properties.IsDone.eq(false));
        return qb.list();
    }

    public static void setKidActivityIsDone(Context context, Long kidActivityId, boolean isDone) {
        DaoSession daoSession = Utils.getDaoSession(context);
        KidActivityDao kidActivityDao = daoSession.getKidActivityDao();
        KidActivity kidActivity = getKidActivityById(context, kidActivityId);
        kidActivity.setIsDone(isDone);
        kidActivityDao.update(kidActivity);
    }

    public static void undoneAllKidActivities(Context context) {
        List<KidActivity> kidActivities = getAllKidsActivities(context);
        for(KidActivity kidActivity : kidActivities) {
            setKidActivityIsDone(context, kidActivity.getId(), false);
        }
    }

    public static KidActivity getKidActivityByNFCDeviceId(Context context, Integer deviceId) {
        DaoSession daoSession = Utils.getDaoSession(context);
        KidActivityDao kidActivityDao = daoSession.getKidActivityDao();
        QueryBuilder qb = kidActivityDao.queryBuilder();
        qb.join(NFCDevice.class, NFCDeviceDao.Properties.KidActivityId)
                .where(NFCDeviceDao.Properties.DeviceId.eq(deviceId));
        List<KidActivity> kidActivities = qb.list();
        if(!kidActivities.isEmpty())
            return kidActivities.get(0);
        return null;
    }

    public static void insertKidActivitiesFromModel(Context context, List<ManagerKidActivity> kidActivities) {
        DaoSession daoSession = Utils.getDaoSession(context);
        KidActivityDao kidActivityDao = daoSession.getKidActivityDao();

        for(ManagerKidActivity kidActivity : kidActivities){
            KidActivity newKidActivity = Utils.createKidActivity(kidActivity.getName(), kidActivity.getImgUrl(), kidActivity.getOrderNumber());
            newKidActivity.setId(kidActivity.getId());
            kidActivityDao.insertOrReplace(newKidActivity);
        }

    }
}
