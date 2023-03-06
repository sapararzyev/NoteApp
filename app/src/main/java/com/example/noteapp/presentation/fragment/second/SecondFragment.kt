package com.example.noteapp.presentation.fragment.second

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.noteapp.R
import com.example.noteapp.databinding.FragmentSecondBinding
import com.example.noteapp.domain.model.Note
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SecondFragment : Fragment() {

    private val viewModel:SecondViewModel by viewModels()
    private lateinit var binding: FragmentSecondBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View{ binding = FragmentSecondBinding.inflate(layoutInflater)
        return binding.root }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSecond.setOnClickListener {
            viewModel.createUseCase(Note(title =binding.meSecond.text.toString(),
                description = binding.youSecond.text.toString(), id = 0, createAt = 0))
        }
    }
}