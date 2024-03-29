package com.john.firstmenubasic.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.john.firstmenubasic.R
import com.john.firstmenubasic.models.Restaurant

class AdapterRestaurant(
    private var listRestaurant : MutableList<Restaurant>,
    private var deleteOnClick: (Int) -> Unit,
    private var updateOnClick: (Int) -> Unit,
    private var sendInfo:      (Int) -> Unit
) : RecyclerView.Adapter<ViewHRestaurant>(){
    /*
    Método que crea la view del ViewHolderHotel
    */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHRestaurant {
        val layoutInflater = LayoutInflater.from(parent. context)//objeto para crear la vista.
        val layoutItemRestaurant = R.layout.item_restaurant //accedo al xml del item a crear.
        return ViewHRestaurant(
            layoutInflater.inflate(layoutItemRestaurant, parent, false),
            deleteOnClick,
            updateOnClick,
            sendInfo
        )

    }
    /*
    Este método, debe renderizar todos los datos o propiedades de cada hotel con la view.
    Accedemos al objeto por medio de position
    */
    override fun onBindViewHolder(holder: ViewHRestaurant, position: Int) {
        holder.renderize(listRestaurant[position]) //renderizamos la view.
        //holder.setOnClickListener(position)
    }
    /*
    Este método, devuelve el número de objetos a representar en el recyclerView.
    */
    override fun getItemCount(): Int = listRestaurant.size
}