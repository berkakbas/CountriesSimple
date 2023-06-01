package com.example.countriessimple.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.countriessimple.databinding.FragmentCountryDetailsBinding
import com.example.countriessimple.util.bindImage
import com.example.countriessimple.util.placeholderProgressBar
import com.example.countriessimple.viewmodel.CountryDetailsViewModel

class CountryDetailsFragment : Fragment() {
    lateinit var binding: FragmentCountryDetailsBinding

    private val viewModel: CountryDetailsViewModel by viewModels()

    private var countryUuid = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentCountryDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            countryUuid = CountryDetailsFragmentArgs.fromBundle(it).countryId
        }
        viewModel.getCountryFromDB(countryUuid)

        observeLiveData()
    }
    private fun observeLiveData() {
        viewModel.country.observe(viewLifecycleOwner) { country ->
            binding.selectedCountry = country
        }
    }
}