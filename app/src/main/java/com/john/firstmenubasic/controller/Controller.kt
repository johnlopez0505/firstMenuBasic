package com.john.firstmenubasic.controller



import android.content.Context
import com.john.firstmenubasic.adapter.AdapterRestaurant
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.john.firstmenubasic.MainActivity
import com.john.firstmenubasic.R
import com.john.firstmenubasic.dao.DaoRestaurant
import com.john.firstmenubasic.dialogues.DialogDeleteRestaurant
import com.john.firstmenubasic.dialogues.DialogEditRestaurant
import com.john.firstmenubasic.dialogues.DialogNewRestaurant
import com.john.firstmenubasic.fragments.RestaurantFragmentDirections
import com.john.firstmenubasic.models.Restaurant

class Controller(private val context: Context): DialogEditRestaurant.EditRestaurantDialogListener{
    lateinit var listRestaurants : MutableList<Restaurant> //lista de objetos
    lateinit var navController : NavController
    init {
        initData()

    }
    fun initData(){
        // listHotels = DaoHotels2.myDao.toMutableList()
        listRestaurants = DaoRestaurant. myDao.getDataRestaurant(). toMutableList() //llamamos al singleton.

    }
    fun loggOut() {
        Toast.makeText( context, "He mostrado los datos en pantalla", Toast. LENGTH_LONG).show()
        listRestaurants.forEach{
            println (it)
        }
    }
    /*
    fun setAdapter() {

    }
    */



    // Cargamos nuestro AdapterHotgel al adapter del RecyclerView

    fun iniciar(recyclerView : RecyclerView) {
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = AdapterRestaurant(listRestaurants,
            { pos -> delRestaurant(pos,recyclerView) }, { pos -> updateRestaurant(pos,recyclerView)},
            {pos -> sendInfo(pos)})

    }
    fun sendInfo(pos: Int){

        val myActivity = context as MainActivity
        val navHost = myActivity //referencia del activity
            .supportFragmentManager //administrador de Fragmentos
            .findFragmentById(R.id.nav_host_fragment_content_main)

        navHost.let {//Si entramos dentro, no es nulo.
            navController = navHost!!.findNavController() //buscamos su NavController
            val (name,city,province,phone,image) = listRestaurants[pos]

            navController.navigate(
                RestaurantFragmentDirections.actionNavRestaurantToNavInformation(
                    data = arrayOf(
                        "$pos",
                        name,
                        city,
                        province,
                        phone,
                        image
                    )
                )
            )
        }
        Toast.makeText(context, "Este es el restaurante ${listRestaurants[pos].name}" +
                " de la posición $pos", Toast.LENGTH_LONG).show()


    }

    fun delRestaurant(pos: Int, recyclerView: RecyclerView) {
        // Llamada para mostrar el diálogo de eliminación
        mostrarDialogoEliminarRestaurante(pos,recyclerView)

    }

    fun updateRestaurant(pos: Int, recyclerView: RecyclerView) {
        //llamada para mostrar el dialogo de edición
        mostrarDialogoEditarRestaurante(pos,recyclerView)
    }


    fun addRestaurant(recyclerView: RecyclerView) {
        mostrarDialogoNewRestaurant(recyclerView)
    }

    // Nueva función para mostrar el diálogo de eliminación
    private fun mostrarDialogoEliminarRestaurante(pos: Int, recyclerView: RecyclerView) {
        val dialog = DialogDeleteRestaurant(pos, listRestaurants[pos].name, this)
        dialog.setDeleteRestaurantDialogListener(object :
            DialogDeleteRestaurant.DeleteRestaurantDialogListener {
            override fun onDialogPositiveClick(pos: Int) {
                val adapter = recyclerView.adapter  as AdapterRestaurant
                Toast.makeText(context, "Borrado el Restaurante ${listRestaurants[pos].name}" +
                        " de la posición $pos", Toast.LENGTH_LONG).show()
                listRestaurants.removeAt(pos)
                // Notificar al adaptador sobre el cambio
                adapter.notifyItemRemoved(pos)
                adapter.notifyDataSetChanged()
            }

            override fun onDialogNegativeClick() {
                Toast.makeText(context, "Cancelado el borrado de  " +
                        "${listRestaurants[pos].name} de la posición $pos", Toast.LENGTH_LONG).show()
            }
        })
        dialog.show((context as AppCompatActivity).supportFragmentManager, "DialogDeleteRestaurant")
    }

    private fun mostrarDialogoEditarRestaurante(pos: Int, recyclerView: RecyclerView) {
        if (pos in 0 until listRestaurants.size) {
            val dialog = DialogEditRestaurant(pos, listRestaurants[pos], this)
            dialog.setEditRestaurantDialogListener(object :
            DialogEditRestaurant.EditRestaurantDialogListener{
                override fun onDialogPositiveClick(
                    pos: Int,
                    newName: String,
                    newCity: String,
                    newProvince: String,
                    newPhoneNumber: String,
                    newImageUrl: String
                ) {

                    // Lógica para guardar la edición del restaurante
                    val adapter = recyclerView.adapter as AdapterRestaurant
                    val editedRestaurant = Restaurant(newName, newCity, newProvince,
                        newPhoneNumber, newImageUrl)
                    listRestaurants[pos] = editedRestaurant
                    adapter.notifyItemChanged(pos)
                    Toast.makeText(context, "Restaurante editado correctamente",
                        Toast.LENGTH_LONG).show()
                }

                override fun onDialogNegativeClick() {
                    // Acciones después de que el usuario hace clic en "Cancelar"
                    Toast.makeText(context, "Edición del restaurante cancelada",
                        Toast.LENGTH_LONG).show()
                }

            })

            dialog.show((context as AppCompatActivity).supportFragmentManager,
                "DialogEditRestaurant")
        } else {
            // Manejar el caso en el que pos no es válido
            Toast.makeText(context, "Posición no válida", Toast.LENGTH_SHORT).show()
        }
    }

    private fun mostrarDialogoNewRestaurant(recyclerView: RecyclerView){
        val dialog = DialogNewRestaurant(object : DialogNewRestaurant.NewRestaurantDialogListener {
            override fun onDialogPositiveClick(
                newName: String,
                newCity: String,
                newProvince: String,
                newPhoneNumber: String,
                newImageUrl: String
            ) {
                // agregamos un nuevo restaurante
                val newRestaurant = Restaurant(newName, newCity, newProvince, newPhoneNumber,
                    newImageUrl)
                listRestaurants.add(newRestaurant)

                // Notificamos al adaptador sobre el cambio
                val adapter = recyclerView.adapter as AdapterRestaurant
                adapter.notifyItemInserted(listRestaurants.size - 1)

                Toast.makeText(context, "Nuevo restaurante agregado", Toast.LENGTH_LONG).show()
            }

            override fun onDialogNegativeClick() {
                // Acciones después de que el usuario hace clic en "Cancelar"
                Toast.makeText(context, "Cancelada la creacion de un nuevo Restaurante",
                    Toast.LENGTH_LONG).show()
            }
        })

        dialog.show((context as AppCompatActivity).supportFragmentManager, "DialogNewRestaurant")
    }

    override fun onDialogPositiveClick(
        pos: Int,
        newName: String,
        newCity: String,
        newProvince: String,
        newPhoneNumber: String,
        newImageUrl: String
    ) {
        TODO("Not yet implemented")
    }

    override fun onDialogNegativeClick() {
        TODO("Not yet implemented")
    }

}

