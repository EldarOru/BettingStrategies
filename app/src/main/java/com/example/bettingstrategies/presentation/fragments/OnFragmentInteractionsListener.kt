package com.example.bettingstrategies.presentation.fragments

import androidx.fragment.app.Fragment

interface OnFragmentsInteractionsListener {

    fun onChangeFragment(fragment: Fragment)

    fun onAddBackStack(name: String, fragment: Fragment)

    fun onPopBackStack()

}