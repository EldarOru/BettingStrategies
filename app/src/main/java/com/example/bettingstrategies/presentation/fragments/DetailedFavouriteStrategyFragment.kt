package com.example.bettingstrategies.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.bettingstrategies.databinding.DetailedFavouriteStrategyFragmentBinding
import com.example.bettingstrategies.databinding.DetailedStrategyFragmentBinding
import com.example.bettingstrategies.domain.models.BettingStrategy
import com.example.bettingstrategies.presentation.viewmodels.DetailedFavouriteStrategyFragmentViewModel
import com.example.bettingstrategies.presentation.viewmodels.DetailedStrategyFragmentViewModel
import com.example.bettingstrategies.presentation.viewmodels.ViewModelFactory
import com.squareup.picasso.Picasso

class DetailedFavouriteStrategyFragment: Fragment() {

    private lateinit var detailedStrategyFragmentViewModel: DetailedFavouriteStrategyFragmentViewModel
    private lateinit var detailedStrategyFragmentBinding: DetailedFavouriteStrategyFragmentBinding
    private var strategyID: Int = BettingStrategy.UNDEFINED_ID

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        detailedStrategyFragmentBinding = DetailedFavouriteStrategyFragmentBinding.inflate(inflater, container, false)
        return detailedStrategyFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        detailedStrategyFragmentViewModel = ViewModelProvider(this, ViewModelFactory())[DetailedFavouriteStrategyFragmentViewModel::class.java]

        getArgs()

        detailedStrategyFragmentViewModel.getStrategies().observe(viewLifecycleOwner){
            setInfo()
        }

    }

    private fun getArgs(){
        strategyID = requireArguments().getInt(STRATEGY_ID)
    }

    private fun setInfo(){
        detailedStrategyFragmentViewModel.getStrategy(strategyID)
        val strategy =detailedStrategyFragmentViewModel.strategy.value
        detailedStrategyFragmentBinding.apply {
            detailedNameStrategyTV.text = strategy?.name
            detailedDescriptionStrategyTV.text = strategy?.description
        }
        Picasso.get().load(strategy?.picUrl).into(detailedStrategyFragmentBinding.detailedPic)
    }

    companion object{
        fun newInstanceDetailedStrategyFragment(id: Int): DetailedFavouriteStrategyFragment{
            return DetailedFavouriteStrategyFragment().apply {
                arguments = Bundle().apply {
                    putInt(STRATEGY_ID, id)
                }
            }
        }
        private const val STRATEGY_ID = "strategyID"
    }
}