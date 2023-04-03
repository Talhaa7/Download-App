package com.example.downloadapp.features.application

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.downloadapp.databinding.FragmentApplicationBinding
import com.example.downloadapp.features.adapter.AppListAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ApplicationFragment : Fragment() {
    private var _binding: FragmentApplicationBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<ApplicationViewModel>()
    lateinit var appListAdapter: AppListAdapter
    private lateinit var swipeRefresh: SwipeRefreshLayout

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentApplicationBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        swipeRefresh = binding.swipeRefresh
        swipeRefresh.setOnRefreshListener {
            binding.searchView.setQuery("",false)
            viewModel.getAppList()
            swipeRefresh.isRefreshing = false
            binding.checkBox.isChecked = false
        }
        setupRecyclerView()

        observeProgressbar()

        observeErrorState()

        observeAppList()
    }



    private fun setupRecyclerView() {
        appListAdapter = AppListAdapter(){
            navigateToAnotherApp(it)
        }
        binding.rvApplicationList.apply {
            adapter = appListAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun observeAppList() {
        val adapter = binding.rvApplicationList.adapter as AppListAdapter

        viewModel.appList.observe(viewLifecycleOwner, Observer { response ->
            response.let { responseModel ->
                binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                    override fun onQueryTextSubmit(p0: String?): Boolean {
                        if (p0?.length!! > 2){
                            viewModel.filterFromSearchTab(p0,responseModel,adapter)
                        }
                        else {
                            adapter.submitList(responseModel)
                        }
                        return false
                    }
                    override fun onQueryTextChange(p0: String?): Boolean {
                        if (p0?.length!! > 2){
                            viewModel.filterFromSearchTab(p0,responseModel,adapter)
                        }
                        else {
                            adapter.submitList(responseModel)
                        }
                        return false
                    }
                })
                binding.checkBox.setOnCheckedChangeListener { _, _ ->
                    if (binding.checkBox.isChecked) {
                        adapter.submitList(viewModel.sortedByDescending())
                    } else {
                        adapter.submitList(responseModel)
                    }
                }
                adapter.submitList(responseModel)
            }
        })

    }

    private fun observeProgressbar() {
        viewModel.isLoading.observe(viewLifecycleOwner, Observer { isLoading ->
            binding.progressBar2.isVisible = isLoading
        })
    }

    private fun observeErrorState() {
        viewModel.errorString.observe(viewLifecycleOwner, Observer { errorString ->
            Toast.makeText(
                requireContext(),
                errorString,
                Toast.LENGTH_LONG
            ).show()
        })
    }
    private fun navigateToAnotherApp(packageName: String) {
        var intent = requireActivity().packageManager.getLaunchIntentForPackage(packageName)
        if (intent != null) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        } else {
            intent = Intent(Intent.ACTION_VIEW)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.data = Uri.parse("market://details?id=$packageName")
            startActivity(intent)
        }
    }
}