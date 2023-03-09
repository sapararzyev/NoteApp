package com.example.noteapp.presentation.fragment.second

import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.noteapp.R
import com.example.noteapp.databinding.FragmentSecondBinding
import com.example.noteapp.domain.model.Note
import com.example.noteapp.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SecondFragment :
    BaseFragment<SecondViewModel, FragmentSecondBinding>(R.layout.fragment_second) {

    override val binding: FragmentSecondBinding by viewBinding(FragmentSecondBinding::bind)
    override val vm: SecondViewModel by viewModels()

    override fun setupRequests() {
        binding.btnSecond.setOnClickListener {
            val title = binding.meSecond.text.toString()
            val description = binding.youSecond.text.toString()
            vm.createUseCase(Note(title = title,
                description = description,
                createAt = 12))
            findNavController().navigateUp()
            findNavController().navigate(R.id.notesFragment)
        }
    }
}