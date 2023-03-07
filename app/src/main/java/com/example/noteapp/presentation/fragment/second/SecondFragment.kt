package com.example.noteapp.presentation.fragment.second

import androidx.fragment.app.viewModels
import com.example.noteapp.base.BaseFragment
import com.example.noteapp.databinding.FragmentSecondBinding
import com.example.noteapp.domain.model.Note

class SecondFragment : BaseFragment<FragmentSecondBinding>(FragmentSecondBinding::inflate) {

    private val viewModel: SecondViewModel by viewModels()

    override fun setubIU() {
        binding.btnSecond.setOnClickListener {
            val title = binding.meSecond.text.toString()
            val description = binding.youSecond.text.toString()
            viewModel.createUseCase(Note(title = title,
                description = description, id = 0, createAt = 0))
        }
    }

}