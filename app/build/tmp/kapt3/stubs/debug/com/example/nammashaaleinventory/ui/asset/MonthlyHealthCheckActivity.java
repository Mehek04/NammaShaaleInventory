package com.example.nammashaaleinventory.ui.asset;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010#\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0011\u001a\u00020\u0012H\u0002J\u0012\u0010\u0013\u001a\u00020\u00122\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0014J\b\u0010\u0016\u001a\u00020\u0012H\u0002J\b\u0010\u0017\u001a\u00020\u0012H\u0002J\b\u0010\u0018\u001a\u00020\u0012H\u0002J\b\u0010\u0019\u001a\u00020\u0012H\u0002J\b\u0010\u001a\u001a\u00020\u0012H\u0002J\u0010\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u001c\u001a\u00020\u001dH\u0002J\u0016\u0010\u001e\u001a\u00020\u00122\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u0002J\u0016\u0010 \u001a\u00020\u00122\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082.\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006!"}, d2 = {"Lcom/example/nammashaaleinventory/ui/asset/MonthlyHealthCheckActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "assetViewModel", "Lcom/example/nammashaaleinventory/viewmodel/AssetViewModel;", "binding", "Lcom/example/nammashaaleinventory/databinding/ActivityMonthlyHealthCheckBinding;", "currentFilter", "", "displayedAssets", "", "Lcom/example/nammashaaleinventory/data/local/entity/Asset;", "healthCheckAdapter", "Lcom/example/nammashaaleinventory/ui/asset/HealthCheckAdapter;", "selectedAssets", "", "", "observeData", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "refreshData", "setupBulkUpdate", "setupFilterChips", "setupRecyclerView", "setupToolbar", "updateChipSelection", "selectedChip", "Lcom/google/android/material/chip/Chip;", "updateDisplay", "assets", "updateSummary", "app_debug"})
public final class MonthlyHealthCheckActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.example.nammashaaleinventory.databinding.ActivityMonthlyHealthCheckBinding binding;
    private com.example.nammashaaleinventory.viewmodel.AssetViewModel assetViewModel;
    private com.example.nammashaaleinventory.ui.asset.HealthCheckAdapter healthCheckAdapter;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String currentFilter = "All";
    @org.jetbrains.annotations.NotNull()
    private java.util.List<com.example.nammashaaleinventory.data.local.entity.Asset> displayedAssets;
    @org.jetbrains.annotations.NotNull()
    private final java.util.Set<java.lang.Long> selectedAssets = null;
    
    public MonthlyHealthCheckActivity() {
        super();
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void setupToolbar() {
    }
    
    private final void setupRecyclerView() {
    }
    
    private final void setupFilterChips() {
    }
    
    private final void updateChipSelection(com.google.android.material.chip.Chip selectedChip) {
    }
    
    private final void setupBulkUpdate() {
    }
    
    private final void observeData() {
    }
    
    private final void refreshData() {
    }
    
    private final void updateSummary(java.util.List<com.example.nammashaaleinventory.data.local.entity.Asset> assets) {
    }
    
    private final void updateDisplay(java.util.List<com.example.nammashaaleinventory.data.local.entity.Asset> assets) {
    }
}