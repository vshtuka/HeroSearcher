package com.example.vladshtuka.herosearcher.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.vladshtuka.herosearcher.R
import com.example.vladshtuka.herosearcher.databinding.HeroItemBinding
import com.example.vladshtuka.herosearcher.domain.model.Hero

class HeroAdapter(
    private val heroClickListener: HeroListener,
    private val likeClickListener: LikeListener
) : ListAdapter<Hero, HeroAdapter.HeroSearchViewHolder>(HeroDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroSearchViewHolder {
        return HeroSearchViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: HeroSearchViewHolder, position: Int) {
        holder.bind(getItem(position)!!, heroClickListener, likeClickListener)
    }

    class HeroSearchViewHolder constructor(private val binding: HeroItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(hero: Hero, heroClickListener: HeroListener, likeClickListener: LikeListener) {
            binding.hero = hero
            binding.heroName.text = hero.name
            binding.heroClickListener = heroClickListener
            binding.likeClickListener = likeClickListener
            if (hero.isLiked) {
                binding.searchLikeButton.setImageResource(R.drawable.ic_baseline_favorite_24)
            } else {
                binding.searchLikeButton.setImageResource(R.drawable.ic_baseline_favorite_border_24)
            }
            if (hero.img.isNotEmpty()) {
                Glide.with(binding.heroPhoto.context)
                    .load(hero.img)
                    .transform(CenterCrop(), RoundedCorners(10))
                    .into(binding.heroPhoto)
            }
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): HeroSearchViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = HeroItemBinding.inflate(layoutInflater, parent, false)
                return HeroSearchViewHolder(binding)
            }
        }

    }

}

class HeroDiffCallback : DiffUtil.ItemCallback<Hero>() {
    override fun areItemsTheSame(oldItem: Hero, newItem: Hero): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Hero, newItem: Hero): Boolean {
        return oldItem == newItem
    }
}

class HeroListener(val heroClickListener: (hero: Hero?) -> Unit) {
    fun onClick(hero: Hero?) = heroClickListener(hero)
}

class LikeListener(val likeClickListener: (hero: Hero, like: ImageView?) -> Unit) {
    fun onClick(hero: Hero, like: ImageView?) = likeClickListener(hero, like)
}