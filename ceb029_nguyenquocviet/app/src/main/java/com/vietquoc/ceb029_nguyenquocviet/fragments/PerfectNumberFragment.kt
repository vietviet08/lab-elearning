package com.vietquoc.ceb029_nguyenquocviet.fragments

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.findNavController
import com.vietquoc.ceb029_nguyenquocviet.R
import com.vietquoc.ceb029_nguyenquocviet.databinding.FragmentPerfectNumberBinding
import com.vietquoc.ceb029_nguyenquocviet.viewmodel.PerfectNumberViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PerfectNumberFragment : Fragment(), MenuProvider {

    private var _binding: FragmentPerfectNumberBinding? = null
    private val binding get() = _binding!!

    private val viewModel: PerfectNumberViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPerfectNumberBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)

        val actionBar = (activity as? AppCompatActivity)?.supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setDisplayShowHomeEnabled(true)

        binding.btnCheck.setOnClickListener {
            val number = binding.etNumber.text.toString().toIntOrNull()
            if (number != null) {
                viewModel.checkPerfectNumber(number)
            } else {
                binding.tvResult.text = "Vui lòng nhập số hợp lệ"
            }
        }

        viewModel.result.observe(viewLifecycleOwner) { result ->
            binding.tvResult.text = result
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateMenu(p0: Menu, p1: MenuInflater) {
        p0.clear()
        p1.inflate(R.menu.menu_main, p0)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {

            android.R.id.home -> {
                view?.findNavController()
                    ?.popBackStack(R.id.homeFragment, false)
                return true
            }

            else -> return false
        }
    }

}