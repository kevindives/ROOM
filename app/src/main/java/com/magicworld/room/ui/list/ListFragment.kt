package com.magicworld.room.ui.list

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.magicworld.room.R
import com.magicworld.room.model.User
import com.magicworld.room.viewmodel.UserViewModel
import com.magicworld.room.databinding.FragmentListBinding

class ListFragment : Fragment() {

    private lateinit var listBinding: FragmentListBinding
    private val mUserViewModel : UserViewModel by viewModels()
    private lateinit var listAdapter: ListAdapter
    private var listUser :ArrayList<User> = arrayListOf()
    private lateinit var auth : FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        listBinding = FragmentListBinding.inflate(inflater,container,false)
        return listBinding.root
    }

    override fun onStart() {
        super.onStart()
        mUserViewModel.checkUserLoggedIn()
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId ){
            R.id.menu_delete->{
                deleteAllUser()
            }
            R.id.sign_out-> {
                signOut()
            }

        }
        return super.onOptionsItemSelected(item)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        /*
        mUserViewModel.readAllData.observe(viewLifecycleOwner){ result->

        }*/

        mUserViewModel.getDataFromFirebase()

        mUserViewModel.onGetDataFromFirebase.observe(viewLifecycleOwner) {result ->
            onReadAllDataSubscribe(result)
        }
        mUserViewModel.onCheckUserLooggedIn.observe(viewLifecycleOwner){ result ->
            onCheckUserLoggedInSubscribe(result)
        }

        listAdapter = ListAdapter(listUser, onItemClicked = {onUserClicked(it)})
        listBinding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = listAdapter
            setHasFixedSize(false)
        }

        listBinding.floatingActionButton.setOnClickListener{
            findNavController().navigate(ListFragmentDirections.actionListFragmentToAddFragment())
        }
    }


    private fun onReadAllDataSubscribe(result: List<User>?) {
        result?.let { listAdapterList->
            listAdapter.appendItems(listAdapterList as MutableList<User>)
        }
    }

    private fun onCheckUserLoggedInSubscribe(result: Boolean?) {
        result?.let { isLoggedIn ->
            if (!isLoggedIn)
                findNavController().navigate(ListFragmentDirections.actionListFragmentToLoginFragment())
        }
    }

    private fun onUserClicked(user: User) {
        findNavController().navigate(ListFragmentDirections.actionListFragmentToUpdateFragment(currentUser = user))
    }

    private fun signOut() {
        auth = Firebase.auth
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("yes"){_, _ ->
            auth.signOut()
            findNavController().navigate(ListFragmentDirections.actionListFragmentToLoginFragment())
        }
        builder.setNegativeButton("No"){_, _ ->}
        builder.setTitle("Sign out")
        builder.setMessage("Do you want to log out")
        builder.create().show()
    }

    private fun deleteAllUser() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){_, _ ->
            mUserViewModel.deleteAllUser()
            Toast.makeText(context,"Successfully removed everything", Toast.LENGTH_SHORT).show()
        }
        builder.setNegativeButton("No"){_, _ ->}
        builder.setTitle("Delete everything? ")
        builder.setMessage("Are you sure you want to delete everything?")
        builder.create().show()
    }

}