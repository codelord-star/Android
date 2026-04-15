package com.example.firstapp.data

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.snapshotFlow
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.firstapp.models.User
import com.example.firstapp.navigation.ROUTE_DASHBOARD
import com.example.firstapp.navigation.ROUTE_LOGIN
import com.example.firstapp.navigation.ROUTE_REGISTER
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class AuthViewModel(var navController: NavHostController, var context: Context){
    var mAuth= FirebaseAuth.getInstance()
    //register function
    fun signup(fullname: String, email: String, password: String, confirmpass: String) {
        //validation
        if (email.isBlank() || password.isBlank() || confirmpass.isBlank()) {
            Toast.makeText(context, "Email and Password cannot be blank", Toast.LENGTH_LONG).show()
            return
        } else if (password != confirmpass) {
            Toast.makeText(context, "Password do not match", Toast.LENGTH_LONG).show()
        } else {
            mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        val userdata = User(fullname, email, password, mAuth.currentUser!!.uid,"User")
                        val regRef = FirebaseDatabase.getInstance().getReference()
                            .child("Users/" + mAuth.currentUser!!.uid)
                        regRef.setValue(userdata).addOnCompleteListener {
                            if (it.isSuccessful) {
                                Toast.makeText(context,"User registered successfully", Toast.LENGTH_LONG).show()
                                navController.navigate(ROUTE_LOGIN)
                            } else {
                                Toast.makeText(context, "${it.exception!!.message}", Toast.LENGTH_LONG).show()
                                navController.navigate(ROUTE_REGISTER)
                            }
                        }
                    }
                    else {
                        navController.navigate(ROUTE_REGISTER)
                    }
                }
        }
    }

    //login function
    fun signin(email: String, password: String){
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener {
            if (it.isSuccessful){
                Toast.makeText(context,"Login successful", Toast.LENGTH_LONG).show()
                navController.navigate(ROUTE_DASHBOARD)
            }else{
                Toast.makeText(context,"Email or password is incorrect", Toast.LENGTH_LONG).show()
            }
        }
    }

    //signout function
    fun signout(){
        mAuth.signOut()
        navController.navigate(ROUTE_LOGIN)
        {popUpTo(0)}
    }

    //get current user name function
    fun getCurrentUserName(onResult: (String) -> Unit){
        val userId = mAuth.currentUser?.uid

        if (userId != null){
            val ref = FirebaseDatabase.getInstance()
                .getReference("Users/$userId")

            ref.get().addOnSuccessListener { snapshot ->
                val name = snapshot.child("fullname").value.toString()
                onResult(name)
            }.addOnFailureListener {
                onResult("User")
            }
        }else{
            onResult("User")
        }
    }
}