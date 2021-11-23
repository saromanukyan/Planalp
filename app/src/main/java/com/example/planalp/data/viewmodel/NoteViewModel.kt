package com.example.planalp.data.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.planalp.data.NoteDatabase
import com.example.planalp.data.models.NoteData
import com.example.planalp.data.repository.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {

    private val noteDao = NoteDatabase.getDatabase(
        application
    ).noteDao()
    private val repository: NoteRepository = NoteRepository(noteDao)

    val getAllData: LiveData<List<NoteData>> = repository.getAllData
    val sortByHighPriority: LiveData<List<NoteData>> = repository.sortByHighPriority
    val sortByLowPriority: LiveData<List<NoteData>> = repository.sortByLowPriority

    fun insertData(noteData: NoteData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertData(noteData)
        }
    }

    fun updateData(noteData: NoteData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateData(noteData)
        }
    }

    fun deleteItem(noteData: NoteData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteItem(noteData)
        }
    }

    fun deleteAll() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAll()
        }
    }

    fun searchDatabase(searchQuery: String): LiveData<List<NoteData>> {
        return repository.searchDatabase(searchQuery)
    }

}