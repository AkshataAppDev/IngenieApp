package com.example.ingenieapp.ui.base

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.ingenieapp.di.ViewModelProviderFactory
import com.example.ingenieapp.ui.MainViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

internal abstract class BaseFragment : DaggerFragment() {
    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    lateinit var mainViewModel: MainViewModel


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainViewModel = activity?.run {
            ViewModelProvider(requireActivity(), providerFactory).get(MainViewModel::class.java)
        } ?: throw Exception("Invalid activity")

    }
}