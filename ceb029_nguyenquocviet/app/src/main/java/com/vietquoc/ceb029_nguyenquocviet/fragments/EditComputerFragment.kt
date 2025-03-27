package com.vietquoc.ceb029_nguyenquocviet.fragments

import android.app.AlertDialog
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
import androidx.navigation.fragment.navArgs
import com.vietquoc.ceb029_nguyenquocviet.R
import com.vietquoc.ceb029_nguyenquocviet.databinding.FragmentEditComputerBinding
import com.vietquoc.ceb029_nguyenquocviet.model.Computer
import com.vietquoc.ceb029_nguyenquocviet.viewmodel.ComputerViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class EditNoteFragment : Fragment(R.layout.fragment_edit_computer), MenuProvider {

    private lateinit var binding: FragmentEditComputerBinding
    private val noteViewModel by viewModels<ComputerViewModel>()
    private lateinit var currentNote: Computer
    private lateinit var editNoteView: View


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEditComputerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)

        editNoteView = view
//        currentNote = args.computer!!

        binding.editName.setText(currentNote.name)
        binding.editType.setText(currentNote.type)

        binding.editNoteFab.setOnClickListener {
            updateNote(editNoteView)
        }

        val actionBar = (activity as? AppCompatActivity)?.supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setDisplayShowHomeEnabled(true)

    }

    private fun updateNote(view: View) {
        val name = binding.editName.text.toString().trim()
        val price = binding.editPrice.text.toString().trim()
        val type = binding.editPrice.text.toString().trim()
        val quantity = binding.editquantity.text.toString().trim()

        val note = Computer(0, name, type, price.toDouble(), quantity.toInt())

        noteViewModel.updateNote(note)
        Toast.makeText(editNoteView.context, "Note updated successfully", Toast.LENGTH_SHORT)
            .show()
        view.findNavController()
            .popBackStack(R.id.homeFragment, false)
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menu.clear()
        menuInflater.inflate(R.menu.menu_edit_note, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            R.id.deleteMenu -> {
                deleteNote(editNoteView)
                return true
            }

            android.R.id.home -> {
                //1
//                findNavController().popBackStack(R.id.homeFragment, false)

                //2
                view?.findNavController()
                    ?.popBackStack(R.id.homeFragment, false)
                return true
            }

            else -> return false
        }
    }

    private fun deleteNote(view: View) {
        AlertDialog.Builder(activity).apply {
            setTitle("Delete Note")
            setMessage("Are you sure you want to delete this note?")
            setPositiveButton("Delete") { _, _ ->
                noteViewModel.deleteNote(currentNote)

                Toast.makeText(
                    editNoteView.context,
                    "Note deleted successfully",
                    Toast.LENGTH_SHORT
                ).show()

                view.findNavController()
                    .popBackStack(R.id.homeFragment, false)
            }
            setNegativeButton("Cancel", null)
        }.create().show()
    }

}