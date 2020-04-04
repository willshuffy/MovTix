package com.willproject.movtix

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.willproject.movtix.utils.Preferences

class EditProfileActivity : AppCompatActivity() {

    lateinit var preferences: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

    }
}
