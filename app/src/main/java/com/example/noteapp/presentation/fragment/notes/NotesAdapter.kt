package com.example.noteapp.presentation.fragment.notes

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapp.databinding.NotesItemBinding
import com.example.noteapp.domain.model.Note

class NotesAdapter : ListAdapter<NotesViewModel, NotesAdapter.NotesViewHolder>(NotesCallback()) {

    private var list: ArrayList<Note> = arrayListOf()

    fun addNote(list: ArrayList<Note>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val binding = NotesItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NotesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        holder.bind(list[position])
    }

    class NotesViewHolder(private val binding: NotesItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(notesModel: Note) {
            binding.itemTvTiyle.text = notesModel.title
            binding.itemTvDes.text = notesModel.description
        }
    }

    class NotesCallback : DiffUtil.ItemCallback<NotesViewModel>() {
        override fun areItemsTheSame(oldItem: NotesViewModel, newItem: NotesViewModel) =
            oldItem == newItem

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: NotesViewModel, newItem: NotesViewModel) =
            oldItem == newItem
    }
}