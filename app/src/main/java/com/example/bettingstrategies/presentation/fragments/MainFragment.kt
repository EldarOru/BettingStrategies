package com.example.bettingstrategies.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bettingstrategies.databinding.MainFragmentBinding
import com.example.bettingstrategies.presentation.adapters.StrategiesListAdapter
import com.example.bettingstrategies.presentation.viewmodels.MainFragmentViewModel
import com.example.bettingstrategies.presentation.viewmodels.ViewModelFactory
import java.lang.RuntimeException

class MainFragment: Fragment() {
    private lateinit var strategiesListAdapter: StrategiesListAdapter
    private lateinit var mainFragmentViewModel: MainFragmentViewModel
    private lateinit var onFragmentsInteractionsListener: OnFragmentsInteractionsListener
    private var mainEventFragmentBinding: MainFragmentBinding? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentsInteractionsListener){
            onFragmentsInteractionsListener = context
        }else{
            throw RuntimeException("Activity must implement OnFragmentsInteractionsListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mainEventFragmentBinding = MainFragmentBinding.inflate(inflater, container, false)
        return mainEventFragmentBinding?.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mainFragmentViewModel = ViewModelProvider(this, ViewModelFactory())[MainFragmentViewModel::class.java]
        setRecyclerView()
        setClicksListener()

        mainFragmentViewModel.getStrategies().observe(viewLifecycleOwner){
            strategiesListAdapter.list = it
        }

        mainFragmentViewModel.getError().observe(viewLifecycleOwner){
            Toast.makeText(this.requireContext(), "You have already had this strategy in favourite", Toast.LENGTH_SHORT).show()
        }

        mainFragmentViewModel.getSuccess().observe(viewLifecycleOwner){
            Toast.makeText(this.requireContext(), "Success", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setRecyclerView() {
        val recyclerView = mainEventFragmentBinding?.strategiesList
        recyclerView?.layoutManager = LinearLayoutManager(context)
        strategiesListAdapter = StrategiesListAdapter()
        recyclerView?.adapter = strategiesListAdapter
    }

    private fun setClicksListener(){
        strategiesListAdapter.onInfoButtonListener = {
            onFragmentsInteractionsListener.onAddBackStack(
                "strategy",
                DetailedStrategyFragment.newInstanceDetailedStrategyFragment(it.id)
            )
        }
        strategiesListAdapter.onAddToFavouriteButtonListener = {
            mainFragmentViewModel.addToFavourite(it)
        }
    }
}