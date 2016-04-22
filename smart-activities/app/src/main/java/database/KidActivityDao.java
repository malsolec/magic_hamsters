package database;

import java.util.List;
import java.util.ArrayList;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.SqlUtils;
import de.greenrobot.dao.internal.DaoConfig;

import database.KidActivity;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table KID_ACTIVITY.
*/
public class KidActivityDao extends AbstractDao<KidActivity, Long> {

    public static final String TABLENAME = "KID_ACTIVITY";

    /**
     * Properties of entity KidActivity.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Name = new Property(1, String.class, "name", false, "NAME");
        public final static Property ImgUrl = new Property(2, String.class, "imgUrl", false, "IMG_URL");
        public final static Property OrderNumber = new Property(3, Integer.class, "orderNumber", false, "ORDER_NUMBER");
        public final static Property KidActivityId = new Property(4, Long.class, "kidActivityId", false, "KID_ACTIVITY_ID");
        public final static Property KidActivityId = new Property(5, Long.class, "kidActivityId", false, "KID_ACTIVITY_ID");
    };

    private DaoSession daoSession;


    public KidActivityDao(DaoConfig config) {
        super(config);
    }
    
    public KidActivityDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'KID_ACTIVITY' (" + //
                "'_id' INTEGER PRIMARY KEY ," + // 0: id
                "'NAME' TEXT," + // 1: name
                "'IMG_URL' TEXT," + // 2: imgUrl
                "'ORDER_NUMBER' INTEGER," + // 3: orderNumber
                "'KID_ACTIVITY_ID' INTEGER," + // 4: kidActivityId
                "'KID_ACTIVITY_ID' INTEGER);"); // 5: kidActivityId
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'KID_ACTIVITY'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, KidActivity entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(2, name);
        }
 
        String imgUrl = entity.getImgUrl();
        if (imgUrl != null) {
            stmt.bindString(3, imgUrl);
        }
 
        Integer orderNumber = entity.getOrderNumber();
        if (orderNumber != null) {
            stmt.bindLong(4, orderNumber);
        }
    }

    @Override
    protected void attachEntity(KidActivity entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public KidActivity readEntity(Cursor cursor, int offset) {
        KidActivity entity = new KidActivity( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // name
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // imgUrl
            cursor.isNull(offset + 3) ? null : cursor.getInt(offset + 3) // orderNumber
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, KidActivity entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setName(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setImgUrl(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setOrderNumber(cursor.isNull(offset + 3) ? null : cursor.getInt(offset + 3));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(KidActivity entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(KidActivity entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
    private String selectDeep;

    protected String getSelectDeep() {
        if (selectDeep == null) {
            StringBuilder builder = new StringBuilder("SELECT ");
            SqlUtils.appendColumns(builder, "T", getAllColumns());
            builder.append(',');
            SqlUtils.appendColumns(builder, "T0", daoSession.getNFCDeviceDao().getAllColumns());
            builder.append(',');
            SqlUtils.appendColumns(builder, "T1", daoSession.getActionDao().getAllColumns());
            builder.append(" FROM KID_ACTIVITY T");
            builder.append(" LEFT JOIN NFCDEVICE T0 ON T.'KID_ACTIVITY_ID'=T0.'_id'");
            builder.append(" LEFT JOIN ACTION T1 ON T.'KID_ACTIVITY_ID'=T1.'_id'");
            builder.append(' ');
            selectDeep = builder.toString();
        }
        return selectDeep;
    }
    
    protected KidActivity loadCurrentDeep(Cursor cursor, boolean lock) {
        KidActivity entity = loadCurrent(cursor, 0, lock);
        int offset = getAllColumns().length;

        NFCDevice nFCDevice = loadCurrentOther(daoSession.getNFCDeviceDao(), cursor, offset);
        entity.setNFCDevice(nFCDevice);
        offset += daoSession.getNFCDeviceDao().getAllColumns().length;

        Action action = loadCurrentOther(daoSession.getActionDao(), cursor, offset);
        entity.setAction(action);

        return entity;    
    }

    public KidActivity loadDeep(Long key) {
        assertSinglePk();
        if (key == null) {
            return null;
        }

        StringBuilder builder = new StringBuilder(getSelectDeep());
        builder.append("WHERE ");
        SqlUtils.appendColumnsEqValue(builder, "T", getPkColumns());
        String sql = builder.toString();
        
        String[] keyArray = new String[] { key.toString() };
        Cursor cursor = db.rawQuery(sql, keyArray);
        
        try {
            boolean available = cursor.moveToFirst();
            if (!available) {
                return null;
            } else if (!cursor.isLast()) {
                throw new IllegalStateException("Expected unique result, but count was " + cursor.getCount());
            }
            return loadCurrentDeep(cursor, true);
        } finally {
            cursor.close();
        }
    }
    
    /** Reads all available rows from the given cursor and returns a list of new ImageTO objects. */
    public List<KidActivity> loadAllDeepFromCursor(Cursor cursor) {
        int count = cursor.getCount();
        List<KidActivity> list = new ArrayList<KidActivity>(count);
        
        if (cursor.moveToFirst()) {
            if (identityScope != null) {
                identityScope.lock();
                identityScope.reserveRoom(count);
            }
            try {
                do {
                    list.add(loadCurrentDeep(cursor, false));
                } while (cursor.moveToNext());
            } finally {
                if (identityScope != null) {
                    identityScope.unlock();
                }
            }
        }
        return list;
    }
    
    protected List<KidActivity> loadDeepAllAndCloseCursor(Cursor cursor) {
        try {
            return loadAllDeepFromCursor(cursor);
        } finally {
            cursor.close();
        }
    }
    

    /** A raw-style query where you can pass any WHERE clause and arguments. */
    public List<KidActivity> queryDeep(String where, String... selectionArg) {
        Cursor cursor = db.rawQuery(getSelectDeep() + where, selectionArg);
        return loadDeepAllAndCloseCursor(cursor);
    }
 
}
