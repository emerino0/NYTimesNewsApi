package com.example.nytimesNewsApi.presentation.ui.fragments

import android.app.AlertDialog
import com.example.nytimesNewsApi.R
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import com.example.nytimesNewsApi.databinding.FragmentNewsFilterBinding
import com.example.nytimesNewsApi.domain.FilterNewsArticle


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


        // Spinner Article Type
        val articlesTypeList = listOf("emailed","shared","viewed")
        binding.spArticleType.adapter = context?.let {
            ArrayAdapter(
                it, R.layout.support_simple_spinner_dropdown_item,
                articlesTypeList)
        }

        binding.spArticleType.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            )
            {
                binding.tvArticleShared.isVisible = articlesTypeList[position] == "shared"
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }








        //Alert Dialog - Shared Articles Platforms
        val articlesSharedPlatform = arrayOf("facebook","twitter")
        val articlesSharedPlatformSelected = booleanArrayOf(false,false)

        val alertDialogSharedPlatforms: AlertDialog? =
            createDialogSharedPlatForms(articlesSharedPlatform, articlesSharedPlatformSelected)

        binding.tvArticleShared.setOnClickListener{
            alertDialogSharedPlatforms?.show()
        }


        // Spinner Article Period
        binding.spArticlePeriod.adapter = context?.let {
            ArrayAdapter(
                it, android.R.layout.simple_spinner_item,
                listOf("1","7","30"))
        }


        //Button Accept News Filter
        binding.btAccept.setOnClickListener{

            val articleType = binding.spArticleType.selectedItem.toString().trim()
            val period = binding.spArticlePeriod.selectedItem.toString().trim()
            val sharedType = binding.tvArticleShared.text.toString().trim()

            if(articleType== "shared" && (sharedType== "Platform"||sharedType==""))
                Toast.makeText(context, "Elija una plataforma", Toast.LENGTH_SHORT).show()
            else
            {

                val filterNewsArticle = FilterNewsArticle(articleType,period,sharedType)

                val direction = NewsFilterFragmentDirections
                    .actionNewsFilterFragmentToNewsListFragment(filterNewsArticle)
                it.findNavController().navigate(direction)
            }
        }

        return binding.root

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    private fun createDialogSharedPlatForms(
        articlesSharedPlatform: Array<String>,
        articlesSharedPlatformSelected: BooleanArray
    ): AlertDialog? {
        return activity?.let { it ->
            val builder = AlertDialog.Builder(it)
            builder.apply {
                setTitle("Selecciona plataformas")

                setMultiChoiceItems(
                    articlesSharedPlatform,
                    articlesSharedPlatformSelected
                ) { _, position, isChecked ->
                    articlesSharedPlatformSelected[position] = isChecked
                }

                setPositiveButton(
                    "Aceptar"
                ) { _, _ ->
                    binding.tvArticleShared.text = ""
                    for (i in 0 until articlesSharedPlatformSelected.count()) {
                        if (articlesSharedPlatformSelected[i]) {
                            when (articlesSharedPlatform[i]) {
                                "twitter" -> {
                                    Toast.makeText(
                                        context,
                                        "twitter no disponible",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    articlesSharedPlatformSelected[i] = false
                                }
                                "facebook" ->
                                    "${binding.tvArticleShared.text} ${articlesSharedPlatform[i]}"
                                        .also { binding.tvArticleShared.text = it }
                            }
                        }
                    }
                }
                setNegativeButton("Cancelar") { _, _ -> }

            }

            builder.create()
        }
    }
}