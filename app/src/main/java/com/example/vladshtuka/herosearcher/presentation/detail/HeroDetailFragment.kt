package com.example.vladshtuka.herosearcher.presentation.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.vladshtuka.herosearcher.R
import com.example.vladshtuka.herosearcher.databinding.FragmentHeroDetailBinding
import com.example.vladshtuka.herosearcher.domain.model.Hero
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HeroDetailFragment : Fragment() {

    private lateinit var binding: FragmentHeroDetailBinding
    private val viewModel: HeroDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_hero_detail, container, false
        )
        val hero = HeroDetailFragmentArgs.fromBundle(requireArguments()).hero
        getHeroInformation(hero)
        getHeroPhoto(hero)
        initLikeButton(hero)
        handleLikeButton(hero)
        setUpObservers()

        return binding.root
    }

    private fun setUpObservers() {
        viewModel.isLike.observe(viewLifecycleOwner) { isLike ->
            if (isLike) {
                binding.detailHeroLike.setImageResource(R.drawable.ic_baseline_favorite_24)
            } else {
                binding.detailHeroLike.setImageResource(R.drawable.ic_baseline_favorite_border_24)
            }
        }
    }

    private fun initLikeButton(hero: Hero?) {
        viewModel.initLikeButton(hero!!)
    }

    private fun handleLikeButton(hero: Hero?) {
        binding.detailHeroLike.setOnClickListener {
            if (hero!!.isLiked) {
                viewModel.deleteHeroFromFavorites(hero)
            } else {
                viewModel.insertHeroToFavorites(hero)
            }
        }


    }

    private fun getHeroInformation(hero: Hero?) {
        val heroInformation =
            "Name: ${hero?.name}\n" +
                    "Birthday: ${hero?.birthday}\n" +
                    "Status: ${hero?.status}\n" +
                    "Nickname: ${hero?.nickname}\n" +
                    "Portrayed: ${hero?.portrayed}\n" +
                    "Category: ${hero?.category}"
        binding.detailHeroInformation.text = heroInformation
    }

    private fun getHeroPhoto(hero: Hero?) {
        if (hero != null && hero.img.isNotEmpty()) {
            Glide.with(requireContext())
                .load(hero.img)
                .transform(CenterCrop(), RoundedCorners(10))
                .into(binding.detailHeroPhoto)
        }
    }

}