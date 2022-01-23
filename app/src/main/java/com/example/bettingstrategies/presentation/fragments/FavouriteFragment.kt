package com.example.bettingstrategies.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bettingstrategies.databinding.FavouriteFragmentBinding
import com.example.bettingstrategies.databinding.MainFragmentBinding
import com.example.bettingstrategies.presentation.adapters.FavouriteStrategiesListAdapter
import com.example.bettingstrategies.presentation.adapters.StrategiesListAdapter
import com.example.bettingstrategies.presentation.viewmodels.FavouriteFragmentViewModel
import com.example.bettingstrategies.presentation.viewmodels.MainFragmentViewModel
import com.example.bettingstrategies.presentation.viewmodels.ViewModelFactory
import java.lang.RuntimeException

class FavouriteFragment: Fragment() {
    private lateinit var favouriteStrategiesListAdapter: FavouriteStrategiesListAdapter
    private lateinit var favouriteFragmentViewModel: FavouriteFragmentViewModel
    private lateinit var onFragmentsInteractionsListener: OnFragmentsInteractionsListener
    private var favouriteFragmentBinding: FavouriteFragmentBinding? = null

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
        favouriteFragmentBinding = FavouriteFragmentBinding.inflate(inflater, container, false)
        return favouriteFragmentBinding?.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        favouriteFragmentViewModel = ViewModelProvider(this, ViewModelFactory())[FavouriteFragmentViewModel::class.java]
        setRecyclerView()
        setClicksListener()
        favouriteFragmentViewModel.getFavouriteStrategies().observe(viewLifecycleOwner){
            favouriteStrategiesListAdapter.list = it
        }

        favouriteFragmentViewModel.successLiveData.observe(viewLifecycleOwner){
            Toast.makeText(this.requireContext(), "Success", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setRecyclerView() {
        val recyclerView = favouriteFragmentBinding?.strategiesList
        recyclerView?.layoutManager = LinearLayoutManager(context)
        favouriteStrategiesListAdapter = FavouriteStrategiesListAdapter()
        recyclerView?.adapter = favouriteStrategiesListAdapter
        setupSwipeListener(recyclerView as RecyclerView)
    }

    private fun setClicksListener(){
        favouriteStrategiesListAdapter.onInfoButtonListener = {
            onFragmentsInteractionsListener.onAddBackStack(
                "strategy",
                DetailedFavouriteStrategyFragment.newInstanceDetailedStrategyFragment(it.id)
            )
        }
    }

    private fun setupSwipeListener(recyclerView: RecyclerView) {
        val myCallBack = object : ItemTouchHelper.SimpleCallback(0,
            ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT) {
            override fun onMove(recyclerView: RecyclerView,
                                viewHolder: RecyclerView.ViewHolder,
                                target: RecyclerView.ViewHolder): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = favouriteStrategiesListAdapter.list[viewHolder.adapterPosition]
                Toast.makeText(context, "${item.name} successfully deleted", Toast.LENGTH_SHORT).show()
                favouriteFragmentViewModel.deleteFromFavourites(item)
            }
        }
        val itemTouchHelper = ItemTouchHelper(myCallBack)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }
}