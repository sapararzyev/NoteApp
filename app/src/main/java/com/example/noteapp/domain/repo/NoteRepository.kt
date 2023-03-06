package com.example.noteapp.domain.repo

import com.example.noteapp.domain.model.Note
import com.example.noteapp.domain.utils.ResultStatus
import kotlinx.coroutines.flow.Flow

interface NoteRepository {

    fun createNote(note: Note):Flow<ResultStatus<Unit>>

    fun getAllNotes():Flow<ResultStatus<List<Note>>>

    fun editNote(note: Note):Flow<ResultStatus<Unit>>

    fun deleteNote(note: Note):Flow<ResultStatus<Unit>>
}