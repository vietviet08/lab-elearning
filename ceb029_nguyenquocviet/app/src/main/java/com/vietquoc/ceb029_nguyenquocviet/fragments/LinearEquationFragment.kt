package com.vietquoc.ceb029_nguyenquocviet.fragments

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.findNavController
import com.vietquoc.ceb029_nguyenquocviet.R
import com.vietquoc.ceb029_nguyenquocviet.databinding.FragmentLinearEquationBinding
import com.vietquoc.ceb029_nguyenquocviet.viewmodel.LinearEquationViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LinearEquationFragment : Fragment(), MenuProvider {

    private var _binding: FragmentLinearEquationBinding? = null
    private val binding get() = _binding!!

    private val viewModel: LinearEquationViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLinearEquationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)

        val actionBar = (activity as? AppCompatActivity)?.supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setDisplayShowHomeEnabled(true)

        viewModel.solution.observe(viewLifecycleOwner) { result ->
            binding.tvResult.text = result
        }

        binding.btnSolve.setOnClickListener {
            val aText = binding.edtA.text.toString()
            val bText = binding.edtB.text.toString()

            if (aText.isNotEmpty() && bText.isNotEmpty()) {
                val a = aText.toDoubleOrNull()
                val b = bText.toDoubleOrNull()

                if (a != null && b != null) {
                    viewModel.solveEquation(a, b)
                } else {
                    Toast.makeText(requireContext(), "Vui lòng nhập số hợp lệ!", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(requireContext(), "Vui lòng nhập đầy đủ a và b!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.menu_main, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
            android.R.id.home -> {
                view?.findNavController()?.popBackStack()
                true
            }
            else -> false
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
