package com.example.planalp.ui

import android.app.Application
import android.view.View
import android.widget.AdapterView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.planalp.R
import com.example.planalp.data.models.NoteData
import com.example.planalp.data.models.NotePriority

class FragmentViewModel(application: Application) : AndroidViewModel(application) {

    val emptyDatabase: MutableLiveData<Boolean> = MutableLiveData(false)

    fun checkIfDatabaseEmpty(noteData: List<NoteData>) {
        emptyDatabase.value = noteData.isEmpty()
    }

    val listener: AdapterView.OnItemSelectedListener = object :
        AdapterView.OnItemSelectedListener {
        override fun onNothingSelected(p0: AdapterView<*>?) {}
        override fun onItemSelected(
            parent: AdapterView<*>?,
            view: View?,
            position: Int,
            id: Long
        ) {
            when (position) {
                0 -> {
                    (parent?.getChildAt(0) as TextView).setTextColor(
                        ContextCompat.getColor(
                            application,
                            R.color.dark_orange
                        )
                    )
                }
                1 -> {
                    (parent?.getChildAt(0) as TextView).setTextColor(
                        ContextCompat.getColor(
                            application,
                            R.color.orange
                        )
                    )
                }
                2 -> {
                    (parent?.getChildAt(0) as TextView).setTextColor(
                        ContextCompat.getColor(
                            application,
                            R.color.light_orange
                        )
                    )
                }
            }
        }
    }

    fun verifyDataFromUser(title: String, description: String): Boolean {
        return !(title.isEmpty() || description.isEmpty())
    }

    fun parsePriority(priority: String): NotePriority {
        return when (priority) {
            "High Priority" -> {
                NotePriority.HIGH
            }
            "Medium Priority" -> {
                NotePriority.MEDIUM
            }
            "Low Priority" -> {
                NotePriority.LOW
            }
            else -> NotePriority.LOW
        }
    }

    fun parsePriorityToInt(priority: NotePriority): Int {
        return when (priority) {
            NotePriority.HIGH -> {
                0
            }
            NotePriority.MEDIUM -> {
                1
            }
            NotePriority.LOW -> {
                2
            }
        }
    }

}