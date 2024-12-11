
package com.example.apptodolist

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.CalendarView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var btnPekerjaan: Button
    private var selectedDate: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnPekerjaan = findViewById(R.id.btnPekerjaan)
        btnPekerjaan.setOnClickListener {
            showUploadDialog()
        }
    }

    private fun showUploadDialog() {
        val dialog = Dialog(this)
        val dialogView = LayoutInflater.from(this).inflate(R.layout.upload_list, null)
        dialog.setContentView(dialogView)

        val editTextJobName = dialogView.findViewById<EditText>(R.id.editTextJobName)
        val calendarView = dialogView.findViewById<CalendarView>(R.id.calendarView)
        val btnSimpan = dialogView.findViewById<Button>(R.id.btnSimpan)

        calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val calendar = Calendar.getInstance()
            calendar.set(year, month, dayOfMonth)
            selectedDate = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(calendar.time)
        }

        btnSimpan.setOnClickListener {
            val jobName = editTextJobName.text.toString()

            if (jobName.isNotEmpty() && selectedDate.isNotEmpty()) {
                Toast.makeText(this, "Kegiatan $jobName pada $selectedDate berhasil disimpan", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            } else {
                Toast.makeText(this, "Harap isi nama kegiatan dan pilih tanggal", Toast.LENGTH_SHORT).show()
            }
        }

        dialog.show()
    }
}