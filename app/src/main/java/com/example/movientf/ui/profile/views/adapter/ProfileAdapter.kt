package com.example.movientf.ui.profile.views.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.movientf.databinding.FragmentProfileItemBinding
import com.example.movientf.domain.model.ProfileModel

class ProfileAdapter (
    private val dataSource: MutableList<ProfileModel>,
    var onItemClickListener: ((profileModel: ProfileModel) -> Unit),
    val context: Context
    ) : RecyclerView.Adapter<ProfileAdapter.ViewHolder>(){

    inner class ViewHolder(
        private var binding: FragmentProfileItemBinding,
        private var ctx: Context,
        var onItemClickListener: ((profileModel: ProfileModel) -> Unit)
    ) : RecyclerView.ViewHolder(binding!!.root)
    {
        var root: ConstraintLayout = binding.layoutItemProfile

        fun bind(dataSource: ProfileModel){
            binding.apply {
                tvIdProfile?.text = dataSource.id
                tvProfileName?.text = dataSource.user_name
                Glide.with(context)
                    .load(dataSource.image)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(binding!!.imgProfile)
            }

            binding?.layoutItemProfile?.setOnClickListener {

                onItemClickListener.invoke(
                    dataSource)
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding = FragmentProfileItemBinding.inflate(
            LayoutInflater.from(viewGroup.context),viewGroup,false)
        return ViewHolder(binding, viewGroup.context, onItemClickListener)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val profileModel: ProfileModel = dataSource[position]
        viewHolder.bind(profileModel)
    }

    override fun getItemCount() = dataSource.size

}