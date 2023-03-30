package com.dennis.porterapi.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.activityViewModels
import com.dennis.porterapi.adapters.MyListAdapter
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
        val dataList = viewModel.characters

        val adapter = dataList.value?.let { MyListAdapter(it) }
        binding?.listView?.adapter = adapter

        viewModel.characters.observe(this.viewLifecycleOwner){
        adapter?.notifyDataSetChanged()
        }



        binding?.searchView?.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                binding?.searchView?.clearFocus()
           /*     if (characters.contains(query)){
                    adapter.filter.filter(query)
                }

            */
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
           //     adapter.filter.filter(newText)
                return false
            }

        })
    }
}