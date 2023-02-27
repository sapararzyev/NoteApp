package com.example.noteapp.domain.usecase

import com.example.noteapp.domain.model.Note
import com.example.noteapp.domain.repo.NoteRepository

class GetAllNotesUseCase(private val noteRepository: NoteRepository) {

    fun getAllNotes(note:Note) = noteRepository.getAllNotes()
}