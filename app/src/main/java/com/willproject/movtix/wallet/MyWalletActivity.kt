package com.willproject.movtix.wallet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.willproject.movtix.R
import com.willproject.movtix.utils.Preferences
import com.willproject.movtix.wallet.adapter.WalletAdapter
import com.willproject.movtix.wallet.model.Wallet
import kotlinx.android.synthetic.main.activity_my_wallet.*
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class MyWalletActivity : AppCompatActivity() {

    lateinit var preferences: Preferences
    private var datalist = ArrayList<Wallet>()
    private var total:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_wallet)

        preferences = Preferences(this)

        val localeID = Locale("in", "ID")
        val formatRupiah = NumberFormat.getCurrencyInstance(localeID)
        tv_saldo.setText(formatRupiah.format(preferences.getValues("saldo")!!.toDouble()))

        datalist.add(
            Wallet(
                "Avengers Returns",
                "Sabtu 12 Jan, 2020",
                7000.0,
                "0"
            )
        )
        datalist.add(
            Wallet(
                "Toup Up",
                "Sabtu 12 Jan, 2020",
                90000.0,
                "1"
            )
        )
        datalist.add(
            Wallet(
                "Avengers Returns",
                "Sabtu 12 Jan, 2020",
                7000.0,
                "0"
            )
        )
        datalist.add(
            Wallet(
                "Avengers Returns",
                "Sabtu 12 Jan, 2020",
                7000.0,
                "0"
            )
        )

        rv_transaksi.layoutManager = LinearLayoutManager(this)
        rv_transaksi.adapter = WalletAdapter(datalist){
        }

        iv_back.setOnClickListener {
            finish()
        }

        btn_top_up.setOnClickListener {
            startActivity(Intent(this@MyWalletActivity, MyWalletTopUpActivity::class.java))
        }

    }
}
