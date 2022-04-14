package com.example.mov.sign.signin

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mov.HomeActivity
import com.example.mov.R
import com.example.mov.sign.signup.SignUpActivity
import com.example.mov.util.Preferences
import com.google.firebase.database.*


class SignInActivity : AppCompatActivity() {

    lateinit var mDatabase: DatabaseReference
    lateinit var preferences: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        mDatabase = FirebaseDatabase.getInstance("https://movproject-d4a18-default-rtdb.firebaseio.com/").getReference("User")
        preferences = Preferences(this)

        preferences.setValues("onboarding", "1")
        if (preferences.getValues("status").equals("1")){
            finishAffinity()

            var goHome = Intent(this@SignInActivity, HomeActivity::class.java)
            startActivity(goHome)
        }
        val btn_daftar: Button = findViewById(R.id.btn_daftar_akun)
        val btn_signin: Button = findViewById(R.id.btn_masuk)
        btn_signin.setOnClickListener {
            var iUsername: String = findViewById<EditText?>(R.id.in_username).text.toString()
            var iPassword: String = findViewById<EditText?>(R.id.in_passaword).text.toString()
            var Username = iUsername
            var Password = iPassword

            if (Username.equals("")) {
                Toast.makeText(this@SignInActivity, "Silahkan isi username anda", Toast.LENGTH_SHORT).show()
            } else if (Password.equals("")) {
                Toast.makeText(this@SignInActivity, "Silahkan isi password anda", Toast.LENGTH_SHORT).show()
            } else {
                pushLogin(Username, Password)
            }
        }
        btn_daftar.setOnClickListener {
            var intent = Intent(this@SignInActivity, SignUpActivity::class.java)
            startActivity(intent)
        }

    }

    private fun pushLogin(username: String, password: String) {
        mDatabase.child(username).addValueEventListener(object :  ValueEventListener{
            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(this@SignInActivity, databaseError.message, Toast.LENGTH_SHORT).show()
            }

            override fun onDataChange( dataSnapshot: DataSnapshot) {
                var User = dataSnapshot.getValue(User::class.java)
                if (User == null) {
                    Toast.makeText(this@SignInActivity, "Data tidak ditemukan!", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    if (User.password.equals(password)) {
                        Toast.makeText(this@SignInActivity, "Selamat Datang", Toast.LENGTH_LONG)
                            .show()

                        preferences.setValues("nama", User.nama.toString())
                        preferences.setValues("user", User.username.toString())
                        preferences.setValues("url", User.url.toString())
                        preferences.setValues("email", User.email.toString())
                        preferences.setValues("saldo", User.saldo.toString())
                        preferences.setValues("pass", User.password.toString())
                        preferences.setValues("status", "1")

                        finishAffinity()

                        val intent = Intent(
                            this@SignInActivity,
                            HomeActivity::class.java
                        )
                        startActivity(intent)

                    } else {
                        Toast.makeText(
                            this@SignInActivity,
                            "Password Anda Salah",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }

        })
    }


}
