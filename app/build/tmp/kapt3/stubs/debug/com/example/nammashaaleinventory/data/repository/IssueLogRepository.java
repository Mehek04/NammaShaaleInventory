package com.example.nammashaaleinventory.data.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\bH\u0086@\u00a2\u0006\u0002\u0010\u0011J\u0016\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0010\u001a\u00020\bH\u0086@\u00a2\u0006\u0002\u0010\u0011R\u001d\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0014"}, d2 = {"Lcom/example/nammashaaleinventory/data/repository/IssueLogRepository;", "", "issueLogDao", "Lcom/example/nammashaaleinventory/data/local/dao/IssueLogDao;", "(Lcom/example/nammashaaleinventory/data/local/dao/IssueLogDao;)V", "allIssueLogs", "Landroidx/lifecycle/LiveData;", "", "Lcom/example/nammashaaleinventory/data/local/entity/IssueLogEntity;", "getAllIssueLogs", "()Landroidx/lifecycle/LiveData;", "issueCount", "", "getIssueCount", "delete", "", "issueLog", "(Lcom/example/nammashaaleinventory/data/local/entity/IssueLogEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insert", "", "app_debug"})
public final class IssueLogRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.example.nammashaaleinventory.data.local.dao.IssueLogDao issueLogDao = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<com.example.nammashaaleinventory.data.local.entity.IssueLogEntity>> allIssueLogs = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.Integer> issueCount = null;
    
    public IssueLogRepository(@org.jetbrains.annotations.NotNull()
    com.example.nammashaaleinventory.data.local.dao.IssueLogDao issueLogDao) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.example.nammashaaleinventory.data.local.entity.IssueLogEntity>> getAllIssueLogs() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Integer> getIssueCount() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object insert(@org.jetbrains.annotations.NotNull()
    com.example.nammashaaleinventory.data.local.entity.IssueLogEntity issueLog, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object delete(@org.jetbrains.annotations.NotNull()
    com.example.nammashaaleinventory.data.local.entity.IssueLogEntity issueLog, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
}