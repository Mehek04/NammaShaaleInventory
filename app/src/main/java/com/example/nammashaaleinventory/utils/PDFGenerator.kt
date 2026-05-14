package com.example.nammashaaleinventory.utils

import android.content.Context
import android.content.Intent
import android.os.Environment
import android.widget.Toast
import androidx.core.content.FileProvider
import com.example.nammashaaleinventory.data.local.entity.Asset
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*

class PDFGenerator(private val context: Context) {

    fun generateReport(assets: List<Asset>): File {
        val dateFormat = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault())
        val fileName = "NammaShaale_Report_${dateFormat.format(Date())}.txt"

        // Save to app-specific folder in Documents
        val appDir = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), "NammaShaale")
        if (!appDir.exists()) appDir.mkdirs()

        val file = File(appDir, fileName)

        FileOutputStream(file).use { fos ->
            val reportText = buildReportText(assets)
            fos.write(reportText.toByteArray())
            fos.flush()
        }

        Toast.makeText(context, "Saved to: Documents/NammaShaale/$fileName", Toast.LENGTH_LONG).show()
        return file
    }

    private fun buildReportText(assets: List<Asset>): String {
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

        return sb.toString()
    }

    fun sharePDF(file: File) {
        try {
            val uri = FileProvider.getUriForFile(
                context,
                "${context.packageName}.fileprovider",
                file
            )
            val intent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_STREAM, uri)
                putExtra(Intent.EXTRA_SUBJECT, "Namma-Shaale Inventory Report")
                addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            }
            context.startActivity(Intent.createChooser(intent, "Share Report via"))
        } catch (e: Exception) {
            Toast.makeText(context, "Report saved in Documents/NammaShaale folder", Toast.LENGTH_LONG).show()
        }
    }
}