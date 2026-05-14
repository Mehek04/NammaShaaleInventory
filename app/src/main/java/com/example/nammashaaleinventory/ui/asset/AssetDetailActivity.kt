package com.example.nammashaaleinventory.ui.asset

import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.nammashaaleinventory.data.local.entity.Asset
import com.example.nammashaaleinventory.databinding.ActivityAssetDetailBinding
import com.example.nammashaaleinventory.utils.QRCodeGenerator
import com.example.nammashaaleinventory.viewmodel.AssetViewModel
import kotlinx.coroutines.launch
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class AssetDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAssetDetailBinding
    private lateinit var assetViewModel: AssetViewModel
    private var currentAsset: Asset? = null
    private var assetId: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAssetDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        assetViewModel = ViewModelProvider(this)[AssetViewModel::class.java]

        setupToolbar()

        assetId = intent.getLongExtra("asset_id", 0)

        if (assetId > 0) {
            loadAsset()
        }

        setupClickListeners()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }
    }

    private fun setupClickListeners() {
        binding.btnEdit.setOnClickListener {
            Toast.makeText(this, "Edit feature coming soon", Toast.LENGTH_SHORT).show()
        }

        binding.btnDelete.setOnClickListener {
            showDeleteConfirmation()
        }

        binding.btnQRCode.setOnClickListener {
            showQRCode()
        }
    }

    private fun loadAsset() {
        lifecycleScope.launch {
            currentAsset = assetViewModel.getAssetById(assetId)
            currentAsset?.let { asset ->
                displayAssetDetails(asset)
            }
        }
    }

    private fun displayAssetDetails(asset: Asset) {
        binding.tvAssetName.text = asset.name
        binding.tvSerialNumber.text = "Serial Number: ${asset.serialNumber}"
        binding.tvCategory.text = "Category: ${asset.category}"
        binding.tvCondition.text = "Condition: ${asset.condition}"

        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        binding.tvDate.text = "Added: ${dateFormat.format(Date(asset.addedDate))}"

        if (asset.photoPath.isNotEmpty()) {
            val photoFile = File(asset.photoPath)
            if (photoFile.exists()) {
                val bitmap = BitmapFactory.decodeFile(asset.photoPath)
                binding.ivAssetPhoto.setImageBitmap(bitmap)
            }
        }
    }

    private fun showQRCode() {
        currentAsset?.let { asset ->
            val qrData = "Asset: ${asset.name}\nSerial: ${asset.serialNumber}\nCategory: ${asset.category}\nCondition: ${asset.condition}"
            val qrBitmap = QRCodeGenerator.generateQRCode(qrData, 512)

            val imageView = ImageView(this)
            imageView.setImageBitmap(qrBitmap)
            imageView.setPadding(32, 32, 32, 32)

            AlertDialog.Builder(this)
                .setTitle("QR Code - ${asset.name}")
                .setView(imageView)
                .setPositiveButton("Close", null)
                .show()
        }
    }

    private fun showDeleteConfirmation() {
        AlertDialog.Builder(this)
            .setTitle("Delete Asset")
            .setMessage("Are you sure you want to delete this asset?")
            .setPositiveButton("Delete") { _, _ ->
                currentAsset?.let { asset ->
                    assetViewModel.delete(asset)
                    Toast.makeText(this, "Asset deleted", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }
}