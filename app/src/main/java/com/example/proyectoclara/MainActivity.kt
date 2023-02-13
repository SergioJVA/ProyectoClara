package com.example.proyectoclara

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.core.view.*
import com.example.proyectoclara.Data.ItemsUsuarios
import com.example.proyectoclara.databinding.ActivityInicioBinding
import com.example.proyectoclara.databinding.ActivityMainBinding
import com.example.proyectoclara.databinding.ActivityRegistroBinding
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var binding1 : ActivityInicioBinding
    lateinit var binding2 : ActivityRegistroBinding
    lateinit var binding3 : Usuarios
    val auth = FirebaseAuth.getInstance()
    val currentUser = auth.currentUser
    val uid = currentUser?.uid
    val db = FirebaseFirestore.getInstance()

   //var botonInvisible = findViewById<Button>(R.id.mostrarUsuario)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding1 = ActivityInicioBinding.inflate(layoutInflater)
        binding2 = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val db = Firebase.firestore

        binding.sesion.setOnClickListener{
            // Al pulsar sobre el botón INICIAR SESION, comprobamos autentificacion
            //pasandole a Firebase el correo y la contraseña
            login()

        }



        binding.botonOlvidado.setOnClickListener{
            startActivity(Intent(this,OlvidoPasswordActivity::class.java))
        }
    }

    private fun login() {
        // Si el correo y el password no son campos vacios:
        if (binding.correo.text.isNotEmpty() && binding.EscribirContrasena.text.isNotEmpty()){
            // Iniciamos sesion con el método signIn y enviamos a Firebase el correo y la contraseña
            FirebaseAuth.getInstance().signInWithEmailAndPassword(
                binding.correo.text.toString(),
                binding.EscribirContrasena.text.toString()
            )
                .addOnCompleteListener{
                    // Si la autenticacion tuvo exito:
                    if (it.isSuccessful){
                        db.collection("usuarios").document(binding.correo.toString()).get()
                            .addOnSuccessListener { documentSnapshot ->
                                if (documentSnapshot.exists()) {
                                    val privilegios = documentSnapshot["privilegios"].toString()
                                    if (privilegios == "admin") {
                                        binding1.mostrarUsuario.visibility = View.VISIBLE
                                    }
                                }
                            }
                        // Obtengo los datos de la base de datos y cambio de activity
                        val intent = Intent(this, InicioActivity::class.java)
                        startActivity(intent)
                    }
                    else {
                                Toast.makeText(this,"Correo o contraseña incorrecta", Toast.LENGTH_SHORT).show()
                    }

                }


        }
        else {
            Toast.makeText(this,"Algún campo está vacío", Toast.LENGTH_SHORT).show()
        }
    }






}