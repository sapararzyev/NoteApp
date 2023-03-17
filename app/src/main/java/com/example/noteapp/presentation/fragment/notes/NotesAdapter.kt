package com.example.noteapp.presentation.fragment.notes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapp.databinding.NotesItemBinding
import com.example.noteapp.domain.model.Note

class NotesAdapter(
    private val onLongItemClikListener: (position: Int) -> Unit,
    private val onItemClikListener: (Note) -> Unit,
) : ListAdapter<Note, NotesAdapter.NotesViewHolder>(NotesCallback()) {

//    private var list = mutableListOf<Note>()
//    private var deleteNote : Note? = null

//    fun delete(){
//        if (delete != null){
//            list.removeAt(deleteNote)
//            deleteNote = null
//            notifyDataSetChanged()
//        }
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val binding = NotesItemBinding.inflate(LayoutInflater.from(parent.context),
            parent, false)
        return NotesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val model = getItem(position)
        holder.bind(model)
        holder.itemView.setOnClickListener {
            onItemClikListener(model)
        }
    }

    inner class NotesViewHolder(private val binding: NotesItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(notesModel: Note) {
            with(binding) {
                itemTvTiyle.text = notesModel.title
                itemTvDes.text = notesModel.description

                root.setOnLongClickListener {
                    onLongItemClikListener(adapterPosition)
                    true
                }
            }
        }
    }

    class NotesCallback : DiffUtil.ItemCallback<Note>() {
        override fun areItemsTheSame(oldItem: Note, newItem: Note) =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: Note, newItem: Note) =
            oldItem == newItem
    }
}