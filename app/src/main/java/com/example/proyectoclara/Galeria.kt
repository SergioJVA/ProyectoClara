package com.example.proyectoclara

import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ImageButton
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import com.example.proyectoclara.databinding.ActivityGaleriaBinding

class Galeria : AppCompatActivity() {
    lateinit var imagen: ImageButton
    lateinit var binding: ActivityGaleriaBinding
    val pickFoto = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        val image = it.data?.extras?.get("data") as Bitmap
        binding.imageButton.setImageBitmap(image)
    }
    val pickMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()){
        // Devuelve la uri de la imagen seleccionada:
            uri ->
        if (uri!=null){
            //Imagen seleccionada
            imagen.setImageURI(uri)
        }else {
            // No se ha seleccionado ninguna imagen.
        }
    }




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGaleriaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        imagen=binding.imageButton


        // Cuando pulsemos sobre el ImageButton, llamaremos al launcher (pickMedia) para lanzarlo:
        binding.galeria.setOnClickListener{
            pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }
        binding.camara.setOnClickListener{
            pickFoto.launch(Intent(MediaStore.ACTION_IMAGE_CAPTURE))
        }



    }
}