package com.example.assessment


import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.assessment.databinding.ActivityOnBoardingBinding
import com.example.assessment.viewmodel.UserViewModel

class ActivityOnBoarding : AppCompatActivity() {
    private lateinit var binding: ActivityOnBoardingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnBoardingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val model: UserViewModel by viewModels()
        val pagelist = arrayListOf(FragmentOnBoardingPrincipal(),FragmentOnBoarding(), FragmentOnboradingSecundario(), FragmentForm())
        val adapter  = PageAdapter(pagelist, supportFragmentManager, lifecycle)

        binding.viewPager.adapter = adapter
        binding.dotsIndicator.setViewPager2(binding.viewPager)

        var index = 0
        binding.proximo.setOnClickListener{
            if (index < 3){
                index += 1
                binding.viewPager.currentItem = index
            }
            else{


                if (model.nome.value.isNullOrEmpty()){
                    Toast.makeText(it.context, "Campo nome é obrigatório.", Toast.LENGTH_SHORT).show()
                } else {
                    finish()
                    val intent = Intent(this, MainActivity::class.java)
                    intent.putExtra("nome", model.nome.value)
                    startActivity(intent)
                }
            }
        }

    }

}

