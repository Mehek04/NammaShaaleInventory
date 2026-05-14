package com.example.nammashaaleinventory.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nammashaaleinventory.R
import com.example.nammashaaleinventory.data.local.entity.Asset

class AssetAdapter(
    private var assets: List<Asset>,
    private val onItemClick: (Asset) -> Unit
) : RecyclerView.Adapter<AssetAdapter.AssetViewHolder>() {

    class AssetViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.tvAssetName)
        val serialTextView: TextView = itemView.findViewById(R.id.tvSerialNumber)
        val categoryTextView: TextView = itemView.findViewById(R.id.tvCategory)
        val conditionTextView: TextView = itemView.findViewById(R.id.tvCondition)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AssetViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_asset, parent, false)
        return AssetViewHolder(view)
    }

    override fun onBindViewHolder(holder: AssetViewHolder, position: Int) {
        val asset = assets[position]
        holder.nameTextView.text = asset.name
        holder.serialTextView.text = "SN: ${asset.serialNumber}"
        holder.categoryTextView.text = asset.category
        holder.conditionTextView.text = asset.condition

        when (asset.condition) {
            "Working" -> holder.conditionTextView.setTextColor(Color.parseColor("#4CAF50"))
            "Needs Repair" -> holder.conditionTextView.setTextColor(Color.parseColor("#FFC107"))
            "Broken" -> holder.conditionTextView.setTextColor(Color.parseColor("#F44336"))
        }

        holder.itemView.setOnClickListener {
            onItemClick(asset)
        }
    }

    override fun getItemCount() = assets.size

    fun updateData(newAssets: List<Asset>) {
        assets = newAssets
        notifyDataSetChanged()
    }
}