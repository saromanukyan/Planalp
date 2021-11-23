package com.example.planalp.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.planalp.data.models.NoteData

@Dao
interface NoteDao {
    @Query("SELECT * FROM note_table ORDER BY id ASC")
    fun getAllData(): LiveData<List<NoteData>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertData(noteData: NoteData)

    @Update
    suspend fun updateData(noteData: NoteData)

    @Delete
    suspend fun deleteItem(noteData: NoteData)

    @Query("DELETE FROM note_table")
    suspend fun deleteAll()

    @Query("SELECT * FROM note_table WHERE title LIKE :searchQuery")
    fun searchDatabase(searchQuery: String): LiveData<List<NoteData>>

    @Query("SELECT * FROM note_table ORDER BY CASE WHEN notePriority LIKE 'H%' THEN 1 WHEN notePriority LIKE 'M%' THEN 2 WHEN notePriority LIKE 'L%' THEN 3 END")
    fun sortByHighPriority(): LiveData<List<NoteData>>

    @Query("SELECT * FROM note_table ORDER BY CASE WHEN notePriority LIKE 'L%' THEN 1 WHEN notePriority LIKE 'M%' THEN 2 WHEN notePriority LIKE 'H%' THEN 3 END")
    fun sortByLowPriority(): LiveData<List<NoteData>>

}