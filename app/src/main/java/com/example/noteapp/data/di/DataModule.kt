package com.example.noteapp.data.di

import android.content.Context
import androidx.room.Room
import com.example.noteapp.data.local.AppDataBase
import com.example.noteapp.data.local.NoteDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    fun appDataBase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,
        AppDataBase::class.java,
        "Note-db"
    ).build()

    @Provides
    fun noteDao(appDataBase: AppDataBase): NoteDao = appDataBase.noteDao()

}