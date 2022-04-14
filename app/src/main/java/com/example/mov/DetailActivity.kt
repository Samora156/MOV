package com.example.mov

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.mov.dashboard.NowPlayingAdapter
import com.example.mov.dashboard.WhoPlayAdapter
import com.example.mov.databinding.ActivityDetailBinding
import com.example.mov.model.Film
import com.example.mov.model.Plays
import com.google.firebase.database.*


class DetailActivity : AppCompatActivity() {
private lateinit var binding: ActivityDetailBinding

private lateinit var mDatabase: DatabaseReference
private var dataList = ArrayList<Plays>()


    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityDetailBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val data = intent.getParcelableExtra<Film>("data")
        mDatabase = FirebaseDatabase.getInstance("https://movproject-d4a18-default-rtdb.firebaseio.com/").getReference("Film")
            .child(data?.judul.toString())
            .child("play")

        binding.tvKursi.text = data?.judul
        binding.tvGenre.text = data?.genre
        binding.tvDesc.text = data?.desc
        binding.tvRating.text = data?.rating

        Glide.with(this)
            .load(data?.poster)
            .into(binding.ivPoster)

        binding.rvWhoPlay.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        getData()

        binding.btnPilihBangku.setOnClickListener {
            var goPilihBangku: Intent = Intent(this@DetailActivity, PilihBangkuActivity::class.java).putExtra("data", data)
            startActivity(goPilihBangku)
        }

    }

    private fun getData() {
        mDatabase.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                dataList.clear()
                for (getdataSnapshot in dataSnapshot.getChildren()) {

                    val film = getdataSnapshot.getValue(Plays::class.java!!)
                    dataList.add(film!!)
                }
                binding.rvWhoPlay.adapter = WhoPlayAdapter(dataList) {

                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(this@DetailActivity, ""+databaseError.message, Toast.LENGTH_LONG).show()
            }

        })
    }
}