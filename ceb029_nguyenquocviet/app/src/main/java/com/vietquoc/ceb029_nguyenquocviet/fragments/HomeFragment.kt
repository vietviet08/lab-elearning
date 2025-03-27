package com.vietquoc.ceb029_nguyenquocviet.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.vietquoc.ceb029_nguyenquocviet.R
import com.vietquoc.ceb029_nguyenquocviet.databinding.FragmentHomeBinding
import com.vietquoc.ceb029_nguyenquocviet.databinding.FragmentHomeComputerBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnPerfectNumber.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_perfectNumberFragment)
        }

        binding.btnLinearEquation.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_linearEquationFragment)
        }

        binding.btnManageComputers.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_homeComputerFragment)
        }
    }

}