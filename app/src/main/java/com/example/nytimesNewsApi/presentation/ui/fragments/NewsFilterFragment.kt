package com.example.nytimesNewsApi.presentation.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.nytimesNewsApi.databinding.FragmentNewsFilterBinding


class NewsFilterFragment : Fragment() {

    private var _binding: FragmentNewsFilterBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentNewsFilterBinding.inflate(
            inflater,
            container,
            false
        )

        return binding.root
    }
}