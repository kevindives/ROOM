package com.magicworld.room.ui.login

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.magicworld.room.MainActivity
import com.magicworld.room.databinding.LoginFragmentBinding
import com.magicworld.room.utils.isEmailValid

class LoginFragment : Fragment() {

    private lateinit var loginBinding :LoginFragmentBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (activity as MainActivity).hideIcon()
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        loginBinding = LoginFragmentBinding.inflate(inflater, container, false)
        return loginBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.onUserLoggedIn.observe(viewLifecycleOwner){result ->
            onUserLoggedInSubscribe(result)
        }
        with(loginBinding){
            loginButton.setOnClickListener{
                val email = emailEditText.text.toString()
                val password = passwordEditText.text.toString()
                if (email.isEmpty() || password.isEmpty())
                    Toast.makeText(context, "Debe digitar correo y contraseña", Toast.LENGTH_SHORT).show()
                else{
                    if (isEmailValid(email))
                        viewModel.login(email, password)
                    else
                        Toast.makeText(context, "El correo no tiene un formato válido", Toast.LENGTH_SHORT).show()
                }
            }
            registerTextView.setOnClickListener{
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
            }
        }
    }

    private fun onUserLoggedInSubscribe(result: String?) {
        Toast.makeText(context, result, Toast.LENGTH_SHORT).show()
        if (result.equals("Bienvenido"))
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToListFragment())
    }

}