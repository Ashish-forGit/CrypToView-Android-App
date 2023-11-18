package com.example.cryptoapp.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.cryptoapp.R
import com.example.cryptoapp.adapter.MarketAdapter
import com.example.cryptoapp.apis.Apiinterface
import com.example.cryptoapp.apis.Apiutilities
import com.example.cryptoapp.databinding.FragmentMarketBinding
import com.example.cryptoapp.models.CryptoCurrency
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Locale

class MarketFragment : Fragment() {

    private lateinit var binding : FragmentMarketBinding
    private lateinit var list: List<CryptoCurrency>
    private lateinit var adapter: MarketAdapter




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding =  FragmentMarketBinding.inflate(layoutInflater)

        list = listOf()
        adapter = MarketAdapter(requireContext(), list)
        binding.currencyRecyclerView.adapter = adapter

        lifecycleScope.launch(Dispatchers.IO) {
            val res = Apiutilities.getInstance().create(Apiinterface::class.java).getMarketData()

            if (res.body() != null){
                withContext(Dispatchers.Main){
                    list = res.body()!!.data.cryptoCurrencyList

                    adapter.updateData(list)
                    binding.spinKitView.visibility = GONE
                }
            }

            searchCoin()
        }


        return binding.root
    }
    lateinit var searchText : String
    private fun searchCoin(){
        binding.searchEditText.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                searchText = p0.toString().toLowerCase()

                updateRecyclerView()
            }

        })
    }

    private fun updateRecyclerView() {
        val data = ArrayList<CryptoCurrency>()

        for (item in list){
            var coinName = item.name.toLowerCase(Locale.getDefault())
            var coinsSymbol = item.symbol.toLowerCase(Locale.getDefault())

            if (coinName.contains(searchText) || coinsSymbol.contains(searchText)){
                data.add(item)
            }
        }
        adapter.updateData(data)
    }
}