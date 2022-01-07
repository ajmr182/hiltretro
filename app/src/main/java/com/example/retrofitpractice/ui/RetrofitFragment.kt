package com.example.retrofitpractice.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofitpractice.R
import com.example.retrofitpractice.databinding.FragmentRetrofitBinding
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.FragmentScoped

@AndroidEntryPoint
class RetrofitFragment : Fragment() {

    lateinit var retrofitViewModel: RetrofitViewModel
    private var _binding: FragmentRetrofitBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        retrofitViewModel =
            ViewModelProvider(this)[RetrofitViewModel::class.java]
        _binding = FragmentRetrofitBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        startObservables()
    }

    private fun startObservables() {

        retrofitViewModel.requestFeed()
        retrofitViewModel.postResponse.observe(viewLifecycleOwner, {
            val adapter = RetrofitAdapter(it)
            Toast.makeText(requireContext(), "ok", Toast.LENGTH_SHORT).show()
            binding.rvRetrofit.setHasFixedSize(true)
            binding.rvRetrofit.layoutManager = LinearLayoutManager(requireContext())
            binding.rvRetrofit.adapter = adapter
        })
    }
}