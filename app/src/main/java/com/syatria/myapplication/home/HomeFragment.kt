package com.syatria.myapplication.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.syatria.core.data.Resource
import com.syatria.core.ui.UserAdapter
import com.syatria.myapplication.R
import com.syatria.myapplication.databinding.FragmentHomeBinding
import com.syatria.myapplication.detail.DetailUserActivity
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModel()
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val userAdapter = UserAdapter {
                val intent = Intent(activity, DetailUserActivity::class.java)
                intent.putExtra(DetailUserActivity.EXTRA_DETAIL, it)
                startActivity(intent)
            }


            homeViewModel.user.observe(viewLifecycleOwner) { userData ->
                if (userData != null) {

                    binding?.apply {
                        when (userData) {
                            is Resource.Loading -> progressBar?.visibility = View.VISIBLE
                            is Resource.Success -> {
                                userAdapter.submitList(userData.data)
                                binding?.rvUserlist?.apply {
                                    layoutManager = LinearLayoutManager(context)
                                    setHasFixedSize(true)
                                    adapter = userAdapter

                                }
                                progressBar?.visibility = View.GONE
                            }

                            is Resource.Error -> {
                                progressBar?.visibility = View.GONE
                                viewError?.root?.visibility = View.VISIBLE
                                viewError?.tvError?.text =
                                    userData.message ?: "Something gone wrong"
                            }


                        }
                    }

                } else {
                    Toast.makeText(context, "data null", Toast.LENGTH_SHORT).show()
                }
            }


        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}