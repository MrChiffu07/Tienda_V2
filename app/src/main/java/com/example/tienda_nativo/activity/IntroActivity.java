package com.example.tienda_nativo.activity;

import android.content.Intent;
import android.os.Bundle;

import com.example.tienda_nativo.databinding.ActivityIntroBinding;

public class IntroActivity extends BaseActivity{

    private ActivityIntroBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inflar el binding
        binding = ActivityIntroBinding.inflate(getLayoutInflater());

        // Establecer la vista raíz
        setContentView(binding.getRoot());

        // Configurar el listener del botón de inicio
        binding.starBtn.setOnClickListener(v -> {
            // Iniciar MainActivity
            Intent intent = new Intent(IntroActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }
}
