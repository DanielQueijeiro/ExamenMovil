package com.example.kotlin.examen.framework.views.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin.examen.R
import com.example.kotlin.examen.data.model.PersonajeBase
import com.example.kotlin.examen.databinding.FragmentPersonajesBinding
import com.example.kotlin.examen.framework.adapters.PersonajeAdapter
import com.example.kotlin.examen.framework.viewmodel.PersonajeViewModel

class PersonajeFragment : Fragment() {
    private var _binding: FragmentPersonajesBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: PersonajeViewModel
    private lateinit var recyclerView: RecyclerView
    private val adapter: PersonajeAdapter = PersonajeAdapter()
    private lateinit var data: ArrayList<PersonajeBase>

    private lateinit var btnFilterGender: Button
    private lateinit var btnFilterAffiliation: Button
    private lateinit var btnFilterRace: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this)[PersonajeViewModel::class.java]
        _binding = FragmentPersonajesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        data = ArrayList()

        initializeComponents(root)
        initializeObservers()
        loadPage(1)

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initializeComponents(root: View) {
        recyclerView = root.findViewById(R.id.RVPersonajes)
    }

    private fun initializeObservers() {
        viewModel.personajeObjectLiveData.observe(viewLifecycleOwner) { personajeObject ->
            data = personajeObject.items // Inicializa la lista completa de personajes
            setUpRecyclerView(data)
        }
    }

    private fun setUpRecyclerView(dataForList: ArrayList<PersonajeBase>) {
        recyclerView.setHasFixedSize(true)
        val gridLayoutManager = GridLayoutManager(
            requireContext(),
            1,
            GridLayoutManager.VERTICAL,
            false
        )
        recyclerView.layoutManager = gridLayoutManager
        adapter.PersonajeAdapter(dataForList, requireContext())
        recyclerView.adapter = adapter
    }

    fun loadPage(page: Int) {
        viewModel.getPersonajeList(page)
    }

    fun showGenderFilterDialog() {
        val genders = arrayOf("Male", "Female")
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Selecciona un género")
        builder.setItems(genders) { _, which ->
            val selectedGender = genders[which]
            filterByGender(selectedGender)
        }
        builder.show()
    }

    fun filterByGender(gender: String) {
        val filteredList = data.filter { it.gender == gender }
        if (filteredList.isEmpty()) {
            Toast.makeText(requireContext(), "No hay personajes que coincidan con el filtro en esta página. Cambia de página y vuelve a filtrar.", Toast.LENGTH_LONG).show()
        } else {
            setUpRecyclerView(ArrayList(filteredList))
        }
    }

    fun showAffiliationFilterDialog() {
        val affiliations = arrayOf("Z Fighter", "Army of Frieza", "Freelancer", "Villain", "Assistant of Beerus", "Pride Troopers", "Assistant of Vermoud")
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Selecciona una afiliación")
        builder.setItems(affiliations) { _, which ->
            val selectedAffiliation = affiliations[which]
            filterByAffiliation(selectedAffiliation)
        }
        builder.show()
    }

    fun showRaceFilterDialog() {
        val races = arrayOf("Saiyan", "Namekian", "Human", "Frieza Race", "Android", "Majin", "God", "Angel", "Unknown", "Jiren Race", "Nucleico benigno")
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Selecciona una raza")
        builder.setItems(races) { _, which ->
            val selectedRace = races[which]
            filterByRace(selectedRace)
        }
        builder.show()
    }

    fun filterByAffiliation(affiliation: String) {
        val filteredList = data.filter { it.affiliation == affiliation }
        if (filteredList.isEmpty()) {
            Toast.makeText(requireContext(), "No hay personajes que coincidan con el filtro en esta página. Cambia de página y vuelve a filtrar.", Toast.LENGTH_LONG).show()
        } else {
            setUpRecyclerView(ArrayList(filteredList))
        }
    }

    fun filterByRace(race: String) {
        val filteredList = data.filter { it.race == race }
        if (filteredList.isEmpty()) {
            Toast.makeText(requireContext(), "No hay personajes que coincidan con el filtro en esta página. Cambia de página y vuelve a filtrar.", Toast.LENGTH_LONG).show()
        } else {
            setUpRecyclerView(ArrayList(filteredList))
        }
    }
}