package com.example.mov

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.mov.dashboard.ComingSoonAdapter
import com.example.mov.dashboard.NowPlayingAdapter
import com.example.mov.databinding.FragmentDashboardBinding
import com.example.mov.model.Film
import com.example.mov.util.Preferences
import com.google.firebase.database.*
import java.text.NumberFormat
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

private lateinit var preferences: Preferences
private  lateinit var mDatabase: DatabaseReference

private var _binding: FragmentDashboardBinding? = null
// This property is only valid between onCreateView and
// onDestroyView.
private val binding get() = _binding!!

private var dataList = ArrayList<Film>()

/**
 * A simple [Fragment] subclass.
 * Use the [DashboardFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DashboardFragment : Fragment() {

    private lateinit var preferences: Preferences
    lateinit var mDatabase: DatabaseReference

    private var dataList = ArrayList<Film>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        preferences = Preferences(requireActivity().applicationContext)
        mDatabase = FirebaseDatabase.getInstance("https://movproject-d4a18-default-rtdb.firebaseio.com/").getReference("Film")

        binding.tvNama.setText(preferences.getValues("nama"))
        if (!preferences.getValues("saldo").equals("")){
            currecy(preferences.getValues("saldo")!!.toDouble(), binding.tvSaldo)
        }

        Glide.with(this)
            .load(preferences.getValues("url"))
            .apply(RequestOptions.circleCropTransform())
            .into(binding.ivProfile)

        binding.rvNowPlaying.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.rvComingSoon.layoutManager = LinearLayoutManager(requireContext().applicationContext)
        getData()
    }

    private fun getData() {
        mDatabase.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                dataList.clear()
                for (getdataSnapshot in dataSnapshot.getChildren()) {

                    val film = getdataSnapshot.getValue(Film::class.java!!)
                    dataList.add(film!!)
                }

                if (dataList.isNotEmpty()) {
                    binding.rvNowPlaying.adapter = NowPlayingAdapter(dataList) {
                        val intent = Intent(
                            context,
                            DetailActivity::class.java
                        ).putExtra("data", it)
                        startActivity(intent)
                    }

                    binding.rvComingSoon.adapter = ComingSoonAdapter(dataList) {
                        val intent = Intent(
                            context,
                            DetailActivity::class.java
                        ).putExtra("data", it)
                        startActivity(intent)
                    }
                }

            }
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, ""+error.message, Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun currecy(harga:Double, textView: TextView) {
        val localeID = Locale("in", "ID")
        val formatRupiah = NumberFormat.getCurrencyInstance(localeID)
        textView.setText(formatRupiah.format(harga as Double))

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}



