package com.example.bettingstrategies.presentation.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.bettingstrategies.R
import com.example.bettingstrategies.databinding.ActivityMainBinding
import com.example.bettingstrategies.presentation.fragments.BottomNavigationFragment
import com.example.bettingstrategies.presentation.fragments.MainFragment
import com.example.bettingstrategies.presentation.fragments.OnFragmentsInteractionsListener

class MainActivity : AppCompatActivity(), OnFragmentsInteractionsListener {
    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        supportFragmentManager.beginTransaction()
            .replace(binding?.mainContainer!!.id, MainFragment())
            .replace(binding?.bottomContainer!!.id, BottomNavigationFragment())
            .commit()
    }

    override fun onChangeFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(binding?.mainContainer!!.id, fragment)
            .commit()
    }

    override fun onAddBackStack(name: String, fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .addToBackStack(name)
            .replace(R.id.main_container, fragment)
            .commit()
    }

    override fun onPopBackStack() {
        for(i in 0..supportFragmentManager.backStackEntryCount) {
            supportFragmentManager.popBackStack()
        }
    }
}