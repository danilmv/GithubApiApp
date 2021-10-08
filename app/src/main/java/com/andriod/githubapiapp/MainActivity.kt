package com.andriod.githubapiapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.andriod.githubapiapp.databinding.ActivityMainBinding
import com.andriod.githubapiapp.utils.app

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            app.router.showUserList(this)
        }
    }
}