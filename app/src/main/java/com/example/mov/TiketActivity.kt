package com.example.mov

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.mov.dashboard.TiketAdapter
import com.example.mov.databinding.ActivityTiketBinding
import com.example.mov.databinding.FragmentTiketBinding
import com.example.mov.model.Checkout
import com.example.mov.model.Film
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class TiketActivity : AppCompatActivity() {

    lateinit var binding: ActivityTiketBinding
    private var  datalist = ArrayList<Checkout>()


    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityTiketBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        var data = intent.getParcelableExtra<Film>("data")

        binding.tvTitle.text = data!!.judul
        binding.tvDirec.text = data!!.director
        binding.tvRating.text = data!!.rating

        Glide.with(this)
            .load(data.poster)
            .into(binding.ivPosterImage)

        binding.rcCheckout.layoutManager = LinearLayoutManager(this)

        datalist.add(Checkout("C1", ""))
        datalist.add(Checkout("C2", ""))

        binding.rcCheckout.adapter = TiketAdapter(datalist) {

        }

    }
}