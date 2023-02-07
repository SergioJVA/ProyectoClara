package com.example.proyectoclara

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.example.proyectoclara.databinding.ActivityInicioBinding
import com.example.proyectoclara.databinding.ActivityMainBinding
import com.example.proyectoclara.databinding.ActivityMostrarUsuariosBinding
import com.example.proyectoclara.databinding.ActivityOlvidoPasswordBinding
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth

class OlvidoPasswordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding = ActivityOlvidoPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.emailOlvidado.setOnClickListener{
            var comprobacionEmail = binding.textEmail.text.toString()
            if (comprobacionEmail.isNotEmpty()){
                enviarEmail(comprobacionEmail)
            }

        }


}
    fun enviarEmail(email: String) {
        FirebaseAuth.getInstance().sendPasswordResetEmail(email)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "Email enviado.")
                } else {
                    Log.e(TAG, "Fallo al enviar el email.")
                }
            }
    }




}

