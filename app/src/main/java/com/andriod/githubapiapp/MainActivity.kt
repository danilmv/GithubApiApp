package com.andriod.githubapiapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.andriod.githubapiapp.utils.app

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        app.dataProvider.apply {
            subscribe {
                Toast.makeText(
                    this@MainActivity,
                    "Data loaded: ${users.size} records",
                    Toast.LENGTH_SHORT
                ).show()
            }
            readData()
        }
    }
}