package com.rudhra.newsdoc.ui.news

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.rudhra.newsdoc.R
import com.rudhra.newsdoc.databinding.FragmentNewsBinding
import com.rudhra.newsdoc.ui.adapter.NewsAdapter
import com.rudhra.newsdoc.ui.newsDetails.NewsDetailsActivity
import com.rudhra.newsdoc.ui.newsDetails.NewsDetailsActivity.Companion.NEWS_ITEM


class NewsFragment(private val category: String) : Fragment(), NewsAdapter.OnClickListener {


    private lateinit var newsViewModel: NewsViewModel
    private var adapter: NewsAdapter = NewsAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        newsViewModel =
            ViewModelProvider(this, NewsViewModelFactory(category)).get(NewsViewModel::class.java)
        val binding: FragmentNewsBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_news, container, false)
        binding.recyclerView.adapter = adapter
        newsViewModel.newsList.observe(viewLifecycleOwner, Observer {
            adapter.listItems = it
            binding.swipeRefresh.isRefreshing = false
            binding.progressBar.visibility = View.GONE
        })
        binding.swipeRefresh.setOnRefreshListener { newsViewModel.onRefresh() }
        return binding.root
    }

    override fun onItemClick(url: String, type: NewsAdapter.ClickType) {

        when (type) {
            NewsAdapter.ClickType.ITEM -> {
                val intent = Intent(activity, NewsDetailsActivity::class.java)
                intent.putExtra(NEWS_ITEM, url)
                startActivity(intent)
            }

            NewsAdapter.ClickType.SHARE -> {
                val sharingIntent = Intent(Intent.ACTION_SEND)
                sharingIntent.type = "text/plain"
                sharingIntent.putExtra(Intent.EXTRA_TEXT, url)
                startActivity(
                    Intent.createChooser(
                        sharingIntent,
                        getString(R.string.share_article)
                    )
                )
            }
        }
    }
}
