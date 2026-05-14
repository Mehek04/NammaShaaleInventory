package com.example.nammashaaleinventory.ui.dashboard;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u000f\u001a\u00020\u0010H\u0002J\b\u0010\u0011\u001a\u00020\u0010H\u0002J\b\u0010\u0012\u001a\u00020\u0010H\u0002J\b\u0010\u0013\u001a\u00020\u0010H\u0002J\b\u0010\u0014\u001a\u00020\u0010H\u0002J\u0012\u0010\u0015\u001a\u00020\u00102\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0014J\u0012\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0016J\u0010\u0010\u001c\u001a\u00020\u00192\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J\b\u0010\u001f\u001a\u00020\u0010H\u0002J\u0016\u0010 \u001a\u00020\u00102\f\u0010!\u001a\b\u0012\u0004\u0012\u00020#0\"H\u0002J\b\u0010$\u001a\u00020\u0010H\u0002J\b\u0010%\u001a\u00020\u0010H\u0002J\b\u0010&\u001a\u00020\u0010H\u0002J\b\u0010\'\u001a\u00020\u0010H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006("}, d2 = {"Lcom/example/nammashaaleinventory/ui/dashboard/DashboardActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "assetViewModel", "Lcom/example/nammashaaleinventory/viewmodel/AssetViewModel;", "binding", "Lcom/example/nammashaaleinventory/databinding/ActivityDashboardBinding;", "csvExporter", "Lcom/example/nammashaaleinventory/utils/CSVExporter;", "pdfGenerator", "Lcom/example/nammashaaleinventory/utils/PDFGenerator;", "sessionManager", "Lcom/example/nammashaaleinventory/utils/SessionManager;", "themeManager", "Lcom/example/nammashaaleinventory/utils/ThemeManager;", "animateCards", "", "exportCSV", "exportPDF", "logout", "observeData", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateOptionsMenu", "", "menu", "Landroid/view/Menu;", "onOptionsItemSelected", "item", "Landroid/view/MenuItem;", "setupClickListeners", "setupPieChart", "assets", "", "Lcom/example/nammashaaleinventory/data/local/entity/Asset;", "setupSwipeRefresh", "setupToolbar", "showExportDialog", "showThemeDialog", "app_debug"})
public final class DashboardActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.example.nammashaaleinventory.databinding.ActivityDashboardBinding binding;
    private com.example.nammashaaleinventory.viewmodel.AssetViewModel assetViewModel;
    private com.example.nammashaaleinventory.utils.ThemeManager themeManager;
    private com.example.nammashaaleinventory.utils.SessionManager sessionManager;
    private com.example.nammashaaleinventory.utils.CSVExporter csvExporter;
    private com.example.nammashaaleinventory.utils.PDFGenerator pdfGenerator;
    
    public DashboardActivity() {
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
    
    private final void logout() {
    }
    
    private final void setupClickListeners() {
    }
    
    private final void setupSwipeRefresh() {
    }
    
    private final void observeData() {
    }
    
    private final void setupPieChart(java.util.List<com.example.nammashaaleinventory.data.local.entity.Asset> assets) {
    }
    
    private final void showExportDialog() {
    }
    
    private final void exportCSV() {
    }
    
    private final void exportPDF() {
    }
    
    private final void showThemeDialog() {
    }
    
    private final void animateCards() {
    }
}