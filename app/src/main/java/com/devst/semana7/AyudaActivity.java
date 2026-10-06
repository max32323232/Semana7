package com.devst.semana7;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class AyudaActivity extends AppCompatActivity {

    // Botón para regresar
    Button btnVolverAyuda;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_ayuda);


        // =========================================
        // Conectar componente del XML
        // =========================================

        btnVolverAyuda = findViewById(R.id.btnVolverAyuda);


        // =========================================
        // Botón volver
        // =========================================

        btnVolverAyuda.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {

                        // Cerramos AyudaActivity
                        finish();

                    }
                }
        );

    }

}