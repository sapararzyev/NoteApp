package com.example.noteapp.presentation.fragment.notes

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.noteapp.R
import com.example.noteapp.databinding.FragmentNotesBinding
import kotlinx.coroutines.launch


class NotesFragment : Fragment(R.layout.fragment_notes) {

    private lateinit var adapter: NotesAdapter
    private lateinit var viewModel: NotesViewModel
    private lateinit var binding: FragmentNotesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentNotesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = NotesAdapter()
        binding.rvNotes.adapter = adapter
        binding.btnAddNote.setOnClickListener {
            findNavController().navigate(R.id.secondFragment)
        }


    }
}