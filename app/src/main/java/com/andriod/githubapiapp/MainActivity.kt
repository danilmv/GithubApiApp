package com.andriod.githubapiapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.andriod.githubapiapp.databinding.ActivityMainBinding
import com.andriod.githubapiapp.utils.app
import com.github.terrakok.cicerone.androidx.AppNavigator

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val navigator by lazy { AppNavigator(this, binding.container.id) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            app.router.replaceScreen(Screens.UserList())
        }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        app.navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        app.navigatorHolder.removeNavigator()
        super.onPause()
    }
}