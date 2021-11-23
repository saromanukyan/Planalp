package com.example.planalp.ui.add

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.planalp.R
import com.example.planalp.data.models.NoteData
import com.example.planalp.data.viewmodel.NoteViewModel
import com.example.planalp.databinding.FragmentAddBinding
import com.example.planalp.ui.FragmentViewModel

class AddFragment : Fragment() {
    private val noteViewModel: NoteViewModel by viewModels()
    private val fragmentViewModel: FragmentViewModel by viewModels()
    private lateinit var binding: FragmentAddBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddBinding.inflate(layoutInflater, container, false)

        setHasOptionsMenu(true)
        binding.addSpinner.onItemSelectedListener = fragmentViewModel.listener
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add_fragment_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_add) {
            insertDataToDb()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun insertDataToDb() {

        val title = binding.titleEt.text.toString()
        val priority = binding.addSpinner.selectedItem.toString()
        val description = binding.descriptionEt.text.toString()

        val validation = fragmentViewModel.verifyDataFromUser(title, description)
        if (validation) {
            val newData = NoteData(
                0,
                title,
                fragmentViewModel.parsePriority(priority),
                description
            )
            noteViewModel.insertData(newData)
            Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_SHORT).show()

            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "You must fill in all fields.", Toast.LENGTH_SHORT)
                .show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding
    }
}