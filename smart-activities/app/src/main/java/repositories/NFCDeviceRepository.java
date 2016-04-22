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

import database.Action;
import database.ActionDao;
import database.DaoSession;
import database.NFCDevice;
import database.NFCDeviceDao;
import de.greenrobot.dao.query.QueryBuilder;

public class NFCDeviceRepository {

    public static NFCDevice getNFCDeviceById(Context context, Long nfcDeviceId) {
        DaoSession daoSession = Utils.getDaoSession(context);
        NFCDeviceDao nfcDeviceDao = daoSession.getNFCDeviceDao();
        return nfcDeviceDao.load(nfcDeviceId);
    }

    public static NFCDevice getNFCDeviceByDeviceId(Context context, String deviceId) {
        DaoSession daoSession = Utils.getDaoSession(context);
        NFCDeviceDao nfcDeviceDao = daoSession.getNFCDeviceDao();
        QueryBuilder qb = nfcDeviceDao.queryBuilder();
        qb.where(NFCDeviceDao.Properties.DeviceId.eq(deviceId));
        List<NFCDevice> nfcDevices = qb.list();
        if(!nfcDevices.isEmpty())
            return nfcDevices.get(0);
        return null;
    }
}
