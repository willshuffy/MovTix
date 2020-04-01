package com.willproject.movtix.home

import android.app.Activity
import android.app.Dialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Window
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.willproject.movtix.R
import com.willproject.movtix.checkout.model.Checkout
import com.willproject.movtix.home.model.Film
import kotlinx.android.synthetic.main.activity_tiket.*
import kotlinx.android.synthetic.main.activity_tiket.iv_back
import kotlinx.android.synthetic.main.ticket_dialog.view.*

class TiketActivity : AppCompatActivity() {

    private var dataList = ArrayList<Checkout>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tiket)

        val data = intent.getParcelableExtra<Film>("data")

        tv_title.text = data.judul
        tv_genre.text = data.genre
        tv_rate.text = data.rating

        Glide.with(this)
            .load(data.poster)
            .into(iv_poster_image)

        rc_checkout.layoutManager = LinearLayoutManager(this)
        dataList.add(Checkout("C1", ""))
        dataList.add(Checkout("C2", ""))

        rc_checkout.adapter = TiketAdapter(dataList) {
        }


        iv_back.setOnClickListener {
            finish()
        }


        iv_barcode.setOnClickListener {

            val view = LayoutInflater.from(this).inflate(R.layout.ticket_dialog, null)
            val alert = AlertDialog.Builder(this, R.style.CustomAlertDialog)
                .setView(view)
                .setCancelable(false)

            val mAlertDialog = alert.show()
            mAlertDialog?.window?.setLayout(500, 700)

            view.btn_close_dialog.setOnClickListener {
                mAlertDialog.dismiss()
            }
        }

    }

//
}
