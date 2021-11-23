package com.example.planalp.data.converter

import androidx.room.TypeConverter
import com.example.planalp.data.models.NotePriority
class PriorityConverter {
    @TypeConverter
    fun fromPriority(notePriority: NotePriority): String {
        return notePriority.name
    }
    @TypeConverter
    fun toPriority(priority: String): NotePriority {
        return NotePriority.valueOf(priority)
    }
}