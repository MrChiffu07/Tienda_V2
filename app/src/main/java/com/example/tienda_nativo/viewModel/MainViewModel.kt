package com.example.tienda_nativo.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tienda_nativo.Model.BrandModel
import com.example.tienda_nativo.Model.SliderModel
import com.example.tienda_nativo.Model.itemModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainViewModel : ViewModel() {

    private val firebaseDatabase = FirebaseDatabase.getInstance()

    private val _banner = MutableLiveData<List<SliderModel>>()
    private val _brand = MutableLiveData<MutableList<BrandModel>>()
    private val _popular = MutableLiveData<MutableList<itemModel>>()

    val brands: LiveData<MutableList<BrandModel>> = _brand
    val banners: LiveData<List<SliderModel>> = _banner
    val popular: LiveData<MutableList<itemModel>> = _popular


    fun loadBanners() {
        firebaseDatabase.getReference("Banner").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists = mutableListOf<SliderModel>()
                for (childSnapshot in snapshot.children) {
                    val list = childSnapshot.getValue(SliderModel::class.java)
                    if (list != null) {
                        lists.add(list)
                    }
                }
                _banner.value = lists
            }

            override fun onCancelled(error: DatabaseError) {
                // Manejar el error aquí si es necesario
            }
        })
    }

    fun loadBrand() {
        val ref = firebaseDatabase.getReference("Category")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists = mutableListOf<BrandModel>()
                for (childSnapshot in snapshot.children) {
                    val list = childSnapshot.getValue(BrandModel::class.java)
                    if (list != null) {
                        lists.add(list)
                    }else {
                        Log.d("MainViewModel", "Snapshot vacío o inválido en ${childSnapshot.key}")
                    }
                }
                _brand.value = lists
            }

            override fun onCancelled(error: DatabaseError) {
                // Manejar el error aquí si es necesario
            }
        })
    }

    fun loadPupolar () {
        val ref = firebaseDatabase.getReference("Items")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists = mutableListOf<itemModel>()
                for (childSnapshot in snapshot.children) {
                    val list = childSnapshot.getValue(itemModel::class.java)
                    if (list != null) {
                        lists.add(list)
                    }else {
                        Log.d("MainViewModel", "Snapshot vacío o inválido en ${childSnapshot.key}")
                    }
                }
                _popular.value = lists
            }

            override fun onCancelled(error: DatabaseError) {
                // Manejar el error aquí si es necesario
            }
        })
    }


}
