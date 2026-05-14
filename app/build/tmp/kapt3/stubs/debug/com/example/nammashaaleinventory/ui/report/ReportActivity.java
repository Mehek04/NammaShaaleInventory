package com.example.nammashaaleinventory.ui.report;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u000e\u001a\u00020\u000fH\u0002J\u0016\u0010\u0010\u001a\u00020\u000f2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0002J\b\u0010\u0012\u001a\u00020\u000fH\u0002J\u0012\u0010\u0013\u001a\u00020\u000f2\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0014J\b\u0010\u0016\u001a\u00020\u000fH\u0002J\b\u0010\u0017\u001a\u00020\u000fH\u0002J\b\u0010\u0018\u001a\u00020\u000fH\u0002R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0019"}, d2 = {"Lcom/example/nammashaaleinventory/ui/report/ReportActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "allAssets", "", "Lcom/example/nammashaaleinventory/data/local/entity/Asset;", "assetViewModel", "Lcom/example/nammashaaleinventory/viewmodel/AssetViewModel;", "binding", "Lcom/example/nammashaaleinventory/databinding/ActivityReportBinding;", "pdfGenerator", "Lcom/example/nammashaaleinventory/utils/PDFGenerator;", "reportText", "", "exportToPDF", "", "generateReport", "assets", "observeData", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "setupButtons", "setupToolbar", "shareReport", "app_debug"})
public final class ReportActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.example.nammashaaleinventory.databinding.ActivityReportBinding binding;
    private com.example.nammashaaleinventory.viewmodel.AssetViewModel assetViewModel;
    private com.example.nammashaaleinventory.utils.PDFGenerator pdfGenerator;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String reportText = "";
    @org.jetbrains.annotations.NotNull()
    private java.util.List<com.example.nammashaaleinventory.data.local.entity.Asset> allAssets;
    
    public ReportActivity() {
        super();
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void setupToolbar() {
    }
    
    private final void observeData() {
    }
    
    private final void generateReport(java.util.List<com.example.nammashaaleinventory.data.local.entity.Asset> assets) {
    }
    
    private final void setupButtons() {
    }
    
    private final void shareReport() {
    }
    
    private final void exportToPDF() {
    }
}