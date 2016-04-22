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

    public static KidActivity getKidActivityByNFCDeviceId(Context context, Integer deviceId) {
        DaoSession daoSession = Utils.getDaoSession(context);
        KidActivityDao kidActivityDao = daoSession.getKidActivityDao();
        QueryBuilder qb = kidActivityDao.queryBuilder();
        qb.join(NFCDeviceDao.Properties.KidActivityId, NFCDevice.class)
                .where(NFCDeviceDao.Properties.DeviceId.eq(deviceId));
        List<KidActivity> kidActivities = qb.list();
        if(!kidActivities.isEmpty())
            return kidActivities.get(0);
        return null;
    }
}
