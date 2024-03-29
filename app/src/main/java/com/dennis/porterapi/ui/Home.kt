@file:Suppress("DEPRECATION")

package com.dennis.porterapi.ui

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.dennis.porterapi.adapters.CharactersAdapter
import com.dennis.porterapi.data.Characters
import com.dennis.porterapi.databinding.FragmentHomeBinding
import com.dennis.porterapi.network.PotterApi
import com.dennis.porterapi.repository.CharactersRepository
import com.dennis.porterapi.viewmodel.CharactersModelFactory
import com.dennis.porterapi.viewmodel.CharactersViewModel

class Home : Fragment() {
    private val apiService = PotterApi
    private var binding: FragmentHomeBinding? = null
    private val viewModel: CharactersViewModel by activityViewModels{
        CharactersModelFactory(CharactersRepository(apiService))
    }

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
        setUpRecycler()
        onFabClick()
    }
    private fun setUpRecycler(){
        val adapter = CharactersAdapter { character -> adapterOnClick(character) }
        binding?.recyclerListCharacters?.adapter = adapter
        viewModel.isLoading.observe(this.viewLifecycleOwner) { isLoading ->
            binding?.progressBar?.visibility =
                if (isLoading)
                    View.VISIBLE
                else
                    View.GONE
        }
        if (isNetworkAvailable()) {
            viewModel.characters.observe(this.viewLifecycleOwner) { characters ->
                characters.let {
                    adapter.submitList(it as MutableList<Characters>)
                }
            }
        } else {
            Toast.makeText(requireContext(), "No internet connection", Toast.LENGTH_LONG).show()
        }
    }

    private fun adapterOnClick(character: Characters) {
        val name = character.name
        val species = character.species
        val gender = character.gender
        val house = character.house
        val alias = character.alias
        val hairColor = character.hairColour
        val eyeColor = character.eyeColour
        val patronus = character.patronus ?: "info not available"
        val actor = character.actor
        val ancestry = character.ancestry
        val dateOfBirth = character.dateOfBirth ?: "info not available"
        val imagePath = character.image
        val action = HomeDirections.actionHome2ToDetails(
            name,
            alias.toString(),
            house,
            species,
            gender,
            dateOfBirth,
            ancestry,
            patronus,
            hairColor,
            eyeColor,
            actor,
            imagePath
        )
        findNavController().navigate(action)
    }

    private fun onFabClick() {
        binding?.fabSearch?.setOnClickListener {
            findNavController().navigate(HomeDirections.actionHomePageToSearchFragment())
        }
    }

    private fun isNetworkAvailable(): Boolean {
        val connectivityManager =
            activity?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}