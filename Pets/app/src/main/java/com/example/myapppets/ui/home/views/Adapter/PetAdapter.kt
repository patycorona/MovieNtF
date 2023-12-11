package com.example.myapppets.ui.home.views.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.myapppets.R
import com.example.myapppets.domian.model.PetModel

class PetAdapter(
    private val dataSource: MutableList<PetModel>,
    var onListHitItemClickListener: ((petsModel: PetModel) -> Unit),
    val context: Context,
    val onItemClickToShare: ((petModel: PetModel) -> Unit)
) : RecyclerView.Adapter<PetAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var title: TextView = view.findViewById(R.id.tv_Name_pet) as TextView
        var desc: TextView = view.findViewById(R.id.tv_description_pet) as TextView
        var image: ImageView = view.findViewById(R.id.img_pet) as ImageView
        var toShare: TextView = view.findViewById(R.id.tv_Compartir) as TextView
        var root: ConstraintLayout =
            view.findViewById(R.id.layout_item_pet) as ConstraintLayout
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.fragment_item_pet, viewGroup, false)
         return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.title.text = dataSource[position].name
        viewHolder.desc.text = dataSource[position].raza


        Glide.with(context)
            .load(dataSource[position].image)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .centerCrop()
            .circleCrop()
            .into(viewHolder.image)

        viewHolder.toShare.setOnClickListener {
            onItemClickToShare.invoke(dataSource[position])
        }
        viewHolder.root.setOnClickListener {
            onListHitItemClickListener.invoke(dataSource[position])
        }
    }

    override fun getItemCount() = dataSource.size

}