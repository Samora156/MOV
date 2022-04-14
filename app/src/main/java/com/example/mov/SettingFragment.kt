package com.example.mov

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.mov.dashboard.TiketAdapter
import com.example.mov.databinding.FragmentSettingBinding
import com.example.mov.model.Film
import com.example.mov.sign.signin.User
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
class SettingFragment : Fragment() {

    private lateinit var preferences: Preferences
    private  lateinit var mDatabase: DatabaseReference
    private var datalist = ArrayList<User>()


    private var _binding: FragmentSettingBinding? = null
    private val binding get() = _binding!!

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSettingBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        preferences = Preferences(requireContext())
        mDatabase = FirebaseDatabase.getInstance("https://movproject-d4a18-default-rtdb.firebaseio.com/").getReference("User")

        binding.ivNama.text = preferences.getValues("nama")
        binding.tvEmail.text = preferences.getValues("email")

        Glide.with(this)
            .load(preferences.getValues("url"))
            .apply(RequestOptions.circleCropTransform())
            .into(binding.ivProfile)

        binding.tvMyWallet.setOnClickListener {
            startActivity(Intent(activity, MyWalletlActivity::class.java))
        }

        binding.tvEditProfile.setOnClickListener {
            startActivity(Intent(activity, EditProfileActivity::class.java))
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}