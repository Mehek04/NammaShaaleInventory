package com.example.nammashaaleinventory.ui.issue

import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nammashaaleinventory.adapter.IssueLogAdapter
import com.example.nammashaaleinventory.data.local.entity.IssueLogEntity
import com.example.nammashaaleinventory.databinding.ActivityIssueLogBinding
import com.example.nammashaaleinventory.viewmodel.IssueLogViewModel

class IssueLogActivity : AppCompatActivity() {

    private lateinit var binding: ActivityIssueLogBinding
    private lateinit var issueLogViewModel: IssueLogViewModel
    private lateinit var issueLogAdapter: IssueLogAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIssueLogBinding.inflate(layoutInflater)
        setContentView(binding.root)

        issueLogViewModel = ViewModelProvider(this)[IssueLogViewModel::class.java]

        setupToolbar()
        setupRecyclerView()
        setupFab()
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
        issueLogAdapter = IssueLogAdapter(emptyList()) { _ -> }

        binding.rvIssueLogs.apply {
            adapter = issueLogAdapter
            layoutManager = LinearLayoutManager(this@IssueLogActivity)
            setHasFixedSize(true)
        }
    }

    private fun setupFab() {
        binding.fabAddIssue.setOnClickListener {
            showAddIssueDialog()
        }
    }

    private fun observeData() {
        issueLogViewModel.allIssueLogs.observe(this) { issueLogs ->
            issueLogAdapter.updateData(issueLogs)
        }
    }

    private fun showAddIssueDialog() {
        val editText = EditText(this)
        editText.hint = "Describe the issue..."

        AlertDialog.Builder(this)
            .setTitle("Log New Issue")
            .setView(editText)
            .setPositiveButton("Save") { _, _ ->
                val description = editText.text.toString().trim()
                if (description.isNotEmpty()) {
                    val issueLog = IssueLogEntity(
                        description = description,
                        date = System.currentTimeMillis()
                    )
                    issueLogViewModel.insert(issueLog)
                    Toast.makeText(this, "Issue logged", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }
}