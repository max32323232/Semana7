package com.devst.semana7;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetalleActivity extends AppCompatActivity {

    // Componentes de la pantalla
    TextView txtNombrePrototipo;
    TextView txtCantidadIntents;
    Button btnVolverDetalle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detalle);


        // =========================================
        // Conectar componentes con XML
        // =========================================

        txtNombrePrototipo = findViewById(R.id.txtNombrePrototipo);

        txtCantidadIntents = findViewById(R.id.txtCantidadIntents);

        btnVolverDetalle = findViewById(R.id.btnVolverDetalle);


        // =========================================
        // Recibir datos desde MainActivity
        // =========================================

        String nombrePrototipo =
                getIntent().getStringExtra(
                        "NOMBRE_PROTOTIPO"
                );

        int cantidadIntents =
                getIntent().getIntExtra(
                        "CANTIDAD_INTENTS",
                        0
                );


        // Validamos el nombre recibido
        if (nombrePrototipo == null
                ||
                nombrePrototipo.isEmpty()) {

            nombrePrototipo =
                    "Nombre no recibido";

        }


        // Mostramos los datos recibidos
        txtNombrePrototipo.setText(
                nombrePrototipo
        );

        txtCantidadIntents.setText(
                "Cantidad de intents: "
                        + cantidadIntents
        );


        // =========================================
        // Botón volver
        // =========================================

        btnVolverDetalle.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {

                        // Cerramos esta Activity
                        finish();

                    }
                }
        );

    }

}