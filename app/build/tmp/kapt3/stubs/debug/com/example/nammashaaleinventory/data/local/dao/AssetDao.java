package com.example.nammashaaleinventory.data.local.dao;

/**
 * Data Access Object for Asset operations
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\f\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0014\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\t0\bH\'J\u0018\u0010\n\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u000b\u001a\u00020\fH\u00a7@\u00a2\u0006\u0002\u0010\rJ\u001c\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\t0\b2\u0006\u0010\u000f\u001a\u00020\u0010H\'J\u000e\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\bH\'J\u000e\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00120\bH\'J\u000e\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00120\bH\'J\u0016\u0010\u0015\u001a\u00020\f2\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u001c\u0010\u0016\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\t0\b2\u0006\u0010\u0017\u001a\u00020\u0010H\'J\u0016\u0010\u0018\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J&\u0010\u0019\u001a\u00020\u00032\u0006\u0010\u001a\u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\u00102\u0006\u0010\u001c\u001a\u00020\fH\u00a7@\u00a2\u0006\u0002\u0010\u001d\u00a8\u0006\u001e"}, d2 = {"Lcom/example/nammashaaleinventory/data/local/dao/AssetDao;", "", "delete", "", "asset", "Lcom/example/nammashaaleinventory/data/local/entity/Asset;", "(Lcom/example/nammashaaleinventory/data/local/entity/Asset;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllAssets", "Landroidx/lifecycle/LiveData;", "", "getAssetById", "id", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAssetsByCondition", "condition", "", "getNeedsRepairCount", "", "getTotalCount", "getWorkingCount", "insert", "searchAssets", "query", "update", "updateCondition", "assetId", "newCondition", "checkedDate", "(JLjava/lang/String;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
@androidx.room.Dao()
public abstract interface AssetDao {
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insert(@org.jetbrains.annotations.NotNull()
    com.example.nammashaaleinventory.data.local.entity.Asset asset, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Update()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object update(@org.jetbrains.annotations.NotNull()
    com.example.nammashaaleinventory.data.local.entity.Asset asset, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Delete()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object delete(@org.jetbrains.annotations.NotNull()
    com.example.nammashaaleinventory.data.local.entity.Asset asset, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM assets ORDER BY addedDate DESC")
    @org.jetbrains.annotations.NotNull()
    public abstract androidx.lifecycle.LiveData<java.util.List<com.example.nammashaaleinventory.data.local.entity.Asset>> getAllAssets();
    
    @androidx.room.Query(value = "SELECT * FROM assets WHERE id = :id")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getAssetById(long id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.example.nammashaaleinventory.data.local.entity.Asset> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM assets WHERE name LIKE \'%\' || :query || \'%\'")
    @org.jetbrains.annotations.NotNull()
    public abstract androidx.lifecycle.LiveData<java.util.List<com.example.nammashaaleinventory.data.local.entity.Asset>> searchAssets(@org.jetbrains.annotations.NotNull()
    java.lang.String query);
    
    @androidx.room.Query(value = "SELECT COUNT(*) FROM assets")
    @org.jetbrains.annotations.NotNull()
    public abstract androidx.lifecycle.LiveData<java.lang.Integer> getTotalCount();
    
    @androidx.room.Query(value = "SELECT COUNT(*) FROM assets WHERE condition = \'Needs Repair\' OR condition = \'Broken\'")
    @org.jetbrains.annotations.NotNull()
    public abstract androidx.lifecycle.LiveData<java.lang.Integer> getNeedsRepairCount();
    
    @androidx.room.Query(value = "SELECT COUNT(*) FROM assets WHERE condition = \'Working\'")
    @org.jetbrains.annotations.NotNull()
    public abstract androidx.lifecycle.LiveData<java.lang.Integer> getWorkingCount();
    
    @androidx.room.Query(value = "SELECT * FROM assets WHERE condition = :condition")
    @org.jetbrains.annotations.NotNull()
    public abstract androidx.lifecycle.LiveData<java.util.List<com.example.nammashaaleinventory.data.local.entity.Asset>> getAssetsByCondition(@org.jetbrains.annotations.NotNull()
    java.lang.String condition);
    
    @androidx.room.Query(value = "UPDATE assets SET condition = :newCondition, lastCheckedDate = :checkedDate WHERE id = :assetId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateCondition(long assetId, @org.jetbrains.annotations.NotNull()
    java.lang.String newCondition, long checkedDate, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}