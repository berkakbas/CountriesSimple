package com.example.countriessimple.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.countriessimple.adapter.CountryAdapter
import com.example.countriessimple.databinding.FragmentFeedBinding
import com.example.countriessimple.viewmodel.FeedViewModel

class FeedFragment : Fragment() {
    private val viewModel: FeedViewModel by viewModels()
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

        observeLiveData()
        viewModel.getDataFromAPI()

        binding.countryRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.countryRecyclerView.adapter = countryAdapter
    }

    private fun observeLiveData() {
        viewModel.countries.observe(viewLifecycleOwner) { countries ->
            countryAdapter.updateCountryList(countries)
            binding.countryRecyclerView.visibility = View.VISIBLE
        }
        viewModel.countryError.observe(viewLifecycleOwner) { error ->
            when (error) {
                true -> binding.countryErrorTextView.visibility = View.VISIBLE
                false -> binding.countryErrorTextView.visibility = View.GONE
            }
        }
        viewModel.countryLoading.observe(viewLifecycleOwner) { loading ->
            when (loading) {
                true -> {
                    binding.countryLoadingBar.visibility = View.VISIBLE
                    binding.countryErrorTextView.visibility = View.GONE
                    binding.countryRecyclerView.visibility = View.GONE
                }
                false -> binding.countryLoadingBar.visibility = View.GONE
            }
        }
    }
}