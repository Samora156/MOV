package com.example.mov.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.mov.R
import com.example.mov.sign.signin.SignInActivity
import com.example.mov.util.Preferences

class OnBoardingOneActivity : AppCompatActivity() {

    lateinit var preferences:Preferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding_one)
        preferences = Preferences(this)
        if (preferences.getValues("onboarding").equals("1")){
            finishAffinity()

            var intent = Intent(this@OnBoardingOneActivity, SignInActivity::class.java)
            startActivity(intent)
        }
        val btn_lanjut: Button = findViewById(R.id.btn_daftar)
        btn_lanjut.setOnClickListener {
            val intent = Intent(this@OnBoardingOneActivity,
                OnBoardingTwoActivity::class.java)
            startActivity(intent)
        }

        val btn_lewat: Button = findViewById(R.id.btn_home)
        preferences.setValues("onboarding", "1")

        btn_lewat.setOnClickListener {
            finishAffinity()
            val intent = Intent(this@OnBoardingOneActivity,
                SignInActivity::class.java)
            startActivity(intent)
        }
    }
}