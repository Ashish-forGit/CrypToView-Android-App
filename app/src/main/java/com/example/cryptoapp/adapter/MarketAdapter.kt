package com.example.cryptoapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cryptoapp.R
import com.example.cryptoapp.databinding.CurrencyItemLayoutBinding
import com.example.cryptoapp.models.CryptoCurrency

class MarketAdapter(var context: Context, var list: List<CryptoCurrency>): RecyclerView.Adapter<MarketAdapter.MarketViewHolder>() {

    inner class MarketViewHolder(view: View) : RecyclerView.ViewHolder(view){
        var binding = CurrencyItemLayoutBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarketViewHolder {
        return MarketViewHolder(LayoutInflater.from(context).inflate(R.layout.currency_item_layout, parent, false))
    }

    fun updateData(dataItem: List<CryptoCurrency>){
        list = dataItem
        notifyDataSetChanged()

    }


    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MarketViewHolder, position: Int) {
        val item = list[position]
        holder.binding.currencyNameTextView.text = item.name
        holder.binding.currencyNameTextView.text = item.symbol

        Glide.with(context).load("https://s2.coinmarketcap.com/static/img/coins/64x64/"  + item.id + ".png")
            .thumbnail(Glide.with(context).load(R.drawable.spinner))
            .into(holder.binding.currencyImageView)


        holder.binding.currencyPriceTextView.text = item.quotes[0].price.toString()

        if (item.quotes!![0].percentChange24h > 0){
            holder.binding.currencyChangeTextView.setTextColor(context.resources.getColor(R.color.app_x00))
            holder.binding.currencyChangeTextView.text = "+ ${String.format("%.2f", item.quotes[0].percentChange24h)} %"
        } else{
            holder.binding.currencyChangeTextView.setTextColor(context.resources.getColor(R.color.app_100))
            holder.binding.currencyChangeTextView.text = "${String.format("%.2f", item.quotes[0].percentChange24h)} %"
        }

    }
}