package com.example.noteapp.data.mapers

import com.example.noteapp.data.model.NoteEntity
import com.example.noteapp.domain.model.Note

fun Note.toNoteEntity()=NoteEntity(
    id,title,description,createAt
)

fun NoteEntity.toNote()=Note(
    id,title,description,createAt
)