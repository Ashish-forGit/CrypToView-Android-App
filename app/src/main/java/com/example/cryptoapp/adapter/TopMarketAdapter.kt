package com.example.cryptoapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cryptoapp.R
import com.example.cryptoapp.databinding.HomeCurrencyLayoutBinding
import com.example.cryptoapp.models.CryptoCurrency

class TopMarketAdapter(var context: Context, val list: List<CryptoCurrency>) :RecyclerView.Adapter<TopMarketAdapter.TopMarketViewHolder>() {

    inner class TopMarketViewHolder(view: View) : RecyclerView.ViewHolder(view){
        var binding = HomeCurrencyLayoutBinding.bind(view)

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopMarketViewHolder {
        return TopMarketViewHolder(LayoutInflater.from(context).inflate(R.layout.home_currency_layout, parent, false))
    }
    override fun getItemCount(): Int {
        return list.size
    }
    override fun onBindViewHolder(holder: TopMarketViewHolder, position: Int) {
        val item = list[position]
        holder.binding.topCurrencyNameTextView.text = item.name
        Glide.with(context).load("https://s2.coinmarketcap.com/static/img/coins/64x64/"  + item.id + ".png")
            .thumbnail(Glide.with(context).load(R.drawable.spinner))
            .into(holder.binding.topCurrencyImageView)

        if (item.quotes!![0].percentChange24h > 0){
            holder.binding.topCurrencyChangeTextView.setTextColor(context.resources.getColor(R.color.app_x00))
            holder.binding.topCurrencyChangeTextView.text = "+ ${String.format("%.2f", item.quotes[0].percentChange24h)} %"
        } else{
            holder.binding.topCurrencyChangeTextView.setTextColor(context.resources.getColor(R.color.app_100))
            holder.binding.topCurrencyChangeTextView.text = "${String.format("%.2f", item.quotes[0].percentChange24h)} %"
        }

    }
}