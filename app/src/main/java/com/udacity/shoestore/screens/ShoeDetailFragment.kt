package com.udacity.shoestore.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.viewmodel.ShoeDetailViewModel
import com.udacity.shoestore.viewmodel.ShoeViewModel

class ShoeDetailFragment : Fragment() {
    private val shoeViewModel : ShoeViewModel by activityViewModels()
    private val shoeDetailViewMode : ShoeDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentShoeDetailBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_shoe_detail, container, false
        )

        binding.shoeDetailViewModel = shoeDetailViewMode
        binding.lifecycleOwner = this

        binding.saveButton.setOnClickListener {
            if (shoeDetailViewMode.validateFields()) {
                val shoe = shoeDetailViewMode.SaveShoe()
                shoeViewModel.addShoe(shoe)
                navigateToShoeList()
            } else {
                Toast.makeText(context, getString(R.string.empty_fields_warning), Toast.LENGTH_SHORT).show()
            }
        }

        binding.cancelButton.setOnClickListener {
            findNavController().popBackStack()
        }

        return binding.root
    }

    private fun navigateToShoeList() {
        this.findNavController().navigate(R.id.action_shoeDetailFragment_to_shoeListFragment)
    }

}