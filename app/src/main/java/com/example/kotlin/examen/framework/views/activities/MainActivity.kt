package com.example.kotlin.examen.framework.views.activities

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.kotlin.examen.R
import com.example.kotlin.examen.databinding.ActivityMainBinding
import com.example.kotlin.examen.framework.viewmodel.MainViewModel
import com.example.kotlin.examen.framework.views.fragments.PersonajeFragment
import com.example.kotlin.examen.util.Constants

class MainActivity : AppCompatActivity() {

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

        binding.btnFilterGender.setOnClickListener {
            val fragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_main) as? PersonajeFragment
            fragment?.showGenderFilterDialog()
        }

        binding.btnFilterAffiliation.setOnClickListener {
            val fragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_main) as? PersonajeFragment
            fragment?.showAffiliationFilterDialog()
        }

        binding.btnFilterRace.setOnClickListener {
            val fragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_main) as? PersonajeFragment
            fragment?.showRaceFilterDialog()
        }
    }

    private fun initializeBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun initializeObservers() {
        viewModel.currentPage.observe(this) { page ->
            updatePaginationButtons()
            (currentFragment as? PersonajeFragment)?.loadPage(page)
        }
    }

    private fun initializeListeners() {
        binding.appBarMain.llBack.setOnClickListener {
            viewModel.previousPage()
        }
        binding.appBarMain.llNext.setOnClickListener {
            viewModel.nextPage()
        }
    }

    private fun exchangeCurrentFragment(newFragment: Fragment, newMenuOption: String) {
        currentFragment = newFragment
        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment_content_main, currentFragment)
            .commit()
        currentMenuOption = newMenuOption
    }

    private fun updatePaginationButtons() {
        binding.appBarMain.llBack.isEnabled = viewModel.canGoPrevious()
        binding.appBarMain.llNext.isEnabled = viewModel.canGoNext()
    }
}