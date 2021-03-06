package com.example.mov

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mov.dashboard.WalletAdapter
import com.example.mov.databinding.ActivityEditProfileBinding
import com.example.mov.databinding.ActivityMyWalletBinding
import com.example.mov.databinding.ActivityTopUpBinding
import com.example.mov.databinding.FragmentDashboardBinding.bind
import com.example.mov.databinding.FragmentDashboardBinding.inflate
import com.example.mov.model.Wallet
import com.example.mov.sign.signin.User
import com.example.mov.util.Preferences
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class MyWalletlTopUpActivity : AppCompatActivity() {
private lateinit var binding: ActivityTopUpBinding

    private lateinit var user: User
    private lateinit var mFirebaseDatabase: DatabaseReference
    private lateinit var mFirebaseInstance: FirebaseDatabase
    private lateinit var mDatabase: DatabaseReference


    private lateinit var preferences: Preferences

    private var status10K : Boolean = false
    private var status20K : Boolean = false
    private var status30K : Boolean = false
    private var status40K : Boolean = false
    private var status50K : Boolean = false
    private var status60K : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityTopUpBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initListener()

    }

    private fun initListener() {
        binding.btnTopUp.setOnClickListener {
            startActivity(Intent(this, MyWalletSuccessActivity::class.java))
        }

        binding.tv10k.setOnClickListener {
            if (status10K) {
                deselectMoney(binding.tv10k)
            } else {
                selectMoney(binding.tv10k)
            }
        }

        binding.etAmount.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(
                s: CharSequence,
                start: Int,
                before: Int,
                count: Int
            ) { }

            override fun beforeTextChanged(
                s: CharSequence,
                start: Int,
                count: Int,
                after: Int
            ) {  }

            override fun afterTextChanged(s: Editable) {
                try {
                    if (s.toString().toInt() >= 10000) {
                        binding.btnTopUp.visibility = View.VISIBLE
                    } else {
                        binding.tv10k.setTextColor(resources.getColor(R.color.white))
                        binding.tv10k.setBackgroundResource(R.drawable.shape_line_white)
                        status10K = false
                        binding.btnTopUp.visibility = View.INVISIBLE
                    }
                } catch (e : NumberFormatException) {
                    binding.tv10k.setTextColor(resources.getColor(R.color.white))
                    binding.tv10k.setBackgroundResource(R.drawable.shape_line_white)
                    status10K = false
                    binding.btnTopUp.visibility = View.INVISIBLE
                }
            }
        })
    }

    private fun selectMoney(textView:TextView){
        textView.setTextColor(resources.getColor(R.color.colorPink))
        textView.setBackgroundResource(R.drawable.shape_line_pink)
        status10K = true

        binding.btnTopUp.visibility = View.VISIBLE
        binding.etAmount.setText("10000")
    }

    private fun deselectMoney(textView:TextView){
        textView.setTextColor(resources.getColor(R.color.white))
        textView.setBackgroundResource(R.drawable.shape_line_white)
        status10K = false

        binding.btnTopUp.visibility = View.INVISIBLE
        binding.etAmount.setText("")
    }

}
