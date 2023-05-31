package com.example.elotitosmzt.ui.fragments

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.elotitosmzt.R
import com.example.elotitosmzt.databinding.FragmentLoginBinding
import com.example.elotitosmzt.ui.activities.MainActivity
import com.google.firebase.auth.FirebaseAuth

class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding= FragmentLoginBinding.bind(view)

        firebaseAuth = FirebaseAuth.getInstance()

        showCorrectEmail()

        binding.btnLogin.setOnClickListener {
            val email = binding.loginTextfieldEmail.editText?.text.toString()
            val password = binding.loginTextfieldPassword.editText?.text.toString()
            if (email.isNotEmpty() && password.isNotEmpty()) {
                firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                    if (it.isSuccessful) {
                        findNavController().navigate(Uri.parse("elotitos://orders"))
                    } else {
                        Toast.makeText(context, it.exception.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            }else{ Toast.makeText(context, "Las campos no pueden quedar vacios", Toast.LENGTH_SHORT).show() }
        }


        binding.btnRegister.setOnClickListener{
            findNavController().navigate(Uri.parse("elotitos://signup"))
        }

    }
    fun showCorrectEmail(){
        val editText_email = binding.loginTextfieldEmail.editText!!
        val editText_password = binding.loginTextfieldPassword.editText!!
        editText_password.setOnFocusChangeListener{_,hasFocus ->
            if (hasFocus){
                editText_email.setSelection(0)
            }
        }
    }
}