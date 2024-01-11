package com.john.firstmenubasic.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.john.firstmenubasic.R
import com.john.firstmenubasic.controller.Controller
import com.john.firstmenubasic.databinding.FragmentRestaurantBinding

class RestaurantFragment : Fragment() {
    private var _binding: FragmentRestaurantBinding? = null
    private val binding get() = _binding!!
    lateinit var recyclerView : RecyclerView
    lateinit var controller: Controller
    lateinit var navController: NavController //nuestro navController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRestaurantBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    fun init(){
        recyclerView = binding.myRecyclerView.findViewById(R.id.my_recycler_view)
        controller = Controller(requireContext())
        controller.iniciar(recyclerView)
        //navController.navigate(FragmentStartDirections.actionFragmentStartToFragmentB(name = "Santiago"))

        binding.fab.setOnClickListener{
            controller.addRestaurant(recyclerView)
        }


    }


}