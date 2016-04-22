package database;

import android.database.sqlite.SQLiteDatabase;

import java.util.Map;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.identityscope.IdentityScopeType;
import de.greenrobot.dao.internal.DaoConfig;

import database.KidActivity;
import database.NFCDevice;
import database.Action;

import database.KidActivityDao;
import database.NFCDeviceDao;
import database.ActionDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see de.greenrobot.dao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig kidActivityDaoConfig;
    private final DaoConfig nFCDeviceDaoConfig;
    private final DaoConfig actionDaoConfig;

    private final KidActivityDao kidActivityDao;
    private final NFCDeviceDao nFCDeviceDao;
    private final ActionDao actionDao;

    public DaoSession(SQLiteDatabase db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        kidActivityDaoConfig = daoConfigMap.get(KidActivityDao.class).clone();
        kidActivityDaoConfig.initIdentityScope(type);

        nFCDeviceDaoConfig = daoConfigMap.get(NFCDeviceDao.class).clone();
        nFCDeviceDaoConfig.initIdentityScope(type);

        actionDaoConfig = daoConfigMap.get(ActionDao.class).clone();
        actionDaoConfig.initIdentityScope(type);

        kidActivityDao = new KidActivityDao(kidActivityDaoConfig, this);
        nFCDeviceDao = new NFCDeviceDao(nFCDeviceDaoConfig, this);
        actionDao = new ActionDao(actionDaoConfig, this);

        registerDao(KidActivity.class, kidActivityDao);
        registerDao(NFCDevice.class, nFCDeviceDao);
        registerDao(Action.class, actionDao);
    }
    
    public void clear() {
        kidActivityDaoConfig.getIdentityScope().clear();
        nFCDeviceDaoConfig.getIdentityScope().clear();
        actionDaoConfig.getIdentityScope().clear();
    }

    public KidActivityDao getKidActivityDao() {
        return kidActivityDao;
    }

    public NFCDeviceDao getNFCDeviceDao() {
        return nFCDeviceDao;
    }

    public ActionDao getActionDao() {
        return actionDao;
    }

}