package com.example.countriessimple.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.countriessimple.R
import com.example.countriessimple.model.Country
import com.example.countriessimple.util.bindImage
import com.example.countriessimple.util.placeholderProgressBar
import com.example.countriessimple.view.FeedFragmentDirections

class CountryAdapter(val countryList: ArrayList<Country>) : RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {

    class CountryViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView
        val image: ImageView

        init {
            name = view.findViewById(R.id.row_name)
            image = view.findViewById(R.id.row_image)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): CountryViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.country_row, viewGroup, false)
        return CountryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return countryList.size
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val country = countryList[position]
        holder.name.text = country.countryName
        holder.image.bindImage(country.imageUrl, placeholderProgressBar(holder.view.context))
        holder.view.setOnClickListener {
            val action = FeedFragmentDirections.actionFeedFragmentToCountryDetailsFragment(countryId = country.uuid)
            holder.view.findNavController().navigate(action)
        }
    }

    fun updateCountryList(newCountryList: List<Country>) {
        countryList.clear()
        countryList.addAll(newCountryList)
        notifyDataSetChanged()
    }
}