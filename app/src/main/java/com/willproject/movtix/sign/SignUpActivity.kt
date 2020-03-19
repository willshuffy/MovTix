package com.willproject.movtix.sign

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.*
import com.willproject.movtix.HomeActivity
import com.willproject.movtix.R
import com.willproject.movtix.sign.signin.User
import com.willproject.movtix.utils.Preferences
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {

    lateinit var sUsername:String
    lateinit var sPassword:String
    lateinit var sNama:String
    lateinit var sEmail:String

    private lateinit var mFirebaseDatabase: DatabaseReference
    private lateinit var mFirebaseInstance: FirebaseDatabase
    private lateinit var mDatabase: DatabaseReference

    private lateinit var preferences: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        mFirebaseInstance = FirebaseDatabase.getInstance()
        mDatabase = FirebaseDatabase.getInstance().getReference()
        mFirebaseDatabase = mFirebaseInstance.getReference("User")

        preferences = Preferences(this)

        btn_next.setOnClickListener {
            sUsername = et_username.text.toString()
            sPassword = et_password.text.toString()
            sNama = et_nama.text.toString()
            sEmail = et_email.text.toString()

            if (sUsername.equals("")){
                et_username.error = "Silahkan isi username"
                et_username.requestFocus()
            }else if (sPassword.equals("")){
                et_password.error = "Silahkan isi password"
                et_password.requestFocus()
            }else if (sNama.equals("")){
                et_nama.error = "Silahkan isi nama"
                et_nama.requestFocus()
            }else if (sEmail.equals("")){
                et_email.error = "Silahkan isi email"
                et_email.requestFocus()
            }else{
                savUser (sUsername, sPassword, sNama, sEmail)
            }
        }
    }

    private fun savUser(sUsername: String, sPassword: String, sNama: String, sEmail: String) {

        val user = User()
        user.username = sUsername
        user.password = sPassword
        user.nama = sNama
        user.email = sEmail

        if (sUsername !=null){
            checkingUsername(sUsername, user)
        }
    }

    private fun checkingUsername(sUsername: String, data: User) {
        mFirebaseDatabase.child(sUsername).addValueEventListener(object : ValueEventListener{
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@SignUpActivity, ""+error.message, Toast.LENGTH_SHORT).show()
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {

                val user = dataSnapshot.getValue(User::class.java)
                if (user == null){
                    mFirebaseDatabase.child(sUsername).setValue(data)

                    preferences.setValues("nama", data.nama.toString())
                    preferences.setValues("user", data.username.toString())
                    preferences.setValues("url", "")
                    preferences.setValues("email", data.email.toString())
                    preferences.setValues("status", "1")

                    val intent = Intent(this@SignUpActivity, SignUpPhotoScreenActivity::class.java).putExtra("nama", data.nama);
                    startActivity(intent)
                }else{
                    Toast.makeText(this@SignUpActivity, "User sudah digunakan", Toast.LENGTH_SHORT).show()
                }

            }

        })
    }


}
