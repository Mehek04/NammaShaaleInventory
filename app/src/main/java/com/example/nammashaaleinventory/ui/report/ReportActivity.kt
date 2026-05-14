package com.example.nammashaaleinventory.ui.report

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.nammashaaleinventory.databinding.ActivityReportBinding
import com.example.nammashaaleinventory.utils.PDFGenerator
import com.example.nammashaaleinventory.viewmodel.AssetViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class ReportActivity : AppCompatActivity() {

    private lateinit var binding: ActivityReportBinding
    private lateinit var assetViewModel: AssetViewModel
    private lateinit var pdfGenerator: PDFGenerator
    private var reportText = ""
    private var allAssets = listOf<com.example.nammashaaleinventory.data.local.entity.Asset>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReportBinding.inflate(layoutInflater)
        setContentView(binding.root)

        assetViewModel = ViewModelProvider(this)[AssetViewModel::class.java]
        pdfGenerator = PDFGenerator(this)

        setupToolbar()
        observeData()
        setupButtons()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }
    }

    private fun observeData() {
        assetViewModel.allAssets.observe(this) { assets ->
            allAssets = assets
            generateReport(assets)
        }
    }

    private fun generateReport(assets: List<com.example.nammashaaleinventory.data.local.entity.Asset>) {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
        val currentDate = dateFormat.format(Date())

        val working = assets.count { it.condition == "Working" }
        val needsRepair = assets.count { it.condition == "Needs Repair" }
        val broken = assets.count { it.condition == "Broken" }

        val sb = StringBuilder()
        sb.appendLine("=================================")
        sb.appendLine("   NAMMA-SHAALE INVENTORY REPORT")
        sb.appendLine("=================================")
        sb.appendLine()
        sb.appendLine("Generated: $currentDate")
        sb.appendLine()
        sb.appendLine("--- SUMMARY ---")
        sb.appendLine("Total Assets: ${assets.size}")
        sb.appendLine("Working: $working")
        sb.appendLine("Needs Repair: $needsRepair")
        sb.appendLine("Broken: $broken")
        sb.appendLine()
        sb.appendLine("--- DETAILS ---")
        sb.appendLine()

        assets.forEachIndexed { index, asset ->
            sb.appendLine("${index + 1}. ${asset.name}")
            sb.appendLine("   Serial: ${asset.serialNumber}")
            sb.appendLine("   Category: ${asset.category}")
            sb.appendLine("   Condition: ${asset.condition}")
            sb.appendLine("   Added: ${dateFormat.format(Date(asset.addedDate))}")
            sb.appendLine()
        }

        sb.appendLine("=================================")
        sb.appendLine("End of Report")
        sb.appendLine("=================================")

        reportText = sb.toString()
        binding.tvReportContent.text = reportText
    }

    private fun setupButtons() {
        binding.btnShareReport.setOnClickListener {
            shareReport()
        }

        binding.btnExportPdf.setOnClickListener {
            exportToPDF()
        }
    }

    private fun shareReport() {
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_SUBJECT, "Namma-Shaale Inventory Report")
            putExtra(Intent.EXTRA_TEXT, reportText)
        }
        startActivity(Intent.createChooser(intent, "Share Report via"))
    }

    private fun exportToPDF() {
        lifecycleScope.launch {
            try {
                if (allAssets.isEmpty()) {
                    Toast.makeText(this@ReportActivity, "No assets to export", Toast.LENGTH_SHORT).show()
                    return@launch
                }
                val file = pdfGenerator.generateReport(allAssets)
                pdfGenerator.sharePDF(file)
                Toast.makeText(this@ReportActivity, "PDF exported successfully!", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                Toast.makeText(this@ReportActivity, "Export failed: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}