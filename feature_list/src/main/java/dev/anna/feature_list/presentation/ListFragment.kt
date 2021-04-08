package dev.anna.feature_list.presentation

import android.content.res.Resources
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import dev.anna.core.navigation.ContentScreen
import dev.anna.core.navigation.Router
import dev.anna.feature_list.R
import dev.anna.feature_list.databinding.FragmentListBinding
import dev.anna.feature_list.di.SimpleDI
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect

class ListFragment : Fragment() {
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    val viewModel = SimpleDI.getViewModel()

    var span = 2

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        subscribe()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        binding.ervList.itemAnimator = null
        return binding.root
    }

    private fun subscribe() {
        lifecycleScope.launchWhenCreated {
            viewModel.error.collect {
//                Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
            }
        }
        lifecycleScope.launchWhenCreated {
            viewModel.state.collect {
                binding.swipeToRefresh.isRefreshing = false
                binding.ervList.withModels {
                    val size = Resources.getSystem().displayMetrics.widthPixels / span
                    it.list.forEach { s ->
                        imageViewHolder {
                            id(s)
                            data(Pair(s, size))
                            onClick {
                                (requireActivity() as Router).goTo(ContentScreen(s))
                            }
                            spanSizeOverride { totalSpanCount, position, itemCount ->
                                totalSpanCount / span
                            }
                        }
                    }
                }
            }
        }
    }

    private fun isTablet(): Boolean {
        return resources.configuration.smallestScreenWidthDp >= 600
    }

    private fun initView() {
        span = if (isTablet()) 3 else 2
        binding.ervList.layoutManager = GridLayoutManager(requireContext(), span)
        binding.swipeToRefresh.setOnRefreshListener {
            viewModel.loadData()
        }
    }
}