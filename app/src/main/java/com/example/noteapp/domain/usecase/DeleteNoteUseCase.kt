package com.example.noteapp.domain.usecase

import com.example.noteapp.domain.model.Note
import com.example.noteapp.domain.repo.NoteRepository

class DeleteNoteUseCase(private val noteRepository: NoteRepository) {

    fun deleteNote(note:Note) = noteRepository.deleteNote(note)
}