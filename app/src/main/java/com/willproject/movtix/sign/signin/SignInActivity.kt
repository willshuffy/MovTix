package com.willproject.movtix.sign.signin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import com.willproject.movtix.HomeActivity
import com.willproject.movtix.R
import kotlinx.android.synthetic.main.activity_sign_in.*


class SignInActivity : AppCompatActivity() {

    lateinit var iUsername: String
    lateinit var iPassword: String

    lateinit var mDatabase: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        mDatabase =FirebaseDatabase.getInstance().getReference("User")

        btn_next.setOnClickListener {
            iUsername = et_username.text.toString()
            iPassword = et_password.text.toString()

            if (iUsername.equals("")){
                et_username.error = "Silahkan tulis Username anda"
                et_username.requestFocus()
            }else if (iPassword.equals("")) {
                et_password.error = "Silahkan tulis Password anda"
                et_password.requestFocus()
            }else{
                pushLogin(iUsername, iPassword)
            }
        }
    }

    private fun pushLogin(iUsername: String, iPassword : String){
        mDatabase.child(iUsername).addValueEventListener(object : ValueEventListener{
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                val user = dataSnapshot.getValue(User::class.java)
                if (user == null){
                    Toast.makeText(this@SignInActivity, "User tidak ditemukan", Toast.LENGTH_SHORT).show()
                }else{
                    if (user.password.equals(iPassword)){
                        Toast.makeText(this@SignInActivity, "Selamat Datang", Toast.LENGTH_SHORT).show()

//                        preferences.setValues("nama", user.nama.toString())
//                        preferences.setValues("user", user.username.toString())
//                        preferences.setValues("url", user.url.toString())
//                        preferences.setValues("email", user.email.toString())
//                        preferences.setValues("saldo", user.saldo.toString())
//                        preferences.setValues("status", "1")

                        finishAffinity()

                        val intent = Intent(this@SignInActivity, HomeActivity::class.java)
                        startActivity(intent)
                    }else{
                        Toast.makeText(this@SignInActivity, "Password anda salah", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@SignInActivity, ""+error.message, Toast.LENGTH_SHORT).show()
            }


        })
    }
}
