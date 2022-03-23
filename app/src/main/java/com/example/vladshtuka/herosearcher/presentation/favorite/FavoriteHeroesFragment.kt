package com.example.vladshtuka.herosearcher.presentation.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.vladshtuka.herosearcher.R
import com.example.vladshtuka.herosearcher.databinding.FragmentFavoriteHeroesBinding
import com.example.vladshtuka.herosearcher.presentation.adapter.HeroAdapter
import com.example.vladshtuka.herosearcher.presentation.adapter.HeroListener
import com.example.vladshtuka.herosearcher.presentation.adapter.LikeListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteHeroesFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteHeroesBinding
    private val viewModel: FavoriteHeroesViewModel by viewModels()

    private lateinit var adapter: HeroAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_favorite_heroes, container, false
        )
        initRecyclerView()
        setUpObservers()

        return binding.root
    }

    private fun initRecyclerView() {
        adapter = HeroAdapter((HeroListener { hero ->
            viewModel.onHeroClicked(hero)
        }), LikeListener { hero, _ ->
            viewModel.deleteHeroFromFavorites(hero)
        })
        binding.favoriteHeroRecyclerView.adapter = adapter
    }

    private fun setUpObservers() {
        viewModel.favoriteHeroesList.observe(viewLifecycleOwner) { favoriteHeroesList ->
            adapter.submitList(favoriteHeroesList)
        }

        viewModel.navigateToHeroDetail.observe(viewLifecycleOwner) { favoriteHero ->
            favoriteHero?.let {
                this.findNavController().navigate(
                    FavoriteHeroesFragmentDirections
                        .actionFavoriteHeroesFragmentToHeroInformationFragment(favoriteHero)
                )
                viewModel.onHeroDetailNavigated()
            }
        }
    }

}