package com.example.nytimesNewsApi.presentation.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nytimesNewsApi.data.repositories.NewsRepository
import com.example.nytimesNewsApi.data.util.Constants
import com.example.nytimesNewsApi.databinding.FragmentNewsListBinding
import com.example.nytimesNewsApi.domain.FilterNewsArticle
import com.example.nytimesNewsApi.presentation.ui.adapters.NewsListAdapter
import com.example.nytimesNewsApi.presentation.viewmodels.NewsListViewModel
import com.example.nytimesNewsApi.presentation.viewmodels.NewsListViewModelFactory


class NewsListFragment : Fragment() {

    private var _binding: FragmentNewsListBinding? = null
    private val binding get() = _binding!!
    private lateinit var newsAdapter: NewsListAdapter
    private lateinit var newsViewModel: NewsListViewModel
    private val args: NewsListFragmentArgs by navArgs()
    private lateinit var filterNewsArticle: FilterNewsArticle

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentNewsListBinding.inflate(
            inflater,
            container,
            false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        filterNewsArticle =args.filterNewsArticle!!

        setUpViewModel()

        requireActivity().title = "NYTimesNews"

        setUpRecyclerView()

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    private fun setUpRecyclerView() {
        newsAdapter = NewsListAdapter()

        binding.rvArticlesList.apply {
            layoutManager = LinearLayoutManager(activity)
            setHasFixedSize(true)
            addItemDecoration(
                object : DividerItemDecoration(
                    activity, LinearLayout.VERTICAL
                ) {})
            adapter = newsAdapter
        }

        fetchingData()
    }

    private fun fetchingData() {
        activity?.let {
            if (Constants.isNetworkAvailable(requireActivity())) {
                newsViewModel.newsResult()

                newsViewModel.newsResult()
                    .observe(viewLifecycleOwner) { news ->
                        if (news != null) {
                            newsAdapter.differ.submitList(news.results)
                        }
                    }
            } else
                Toast.makeText(activity, "No internet connection", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setUpViewModel() {

        val newsRepository = NewsRepository(
            filterNewsArticle.articleType,
            filterNewsArticle.period,
            filterNewsArticle.sharedType
        )


        val viewModelProviderFactory =
            NewsListViewModelFactory(
                requireActivity().application,
                newsRepository
            )


        newsViewModel = ViewModelProvider(
            this,
            viewModelProviderFactory
        ).get(NewsListViewModel::class.java)

    }
}