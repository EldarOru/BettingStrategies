package com.example.bettingstrategies.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bettingstrategies.databinding.BettingStrategyBinding
import com.example.bettingstrategies.databinding.FavouriteBettingStrategyBinding
import com.example.bettingstrategies.domain.models.BettingStrategy
import com.squareup.picasso.Picasso

class FavouriteStrategiesListAdapter: RecyclerView.Adapter<FavouriteStrategiesListAdapter.FavouriteStrategyItemViewHolder>() {

    var onInfoButtonListener: ((BettingStrategy) -> Unit)? = null

    var list = listOf<BettingStrategy>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavouriteStrategyItemViewHolder {
        val strategyView = FavouriteBettingStrategyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavouriteStrategyItemViewHolder(strategyView)
    }

    override fun onBindViewHolder(
        holder: FavouriteStrategyItemViewHolder,
        position: Int
    ) {
        val strategy = list[position]
        holder.favouriteBettingStrategyBinding.strategyNameEt.text = strategy.name
        Picasso.get().load(strategy.picUrl).into(holder.favouriteBettingStrategyBinding.strategyImage)
        holder.favouriteBettingStrategyBinding.infoButton.setOnClickListener {
            onInfoButtonListener?.invoke(strategy)
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }

    class FavouriteStrategyItemViewHolder(val favouriteBettingStrategyBinding: FavouriteBettingStrategyBinding): RecyclerView.ViewHolder(favouriteBettingStrategyBinding.root)
}