package com.example.nammashaaleinventory.ui.repair

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nammashaaleinventory.adapter.AssetAdapter
import com.example.nammashaaleinventory.data.local.entity.Asset
import com.example.nammashaaleinventory.data.local.entity.RepairEntity
import com.example.nammashaaleinventory.databinding.ActivityRepairRequestBinding
import com.example.nammashaaleinventory.viewmodel.AssetViewModel
import com.example.nammashaaleinventory.viewmodel.RepairViewModel

class RepairRequestActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRepairRequestBinding
    private lateinit var assetViewModel: AssetViewModel
    private lateinit var repairViewModel: RepairViewModel
    private lateinit var assetAdapter: AssetAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRepairRequestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        assetViewModel = ViewModelProvider(this)[AssetViewModel::class.java]
        repairViewModel = ViewModelProvider(this)[RepairViewModel::class.java]

        setupToolbar()
        setupRecyclerView()
        observeData()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }
    }

    private fun setupRecyclerView() {
        assetAdapter = AssetAdapter(emptyList()) { asset ->
            showRepairOptions(asset)
        }

        binding.rvRepairs.apply {
            adapter = assetAdapter
            layoutManager = LinearLayoutManager(this@RepairRequestActivity)
            setHasFixedSize(true)
        }
    }

    private fun observeData() {
        assetViewModel.getAssetsByCondition("Needs Repair").observe(this) { needsRepair ->
            assetViewModel.getAssetsByCondition("Broken").observe(this) { broken ->
                val allDamaged = needsRepair + broken
                assetAdapter.updateData(allDamaged)
            }
        }
    }

    private fun showRepairOptions(asset: Asset) {
        val options = arrayOf("Request Repair", "Mark as Working", "Cancel")

        AlertDialog.Builder(this)
            .setTitle("Repair Options for ${asset.name}")
            .setItems(options) { _, which ->
                when (which) {
                    0 -> {
                        val repair = RepairEntity(
                            assetId = asset.id,
                            assetName = asset.name,
                            condition = asset.condition
                        )
                        repairViewModel.insert(repair)
                        Toast.makeText(this, "Repair requested", Toast.LENGTH_SHORT).show()
                    }
                    1 -> {
                        assetViewModel.updateCondition(asset.id, "Working")
                        Toast.makeText(this, "Marked as working", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            .show()
    }
}