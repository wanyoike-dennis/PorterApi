package com.dennis.porterapi.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.dennis.porterapi.adapters.CharactersAdapter
import com.dennis.porterapi.data.Characters
import com.dennis.porterapi.databinding.FragmentHomeBinding
import com.dennis.porterapi.viewmodel.CharactersViewModel

class Home : Fragment() {
    private var binding: FragmentHomeBinding? = null
    private val viewModel: CharactersViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding = FragmentHomeBinding.inflate(inflater, container, false)
        binding = fragmentBinding

        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = CharactersAdapter { character -> adapterOnClick(character) }
        binding?.recyclerListCharacters?.adapter = adapter

        viewModel.characters.observe(this.viewLifecycleOwner) { characters ->
            characters.let {
                adapter.submitList(it as MutableList<Characters>)
            }
        }


    }

    private fun adapterOnClick(character: Characters) {
        val name = character.name
        val species = character.species
        val gender = character.gender
        val house = character.house
        val dateOfBirth = character.dateOfBirth
        val imagePath = character.image
        val action = HomeDirections.actionHome2ToDetails(
            name,
            species,
            gender,
            house,
            dateOfBirth,
            imagePath
        )
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}