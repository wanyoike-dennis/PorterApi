package com.dennis.porterapi.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.dennis.porterapi.R
import com.dennis.porterapi.databinding.FragmentDetailsBinding


class Details : Fragment() {

    private var binding: FragmentDetailsBinding? = null
    private val args: DetailsArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding = FragmentDetailsBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadData()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun loadData() {
        binding?.apply {
            nameTextView.text = args.name
            alternateName.text= args.alias
            speciesTextView.text = args.species
            genderTextView.text = args.gender
            houseTextView.text = args.house
            hairColorTextView.text=args.hairColor
            eyeColorTextView.text=args.eyeColor
            ancestryTextView.text=args.ancestry
            actorTextView.text=args.actor
            dateOfBirthTextView.text = args.dob
            val imageUrl = args.imagePath
            Glide.with(requireActivity())
                .load(imageUrl)
                .centerCrop()
                .placeholder(R.drawable.loading_img)
                .error(R.drawable.ic_connection_error)
                .into(imageView)
        }
    }

}