package com.example.kotlin.examen.framework.views.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.kotlin.examen.R
import com.example.kotlin.examen.databinding.ActivityMainBinding
import com.example.kotlin.examen.framework.viewmodel.MainViewModel
import com.example.kotlin.examen.framework.views.fragments.PersonajeFragment
import com.example.kotlin.examen.util.Constants

class MainActivity: AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels()

    private lateinit var currentFragment: Fragment
    private var currentMenuOption: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initializeBinding()
        initializeObservers()
        initializeListeners()
        exchangeCurrentFragment(PersonajeFragment(), Constants.MENU_FIRST)

    }

    private fun initializeBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun initializeObservers(){

    }

    private fun initializeListeners(){
        binding.appBarMain.llBack.setOnClickListener{
            selectMenuOption(Constants.MENU_FIRST)
        }
        binding.appBarMain.llNext.setOnClickListener{
            selectMenuOption(Constants.MENU_LAST)
        }
    }

    private fun exchangeCurrentFragment(newFragment: Fragment, newMenuOption:String) {
        currentFragment = newFragment

        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment_content_main, currentFragment)
            .commit()

        currentMenuOption = newMenuOption
    }

    private fun selectMenuOption(menuOption: String){
        if(menuOption == currentMenuOption){
            return
        }
    }
}