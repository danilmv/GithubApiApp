package com.andriod.githubapiapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.andriod.githubapiapp.databinding.ActivityMainBinding
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import org.koin.android.ext.android.get
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val navigator by lazy { AppNavigator(this, binding.container.id) }
    private val router: Router by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            router.replaceScreen(Screens.UserList())
        }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        get<NavigatorHolder>().setNavigator(navigator)
    }

    override fun onPause() {
        get<NavigatorHolder>().removeNavigator()
        super.onPause()
    }
}