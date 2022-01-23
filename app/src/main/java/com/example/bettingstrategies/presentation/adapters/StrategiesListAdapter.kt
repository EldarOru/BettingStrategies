package com.example.bettingstrategies.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bettingstrategies.databinding.BettingStrategyBinding
import com.example.bettingstrategies.domain.models.BettingStrategy
import com.squareup.picasso.Picasso

class StrategiesListAdapter: RecyclerView.Adapter<StrategiesListAdapter.StrategyItemViewHolder>() {

    var onInfoButtonListener: ((BettingStrategy) -> Unit)? = null
    var onAddToFavouriteButtonListener: ((BettingStrategy) -> Unit)? = null

    var list = listOf<BettingStrategy>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): StrategyItemViewHolder {
        val strategyView = BettingStrategyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StrategyItemViewHolder(strategyView)
    }

    override fun onBindViewHolder(
        holder: StrategyItemViewHolder,
        position: Int
    ) {
        val strategy = list[position]
        holder.bettingStrategyBinding.strategyNameEt.text = strategy.name
        Picasso.get().load(strategy.picUrl).into(holder.bettingStrategyBinding.strategyImage)
        holder.bettingStrategyBinding.infoButton.setOnClickListener {
            onInfoButtonListener?.invoke(strategy)
        }
        holder.bettingStrategyBinding.addFavouriteButton.setOnClickListener {
            onAddToFavouriteButtonListener?.invoke(strategy)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class StrategyItemViewHolder(val bettingStrategyBinding: BettingStrategyBinding): RecyclerView.ViewHolder(bettingStrategyBinding.root)
}