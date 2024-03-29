package com.john.firstmenubasic.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.john.firstmenubasic.databinding.FragmentInformationBinding


class InformationFragment : Fragment() {
    private var _binding: FragmentInformationBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInformationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val myArgument : InformationFragmentArgs by navArgs()

        val (pos,name,city,province,phone,image) = myArgument.data
        binding.textTitle.text = "Elegiste el Restaurante con id ${pos}"
        binding.itemRestaurante.txtviewName.text     = name
        binding.itemRestaurante.txtviewCity.text     = city
        binding.itemRestaurante.txtviewProvince.text = province
        binding.itemRestaurante.txtviewPhone.text    = phone
        Glide.with(requireView().context).load(image).centerCrop().into(binding.itemRestaurante.ivRestaurant)
        Toast.makeText(context, "Este es el restaurante $name" +
                " de la posición $pos", Toast.LENGTH_LONG).show()
    }


}
// Definimos la función component6 para arrays
private operator fun <T> Array<T>.component6(): T {
    if (this.size >= 6) {
        return this[5]  // Obtener el sexto elemento del array (0-indexed)
    } else {
        throw IndexOutOfBoundsException("El array no tiene al menos 6 elementos.")
    }
}


