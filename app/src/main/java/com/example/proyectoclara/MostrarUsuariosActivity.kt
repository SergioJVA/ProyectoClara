package com.example.proyectoclara

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Adapter
import com.example.proyectoclara.databinding.ActivityInicioBinding
import com.example.proyectoclara.databinding.ActivityMostrarUsuariosBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class MostrarUsuariosActivity() : AppCompatActivity() {

    private lateinit var binding: ActivityMostrarUsuariosBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inflamos el binding
        binding = ActivityMostrarUsuariosBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}