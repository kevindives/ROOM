package com.magicworld.room.ui.add

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.magicworld.room.model.User
import com.magicworld.room.viewmodel.UserViewModel
import com.magicworld.room.databinding.FragmentAddBinding

class AddFragment : Fragment() {

    private  lateinit var addFragmentBinding : FragmentAddBinding
    private val mUserViewModel: UserViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        addFragmentBinding = FragmentAddBinding.inflate(inflater, container, false)
        return addFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addFragmentBinding.addButton.setOnClickListener{
            insertDataToDatabase()
        }
    }

    private fun insertDataToDatabase() {
        with(addFragmentBinding){
            val firstName = addFirstnameTil.text.toString()
            val lastName = addLastnameTil.text.toString()
            val age = addAgeTil.text.toString()
            if (firstName.isEmpty()|| lastName.isEmpty()|| age.isEmpty()){
                Toast.makeText(context, "Please fill out all fields.", Toast.LENGTH_SHORT).show()

            }else{
                //Create User Object
                val user  = User(0,firstName,lastName, Integer.parseInt(age))
                //Add Data to Database
                mUserViewModel.addUser(user)
                Toast.makeText(context, "Successfully added!", Toast.LENGTH_SHORT).show()
                //Navigate Back
                findNavController().navigate(AddFragmentDirections.actionAddFragmentToListFragment())
            }
        }
    }

}