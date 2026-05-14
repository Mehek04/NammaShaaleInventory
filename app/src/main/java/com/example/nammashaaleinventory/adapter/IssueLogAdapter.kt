package com.example.nammashaaleinventory.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nammashaaleinventory.R
import com.example.nammashaaleinventory.data.local.entity.IssueLogEntity
import java.text.SimpleDateFormat
import java.util.*

class IssueLogAdapter(
    private var issueLogs: List<IssueLogEntity>,
    private val onItemClick: (IssueLogEntity) -> Unit
) : RecyclerView.Adapter<IssueLogAdapter.IssueLogViewHolder>() {

    class IssueLogViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val descriptionTextView: TextView = itemView.findViewById(R.id.tvIssueDescription)
        val dateTextView: TextView = itemView.findViewById(R.id.tvIssueDate)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IssueLogViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_issue_log, parent, false)
        return IssueLogViewHolder(view)
    }

    override fun onBindViewHolder(holder: IssueLogViewHolder, position: Int) {
        val issueLog = issueLogs[position]
        holder.descriptionTextView.text = issueLog.description

        val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
        holder.dateTextView.text = dateFormat.format(Date(issueLog.date))

        holder.itemView.setOnClickListener {
            onItemClick(issueLog)
        }
    }

    override fun getItemCount() = issueLogs.size

    fun updateData(newIssueLogs: List<IssueLogEntity>) {
        issueLogs = newIssueLogs
        notifyDataSetChanged()
    }
}