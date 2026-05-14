package com.example.nammashaaleinventory.ui.asset

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nammashaaleinventory.data.local.entity.Asset
import com.example.nammashaaleinventory.databinding.ActivityMonthlyHealthCheckBinding
import com.example.nammashaaleinventory.viewmodel.AssetViewModel
import androidx.lifecycle.ViewModelProvider

class MonthlyHealthCheckActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMonthlyHealthCheckBinding
    private lateinit var assetViewModel: AssetViewModel
    private lateinit var healthCheckAdapter: HealthCheckAdapter
    private var currentFilter = "All"
    private var displayedAssets = listOf<Asset>()
    private val selectedAssets = mutableSetOf<Long>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMonthlyHealthCheckBinding.inflate(layoutInflater)
        setContentView(binding.root)

        assetViewModel = ViewModelProvider(this)[AssetViewModel::class.java]

        setupToolbar()
        setupRecyclerView()
        setupFilterChips()
        setupBulkUpdate()
        observeData()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.setNavigationOnClickListener { finish() }
    }

    private fun setupRecyclerView() {
        healthCheckAdapter = HealthCheckAdapter(emptyList(), emptySet()) { asset, isChecked ->
            if (isChecked) selectedAssets.add(asset.id) else selectedAssets.remove(asset.id)
        }

        binding.rvAssets.apply {
            adapter = healthCheckAdapter
            layoutManager = LinearLayoutManager(this@MonthlyHealthCheckActivity)
            setHasFixedSize(true)
        }
    }

    private fun setupFilterChips() {
        binding.chipAll.setOnClickListener {
            currentFilter = "All"
            updateChipSelection(binding.chipAll)
            assetViewModel.allAssets.observe(this) { assets -> updateDisplay(assets) }
        }
        binding.chipWorking.setOnClickListener {
            currentFilter = "Working"
            updateChipSelection(binding.chipWorking)
            assetViewModel.getAssetsByCondition("Working").observe(this) { assets -> updateDisplay(assets) }
        }
        binding.chipNeedsRepair.setOnClickListener {
            currentFilter = "Needs Repair"
            updateChipSelection(binding.chipNeedsRepair)
            assetViewModel.getAssetsByCondition("Needs Repair").observe(this) { assets -> updateDisplay(assets) }
        }
        binding.chipBroken.setOnClickListener {
            currentFilter = "Broken"
            updateChipSelection(binding.chipBroken)
            assetViewModel.getAssetsByCondition("Broken").observe(this) { assets -> updateDisplay(assets) }
        }
    }

    private fun updateChipSelection(selectedChip: com.google.android.material.chip.Chip) {
        listOf(binding.chipAll, binding.chipWorking, binding.chipNeedsRepair, binding.chipBroken).forEach { chip ->
            chip.isChecked = (chip == selectedChip)
        }
    }

    private fun setupBulkUpdate() {
        binding.btnMarkWorking.setOnClickListener {
            if (selectedAssets.isEmpty()) {
                Toast.makeText(this, "Select assets first!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            AlertDialog.Builder(this)
                .setTitle("Mark as Working")
                .setMessage("Mark ${selectedAssets.size} selected assets as Working?")
                .setPositiveButton("Yes") { _, _ ->
                    selectedAssets.forEach { id -> assetViewModel.updateCondition(id, "Working") }
                    Toast.makeText(this, "${selectedAssets.size} assets marked as Working ✅", Toast.LENGTH_SHORT).show()
                    selectedAssets.clear()
                    refreshData()
                }
                .setNegativeButton("Cancel", null)
                .show()
        }

        binding.btnMarkRepaired.setOnClickListener {
            if (selectedAssets.isEmpty()) {
                Toast.makeText(this, "Select assets first!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            AlertDialog.Builder(this)
                .setTitle("Mark as Repaired")
                .setMessage("Mark ${selectedAssets.size} selected assets as Working (Repaired)?")
                .setPositiveButton("Yes, Mark Repaired") { _, _ ->
                    selectedAssets.forEach { id -> assetViewModel.updateCondition(id, "Working") }
                    Toast.makeText(this, "${selectedAssets.size} assets marked as Repaired! 🎉", Toast.LENGTH_SHORT).show()
                    selectedAssets.clear()
                    refreshData()
                }
                .setNegativeButton("Cancel", null)
                .show()
        }

        binding.btnSelectAll.setOnClickListener {
            displayedAssets.forEach { selectedAssets.add(it.id) }
            healthCheckAdapter.notifyDataSetChanged()
            Toast.makeText(this, "${displayedAssets.size} assets selected", Toast.LENGTH_SHORT).show()
        }

        binding.btnClearSelection.setOnClickListener {
            selectedAssets.clear()
            healthCheckAdapter.notifyDataSetChanged()
            Toast.makeText(this, "Selection cleared", Toast.LENGTH_SHORT).show()
        }
    }

    private fun observeData() {
        assetViewModel.allAssets.observe(this) { assets ->
            updateSummary(assets)
            if (currentFilter == "All") updateDisplay(assets)
        }
    }

    private fun refreshData() {
        when (currentFilter) {
            "Working" -> assetViewModel.getAssetsByCondition("Working").observe(this) { updateDisplay(it) }
            "Needs Repair" -> assetViewModel.getAssetsByCondition("Needs Repair").observe(this) { updateDisplay(it) }
            "Broken" -> assetViewModel.getAssetsByCondition("Broken").observe(this) { updateDisplay(it) }
            else -> assetViewModel.allAssets.observe(this) { updateDisplay(it) }
        }
    }

    private fun updateSummary(assets: List<Asset>) {
        val working = assets.count { it.condition == "Working" }
        val needsRepair = assets.count { it.condition == "Needs Repair" }
        val broken = assets.count { it.condition == "Broken" }

        binding.tvWorkingHealth.text = working.toString()
        binding.tvRepairHealth.text = needsRepair.toString()
        binding.tvBrokenHealth.text = broken.toString()

        val total = assets.size
        val percent = if (total > 0) (working * 100 / total) else 0
        binding.progressHealth.progress = percent
        binding.tvHealthPercent.text = "$percent% Healthy (${working}/${total})"
    }

    private fun updateDisplay(assets: List<Asset>) {
        displayedAssets = assets
        selectedAssets.clear()
        healthCheckAdapter.updateData(assets, selectedAssets)
    }
}

// Custom Adapter with CheckBox
class HealthCheckAdapter(
    private var assets: List<Asset>,
    private var selectedIds: Set<Long>,
    private val onCheckedChange: (Asset, Boolean) -> Unit
) : RecyclerView.Adapter<HealthCheckAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val checkBox: CheckBox = itemView.findViewById(com.example.nammashaaleinventory.R.id.cbSelect)
        val tvName: TextView = itemView.findViewById(com.example.nammashaaleinventory.R.id.tvAssetName)
        val tvSerial: TextView = itemView.findViewById(com.example.nammashaaleinventory.R.id.tvSerialNumber)
        val tvCategory: TextView = itemView.findViewById(com.example.nammashaaleinventory.R.id.tvCategory)
        val tvCondition: TextView = itemView.findViewById(com.example.nammashaaleinventory.R.id.tvCondition)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(com.example.nammashaaleinventory.R.layout.item_health_check, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val asset = assets[position]
        holder.tvName.text = asset.name
        holder.tvSerial.text = "SN: ${asset.serialNumber}"
        holder.tvCategory.text = asset.category
        holder.tvCondition.text = asset.condition

        // Set condition color
        holder.tvCondition.setTextColor(when (asset.condition) {
            "Working" -> Color.parseColor("#4CAF50")
            "Needs Repair" -> Color.parseColor("#FFC107")
            "Broken" -> Color.parseColor("#F44336")
            else -> Color.GRAY
        })

        holder.checkBox.setOnCheckedChangeListener(null)
        holder.checkBox.isChecked = selectedIds.contains(asset.id)
        holder.checkBox.setOnCheckedChangeListener { _, isChecked ->
            onCheckedChange(asset, isChecked)
        }

        holder.itemView.setOnClickListener {
            holder.checkBox.isChecked = !holder.checkBox.isChecked
        }
    }

    override fun getItemCount() = assets.size

    fun updateData(newAssets: List<Asset>, newSelectedIds: Set<Long>) {
        assets = newAssets
        selectedIds = newSelectedIds
        notifyDataSetChanged()
    }
}