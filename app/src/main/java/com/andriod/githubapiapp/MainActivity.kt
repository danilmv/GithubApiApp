package com.andriod.githubapiapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.andriod.githubapiapp.databinding.ActivityMainBinding
import com.andriod.githubapiapp.utils.app
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val navigator by lazy { AppNavigator(this, binding.container.id) }

    @Inject
    lateinit var router: Router

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        app.appComponent.inject(this)

        if (savedInstanceState == null) {
            router.replaceScreen(Screens.UserList())
        }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        app.appComponent.getNavigationHolder().setNavigator(navigator)
    }

    override fun onPause() {
        app.appComponent.getNavigationHolder().removeNavigator()
        super.onPause()
    }
}