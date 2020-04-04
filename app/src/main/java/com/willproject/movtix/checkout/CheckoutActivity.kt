package com.willproject.movtix.checkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.willproject.movtix.R
import com.willproject.movtix.checkout.model.Checkout
import com.willproject.movtix.utils.Preferences
import kotlinx.android.synthetic.main.activity_checkout.*
import kotlinx.android.synthetic.main.activity_detail.*
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class CheckoutActivity : AppCompatActivity() {

    private var dataList = ArrayList<Checkout>()
    private var total:Int = 0

    private lateinit var preferences: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)

        preferences = Preferences(this)
        dataList = intent.getSerializableExtra("data") as ArrayList<Checkout>


        for (a in dataList.indices){
            total += dataList[a].harga!!.toInt()
        }

        dataList.add(Checkout("Total Harus Dibayar", total.toString()))

        btn_save.setOnClickListener {
            val intent = Intent(this@CheckoutActivity,
                CheckoutSuccessActivity::class.java)
            startActivity(intent)
        }

        rv_seat_number.layoutManager = LinearLayoutManager(this)
        rv_seat_number.adapter = CheckoutAdapter(dataList) {
        }



        if (preferences.getValues("saldo")!!.isNotEmpty()){
            val localeID = Locale("in", "ID")
            val formatRupiah = NumberFormat.getCurrencyInstance(localeID)
            tv_saldo.setText(formatRupiah.format(preferences.getValues("saldo")!!.toDouble()))
            btn_save.visibility = View.VISIBLE
        }else{
            tv_saldo.setText("Rp 0")
            btn_save.visibility = View.INVISIBLE
            tv_saldo_tdk_cukup.visibility = View.VISIBLE
            tv_saldo_tdk_cukup.text = "Saldo pada e-wallet kamu tidak mencukupi\n" +
                    "untuk melakukan transaksi"
        }

        imageView2.setOnClickListener {
            finish()
        }

        btn_next.setOnClickListener {
            finish()
        }
    }
}
