package com.example.mymusicplayer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner

class SettingsActivity : AppCompatActivity() {
    var lstColors= mutableListOf<String>("red", "white","black")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        var spinner:Spinner=findViewById(R.id.themeSpinner)
        var myAdapter=ArrayAdapter(this, androidx.constraintlayout.widget.R.layout.support_simple_spinner_dropdown_item,lstColors)
        spinner.adapter=myAdapter
        spinner.onItemSelectedListener=object:AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                println(position)
                var mySp=getSharedPreferences("colorsbg", MODE_PRIVATE)
                var spEditor=mySp.edit()
                spEditor.putString("color",lstColors[position])
                spEditor.apply()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

    }


    override fun onBackPressed() {
        startActivity(Intent(this,MainActivity::class.java))
        finish()
        super.onBackPressed()
    }
}