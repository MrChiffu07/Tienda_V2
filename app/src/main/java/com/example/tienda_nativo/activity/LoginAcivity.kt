package com.example.tienda_nativo.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.tienda_nativo.R
import com.google.android.gms.auth.api.identity.SignInPassword
import com.google.android.gms.tasks.Task
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import kotlinx.coroutines.launch

class LoginAcivity : ViewModel() {
    private val auth: FirebaseAuth = Firebase.auth
    private val loading = MutableLiveData (false)

    fun signInWithEmailAndPassword(email: String, password:String, home: ()->Unit)
    = viewModelScope.launch {
        try {
            auth.signInWithEmailAndPassword( email, password)
                .addOnCompleteListener{task ->
                    if (task.isSuccessful){
                        Log.d("shopShoes", "signInsignInWithEmailAndPassword logueado!!")
                        home ()
                    }else {
                        Log.d("shopShoes", "signInsignInWithEmailAndPassword:${task.result.toString()}")
                    }
                }
        }
        catch(ex:Exception ) {
            Log.d("shopShoes", "signInsignInWithEmailAndPassword:${ex.message}")
        }
    }
}
