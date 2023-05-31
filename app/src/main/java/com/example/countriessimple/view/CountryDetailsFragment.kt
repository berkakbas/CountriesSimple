package com.example.countriessimple.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.countriessimple.R
import com.example.countriessimple.databinding.FragmentCountryDetailsBinding

class CountryDetailsFragment : Fragment() {
    lateinit var binding: FragmentCountryDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentCountryDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }
}