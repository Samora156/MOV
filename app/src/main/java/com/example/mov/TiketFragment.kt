package com.example.mov

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mov.dashboard.CheckoutAdapter
import com.example.mov.dashboard.ComingSoonAdapter
import com.example.mov.dashboard.TiketAdapter
import com.example.mov.databinding.FragmentSettingBinding
import com.example.mov.databinding.FragmentTiketBinding
import com.example.mov.model.Film
import com.example.mov.util.Preferences
import com.google.firebase.database.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SettingFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TiketFragment : Fragment() {

    private lateinit var preferences: Preferences
    private  lateinit var mDatabase: DatabaseReference
    private var datalist = ArrayList<Film>()


    private var _binding: FragmentTiketBinding? = null
    private val binding get() = _binding!!

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTiketBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        preferences = Preferences(requireContext())
        mDatabase = FirebaseDatabase.getInstance("https://movproject-d4a18-default-rtdb.firebaseio.com/").getReference("Film")

        binding.rcTiket.layoutManager = LinearLayoutManager(context)

        getData()
    }

    private fun getData() {
        mDatabase.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                datalist.clear()
                for (getdataSnapshot in dataSnapshot.getChildren()) {

                    val film = getdataSnapshot.getValue(Film::class.java!!)
                    datalist.add(film!!)
                }

                binding.rcTiket.adapter = ComingSoonAdapter(datalist){
                    var intent = Intent(context, TiketActivity::class.java).putExtra("data", it)
                    startActivity(intent)
                }

                binding.tvTotal.setText(datalist.size.toString() +" Movies")

            }


            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, "" + error.message, Toast.LENGTH_LONG).show()
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}