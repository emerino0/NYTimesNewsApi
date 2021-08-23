package com.example.nytimesNewsApi.presentation.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.*
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.nytimesNewsApi.data.models.NewsArticle
import com.example.nytimesNewsApi.databinding.FragmentNewsDetailBinding
import com.example.nytimesNewsApi.domain.FilterNewsArticle


class NewsDetailFragment : Fragment() {

    private var _binding: FragmentNewsDetailBinding? = null
    private val binding get() = _binding!!
    private val argsSelectedNews: NewsDetailFragmentArgs by navArgs()
    lateinit var selectedNews: NewsArticle

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentNewsDetailBinding.inflate(
            inflater,
            container,
            false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        selectedNews= argsSelectedNews.newsArticle!!

        requireActivity().title = selectedNews.title

        setUpWebView()

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    private fun setUpWebView() {
        binding.webView.apply {
            webViewClient = MyWebViewClient()
            loadUrl(selectedNews.url)
        }

        binding.webView.settings.apply {
            javaScriptEnabled = false
            cacheMode = WebSettings.LOAD_DEFAULT
            setSupportZoom(false)
            builtInZoomControls = false
            displayZoomControls = false
            textZoom = 100
            blockNetworkImage = false
            loadsImagesAutomatically = true
        }
    }

    private class MyWebViewClient : WebViewClient() {

        override fun onReceivedHttpError(
            view: WebView?,
            request: WebResourceRequest?,
            errorResponse: WebResourceResponse?
        ) {
            Toast.makeText(view?.context, "HTTP error ${errorResponse?.statusCode}", Toast.LENGTH_LONG).show()

            val direction= NewsDetailFragmentDirections
                .actionNewsDetailFragmentToNewsListFragment(FilterNewsArticle( "emailed","1",""))
            view?.findNavController()?.navigate(direction)

        }
    }
}