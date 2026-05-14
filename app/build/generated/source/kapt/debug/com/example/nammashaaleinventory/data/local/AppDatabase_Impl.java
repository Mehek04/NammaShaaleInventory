package com.example.nammashaaleinventory.data.local;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.example.nammashaaleinventory.data.local.dao.AssetDao;
import com.example.nammashaaleinventory.data.local.dao.AssetDao_Impl;
import com.example.nammashaaleinventory.data.local.dao.IssueLogDao;
import com.example.nammashaaleinventory.data.local.dao.IssueLogDao_Impl;
import com.example.nammashaaleinventory.data.local.dao.RepairDao;
import com.example.nammashaaleinventory.data.local.dao.RepairDao_Impl;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class AppDatabase_Impl extends AppDatabase {
  private volatile AssetDao _assetDao;

  private volatile IssueLogDao _issueLogDao;

  private volatile RepairDao _repairDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `assets` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT NOT NULL, `serialNumber` TEXT NOT NULL, `category` TEXT NOT NULL, `condition` TEXT NOT NULL, `photoPath` TEXT NOT NULL, `addedDate` INTEGER NOT NULL, `lastCheckedDate` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `issue_logs` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `description` TEXT NOT NULL, `date` INTEGER NOT NULL, `assetId` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `repairs` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `assetId` INTEGER NOT NULL, `assetName` TEXT NOT NULL, `condition` TEXT NOT NULL, `requestDate` INTEGER NOT NULL, `status` TEXT NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '2be4d690b4241b2ee1aaab3fea4bc454')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `assets`");
        db.execSQL("DROP TABLE IF EXISTS `issue_logs`");
        db.execSQL("DROP TABLE IF EXISTS `repairs`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsAssets = new HashMap<String, TableInfo.Column>(8);
        _columnsAssets.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAssets.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAssets.put("serialNumber", new TableInfo.Column("serialNumber", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAssets.put("category", new TableInfo.Column("category", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAssets.put("condition", new TableInfo.Column("condition", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAssets.put("photoPath", new TableInfo.Column("photoPath", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAssets.put("addedDate", new TableInfo.Column("addedDate", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAssets.put("lastCheckedDate", new TableInfo.Column("lastCheckedDate", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysAssets = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesAssets = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoAssets = new TableInfo("assets", _columnsAssets, _foreignKeysAssets, _indicesAssets);
        final TableInfo _existingAssets = TableInfo.read(db, "assets");
        if (!_infoAssets.equals(_existingAssets)) {
          return new RoomOpenHelper.ValidationResult(false, "assets(com.example.nammashaaleinventory.data.local.entity.Asset).\n"
                  + " Expected:\n" + _infoAssets + "\n"
                  + " Found:\n" + _existingAssets);
        }
        final HashMap<String, TableInfo.Column> _columnsIssueLogs = new HashMap<String, TableInfo.Column>(4);
        _columnsIssueLogs.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsIssueLogs.put("description", new TableInfo.Column("description", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsIssueLogs.put("date", new TableInfo.Column("date", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsIssueLogs.put("assetId", new TableInfo.Column("assetId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysIssueLogs = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesIssueLogs = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoIssueLogs = new TableInfo("issue_logs", _columnsIssueLogs, _foreignKeysIssueLogs, _indicesIssueLogs);
        final TableInfo _existingIssueLogs = TableInfo.read(db, "issue_logs");
        if (!_infoIssueLogs.equals(_existingIssueLogs)) {
          return new RoomOpenHelper.ValidationResult(false, "issue_logs(com.example.nammashaaleinventory.data.local.entity.IssueLogEntity).\n"
                  + " Expected:\n" + _infoIssueLogs + "\n"
                  + " Found:\n" + _existingIssueLogs);
        }
        final HashMap<String, TableInfo.Column> _columnsRepairs = new HashMap<String, TableInfo.Column>(6);
        _columnsRepairs.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRepairs.put("assetId", new TableInfo.Column("assetId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRepairs.put("assetName", new TableInfo.Column("assetName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRepairs.put("condition", new TableInfo.Column("condition", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRepairs.put("requestDate", new TableInfo.Column("requestDate", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRepairs.put("status", new TableInfo.Column("status", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysRepairs = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesRepairs = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoRepairs = new TableInfo("repairs", _columnsRepairs, _foreignKeysRepairs, _indicesRepairs);
        final TableInfo _existingRepairs = TableInfo.read(db, "repairs");
        if (!_infoRepairs.equals(_existingRepairs)) {
          return new RoomOpenHelper.ValidationResult(false, "repairs(com.example.nammashaaleinventory.data.local.entity.RepairEntity).\n"
                  + " Expected:\n" + _infoRepairs + "\n"
                  + " Found:\n" + _existingRepairs);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "2be4d690b4241b2ee1aaab3fea4bc454", "7d010b2932dad514980a2e4d7033cae4");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "assets","issue_logs","repairs");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `assets`");
      _db.execSQL("DELETE FROM `issue_logs`");
      _db.execSQL("DELETE FROM `repairs`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(AssetDao.class, AssetDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(IssueLogDao.class, IssueLogDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(RepairDao.class, RepairDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public AssetDao assetDao() {
    if (_assetDao != null) {
      return _assetDao;
    } else {
      synchronized(this) {
        if(_assetDao == null) {
          _assetDao = new AssetDao_Impl(this);
        }
        return _assetDao;
      }
    }
  }

  @Override
  public IssueLogDao issueLogDao() {
    if (_issueLogDao != null) {
      return _issueLogDao;
    } else {
      synchronized(this) {
        if(_issueLogDao == null) {
          _issueLogDao = new IssueLogDao_Impl(this);
        }
        return _issueLogDao;
      }
    }
  }

  @Override
  public RepairDao repairDao() {
    if (_repairDao != null) {
      return _repairDao;
    } else {
      synchronized(this) {
        if(_repairDao == null) {
          _repairDao = new RepairDao_Impl(this);
        }
        return _repairDao;
      }
    }
  }
}
