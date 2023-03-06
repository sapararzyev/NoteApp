package com.example.noteapp.presentation.fragment.second

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteapp.domain.model.Note
import com.example.noteapp.domain.usecase.CreateNoteUseCase
import com.example.noteapp.domain.utils.ResultStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SecondViewModel @Inject constructor(
    private val createNoteUseCase: CreateNoteUseCase,
) : ViewModel() {

    private val _SecondState = MutableStateFlow<UiState<List<Note>?>>(UiState.Loading())
    val SecondState = _SecondState.asStateFlow()


    fun createUseCase(note:Note) {
        viewModelScope.launch {
            createNoteUseCase.createNote(note).collect {
                when (it) {
                    is ResultStatus.Loading -> {
                        _SecondState.value = UiState.Loading()
                    }
                    is ResultStatus.Error -> {
                        _SecondState.value = UiState.Error(it.error.toString())
                    }
                    is ResultStatus.Success -> {
                        if (it.data != null) {
                            _SecondState.value = UiState.Success(data = null)
                        }
                    }
                }
            }
        }
    }

}