package com.udacity.shoestore.screens

import android.os.Bundle
import android.view.*
import androidx.core.view.MenuProvider
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.databinding.ViewShoeBinding
import com.udacity.shoestore.viewmodel.ShoeViewModel

class ShoeListFragment : Fragment(), MenuProvider {

    private val shoeVewModel: ShoeViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentShoeListBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_shoe_list, container, false
        )

        activity?.addMenuProvider(this,viewLifecycleOwner, Lifecycle.State.RESUMED)
        binding.flyAddButton.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_shoeListFragment_to_shoeDetailFragment)
        }

        binding.shoeViewModel = shoeVewModel
        binding.lifecycleOwner = this

        shoeVewModel.shoesList.observe(viewLifecycleOwner) { shoesList ->
            for (shoe in shoesList) {
                val shoeBinding: ViewShoeBinding = DataBindingUtil.inflate(
                    layoutInflater, R.layout.view_shoe, container, false
                )
                shoeBinding.shoe = shoe
                binding.shoeListLinearLayout.addView(shoeBinding.root)
            }
        }

        return binding.root
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.menu, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
            R.id.loginFragment -> {
                findNavController().navigate(R.id.action_shoeListFragment_to_loginFragment)
                true
            }
            else -> false
        }
    }

}