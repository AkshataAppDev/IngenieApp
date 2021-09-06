package com.example.ingenieapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.ingenieapp.BaseFragment
import com.example.ingenieapp.R
import com.example.ingenieapp.databinding.CharacterListFragmentDataBinding


internal class CharacterListFragment : BaseFragment() {

    private lateinit var binding: CharacterListFragmentDataBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = mainViewModel

        mainViewModel.navigateToDetails.observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled()?.let {
                findNavController().navigate(
                    R.id.action_charListFragment_to_charDetailFragment,
                    null
                )
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CharacterListFragmentDataBinding
            .inflate(inflater, container, false)


        binding.apply {

            lifecycleOwner = viewLifecycleOwner

            charRecyclerView.adapter = Adapter(
                ItemClickListener { charItem ->
//                Log.d("Item Clicked", "onCreateView: " + charItem.name)
                    mainViewModel.userSelectsItem(charItem)
                })
        }

        return binding.root
    }
}