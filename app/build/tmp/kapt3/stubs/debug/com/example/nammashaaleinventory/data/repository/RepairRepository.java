package com.example.nammashaaleinventory.data.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\bH\u0086@\u00a2\u0006\u0002\u0010\u000eJ\u0016\u0010\u000f\u001a\u00020\u00102\u0006\u0010\r\u001a\u00020\bH\u0086@\u00a2\u0006\u0002\u0010\u000eJ\u0016\u0010\u0011\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\bH\u0086@\u00a2\u0006\u0002\u0010\u000eR\u001d\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0012"}, d2 = {"Lcom/example/nammashaaleinventory/data/repository/RepairRepository;", "", "repairDao", "Lcom/example/nammashaaleinventory/data/local/dao/RepairDao;", "(Lcom/example/nammashaaleinventory/data/local/dao/RepairDao;)V", "allRepairs", "Landroidx/lifecycle/LiveData;", "", "Lcom/example/nammashaaleinventory/data/local/entity/RepairEntity;", "getAllRepairs", "()Landroidx/lifecycle/LiveData;", "delete", "", "repair", "(Lcom/example/nammashaaleinventory/data/local/entity/RepairEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insert", "", "update", "app_debug"})
public final class RepairRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.example.nammashaaleinventory.data.local.dao.RepairDao repairDao = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<com.example.nammashaaleinventory.data.local.entity.RepairEntity>> allRepairs = null;
    
    public RepairRepository(@org.jetbrains.annotations.NotNull()
    com.example.nammashaaleinventory.data.local.dao.RepairDao repairDao) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.example.nammashaaleinventory.data.local.entity.RepairEntity>> getAllRepairs() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object insert(@org.jetbrains.annotations.NotNull()
    com.example.nammashaaleinventory.data.local.entity.RepairEntity repair, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object update(@org.jetbrains.annotations.NotNull()
    com.example.nammashaaleinventory.data.local.entity.RepairEntity repair, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object delete(@org.jetbrains.annotations.NotNull()
    com.example.nammashaaleinventory.data.local.entity.RepairEntity repair, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
}