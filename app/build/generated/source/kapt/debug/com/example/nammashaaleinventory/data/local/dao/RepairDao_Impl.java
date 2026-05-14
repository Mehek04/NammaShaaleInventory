package com.example.nammashaaleinventory.data.local.dao;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.nammashaaleinventory.data.local.entity.RepairEntity;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class RepairDao_Impl implements RepairDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<RepairEntity> __insertionAdapterOfRepairEntity;

  private final EntityDeletionOrUpdateAdapter<RepairEntity> __deletionAdapterOfRepairEntity;

  private final EntityDeletionOrUpdateAdapter<RepairEntity> __updateAdapterOfRepairEntity;

  public RepairDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfRepairEntity = new EntityInsertionAdapter<RepairEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `repairs` (`id`,`assetId`,`assetName`,`condition`,`requestDate`,`status`) VALUES (nullif(?, 0),?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final RepairEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getAssetId());
        if (entity.getAssetName() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getAssetName());
        }
        if (entity.getCondition() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getCondition());
        }
        statement.bindLong(5, entity.getRequestDate());
        if (entity.getStatus() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getStatus());
        }
      }
    };
    this.__deletionAdapterOfRepairEntity = new EntityDeletionOrUpdateAdapter<RepairEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `repairs` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final RepairEntity entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfRepairEntity = new EntityDeletionOrUpdateAdapter<RepairEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `repairs` SET `id` = ?,`assetId` = ?,`assetName` = ?,`condition` = ?,`requestDate` = ?,`status` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final RepairEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getAssetId());
        if (entity.getAssetName() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getAssetName());
        }
        if (entity.getCondition() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getCondition());
        }
        statement.bindLong(5, entity.getRequestDate());
        if (entity.getStatus() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getStatus());
        }
        statement.bindLong(7, entity.getId());
      }
    };
  }

  @Override
  public Object insert(final RepairEntity repair, final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfRepairEntity.insertAndReturnId(repair);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object delete(final RepairEntity repair, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfRepairEntity.handle(repair);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object update(final RepairEntity repair, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfRepairEntity.handle(repair);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public LiveData<List<RepairEntity>> getAllRepairs() {
    final String _sql = "SELECT * FROM repairs ORDER BY requestDate DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[] {"repairs"}, false, new Callable<List<RepairEntity>>() {
      @Override
      @Nullable
      public List<RepairEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfAssetId = CursorUtil.getColumnIndexOrThrow(_cursor, "assetId");
          final int _cursorIndexOfAssetName = CursorUtil.getColumnIndexOrThrow(_cursor, "assetName");
          final int _cursorIndexOfCondition = CursorUtil.getColumnIndexOrThrow(_cursor, "condition");
          final int _cursorIndexOfRequestDate = CursorUtil.getColumnIndexOrThrow(_cursor, "requestDate");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final List<RepairEntity> _result = new ArrayList<RepairEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final RepairEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpAssetId;
            _tmpAssetId = _cursor.getLong(_cursorIndexOfAssetId);
            final String _tmpAssetName;
            if (_cursor.isNull(_cursorIndexOfAssetName)) {
              _tmpAssetName = null;
            } else {
              _tmpAssetName = _cursor.getString(_cursorIndexOfAssetName);
            }
            final String _tmpCondition;
            if (_cursor.isNull(_cursorIndexOfCondition)) {
              _tmpCondition = null;
            } else {
              _tmpCondition = _cursor.getString(_cursorIndexOfCondition);
            }
            final long _tmpRequestDate;
            _tmpRequestDate = _cursor.getLong(_cursorIndexOfRequestDate);
            final String _tmpStatus;
            if (_cursor.isNull(_cursorIndexOfStatus)) {
              _tmpStatus = null;
            } else {
              _tmpStatus = _cursor.getString(_cursorIndexOfStatus);
            }
            _item = new RepairEntity(_tmpId,_tmpAssetId,_tmpAssetName,_tmpCondition,_tmpRequestDate,_tmpStatus);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public LiveData<List<RepairEntity>> getRepairsForAsset(final long assetId) {
    final String _sql = "SELECT * FROM repairs WHERE assetId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, assetId);
    return __db.getInvalidationTracker().createLiveData(new String[] {"repairs"}, false, new Callable<List<RepairEntity>>() {
      @Override
      @Nullable
      public List<RepairEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfAssetId = CursorUtil.getColumnIndexOrThrow(_cursor, "assetId");
          final int _cursorIndexOfAssetName = CursorUtil.getColumnIndexOrThrow(_cursor, "assetName");
          final int _cursorIndexOfCondition = CursorUtil.getColumnIndexOrThrow(_cursor, "condition");
          final int _cursorIndexOfRequestDate = CursorUtil.getColumnIndexOrThrow(_cursor, "requestDate");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final List<RepairEntity> _result = new ArrayList<RepairEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final RepairEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpAssetId;
            _tmpAssetId = _cursor.getLong(_cursorIndexOfAssetId);
            final String _tmpAssetName;
            if (_cursor.isNull(_cursorIndexOfAssetName)) {
              _tmpAssetName = null;
            } else {
              _tmpAssetName = _cursor.getString(_cursorIndexOfAssetName);
            }
            final String _tmpCondition;
            if (_cursor.isNull(_cursorIndexOfCondition)) {
              _tmpCondition = null;
            } else {
              _tmpCondition = _cursor.getString(_cursorIndexOfCondition);
            }
            final long _tmpRequestDate;
            _tmpRequestDate = _cursor.getLong(_cursorIndexOfRequestDate);
            final String _tmpStatus;
            if (_cursor.isNull(_cursorIndexOfStatus)) {
              _tmpStatus = null;
            } else {
              _tmpStatus = _cursor.getString(_cursorIndexOfStatus);
            }
            _item = new RepairEntity(_tmpId,_tmpAssetId,_tmpAssetName,_tmpCondition,_tmpRequestDate,_tmpStatus);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
