package com.example.mov.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.mov.R
import com.example.mov.sign.signin.SignInActivity

class OnBoardingThreeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding_three)
        val btn_lanjut: Button = findViewById(R.id.btn_lanjut_terus)
        btn_lanjut.setOnClickListener {
            finishAffinity()

            var intent = Intent(this@OnBoardingThreeActivity, SignInActivity::class.java)
            startActivity(intent)
        }
    }
}