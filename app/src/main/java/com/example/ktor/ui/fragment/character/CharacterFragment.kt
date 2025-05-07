package com.example.ktor.ui.fragment.character

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ktor.databinding.FragmentCharacterBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CharacterFragment : Fragment() {

    private var binding: FragmentCharacterBinding? = null
    private var viewModel: CharacterViewModel? = null
    private var adapter: CharacterAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCharacterBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        setUpUI()
        binding?.recyclerCharacters?.layoutManager = LinearLayoutManager(requireContext())

    }

    private fun initViewModel(){
        viewModel = ViewModelProvider(this)[CharacterViewModel::class.java]
    }

    private fun setUpUI() {
        lifecycleScope.launch {
            viewModel?.characters?.collect { character ->
                character?.let {
                    adapter = CharacterAdapter(it.results)
                    binding?.recyclerCharacters?.adapter = adapter
                }
            }
        }
    }

}