package com.example.nammashaaleinventory.ui.dashboard

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.nammashaaleinventory.R
import com.example.nammashaaleinventory.databinding.ActivityDashboardBinding
import com.example.nammashaaleinventory.ui.asset.AddAssetActivity
import com.example.nammashaaleinventory.ui.asset.AssetListActivity
import com.example.nammashaaleinventory.ui.asset.MonthlyHealthCheckActivity
import com.example.nammashaaleinventory.ui.issue.IssueLogActivity
import com.example.nammashaaleinventory.ui.login.LoginActivity
import com.example.nammashaaleinventory.ui.repair.RepairRequestActivity
import com.example.nammashaaleinventory.ui.report.ReportActivity
import com.example.nammashaaleinventory.utils.CSVExporter
import com.example.nammashaaleinventory.utils.PDFGenerator
import com.example.nammashaaleinventory.utils.SessionManager
import com.example.nammashaaleinventory.utils.ThemeManager
import com.example.nammashaaleinventory.viewmodel.AssetViewModel
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import kotlinx.coroutines.launch

class DashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDashboardBinding
    private lateinit var assetViewModel: AssetViewModel
    private lateinit var themeManager: ThemeManager
    private lateinit var sessionManager: SessionManager
    private lateinit var csvExporter: CSVExporter
    private lateinit var pdfGenerator: PDFGenerator

    override fun onCreate(savedInstanceState: Bundle?) {
        // Apply theme
        themeManager = ThemeManager(this)
        ThemeManager.applyTheme(themeManager.isDarkMode())

        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        assetViewModel = ViewModelProvider(this)[AssetViewModel::class.java]
        sessionManager = SessionManager(this)
        csvExporter = CSVExporter(this)
        pdfGenerator = PDFGenerator(this)

        setupToolbar()
        setupClickListeners()
        setupSwipeRefresh()
        observeData()
        animateCards()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = "Namma-Shaale Inventory"

        binding.ivDarkMode.setOnClickListener {
            showThemeDialog()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.dashboard_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_logout -> {
                logout()
                true
            }
            R.id.menu_theme -> {
                showThemeDialog()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun logout() {
        AlertDialog.Builder(this)
            .setTitle("Logout")
            .setMessage("Are you sure you want to logout?")
            .setPositiveButton("Logout") { _, _ ->
                sessionManager.logout()
                val intent = Intent(this, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                finish()
                Toast.makeText(this, "Logged out successfully", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun setupClickListeners() {
        binding.fabAddAsset.setOnClickListener {
            startActivity(Intent(this, AddAssetActivity::class.java))
        }

        binding.fabExport.setOnClickListener {
            showExportDialog()
        }

        binding.cardAddAsset.setOnClickListener {
            startActivity(Intent(this, AddAssetActivity::class.java))
        }

        binding.cardAssetList.setOnClickListener {
            startActivity(Intent(this, AssetListActivity::class.java))
        }

        binding.cardMonthlyCheck.setOnClickListener {
            startActivity(Intent(this, MonthlyHealthCheckActivity::class.java))
        }

        binding.cardIssueLog.setOnClickListener {
            startActivity(Intent(this, IssueLogActivity::class.java))
        }

        binding.cardRepairRequest.setOnClickListener {
            startActivity(Intent(this, RepairRequestActivity::class.java))
        }

        binding.cardReport.setOnClickListener {
            startActivity(Intent(this, ReportActivity::class.java))
        }
    }

    private fun setupSwipeRefresh() {
        binding.swipeRefresh.setOnRefreshListener {
            observeData()
            binding.swipeRefresh.isRefreshing = false
            Toast.makeText(this, "Refreshed!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun observeData() {
        assetViewModel.totalCount.observe(this) { count ->
            binding.tvTotalCount.text = count.toString()
        }

        assetViewModel.workingCount.observe(this) { count ->
            binding.tvWorkingCount.text = count.toString()
        }

        assetViewModel.needsRepairCount.observe(this) { count ->
            binding.tvRepairCount.text = count.toString()
        }

        assetViewModel.allAssets.observe(this) { assets ->
            setupPieChart(assets)
        }
    }

    private fun setupPieChart(assets: List<com.example.nammashaaleinventory.data.local.entity.Asset>) {
        val working = assets.count { it.condition == "Working" }.toFloat()
        val needsRepair = assets.count { it.condition == "Needs Repair" }.toFloat()
        val broken = assets.count { it.condition == "Broken" }.toFloat()

        if (working == 0f && needsRepair == 0f && broken == 0f) return

        val entries = ArrayList<PieEntry>()
        if (working > 0) entries.add(PieEntry(working, "Working"))
        if (needsRepair > 0) entries.add(PieEntry(needsRepair, "Needs Repair"))
        if (broken > 0) entries.add(PieEntry(broken, "Broken"))

        val dataSet = PieDataSet(entries, "").apply {
            colors = listOf(
                Color.parseColor("#34A853"),
                Color.parseColor("#FBBC04"),
                Color.parseColor("#EA4335")
            )
            valueTextColor = Color.WHITE
            valueTextSize = 12f
            sliceSpace = 3f
        }

        val data = PieData(dataSet).apply {
            setValueFormatter(PercentFormatter(binding.pieChart))
            setValueTextSize(11f)
            setValueTextColor(Color.WHITE)
        }

        binding.pieChart.apply {
            this.data = data
            description.isEnabled = false
            isDrawHoleEnabled = true
            setHoleColor(Color.TRANSPARENT)
            holeRadius = 40f
            transparentCircleRadius = 45f
            setUsePercentValues(true)
            centerText = "${assets.size}\nAssets"
            setCenterTextSize(14f)
            setCenterTextColor(Color.parseColor("#5F6368"))
            legend.isEnabled = true
            animateY(1000)
            invalidate()
        }
    }

    private fun showExportDialog() {
        val options = arrayOf("📄 Export as PDF", "📊 Export as CSV", "Cancel")
        AlertDialog.Builder(this)
            .setTitle("Export Data")
            .setItems(options) { _, which ->
                when (which) {
                    0 -> exportPDF()
                    1 -> exportCSV()
                }
            }
            .show()
    }

    private fun exportCSV() {
        lifecycleScope.launch {
            val allAssets = assetViewModel.allAssets.value ?: emptyList()
            try {
                if (allAssets.isEmpty()) {
                    Toast.makeText(this@DashboardActivity, "No assets to export", Toast.LENGTH_SHORT).show()
                    return@launch
                }
                val file = csvExporter.exportAssets(allAssets)
                csvExporter.shareCSV(file)
            } catch (e: Exception) {
                Toast.makeText(this@DashboardActivity, "Export failed: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun exportPDF() {
        lifecycleScope.launch {
            val allAssets = assetViewModel.allAssets.value ?: emptyList()
            try {
                if (allAssets.isEmpty()) {
                    Toast.makeText(this@DashboardActivity, "No assets to export", Toast.LENGTH_SHORT).show()
                    return@launch
                }
                val file = pdfGenerator.generateReport(allAssets)
                pdfGenerator.sharePDF(file)
            } catch (e: Exception) {
                Toast.makeText(this@DashboardActivity, "Export failed: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showThemeDialog() {
        val options = arrayOf("☀️ Light Mode", "🌙 Dark Mode")
        val currentTheme = themeManager.isDarkMode()

        AlertDialog.Builder(this)
            .setTitle("Choose Theme")
            .setSingleChoiceItems(options, if (currentTheme) 1 else 0) { dialog, which ->
                themeManager.setDarkMode(which == 1)
                dialog.dismiss()
                recreate()
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun animateCards() {
        val cards = listOf(
            binding.cardAddAsset, binding.cardAssetList, binding.cardMonthlyCheck,
            binding.cardIssueLog, binding.cardRepairRequest, binding.cardReport
        )

        cards.forEachIndexed { index, card ->
            card.alpha = 0f
            card.translationY = 50f
            card.animate()
                .alpha(1f)
                .translationY(0f)
                .setDuration(300)
                .setStartDelay((index * 100).toLong())
                .start()
        }
    }
}