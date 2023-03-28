package com.dennis.porterapi.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dennis.porterapi.databinding.FragmentDetailsBinding

class Details : Fragment() {

    private var binding:FragmentDetailsBinding? = null
    //private lateinit var character: Characters
    // private val viewModel:CharactersViewModel by activityViewModels()
    //   private val args: DetailsArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding = FragmentDetailsBinding.inflate(inflater,container,false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    /*
    private fun loadCharacterData(){
        var currentCharacterId : String? = null
        currentCharacterId = args.characterId
        viewModel.retrieveItem(currentCharacterId).observe(this.viewLifecycleOwner){
            selectedCharacter ->
            character = selectedCharacter
            bind(character)
        }

    }

     */

    /*
    private fun bind(character:Characters){
        binding?.apply {
            txtCharacterName.text = character.name
            txtSpecies.text = character.species
            txtGender.text= character.gender
            txtHouse.text = character.house
            txtDob.text = character.dateOfBirth

            val imageUrl = "@{character.image}"
            Glide.with(imgCharacter).load(imageUrl).into(imgCharacter)
        }

    }

     */

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}