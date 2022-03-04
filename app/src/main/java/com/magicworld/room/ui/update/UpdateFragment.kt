package com.magicworld.room.ui.update

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.magicworld.room.R
import com.magicworld.room.databinding.FragmentUpdateBinding
import com.magicworld.room.model.User
import com.magicworld.room.viewmodel.UserViewModel

class UpdateFragment : Fragment() {

    private lateinit var updateFragment: FragmentUpdateBinding
    private val args : UpdateFragmentArgs by navArgs()
    private val mUserViewModel: UserViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        updateFragment = FragmentUpdateBinding.inflate(inflater, container, false)
        return updateFragment.root

    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete){
            deleteUser()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        val user = args.currentUser
        with(updateFragment){
            updateFirstnameTil.setText(user.firstName)
            updateLastnameTil.setText(user.lastName)
            updateAgeTil.setText(user.age.toString())
            //call function
            updateBtn.setOnClickListener{
                updateItem()
            }
        }

    }
    private fun updateItem(){
        with(updateFragment){
            val user = args.currentUser
            val firstName = updateFirstnameTil.text.toString()
            val lastName = updateLastnameTil.text.toString()
            val age = updateAgeTil.text.toString()
            if (firstName.isEmpty()|| lastName.isEmpty()|| age.isEmpty()) {
                Toast.makeText(context, "Please fill out all fields.", Toast.LENGTH_SHORT).show()
            }else{
                //Create User Object
                val updateUser= User(user.id,firstName,lastName,Integer.parseInt(age))
                //Update Current User
                mUserViewModel.updateUser(updateUser)
                Toast.makeText(context, "Updated successfully !", Toast.LENGTH_SHORT).show()
                //Navigate Back
                findNavController().navigate(UpdateFragmentDirections.actionUpdateFragmentToListFragment())
            }

        }
    }

    private fun deleteUser() {
        val user = args.currentUser
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){_, _ ->
            mUserViewModel.deleteUser(user)
            Toast.makeText(context,"Successfully removed ${user.firstName}",Toast.LENGTH_SHORT).show()
            findNavController().navigate(UpdateFragmentDirections.actionUpdateFragmentToListFragment())
        }
        builder.setNegativeButton("No"){_, _ ->}
        builder.setTitle("Delete ${user.firstName}? ")
        builder.setMessage("Are you sure you want to delete ${user.firstName}?")
        builder.create().show()
    }

}