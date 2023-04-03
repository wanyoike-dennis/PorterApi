package com.dennis.porterapi.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.dennis.porterapi.adapters.RecyclerAdapter
import com.dennis.porterapi.data.Characters
import com.dennis.porterapi.databinding.FragmentSearchBinding
import com.dennis.porterapi.viewmodel.CharactersViewModel

class SearchFragment : Fragment() {

    private val viewModel : CharactersViewModel by activityViewModels()
    private var binding: FragmentSearchBinding?= null
  //  private  lateinit var characters : ArrayList<Characters>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding = FragmentSearchBinding.inflate(inflater,container,false)
        binding=fragmentBinding
        return fragmentBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        loadListComponents()

    }
    override fun onDestroyView() {
        super.onDestroyView()
        binding=null
    }

    private fun loadListComponents(){
        val adapter= RecyclerAdapter{ character -> adapterOnClick(character)}
        binding?.recyclerView?.adapter= adapter

        binding?.searchView?.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                binding?.searchView?.clearFocus()
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
               adapter.filter.filter(newText)
                return false
            }

        })
        viewModel.charactersInHouse.observe(this.viewLifecycleOwner) { characters ->
            adapter.setCharacters(characters)
        }
    }
     private fun adapterOnClick(character:Characters){
         val name = character.name
         val species = character.species
         val gender = character.gender
         val house = character.house
         val alias = character.alias
         val hairColor = character.hairColour
         val eyeColor = character.eyeColour
         val patronus = character.patronus   ?: "info not available"
         val actor= character.actor
         val ancestry= character.ancestry
         val dateOfBirth= character.dateOfBirth ?: "info not available"
         val imagePath = character.image

         val action = SearchFragmentDirections.actionSearchFragmentToDetails(
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

}