package com.example.mov

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mov.dashboard.WalletAdapter
import com.example.mov.databinding.ActivityMyWalletBinding
import com.example.mov.model.Wallet


class MyWalletlActivity : AppCompatActivity() {
private lateinit var binding: ActivityMyWalletBinding

private var dataList = ArrayList<Wallet>()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMyWalletBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        loadDummyData()

    }

    private fun initListener() {
        binding.rvTransaksi.layoutManager = LinearLayoutManager(this)
        binding.rvTransaksi.adapter = WalletAdapter(dataList){

        }

        binding.btnSaldo.setOnClickListener {
            startActivity(Intent(this, MyWalletlTopUpActivity::class.java))
        }
    }

    private fun loadDummyData() {
        dataList.add(
            Wallet(
                "Avengers Returns",
                "Sabtu 12 Jan, 2020",
                700000.0,
                "0"
            )
        )
        dataList.add(
            Wallet(
                "Top Up",
                "Sabtu 12 Jan, 2020",
                1700000.0,
                "1"
            )
        )
        dataList.add(
            Wallet(
                "Avengers Returns",
                "Sabtu 12 Jan, 2020",
                700000.0,
                "0"
            )
        )

        initListener()
    }
}