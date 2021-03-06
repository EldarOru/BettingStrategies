package com.example.bettingstrategies.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.bettingstrategies.databinding.DetailedStrategyFragmentBinding
import com.example.bettingstrategies.domain.models.BettingStrategy
import com.example.bettingstrategies.presentation.viewmodels.DetailedStrategyFragmentViewModel
import com.example.bettingstrategies.presentation.viewmodels.ViewModelFactory
import com.squareup.picasso.Picasso

class DetailedStrategyFragment: Fragment() {

    private lateinit var detailedStrategyFragmentViewModel: DetailedStrategyFragmentViewModel
    private lateinit var detailedStrategyFragmentBinding: DetailedStrategyFragmentBinding
    private var strategyID: Int = BettingStrategy.UNDEFINED_ID

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        detailedStrategyFragmentBinding = DetailedStrategyFragmentBinding.inflate(inflater, container, false)
        return detailedStrategyFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        detailedStrategyFragmentViewModel = ViewModelProvider(this, ViewModelFactory())[DetailedStrategyFragmentViewModel::class.java]

        getArgs()

        detailedStrategyFragmentViewModel.getStrategies().observe(viewLifecycleOwner){
            setInfo()
        }

        detailedStrategyFragmentViewModel.getError().observe(viewLifecycleOwner){
            Toast.makeText(this.requireContext(), "You have already had this strategy in favourite", Toast.LENGTH_SHORT).show()
        }

        detailedStrategyFragmentViewModel.getSuccess().observe(viewLifecycleOwner){
            Toast.makeText(this.requireContext(), "Success", Toast.LENGTH_SHORT).show()
        }

        detailedStrategyFragmentBinding.saveButton.setOnClickListener {
            detailedStrategyFragmentViewModel.addToFavourite(detailedStrategyFragmentViewModel.strategy.value!!)
        }

    }

    private fun getArgs(){
        strategyID = requireArguments().getInt(STRATEGY_ID)
    }

    private fun setInfo(){
        detailedStrategyFragmentViewModel.getStrategy(strategyID)
        val strategy = detailedStrategyFragmentViewModel.strategy.value
        detailedStrategyFragmentBinding.apply {
            detailedNameStrategyTV.text = strategy?.name
            detailedDescriptionStrategyTV.text = strategy?.description
        }
        Picasso.get().load(strategy?.picUrl).into(detailedStrategyFragmentBinding.detailedPic)
    }

    companion object{
        fun newInstanceDetailedStrategyFragment(id: Int): DetailedStrategyFragment{
            return DetailedStrategyFragment().apply {
                arguments = Bundle().apply {
                    putInt(STRATEGY_ID, id)
                }
            }
        }
        private const val STRATEGY_ID = "strategyID"
    }
}