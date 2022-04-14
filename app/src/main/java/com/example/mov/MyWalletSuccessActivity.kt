package com.example.mov

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mov.databinding.ActivityCheckouSuccessBinding
import com.example.mov.databinding.ActivityCheckoutBinding
import com.example.mov.databinding.ActivityMyWalletSuccessBinding

class MyWalletSuccessActivity : AppCompatActivity() {

   lateinit var binding: ActivityMyWalletSuccessBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMyWalletSuccessBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnHome.setOnClickListener {
            finishAffinity()

            val intent = Intent(this@MyWalletSuccessActivity,
                HomeActivity::class.java)
            startActivity(intent)
        }
    }
}