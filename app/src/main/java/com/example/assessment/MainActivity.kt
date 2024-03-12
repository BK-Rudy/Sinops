package com.example.assessment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.assessment.databinding.ActivityMainBinding
import com.example.assessment.viewmodel.UserViewModel
import com.google.android.gms.ads.MobileAds
import com.google.android.material.behavior.HideBottomViewOnScrollBehavior
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        MobileAds.initialize(this) {}

        val nomeUsuario = intent.getStringExtra("nome")
        var viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        viewModel.setNome(nomeUsuario.toString())

        val pagelist = arrayListOf(FragmentHome(), FragmentProfile(), FragmentSettings())
        val adapter = PageAdapter(pagelist, supportFragmentManager, lifecycle)
        binding.viewPager.adapter = adapter
        binding.viewPager.offscreenPageLimit = 3


        binding.bottomNav.setOnNavigationItemSelectedListener { item ->
            when(item.itemId){
                R.id.navigation_home -> binding.viewPager.currentItem = 0
                R.id.navigation_profile-> binding.viewPager.currentItem = 1
                R.id.navigation_about -> binding.viewPager.currentItem = 2
            }
            slideUp()
            true
        }
    }
    private  fun slideUp(){
        val layoutParams = binding.bottomNav.layoutParams as CoordinatorLayout.LayoutParams
        val bottomNavBehavior = layoutParams.behavior as HideBottomViewOnScrollBehavior
        bottomNavBehavior.slideUp(binding.bottomNav)
    }
}