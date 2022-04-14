package com.example.mov

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.mov.databinding.ActivityEditProfileBinding
import com.example.mov.databinding.ActivitySignUpBinding
import com.example.mov.sign.signin.User
import com.example.mov.sign.signup.SignUpPhotoScreenActivity
import com.example.mov.util.Preferences
import com.google.firebase.database.*

class EditProfileActivity : AppCompatActivity() {

    lateinit var sUsername:String
    lateinit var sPassword:String
    lateinit var sNama:String
    lateinit var sEmail:String

    private lateinit var user: User
    private lateinit var binding: ActivityEditProfileBinding
    private lateinit var mFirebaseDatabase: DatabaseReference
    private lateinit var mFirebaseInstance: FirebaseDatabase
    private lateinit var mDatabase: DatabaseReference

    private lateinit var preferences: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityEditProfileBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        mFirebaseInstance =
            FirebaseDatabase.getInstance("https://movproject-d4a18-default-rtdb.firebaseio.com/")
        mDatabase =
            FirebaseDatabase.getInstance("https://movproject-d4a18-default-rtdb.firebaseio.com/")
                .getReference()
        mFirebaseDatabase = mFirebaseInstance.getReference("User")

        preferences = Preferences(this)

        if (preferences != null){

            binding.etUsername.setText(preferences.getValues("user"))
            binding.etEmail.setText(preferences.getValues("email"))
            binding.etNama.setText(preferences.getValues("nama"))
            binding.etPassword.setText(preferences.getValues("pass"))

            Glide.with(this)
                .load(preferences.getValues("url"))
                .apply(RequestOptions.circleCropTransform())
                .into(binding.ivProfile)

            binding.btnSimpan.setOnClickListener {

                sUsername = binding.etUsername.text.toString()
                sPassword = binding.etPassword.text.toString()
                sNama = binding.etNama.text.toString()
                sEmail = binding.etEmail.text.toString()

                if (sUsername.equals("")) {
                    binding.etUsername.error = "Silahkan isi Username"
                    binding.etUsername.requestFocus()
                } else if (sPassword.equals("")) {
                    binding.etPassword.error = "Silahkan isi Password"
                    binding.etPassword.requestFocus()
                } else if (sNama.equals("")) {
                    binding.etNama.error = "Silahkan isi Nama"
                    binding.etNama.requestFocus()
                } else if (sEmail.equals("")) {
                    binding.etEmail.error = "Silahkan isi Email"
                    binding.etEmail.requestFocus()
                } else {

//                    var user = User(user.username, sUsername, sPassword, sEmail, sNama)
                    val user = User()
                    user.email = sEmail
                    user.username = sUsername
                    user.nama = sNama
                    user.password = sPassword
                    user.saldo = preferences.getValues("saldo")
                    user.url = preferences.getValues("url")

                    mFirebaseDatabase.child(user.username!!).setValue(user)

                    if (sUsername != null) {
                        checkingUsername(sUsername, user)

                    }
                }
            }
        }
    }

    private fun checkingUsername(iUsername: String, data: User) {
        mFirebaseDatabase.child(iUsername).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                val user = dataSnapshot.getValue(User::class.java)
                if (user != null) {

                    if (iUsername == user.username){
                    preferences.setValues("nama", data.nama.toString())
                    preferences.setValues("user", data.username.toString())
                    preferences.setValues("saldo", data.saldo.toString())
                    preferences.setValues("url", data.url.toString())
                    preferences.setValues("email", data.email.toString())
                    preferences.setValues("status", "1")

                    val intent = Intent(this@EditProfileActivity, HomeActivity::class.java)
                    startActivity(intent)
                    } else {
                        Toast.makeText(this@EditProfileActivity, "User sudah digunakan", Toast.LENGTH_LONG).show()
                    }


                } else {
                    Toast.makeText(this@EditProfileActivity, "User sudah digunakan", Toast.LENGTH_LONG).show()

                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@EditProfileActivity, ""+error.message, Toast.LENGTH_LONG).show()
            }
        })
    }
}