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
import de.greenrobot.dao.query.QueryBuilder;

public class ActionRepository {

    public static Action getActionById(Context context, Long actionId) {
        DaoSession daoSession = Utils.getDaoSession(context);
        ActionDao actionDao = daoSession.getActionDao();
        return actionDao.load(actionId);
    }

    public static List<Action> getActionsByKidActivityId(Context context, Long kidActivityId) {
        DaoSession daoSession = Utils.getDaoSession(context);
        ActionDao actionDao = daoSession.getActionDao();
        QueryBuilder qb = actionDao.queryBuilder();
        qb.where(ActionDao.Properties.KidActivityId.eq(kidActivityId));
        return qb.list();
    }


}
