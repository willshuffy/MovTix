package com.willproject.movtix.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.willproject.movtix.R
import com.willproject.movtix.sign.signin.SignInActivity
import kotlinx.android.synthetic.main.activity_onboarding_one.btn_next

class OnboardingThreeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding_three)

        btn_next.setOnClickListener {
            finishAffinity()

            val intent = Intent(this@OnboardingThreeActivity,
                SignInActivity::class.java)
            startActivity(intent)
        }
    }
}
