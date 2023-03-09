package com.example.noteapp.presentation.fragment.notes

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapp.databinding.NotesItemBinding
import com.example.noteapp.domain.model.Note

class NotesAdapter(
    private val listener: (model: Note) -> Unit,
) : ListAdapter<Note, NotesAdapter.NotesViewHolder>(NotesCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val binding = NotesItemBinding.inflate(LayoutInflater.from(parent.context),
            parent, false)
        return NotesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val model = getItem(position)
        holder.bind(model)
        holder.itemView.setOnLongClickListener {
            listener(model)
            true
        }
    }

    class NotesViewHolder(private val binding: NotesItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(notesModel: Note) {
            binding.itemTvTiyle.text = notesModel.title
            binding.itemTvDes.text = notesModel.description
        }
    }

    class NotesCallback : DiffUtil.ItemCallback<Note>() {
        override fun areItemsTheSame(oldItem: Note, newItem: Note) =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: Note, newItem: Note) =
            oldItem == newItem
    }
}