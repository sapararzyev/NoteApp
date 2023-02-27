package com.example.noteapp.domain.usecase

import com.example.noteapp.domain.model.Note
import com.example.noteapp.domain.repo.NoteRepository

class EditNoteUseCase(private val noteRepository: NoteRepository) {

    fun editNote(note:Note) = noteRepository.editNote(note)
}