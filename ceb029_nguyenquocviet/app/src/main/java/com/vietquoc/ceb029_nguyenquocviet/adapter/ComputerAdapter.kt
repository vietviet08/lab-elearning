package com.vietquoc.ceb029_nguyenquocviet.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.vietquoc.ceb029_nguyenquocviet.databinding.ComputerLayoutBinding
import com.vietquoc.ceb029_nguyenquocviet.fragments.HomeComputerFragmentDirections
import com.vietquoc.ceb029_nguyenquocviet.model.Computer

class ComputerAdapter : RecyclerView.Adapter<ComputerAdapter.NoteViewHolder>() {
    class NoteViewHolder(val itemBinding: ComputerLayoutBinding) : RecyclerView.ViewHolder(itemBinding.root)

    private val differCallback = object : DiffUtil.ItemCallback<Computer>() {
        override fun areItemsTheSame(oldItem: Computer, newItem: Computer): Boolean {
            return oldItem.id == newItem.id &&
                    oldItem.name == newItem.name &&
                    oldItem.type == newItem.type &&
                    oldItem.price == newItem.price &&
                    oldItem.quantity == newItem.quantity
        }

        override fun areContentsTheSame(oldItem: Computer, newItem: Computer): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            ComputerLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNote = differ.currentList[position]

        holder.itemBinding.name.text = currentNote.name
        holder.itemBinding.type.text = currentNote.type
        holder.itemBinding.price.text = currentNote.price.toString()
        holder.itemBinding.quantity.text = currentNote.quantity.toString()

        holder.itemView.setOnClickListener {
            val direction = HomeComputerFragmentDirections.actionHomeComputerFragmentToEditNoteFragment4(currentNote)
            it.findNavController().navigate(direction)
        }
    }
}