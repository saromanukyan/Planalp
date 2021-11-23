package com.example.planalp.ui.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.planalp.data.models.NoteData
import com.example.planalp.databinding.RowLayoutBinding

class NoteAdapter : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {
    var noteList = emptyList<NoteData>()

    class NoteViewHolder(private val binding: RowLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(noteData: NoteData) {
            binding.noteData = noteData
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): NoteViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = RowLayoutBinding.inflate(layoutInflater, parent, false)
                return NoteViewHolder(
                    binding
                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder.from(
            parent
        )
    }

    override fun getItemCount(): Int {
        return noteList.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentItem = noteList[position]
        holder.bind(currentItem)
    }

    fun setData(noteData: List<NoteData>) {
        val noteDiffUtil = NoteDiffUtil(noteList, noteData)
        val noteDiffResult = DiffUtil.calculateDiff(noteDiffUtil)
        this.noteList = noteData
        noteDiffResult.dispatchUpdatesTo(this)
    }
}