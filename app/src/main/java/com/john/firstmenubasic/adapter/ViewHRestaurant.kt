package com.john.firstmenubasic.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.john.firstmenubasic.databinding.ItemRestaurantBinding
import com.john.firstmenubasic.models.Restaurant

class ViewHRestaurant(
    view: View, var deleteOnClick:
        (Int) -> Unit, var updateOnClick: (Int) -> Unit, var sendInfo: (Int) -> Unit
):
    RecyclerView.ViewHolder (view){

    private lateinit var binding: ItemRestaurantBinding
    init {
        binding = ItemRestaurantBinding.bind(view)
    }
    //método que se encarga de mapear los item por propiedad del modelo.
    fun renderize(restaurant : Restaurant){
        binding.txtviewName.setText(restaurant. name)
        binding.txtviewCity.setText(restaurant. city)
        binding.txtviewProvince.setText(restaurant. province)
        binding.txtviewPhone.setText(restaurant. phone)
        Glide
            .with( itemView.context)
            .load(restaurant. image)
            .centerCrop()
            .into( binding.ivRestaurant)

        setOnClickListener(adapterPosition)
    }

    fun setOnClickListener(position : Int) {
        binding.btnEdit.setOnClickListener {
            updateOnClick(position)
        }
        binding.btnDelete.setOnClickListener {
            deleteOnClick(position)
        }
        binding.ivRestaurant.setOnClickListener{
            sendInfo(position)
        }
    }


}