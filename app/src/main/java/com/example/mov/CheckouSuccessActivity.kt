package com.example.mov

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mov.databinding.ActivityCheckouSuccessBinding
import com.example.mov.databinding.ActivityCheckoutBinding

class CheckouSuccessActivity : AppCompatActivity() {

   lateinit var binding: ActivityCheckouSuccessBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityCheckouSuccessBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnHome.setOnClickListener {
            finishAffinity()

            var intent = Intent(this@CheckouSuccessActivity, HomeActivity::class.java)
            startActivity(intent)
        }
    }
}