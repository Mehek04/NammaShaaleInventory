package com.example.nammashaaleinventory.utils

import android.content.Context
import android.content.Intent
import android.os.Environment
import android.widget.Toast
import androidx.core.content.FileProvider
import com.example.nammashaaleinventory.data.local.entity.Asset
import java.io.File
import java.io.FileWriter
import java.text.SimpleDateFormat
import java.util.*

class CSVExporter(private val context: Context) {

    fun exportAssets(assets: List<Asset>): File {
        val dateFormat = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault())
        val fileName = "NammaShaale_Assets_${dateFormat.format(Date())}.csv"

        // Save to app-specific folder in Documents
        val appDir = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), "NammaShaale")
        if (!appDir.exists()) appDir.mkdirs()

        val file = File(appDir, fileName)

        FileWriter(file).use { writer ->
            writer.append("ID,Name,Serial Number,Category,Condition,Added Date,Last Checked\n")

            assets.forEach { asset ->
                val addedDate = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date(asset.addedDate))
                val checkedDate = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date(asset.lastCheckedDate))

                writer.append("${asset.id},")
                writer.append("\"${asset.name}\",")
                writer.append("\"${asset.serialNumber}\",")
                writer.append("${asset.category},")
                writer.append("${asset.condition},")
                writer.append("${addedDate},")
                writer.append("${checkedDate}\n")
            }
            writer.flush()
        }

        Toast.makeText(context, "Saved to: Documents/NammaShaale/$fileName", Toast.LENGTH_LONG).show()
        return file
    }

    fun shareCSV(file: File) {
        try {
            val uri = FileProvider.getUriForFile(
                context,
                "${context.packageName}.fileprovider",
                file
            )
            val intent = Intent(Intent.ACTION_SEND).apply {
                type = "text/csv"
                putExtra(Intent.EXTRA_STREAM, uri)
                putExtra(Intent.EXTRA_SUBJECT, "Namma-Shaale Assets Export")
                addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            }
            context.startActivity(Intent.createChooser(intent, "Share CSV via"))
        } catch (e: Exception) {
            Toast.makeText(context, "CSV saved in Documents/NammaShaale folder", Toast.LENGTH_LONG).show()
        }
    }
}