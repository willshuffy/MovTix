package com.willproject.movtix.wallet.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.willproject.movtix.R
import com.willproject.movtix.wallet.model.Wallet
import java.text.NumberFormat
import java.util.*

class WalletAdapter(private var data: List<Wallet>,
                    private val listener: (Wallet) -> Unit)
    :RecyclerView.Adapter<WalletAdapter.LeagueViewHolder>(){

    lateinit var COntextAdapter : Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeagueViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        COntextAdapter = parent.context

        val inflatedView: View = layoutInflater.inflate(R.layout.row_item_transaksi,
            parent, false)

        return LeagueViewHolder(inflatedView)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: LeagueViewHolder, position: Int) {
        holder.bindItem(data[position], listener, COntextAdapter, position)
    }

    class LeagueViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val tvTitle: TextView = view.findViewById(R.id.tv_movie)
        private val tvSub: TextView = view.findViewById(R.id.tv_date)
        private val tvMoney: TextView = view.findViewById(R.id.tv_money)


        fun bindItem(data: Wallet, listener: (Wallet) -> Unit, context: Context, position: Int) {

            tvTitle.setText(data.title)
            tvSub.setText(data.date)

            val localeId = Locale("in", "ID")
            val formatRupiah = NumberFormat.getCurrencyInstance(localeId)

            if (data.status.equals("0")){
                tvMoney.setText("- "+formatRupiah.format(data.money!!.toDouble()))
            }else{
                tvMoney.setText(formatRupiah.format(data.money!!.toDouble()))
                tvMoney.setTextColor(Color.GREEN)
            }

            itemView.setOnClickListener {
                listener(data)
            }
        }

    }




}