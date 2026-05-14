package com.example.nammashaaleinventory.ui.asset

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nammashaaleinventory.R
import com.example.nammashaaleinventory.adapter.AssetAdapter
import com.example.nammashaaleinventory.databinding.ActivityAssetListBinding
import com.example.nammashaaleinventory.utils.CSVExporter
import com.example.nammashaaleinventory.viewmodel.AssetViewModel
import kotlinx.coroutines.launch

class AssetListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAssetListBinding
    private lateinit var assetViewModel: AssetViewModel
    private lateinit var assetAdapter: AssetAdapter
    private lateinit var csvExporter: CSVExporter
    private var allAssetsList = listOf<com.example.nammashaaleinventory.data.local.entity.Asset>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAssetListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        assetViewModel = ViewModelProvider(this)[AssetViewModel::class.java]
        csvExporter = CSVExporter(this)

        setupToolbar()
        setupRecyclerView()
        setupSearch()
        observeData()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.setNavigationOnClickListener { finish() }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.asset_list_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_export_csv -> {
                exportToCSV()
                true
            }
            R.id.menu_delete_all -> {
                showDeleteAllDialog()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showDeleteAllDialog() {
        if (allAssetsList.isEmpty()) {
            Toast.makeText(this, "No assets to delete", Toast.LENGTH_SHORT).show()
            return
        }
        AlertDialog.Builder(this)
            .setTitle("Delete All Assets")
            .setMessage("Are you sure you want to delete ALL ${allAssetsList.size} assets? This cannot be undone!")
            .setPositiveButton("Delete All") { _, _ ->
                lifecycleScope.launch {
                    allAssetsList.forEach { asset -> assetViewModel.delete(asset) }
                    Toast.makeText(this@AssetListActivity, "All assets deleted!", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun exportToCSV() {
        lifecycleScope.launch {
            try {
                if (allAssetsList.isEmpty()) {
                    Toast.makeText(this@AssetListActivity, "No assets to export", Toast.LENGTH_SHORT).show()
                    return@launch
                }
                val file = csvExporter.exportAssets(allAssetsList)
                csvExporter.shareCSV(file)
                Toast.makeText(this@AssetListActivity, "CSV exported!", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                Toast.makeText(this@AssetListActivity, "Export failed: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupRecyclerView() {
        assetAdapter = AssetAdapter(emptyList()) { asset ->
            showAssetOptionsDialog(asset)
        }

        binding.rvAssets.apply {
            adapter = assetAdapter
            layoutManager = LinearLayoutManager(this@AssetListActivity)
            setHasFixedSize(true)
        }
    }

    private fun showAssetOptionsDialog(asset: com.example.nammashaaleinventory.data.local.entity.Asset) {
        val options = arrayOf("📋 View Details", "🗑️ Delete Asset", "Cancel")
        AlertDialog.Builder(this)
            .setTitle(asset.name)
            .setItems(options) { _, which ->
                when (which) {
                    0 -> {
                        val intent = Intent(this, AssetDetailActivity::class.java)
                        intent.putExtra("asset_id", asset.id)
                        startActivity(intent)
                    }
                    1 -> {
                        AlertDialog.Builder(this)
                            .setTitle("Delete ${asset.name}?")
                            .setMessage("This action cannot be undone.")
                            .setPositiveButton("Delete") { _, _ ->
                                assetViewModel.delete(asset)
                                Toast.makeText(this, "Asset deleted!", Toast.LENGTH_SHORT).show()
                            }
                            .setNegativeButton("Cancel", null)
                            .show()
                    }
                }
            }
            .show()
    }

    private fun setupSearch() {
        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val query = s?.toString()?.trim() ?: ""
                if (query.isEmpty()) assetAdapter.updateData(allAssetsList)
                else assetViewModel.searchAssets(query)
            }
            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun observeData() {
        assetViewModel.searchResults.observe(this) { assets ->
            if (binding.etSearch.text?.isNotEmpty() == true) assetAdapter.updateData(assets)
        }
        assetViewModel.allAssets.observe(this) { assets ->
            allAssetsList = assets
            if (binding.etSearch.text?.isEmpty() != false) assetAdapter.updateData(assets)
        }
    }
}