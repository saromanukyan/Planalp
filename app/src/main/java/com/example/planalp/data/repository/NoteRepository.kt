package com.example.planalp.data.repository

import androidx.lifecycle.LiveData
import com.example.planalp.data.NoteDao
import com.example.planalp.data.models.NoteData

class NoteRepository(private val noteDao: NoteDao) {

    val getAllData: LiveData<List<NoteData>> = noteDao.getAllData()
    val sortByHighPriority: LiveData<List<NoteData>> = noteDao.sortByHighPriority()
    val sortByLowPriority: LiveData<List<NoteData>> = noteDao.sortByLowPriority()

    suspend fun insertData(noteData: NoteData) {
        noteDao.insertData(noteData)
    }

    suspend fun updateData(noteData: NoteData) {
        noteDao.updateData(noteData)
    }

    suspend fun deleteItem(noteData: NoteData) {
        noteDao.deleteItem(noteData)
    }

    suspend fun deleteAll() {
        noteDao.deleteAll()
    }

    fun searchDatabase(searchQuery: String): LiveData<List<NoteData>> {
        return noteDao.searchDatabase(searchQuery)
    }

}