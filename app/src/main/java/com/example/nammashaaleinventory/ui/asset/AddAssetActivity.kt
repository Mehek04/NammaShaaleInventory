package com.example.nammashaaleinventory.ui.asset

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.lifecycle.ViewModelProvider
import com.example.nammashaaleinventory.data.local.entity.Asset
import com.example.nammashaaleinventory.databinding.ActivityAddAssetBinding
import com.example.nammashaaleinventory.viewmodel.AssetViewModel
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class AddAssetActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddAssetBinding
    private lateinit var assetViewModel: AssetViewModel
    private var photoPath: String = ""
    private var photoUri: Uri? = null

    private val takePictureLauncher = registerForActivityResult(
        ActivityResultContracts.TakePicture()
    ) { success ->
        if (success) {
            binding.ivAssetPhoto.setImageURI(photoUri)
            Toast.makeText(this, "Photo captured!", Toast.LENGTH_SHORT).show()
        }
    }

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            openCamera()
        } else {
            Toast.makeText(this, "Camera permission required", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddAssetBinding.inflate(layoutInflater)
        setContentView(binding.root)

        assetViewModel = ViewModelProvider(this)[AssetViewModel::class.java]

        setupToolbar()
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
        binding.btnTakePhoto.setOnClickListener {
            if (allPermissionsGranted()) {
                openCamera()
            } else {
                requestPermission()
            }
        }

        binding.btnSave.setOnClickListener {
            saveAsset()
        }
    }

    private fun openCamera() {
        val photoFile = createImageFile()
        photoUri = FileProvider.getUriForFile(
            this,
            "${packageName}.fileprovider",
            photoFile
        )
        takePictureLauncher.launch(photoUri)
    }

    private fun createImageFile(): File {
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val imageFileName = "JPEG_${timeStamp}_"
        val storageDir = File(externalMediaDirs.firstOrNull() ?: filesDir, "NammaShaale")
        storageDir.mkdirs()
        val imageFile = File.createTempFile(imageFileName, ".jpg", storageDir)
        photoPath = imageFile.absolutePath
        return imageFile
    }

    private fun saveAsset() {
        val name = binding.etAssetName.text.toString().trim()
        val serialNumber = binding.etSerialNumber.text.toString().trim()

        val category = when (binding.rgCategory.checkedRadioButtonId) {
            binding.rbLab.id -> "Lab"
            binding.rbSports.id -> "Sports"
            binding.rbTablet.id -> "Tablet"
            else -> "Lab"
        }

        val condition = when (binding.rgCondition.checkedRadioButtonId) {
            binding.rbWorking.id -> "Working"
            binding.rbNeedsRepair.id -> "Needs Repair"
            binding.rbBroken.id -> "Broken"
            else -> "Working"
        }

        if (name.isEmpty() || serialNumber.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            return
        }

        val asset = Asset(
            name = name,
            serialNumber = serialNumber,
            category = category,
            condition = condition,
            photoPath = photoPath
        )

        assetViewModel.insert(asset)
        Toast.makeText(this, "Asset saved successfully!", Toast.LENGTH_SHORT).show()
        finish()
    }

    private fun allPermissionsGranted() =
        ContextCompat.checkSelfPermission(
            this, Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED

    private fun requestPermission() {
        requestPermissionLauncher.launch(Manifest.permission.CAMERA)
    }
}