package com.udacity.shoestore.screens

import android.os.Bundle
import android.view.*
import androidx.core.view.MenuProvider
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeListBinding

class ShoeListFragment : Fragment(), MenuProvider {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentShoeListBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_shoe_list, container, false
        )

        activity?.addMenuProvider(this,viewLifecycleOwner, Lifecycle.State.RESUMED)
//        binding.flyAddButton.setOnClickListener { view: View ->
//            view.findNavController().navigate(R.id.action_shoeListFragment_to_shoeDetailFragment)
//        }
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