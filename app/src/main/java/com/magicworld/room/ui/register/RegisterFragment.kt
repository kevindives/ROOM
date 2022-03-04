package com.magicworld.room.ui.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.magicworld.room.databinding.RegisterFragmentBinding
import com.magicworld.room.utils.isEmailValid

class RegisterFragment : Fragment() {

    private lateinit var registerBinding: RegisterFragmentBinding
    private val viewModel: RegisterViewModel by viewModels()
    private lateinit var firstname: String
    private lateinit var lastname: String
    private lateinit var age: String
    private lateinit var email: String
    private lateinit var password: String


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        registerBinding = RegisterFragmentBinding.inflate(inflater, container, false)
        return registerBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.onUserRegistered.observe(viewLifecycleOwner){result ->
            onUserRegisteredSubscribe(result)
        }
        viewModel.onCreateUserInDatabase.observe(viewLifecycleOwner){result ->
            onCreateUserInDatabaseSubscribe(result)
        }

        with(registerBinding){

            registerButton.setOnClickListener{
                firstname = usernameEditText.text.toString()
                lastname = lastnameEditText.text.toString()
                age = ageEditText.text.toString()
                email = emailEditText.text.toString()
                password = passwordEditText.text.toString()

               if (firstname.isEmpty() || lastname.isEmpty() || age.isEmpty() || email.isEmpty() || password.isEmpty() )
                   Toast.makeText(context,"Debe digitar todos los campos", Toast.LENGTH_SHORT).show()
               else
                   if (isEmailValid(email))
                       viewModel.registerUser(email,password)
                   else
                       Toast.makeText(context, "El correo no tiene un formato válido", Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun onUserRegisteredSubscribe(result: String?) {
        if (result.equals("Usuario registrado exitosamente"))
            viewModel.createUserInDataBase(firstname, lastname, age)
        else
            Toast.makeText(context, result, Toast.LENGTH_SHORT).show()
    }
    private fun onCreateUserInDatabaseSubscribe(result: String?) {
        Toast.makeText(context, result, Toast.LENGTH_SHORT).show()
        if (result.equals("Usuario creado de forma exitosa"))
            findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToLoginFragment())
    }
}