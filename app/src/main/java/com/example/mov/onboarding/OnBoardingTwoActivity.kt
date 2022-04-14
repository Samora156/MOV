package com.example.mov.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.mov.R
import com.example.mov.sign.signin.SignInActivity

class OnBoardingTwoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding_two)
        val btn_lanjut: Button = findViewById(R.id.btn_lanjut)
        btn_lanjut.setOnClickListener {
            var intent = Intent(this@OnBoardingTwoActivity, OnBoardingThreeActivity::class.java)
            startActivity(intent)
        }
        val btn_lewat: Button = findViewById(R.id.btn_lewati)
        btn_lewat.setOnClickListener {
            var intent = Intent(this@OnBoardingTwoActivity, SignInActivity::class.java)
            startActivity(intent)
        }
    }
}