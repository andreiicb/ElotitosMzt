package com.example.elotitosmzt.ui.activities

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.elotitosmzt.R
import com.example.elotitosmzt.databinding.ActivitySignUpBinding
import com.example.elotitosmzt.databinding.FragmentLoginBinding
import com.example.elotitosmzt.ui.fragments.LoginFragment
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth

class SignUpActivity : AppCompatActivity(R.layout.activity_sign_up) {

    private lateinit var binding: ActivitySignUpBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivitySignUpBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.btnSignup.setOnClickListener {
            val user = binding.registerTextfieldUsuario.editText?.text.toString()
            val email = binding.registerTextfieldEmail.editText?.text.toString()
            val password = binding.registerTextfieldPassword.editText?.text.toString()
            val confirmPassword = binding.registerTextfieldPassword2.editText?.text.toString()
            if (user.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty()) {
                if (password == confirmPassword) {
                    firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                        if(it.isSuccessful){
                            val intent = Intent(this,LoginFragment::class.java)
                            startActivity(intent)
                        }else{
                            Toast.makeText(this,it.exception.toString(),Toast.LENGTH_SHORT).show()
                        }
                    }
                }else{
                    Toast.makeText(this,"Las contraseñas no coinciden",Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this,"Las campos no pueden quedar vacios",Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnReturnLogin.setOnClickListener {
            val intent = Intent(this@SignUpActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

}