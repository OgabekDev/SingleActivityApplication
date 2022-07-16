package dev.ogabek.android_saa.fragment.main.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dev.ogabek.android_saa.R
import dev.ogabek.android_saa.adapter.FeedAdapter
import dev.ogabek.android_saa.databinding.FragmentHomeBinding
import dev.ogabek.android_saa.fragment.main.home.viewModel.HomeRepository
import dev.ogabek.android_saa.fragment.main.home.viewModel.HomeViewModel
import dev.ogabek.android_saa.fragment.main.home.viewModel.HomeViewModelFactory
import dev.ogabek.android_saa.networking.RetrofitHttp
import dev.ogabek.android_saa.utils.UiStateList
import kotlinx.coroutines.launch
import java.util.*

class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var viewModel: HomeViewModel
    private val binding by viewBinding(FragmentHomeBinding::bind)

    private val adapter by lazy { FeedAdapter(arrayListOf()) }

    private var page: Int = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewModel()
        observes()
        initViews()

    }

    private fun observes() {
        lifecycleScope.launch {
            viewModel.images.collect {
                when (it) {
                    is UiStateList.LOADING -> {
                        Toast.makeText(requireContext(), "Loading", Toast.LENGTH_SHORT).show()
                    }
                    is UiStateList.SUCCESS -> {
                        Toast.makeText(requireContext(), "Done", Toast.LENGTH_SHORT).show()
                        adapter.update(it.data as ArrayList)
                    }
                    is UiStateList.ERROR -> {
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    }
                    else -> Unit
                }
            }
        }
    }

    private fun initViews() {
        viewModel.getImages(++page)

        binding.rvMain.layoutManager =
            StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        binding.rvMain.adapter = adapter

        binding.rvMain.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val layoutManager =
                    StaggeredGridLayoutManager::class.java.cast(recyclerView.layoutManager)
                val totalItemCount = layoutManager?.itemCount
                val lastVisibleItemPositions =
                    (Objects.requireNonNull(recyclerView.layoutManager) as StaggeredGridLayoutManager).findLastVisibleItemPositions(
                        null
                    ).maxOrNull()
                val endHasBeenReached = lastVisibleItemPositions!! + 5 >= totalItemCount!!
                if (totalItemCount > 0 && endHasBeenReached) {
                    viewModel.getImages(++page)
                }
            }
        })

    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(
            this,
            HomeViewModelFactory(HomeRepository(RetrofitHttp.apiService))
        )[HomeViewModel::class.java]
    }

}