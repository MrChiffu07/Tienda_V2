package com.example.tienda_nativo.activity

import android.Manifest
import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.myapplication20.R
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.material.snackbar.Snackbar

class UbicacionActivity : AppCompatActivity() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var textViewCoordenadas: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ubicacion)

        textViewCoordenadas = findViewById(R.id.textViewCoordenadas)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        // Solicitar permisos de ubicación si no están otorgados
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        } else {
            getCurrentLocation()
        }
    }

    // Lanzador de permisos
    private val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
        if (isGranted) {
            getCurrentLocation()
        } else {
            textViewCoordenadas.text = "Permiso de ubicación denegado"
        }
    }

    // Obtener la ubicación actual
    private fun getCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
                if (location != null) {
                    val lat = location.latitude
                    val lon = location.longitude
                    textViewCoordenadas.text = "Coordenadas: Latitud = $lat, Longitud = $lon"

                    // Abrir el mapa con las coordenadas actuales
                    openMap(lat, lon, findViewById(R.id.main))
                } else {
                    textViewCoordenadas.text = "No se pudo obtener la ubicación"
                }
            }
        }
    }

    // Función para abrir el mapa con un intento de respaldo
    private fun openMap(lat: Double, lon: Double, view: View) {
        val geoUri = Uri.parse("geo:$lat,$lon?q=$lat,$lon")
        val geoIntent = Intent(Intent.ACTION_VIEW, geoUri)

        try {
            startActivity(geoIntent)
        } catch (e: ActivityNotFoundException) {
            // Intento alternativo si no se encuentra aplicación para "geo:"
            val webUri = Uri.parse("https://www.google.com/maps/search/?api=1&query=$lat,$lon")
            val webIntent = Intent(Intent.ACTION_VIEW, webUri)
            try {
                startActivity(webIntent)
            } catch (e: ActivityNotFoundException) {
                Snackbar.make(view, "No se encontró ninguna aplicación para abrir el mapa", Snackbar.LENGTH_LONG).show()
            }
        }
    }
}
