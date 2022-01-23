package com.example.bettingstrategies.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.bettingstrategies.R
import com.example.bettingstrategies.databinding.BottomNavigationFragmentBinding
import java.lang.RuntimeException

class BottomNavigationFragment: Fragment(){
    private lateinit var onFragmentsInteractionsListener: OnFragmentsInteractionsListener
    private var bottomNavigationFragmentBinding: BottomNavigationFragmentBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bottomNavigationFragmentBinding =  BottomNavigationFragmentBinding.inflate(inflater, container, false)
        return bottomNavigationFragmentBinding?.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentsInteractionsListener){
            onFragmentsInteractionsListener = context
        }else{
            throw RuntimeException("Activity must implement OnFragmentsInteractionsListener")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bottomNavigationFragmentBinding?.bottomNavigation?.setOnItemSelectedListener {
            when (it.itemId){
                R.id.one -> {onFragmentsInteractionsListener.onChangeFragment(MainFragment())
                    return@setOnItemSelectedListener true}
                R.id.two -> {onFragmentsInteractionsListener.onChangeFragment(FavouriteFragment())
                    return@setOnItemSelectedListener true}
                else -> {
                    Toast.makeText(activity, "f", Toast.LENGTH_LONG).show()
                    return@setOnItemSelectedListener true}
            }
        }


    }
}