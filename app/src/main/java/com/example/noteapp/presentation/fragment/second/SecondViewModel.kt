package com.example.noteapp.presentation.fragment.second

import com.example.noteapp.domain.model.Note
import com.example.noteapp.domain.usecase.CreateNoteUseCase
import com.example.noteapp.presentation.base.BaseViewModel
import com.example.noteapp.presentation.fragment.notes.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject


@HiltViewModel
class SecondViewModel @Inject constructor(
    private val createNoteUseCase: CreateNoteUseCase,
) : BaseViewModel() {

    private val _SecondState = MutableStateFlow<UiState<Unit>>(UiState.Empty())
    val SecondState = _SecondState.asStateFlow()

    fun createUseCase(note:Note) {
      createNoteUseCase.invoke(note).collectFlow(_SecondState)
    }

}