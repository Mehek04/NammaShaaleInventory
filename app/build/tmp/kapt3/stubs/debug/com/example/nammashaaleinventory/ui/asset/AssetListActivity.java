package com.example.nammashaaleinventory.ui.asset;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u000e\u001a\u00020\u000fH\u0002J\b\u0010\u0010\u001a\u00020\u000fH\u0002J\u0012\u0010\u0011\u001a\u00020\u000f2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0014J\u0012\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016J\u0010\u0010\u0018\u001a\u00020\u00152\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J\b\u0010\u001b\u001a\u00020\u000fH\u0002J\b\u0010\u001c\u001a\u00020\u000fH\u0002J\b\u0010\u001d\u001a\u00020\u000fH\u0002J\u0010\u0010\u001e\u001a\u00020\u000f2\u0006\u0010\u001f\u001a\u00020\u0005H\u0002J\b\u0010 \u001a\u00020\u000fH\u0002R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006!"}, d2 = {"Lcom/example/nammashaaleinventory/ui/asset/AssetListActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "allAssetsList", "", "Lcom/example/nammashaaleinventory/data/local/entity/Asset;", "assetAdapter", "Lcom/example/nammashaaleinventory/adapter/AssetAdapter;", "assetViewModel", "Lcom/example/nammashaaleinventory/viewmodel/AssetViewModel;", "binding", "Lcom/example/nammashaaleinventory/databinding/ActivityAssetListBinding;", "csvExporter", "Lcom/example/nammashaaleinventory/utils/CSVExporter;", "exportToCSV", "", "observeData", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateOptionsMenu", "", "menu", "Landroid/view/Menu;", "onOptionsItemSelected", "item", "Landroid/view/MenuItem;", "setupRecyclerView", "setupSearch", "setupToolbar", "showAssetOptionsDialog", "asset", "showDeleteAllDialog", "app_debug"})
public final class AssetListActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.example.nammashaaleinventory.databinding.ActivityAssetListBinding binding;
    private com.example.nammashaaleinventory.viewmodel.AssetViewModel assetViewModel;
    private com.example.nammashaaleinventory.adapter.AssetAdapter assetAdapter;
    private com.example.nammashaaleinventory.utils.CSVExporter csvExporter;
    @org.jetbrains.annotations.NotNull()
    private java.util.List<com.example.nammashaaleinventory.data.local.entity.Asset> allAssetsList;
    
    public AssetListActivity() {
        super();
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void setupToolbar() {
    }
    
    @java.lang.Override()
    public boolean onCreateOptionsMenu(@org.jetbrains.annotations.Nullable()
    android.view.Menu menu) {
        return false;
    }
    
    @java.lang.Override()
    public boolean onOptionsItemSelected(@org.jetbrains.annotations.NotNull()
    android.view.MenuItem item) {
        return false;
    }
    
    private final void showDeleteAllDialog() {
    }
    
    private final void exportToCSV() {
    }
    
    private final void setupRecyclerView() {
    }
    
    private final void showAssetOptionsDialog(com.example.nammashaaleinventory.data.local.entity.Asset asset) {
    }
    
    private final void setupSearch() {
    }
    
    private final void observeData() {
    }
}