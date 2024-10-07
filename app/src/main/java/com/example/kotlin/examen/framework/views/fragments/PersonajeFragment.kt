package com.example.kotlin.examen.framework.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin.examen.R
import com.example.kotlin.examen.data.model.PersonajeBase
import com.example.kotlin.examen.databinding.FragmentPersonajesBinding
import com.example.kotlin.examen.framework.adapters.PersonajeAdapter
import com.example.kotlin.examen.framework.viewmodel.PersonajeViewModel

class PersonajeFragment: Fragment() {
    private var _binding: FragmentPersonajesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var viewModel: PersonajeViewModel

    private lateinit var recyclerView: RecyclerView
    private val adapter : PersonajeAdapter = PersonajeAdapter()
    private lateinit var data:ArrayList<PersonajeBase>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this)[PersonajeViewModel::class.java]

        _binding = FragmentPersonajesBinding.inflate(inflater, container, false)
        val root: View = _binding!!.root

        data = ArrayList()

        initializeComponents(root)
        initializeObservers()
        viewModel.getPersonajeList()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initializeComponents(root:View){
        recyclerView = root.findViewById(R.id.RVPersonajes)
    }

    private fun initializeObservers() {
        viewModel.personajeObjectLiveData.observe(viewLifecycleOwner) { personajeObject ->
            setUpRecyclerView(personajeObject.items)
        }
    }

    private fun setUpRecyclerView(dataForList:ArrayList<PersonajeBase>){
        recyclerView.setHasFixedSize(true)
        /*val linearLayoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL,
            false)*/
        val gridLayoutManager = GridLayoutManager(
            requireContext(),
            1,
            GridLayoutManager.VERTICAL,
            false
        )
        recyclerView.layoutManager = gridLayoutManager
        adapter.PersonajeAdapter(dataForList,requireContext())
        recyclerView.adapter = adapter
    }
}