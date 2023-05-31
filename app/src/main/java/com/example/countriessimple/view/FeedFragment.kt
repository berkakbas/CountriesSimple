package com.example.countriessimple.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.countriessimple.adapter.CountryAdapter
import com.example.countriessimple.databinding.FragmentFeedBinding

class FeedFragment : Fragment() {

    lateinit var binding: FragmentFeedBinding
    private var countryAdapter = CountryAdapter(arrayListOf())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentFeedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.countryRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.countryRecyclerView.adapter = countryAdapter
    }
}