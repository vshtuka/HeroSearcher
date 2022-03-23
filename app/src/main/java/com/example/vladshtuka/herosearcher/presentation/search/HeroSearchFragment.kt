package com.example.vladshtuka.herosearcher.presentation.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.vladshtuka.herosearcher.R
import com.example.vladshtuka.herosearcher.databinding.FragmentHeroSearchBinding
import com.example.vladshtuka.herosearcher.presentation.adapter.HeroAdapter
import com.example.vladshtuka.herosearcher.presentation.adapter.HeroListener
import com.example.vladshtuka.herosearcher.presentation.adapter.LikeListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HeroSearchFragment : Fragment() {

    private lateinit var binding: FragmentHeroSearchBinding
    private val viewModel: HeroSearchViewModel by viewModels()
    private lateinit var adapter: HeroAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_hero_search, container, false
        )
        initRecyclerView()
        initHeroSearch()
        setUpObservers()

        return binding.root
    }

    private fun setUpObservers() {
        viewModel.navigateToHeroDetail.observe(viewLifecycleOwner) { hero ->
            hero?.let {
                this.findNavController().navigate(
                    HeroSearchFragmentDirections
                        .actionHeroSearchFragmentToHeroInformationFragment(hero)
                )
                viewModel.onHeroDetailNavigated()
            }
        }

        viewModel.matchedHeroesList.observe(viewLifecycleOwner) { heroesList ->
            adapter.submitList(heroesList)
        }
    }

    private fun initRecyclerView() {
        adapter = HeroAdapter((HeroListener { hero ->
            viewModel.onHeroClicked(hero)
        }), LikeListener { hero, like ->
            if (hero.isLiked) {
                like?.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                viewModel.deleteHeroFromFavorites(hero)
            } else {
                like?.setImageResource(R.drawable.ic_baseline_favorite_24)
                viewModel.insertHeroToFavorites(hero)
            }
        })
        binding.heroSearchRecyclerView.adapter = adapter
    }

    private fun initHeroSearch() {
        binding.heroSearchEditText.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(
                sequence: CharSequence,
                start: Int,
                before: Int,
                count: Int
            ) {
                viewModel.getMatchedNames(sequence.toString())
            }
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable) {}
        })
    }

}