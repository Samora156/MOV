package com.example.mov

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.mov.DashboardFragment
import com.example.mov.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityHomeBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val fragmentTiket = TiketFragment()
        val fragmentSetting = SettingFragment()
        val fragmentHome = DashboardFragment()

        setFragment(fragmentHome)

        binding.ivMenu1.setOnClickListener {
            setFragment(fragmentHome)

            changeIcon(binding.ivMenu1, R.drawable.ic_home_active)
            changeIcon(binding.ivMenu2, R.drawable.ic_tiket)
            changeIcon(binding.ivMenu3, R.drawable.ic_profile)
        }

        binding.ivMenu2.setOnClickListener {
            setFragment(fragmentTiket)

            changeIcon(binding.ivMenu1, R.drawable.ic_home)
            changeIcon(binding.ivMenu2, R.drawable.ic_tiket_active)
            changeIcon(binding.ivMenu3, R.drawable.ic_profile)
        }

        binding.ivMenu3.setOnClickListener {
            setFragment(fragmentSetting)

            changeIcon(binding.ivMenu1, R.drawable.ic_home)
            changeIcon(binding.ivMenu2, R.drawable.ic_tiket)
            changeIcon(binding.ivMenu3, R.drawable.ic_profile_active)
        }
    }

    protected fun setFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.layout_frame, fragment)
        fragmentTransaction.commit()
    }

    private fun changeIcon(imageView: ImageView, int: Int){
        imageView.setImageResource(int)
    }
}