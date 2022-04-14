package com.example.mov.sign.signup

import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.util.Patterns
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mov.R
import com.example.mov.databinding.ActivitySignUpBinding
import com.example.mov.sign.signin.SignInActivity
import com.example.mov.sign.signin.User
import com.example.mov.util.Preferences
import com.google.firebase.database.*

class SignUpActivity : AppCompatActivity() {

    lateinit var sUsername:String
    lateinit var sPassword:String
    lateinit var sNama:String
    lateinit var sEmail:String

    private lateinit var binding: ActivitySignUpBinding
    private lateinit var mFirebaseDatabase: DatabaseReference
    private lateinit var mFirebaseInstance: FirebaseDatabase
    private lateinit var mDatabase: DatabaseReference

    private lateinit var preferences: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        mFirebaseInstance = FirebaseDatabase.getInstance("https://movproject-d4a18-default-rtdb.firebaseio.com/")
        mDatabase = FirebaseDatabase.getInstance("https://movproject-d4a18-default-rtdb.firebaseio.com/").getReference()
        mFirebaseDatabase = mFirebaseInstance.getReference("User")

        preferences = Preferences(this)

        binding.btnSignup.setOnClickListener {
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
                    saveUser(sUsername, sPassword, sNama, sEmail)
            }
        }
    }

    private fun saveUser(sUsername: String, sPassword: String, sNama: String, sEmail: String) {

        val user = User()
        user.email = sEmail
        user.username = sUsername
        user.nama = sNama
        user.password = sPassword

        if (sUsername != null) {
            checkingUsername(sUsername, user)

        }

    }

    private fun checkingUsername(iUsername: String, data: User) {
        mFirebaseDatabase.child(iUsername).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                val user = dataSnapshot.getValue(User::class.java)
                if (user == null) {
                    mFirebaseDatabase.child(iUsername).setValue(data)

                     preferences.setValues("nama", data.nama.toString())
                     preferences.setValues("user", data.username.toString())
                     preferences.setValues("saldo", "")
                     preferences.setValues("url", "")
                     preferences.setValues("email", data.email.toString())
                     preferences.setValues("status", "1")

                    val intent = Intent(this@SignUpActivity,
                        SignUpPhotoScreenActivity::class.java).putExtra("data", data)
                    startActivity(intent)

                } else {
                    Toast.makeText(this@SignUpActivity, "User sudah digunakan", Toast.LENGTH_LONG).show()

                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@SignUpActivity, ""+error.message, Toast.LENGTH_LONG).show()
            }
        })
    }
}
