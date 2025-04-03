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
import androidx.navigation.fragment.findNavController
import com.vietquoc.ceb029_nguyenquocviet.R
import com.vietquoc.ceb029_nguyenquocviet.databinding.FragmentAddComputerBinding
import com.vietquoc.ceb029_nguyenquocviet.model.Computer
import com.vietquoc.ceb029_nguyenquocviet.viewmodel.ComputerViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddComputerFragment : Fragment(R.layout.fragment_add_computer), MenuProvider {

    private lateinit var binding: FragmentAddComputerBinding
    private val noteViewModel by viewModels<ComputerViewModel>()
    private lateinit var addNoteView: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddComputerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)

        addNoteView = view

        binding.addComputerButton.setOnClickListener {
            saveNote(addNoteView)
        }

        val actionBar = (activity as? AppCompatActivity)?.supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setDisplayShowHomeEnabled(true)
    }

    private fun saveNote(view: View) {
        val name = binding.addName.text.toString().trim()
        val price = binding.addPrice.text.toString().trim()
        val type = binding.addType.text.toString().trim()
        val quantity = binding.addquantity.text.toString().trim()

        val note = Computer(0, name, type, quantity.toInt(), price.toDouble())
        noteViewModel.addComputer(note)

        Toast.makeText(addNoteView.context, "Computer saved successfully", Toast.LENGTH_SHORT)
            .show()
        view.findNavController()
            .popBackStack(R.id.homeFragment, false)
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menu.clear()
        menuInflater.inflate(R.menu.menu_add_note, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
//            R.id.saveMenu -> {
//                saveNote(addNoteView)
//                return true
//            }

            android.R.id.home -> {
                findNavController().popBackStack(R.id.homeFragment, false)
                return true
            }

            else -> return false
        }
    }

}