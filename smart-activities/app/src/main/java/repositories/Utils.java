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

import database.DaoMaster;
import database.DaoSession;
import database.KidActivity;

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

//    public static void setUpExamples(Context context){
//        List<KidActivity> kidActivities = new ArrayList<>();
//
//        kidActivities.add();
//
//        List<Child> childList = new ArrayList<>();
//        ChildDao childDao = getDaoSession(context).getChildDao();
//        for(int childId = 0; childId< numberOfChildren; childId++)
//        {
//            Child child = new Child();
//            child.setCode(code + childId);
//            child.setIsArchived(false);
//            childDao.insertOrReplace(child);
//            childList.add(child);
//
//        }
//        return childList;
//    }

    public static KidActivity createKidActivity(String name, String imgUrl, Integer orderNumber) {
        KidActivity kidActivity = new KidActivity();
        kidActivity.setName(name);
        kidActivity.setImgUrl(imgUrl);
        kidActivity.setOrderNumber(orderNumber);
        return kidActivity;
    }
}
