package com.john.firstmenubasic.dialogues


import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.ImageView
import androidx.fragment.app.DialogFragment
import com.john.firstmenubasic.R
import com.john.firstmenubasic.models.Restaurant

class DialogEditRestaurant(
    val pos: Int,
    val restaurant: Restaurant,
    var onEditRestaurantDialogListener: EditRestaurantDialogListener
) : DialogFragment() {

    private lateinit var newNameEditText: EditText
    private lateinit var newCityEditText: EditText
    private lateinit var newProvinceEditText: EditText
    private lateinit var newPhoneNumberEditText: EditText
    private lateinit var newImageUrlEditText: EditText
    private lateinit var imgRestaurant: ImageView

    interface EditRestaurantDialogListener {
        fun onDialogPositiveClick(pos: Int, newName: String, newCity: String,
                                  newProvince: String, newPhoneNumber: String, newImageUrl: String)
        fun onDialogNegativeClick()
    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view = LayoutInflater.from(requireContext()).inflate(
            R.layout
            .edit_restaurant_dialog_listener, null)
        newNameEditText = view.findViewById(R.id.editTextNewName)
        newCityEditText = view.findViewById(R.id.editTextNewCity)
        newProvinceEditText = view.findViewById(R.id.editTextNewProvince)
        newPhoneNumberEditText = view.findViewById(R.id.editTextNewPhoneNumber)
        newImageUrlEditText = view.findViewById(R.id.editTextNewImageUrl)

        // Prellenar los campos con los valores actuales del restaurante
        newNameEditText.setText(restaurant.name)
        newCityEditText.setText(restaurant.city)
        newProvinceEditText.setText(restaurant.province)
        newPhoneNumberEditText.setText(restaurant.phone)
        newImageUrlEditText.setText(restaurant.image)

        return AlertDialog.Builder(requireContext())

            .setView(view)
            .setTitle("Editar restaurante")
            .setPositiveButton("Guardar") { dialog, id ->
                val newName = newNameEditText.text.toString().trim()
                val newCity = newCityEditText.text.toString().trim()
                val newProvince = newProvinceEditText.text.toString().trim()
                val newPhoneNumber = newPhoneNumberEditText.text.toString().trim()
                val newImageUrl = newImageUrlEditText.text.toString().trim()
                onEditRestaurantDialogListener?.onDialogPositiveClick(pos, newName, newCity,
                    newProvince, newPhoneNumber, newImageUrl)
            }
            .setNegativeButton("Cancelar") { dialog, id ->
                onEditRestaurantDialogListener?.onDialogNegativeClick()
                dialog.dismiss()
            }
            .create()
    }

    fun setEditRestaurantDialogListener(listener: EditRestaurantDialogListener) {
        onEditRestaurantDialogListener = listener
    }
}
