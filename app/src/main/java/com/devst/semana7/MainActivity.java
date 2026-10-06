package com.devst.semana7;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Patterns;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class MainActivity extends AppCompatActivity {

    // Botones
    Button btnLinterna;
    Button btnSegundaVista;
    Button btnUbicacion;
    Button btnMapa;
    Button btnWeb;
    Button btnTelefono;
    Button btnCorreo;
    Button btnWifi;

    // Componentes Correo
    EditText etCorreo;

    // Componentes Telefono
    EditText etTelefono;

    // Componentes Página Web
    EditText etUrl;

    // TextView para mostrar ubicación
    TextView txtUbicacion;

    // Variables de la linterna
    CameraManager cameraManager;
    String idCamara;
    boolean linternaEncendida = false;

    // Variables de ubicación
    LocationManager locationManager;
    double latitud = 0;
    double longitud = 0;
    boolean ubicacionObtenida = false;

    // Códigos para permisos
    final int PERMISO_UBICACION = 100;
    final int PERMISO_CAMARA = 200;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


        // =========================================
        // CONECTAR COMPONENTES DEL XML
        // =========================================

        btnLinterna = findViewById(R.id.btnLinterna);
        btnSegundaVista = findViewById(R.id.btnSegundaVista);
        btnUbicacion = findViewById(R.id.btnUbicacion);
        btnMapa = findViewById(R.id.btnMapa);
        btnWeb = findViewById(R.id.btnWeb);
        etUrl = findViewById(R.id.etUrl);
        btnTelefono = findViewById(R.id.btnTelefono);
        etTelefono = findViewById(R.id.etTelefono);
        btnCorreo = findViewById(R.id.btnCorreo);
        etCorreo = findViewById(R.id.etCorreo);
        btnWifi = findViewById(R.id.btnWifi);


        txtUbicacion = findViewById(R.id.txtUbicacion);


        // =========================================
        // PREPARAR LA LINTERNA
        // =========================================

        cameraManager =
                (CameraManager) getSystemService(Context.CAMERA_SERVICE);

        try {

            String[] camaras =
                    cameraManager.getCameraIdList();

            if (camaras.length > 0) {

                idCamara = camaras[0];

            }

        } catch (CameraAccessException e) {

            Toast.makeText(
                    MainActivity.this,
                    "Error al acceder a la cámara",
                    Toast.LENGTH_SHORT
            ).show();

        }

        // =========================================
        // Intent Implícito
        // Abrir Página Web
        // =========================================

        btnWeb.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        abrirPaginaWeb();
                    }
                }
        );

        // =========================================
        // Intent Implícito
        // Abrir Configuración de wifi
        // =========================================

        btnWifi.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {

                        abrirConfiguracionWifi();
                    }
                }
        );

        // =========================================
        // Intent Implícito
        // Abrir el marcador Telefónico
        // =========================================

        btnTelefono.setOnClickListener(
                new View.OnClickListener(){

                    @Override
                    public void onClick(View view){

                        abrirMarcador();
                    }
                }
        );

        // =========================================
        // Intent Implícito
        // Preparar un correo Electrónico
        // =========================================

        btnCorreo.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        prepararCorreo();

                    }
                }
        );

                // =========================================
                // BOTÓN LINTERNA
                // =========================================

                btnLinterna.setOnClickListener(
                        new View.OnClickListener() {

                            @Override
                            public void onClick(View view) {

                                // Revisamos permiso de cámara
                                if (ActivityCompat.checkSelfPermission(
                                        MainActivity.this,
                                        Manifest.permission.CAMERA)
                                        != PackageManager.PERMISSION_GRANTED) {

                                    // Pedimos permiso
                                    ActivityCompat.requestPermissions(
                                            MainActivity.this,
                                            new String[]{
                                                    Manifest.permission.CAMERA
                                            },
                                            PERMISO_CAMARA
                                    );

                                    return;
                                }

                                cambiarLinterna();

                            }
                        });


        // =========================================
        // INTENT EXPLÍCITO
        // IR A OTRA PANTALLA
        // =========================================

        btnSegundaVista.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {

                        /*
                         * INTENT EXPLÍCITO
                         *
                         * Le indicamos exactamente
                         * qué Activity queremos abrir.
                         */

                        Intent intent =
                                new Intent(
                                        MainActivity.this,
                                        SegundaActivity.class
                                );

                        startActivity(intent);

                    }
                });


        // =========================================
        // OBTENER GEOLOCALIZACIÓN
        // =========================================

        btnUbicacion.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {

                        obtenerUbicacion();

                    }
                });


        // =========================================
        // INTENT IMPLÍCITO
        // ABRIR UBICACIÓN EN MAPA
        // =========================================

        btnMapa.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {

                        abrirMapa();

                    }
                });

    }


    // =============================================
    // ENCENDER / APAGAR LINTERNA
    // =============================================

    private void cambiarLinterna() {

        if (idCamara == null) {

            Toast.makeText(
                    MainActivity.this,
                    "No se encontró una cámara con flash",
                    Toast.LENGTH_SHORT
            ).show();

            return;
        }

        try {

            // Cambiamos el estado
            linternaEncendida = !linternaEncendida;

            // Encendemos o apagamos
            cameraManager.setTorchMode(
                    idCamara,
                    linternaEncendida
            );


            // Cambiar texto del botón

            if (linternaEncendida) {

                btnLinterna.setText(
                        "APAGAR LINTERNA"
                );

            } else {

                btnLinterna.setText(
                        "ENCENDER LINTERNA"
                );

            }

        } catch (CameraAccessException e) {

            Toast.makeText(
                    MainActivity.this,
                    "No se pudo controlar la linterna",
                    Toast.LENGTH_SHORT
            ).show();

        }

    }


    // =============================================
    // OBTENER UBICACIÓN
    // =============================================

    private void obtenerUbicacion() {

        locationManager =
                (LocationManager)
                        getSystemService(
                                Context.LOCATION_SERVICE
                        );


        // Revisamos permiso
        if (ActivityCompat.checkSelfPermission(
                MainActivity.this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {


            // Pedimos permiso
            ActivityCompat.requestPermissions(
                    MainActivity.this,
                    new String[]{
                            Manifest.permission.ACCESS_FINE_LOCATION
                    },
                    PERMISO_UBICACION
            );

            return;
        }


        // Revisamos si el GPS está encendido
        if (!locationManager.isProviderEnabled(
                LocationManager.GPS_PROVIDER)) {

            Toast.makeText(
                    MainActivity.this,
                    "Debes activar el GPS",
                    Toast.LENGTH_LONG
            ).show();


            /*
             * INTENT IMPLÍCITO
             *
             * Android abre la pantalla
             * de configuración correspondiente.
             */

            Intent intent =
                    new Intent(
                            Settings.ACTION_LOCATION_SOURCE_SETTINGS
                    );

            startActivity(intent);

            return;
        }


        txtUbicacion.setText(
                "Buscando ubicación..."
        );


        // Pedimos una ubicación
        locationManager.requestLocationUpdates(

                LocationManager.GPS_PROVIDER,

                1000,

                1,

                new LocationListener() {

                    @Override
                    public void onLocationChanged(
                            @NonNull Location location) {

                        // Guardamos los datos
                        latitud =
                                location.getLatitude();

                        longitud =
                                location.getLongitude();

                        ubicacionObtenida = true;


                        // Mostramos la ubicación
                        txtUbicacion.setText(

                                "Latitud: "
                                        + latitud
                                        +
                                        "\nLongitud: "
                                        + longitud

                        );


                        Toast.makeText(
                                MainActivity.this,
                                "Ubicación obtenida",
                                Toast.LENGTH_SHORT
                        ).show();


                        // Dejamos de pedir actualizaciones
                        locationManager.removeUpdates(this);

                    }
                }
        );

    }

    // =============================================
    // Abrir Página Web
    // =============================================
    private void abrirPaginaWeb(){

        // Obtener dirección ingresada
        String url = etUrl.getText()
                .toString()
                .trim();

        // Validamos que el campo no esté vacío
        if (url.isEmpty()){

            etUrl.setError(
                    "Debes ingresar una dirección web"
            );
            etUrl.requestFocus();
            return;
        }

        // Agregamos https:// si el usuario no lo escribió
        if (!url.startsWith("http://")
                &&
                !url.startsWith("http://")) {
            url = "http://" + url;
        }

        // Validar formato de dirección
        if (!Patterns.WEB_URL.matcher(url).matches()){

            etUrl.setError(
                    "La dirección web no es válida"
            );

            etUrl.requestFocus();

            return;
        }

        // Convertimos el texto en una direccion URI
        Uri paginaWeb = Uri.parse(url);

        // Creamos el intent implícito
        Intent intent = new Intent(
                Intent.ACTION_VIEW,
                paginaWeb
        );

        // Comprobamos que exista un navegador
        if (intent.resolveActivity(
                getPackageManager()) != null){

            startActivity(intent);
        } else {
            Toast.makeText(
                    MainActivity.this,
                    "No existe un navegador instalado",
                    Toast.LENGTH_SHORT
            ).show();
        }

    }

    // =============================================
    // Abrir Marcador Telefónico
    // =============================================

    private void abrirMarcador() {
        // Obtenemos el teléfono ingresado
        String telefono = etTelefono.getText()
                .toString()
                .trim();

        // Validamos que el campo no esté vacío
        if (telefono.isEmpty()) {

            etTelefono.setError(
                    "Debes ingresar un número de teléfono"
            );

            etTelefono.requestFocus();
            return;
        }

        // Eliminamos espacios, guiones y paréntesis
        String telefonoLimpio = telefono
                .replace(" ","")
                .replace("-","")
                .replace("(","")
                .replace(")","");

        // Validamos números nacionales e internacionales
        if (!telefonoLimpio.matches("[+]?[0-9]{8,15}")){

            etTelefono.setError(
                    "El número de teléfono no es válido"
            );

            etTelefono.requestFocus();

            return;
        }

        // Creamos la dirección telefónica
        Uri numeroTelefono = Uri.parse(
                "tel:" + telefonoLimpio
        );

        // Creamos el intent implícito
        Intent intent = new Intent(
                Intent.ACTION_DIAL,
                numeroTelefono
        );

        // Verificamos que exista una aplicación compatible
        if (intent.resolveActivity(
                getPackageManager()) != null) {

            startActivity(intent);
        } else {
            Toast.makeText(
                    MainActivity.this,
                    "No existe una aplicación de teléfono",
                    Toast.LENGTH_SHORT
            ).show();
        }
    }

    // =============================================
    // Abrir Marcador Telefónico
    // =============================================

    private void prepararCorreo(){

        // Obtenemos el correo ingresado
        String correo = etCorreo.getText()
                .toString()
                .trim();

        // Validamos que el campo no esté vacío
        if (correo.isEmpty()) {

            etCorreo.setError(
                    "Debes ingresar un correo electrónico"
            );

            etCorreo.requestFocus();

            return;
        }

        // Validamos el formato del correo
        if (!Patterns.EMAIL_ADDRESS.matcher(correo).matches()) {

            etCorreo.setError(
                    "El correo electrónico no es válido"
            );

            etCorreo.requestFocus();

            return;
        }

        // Datos que aparecerán preparados
        String asunto = "Prueba de Intent Android";

        String mensaje =
                "Hola, este correo fue preparado "
                + "desde la aplicacion de Intents.";

        // Creamos la dirección mailto
        Uri direccionCorreo = Uri.parse(
                "mailto:" + Uri.encode(correo)
                                + "?subject=" + Uri.encode(asunto)
                                + "&body" + Uri.encode(mensaje)
        );

        // Creamos el intent implícito
        Intent intent = new Intent(
                Intent.ACTION_SENDTO,
                direccionCorreo
        );

        // Verificamos que exista una aplicación de correo
        if (intent.resolveActivity(
                getPackageManager()) != null) {

            startActivity(intent);
        } else {
            Toast.makeText(
                    MainActivity.this,
                    "No existe una aplicación de correo instalada",
                    Toast.LENGTH_SHORT
            ).show();
        }
    }

    // =============================================
    // Abrir ajustes de Wi-Fi
    // =============================================

    private void abrirConfiguracionWifi() {
        // Creamos un intent para abrir
        // la configuración de Wi fi del teléfono
        Intent intent = new Intent(
                Settings.ACTION_WIFI_SETTINGS
        );

        if (intent.resolveActivity(
                getPackageManager()) != null ) {

            startActivity(intent);
        } else {
            Toast.makeText(
                    MainActivity.this,
                    "No se pudo abrir la configuración de Wi-Fi",
                    Toast.LENGTH_SHORT
            ).show();
        }
    }

    // =============================================
    // Abrir Mapa
    // =============================================

    private void abrirMapa() {

        // Revisamos si tenemos ubicación
        if (!ubicacionObtenida) {

            Toast.makeText(
                    MainActivity.this,
                    "Primero obtén tu ubicación",
                    Toast.LENGTH_SHORT
            ).show();

            return;
        }


        // Creamos la dirección para el mapa
        String direccion =

                "geo:"
                        + latitud
                        + ","
                        + longitud
                        + "?q="
                        + latitud
                        + ","
                        + longitud;


        Uri uri =
                Uri.parse(direccion);


        /*
         * INTENT IMPLÍCITO
         *
         * No decimos qué aplicación abrir.
         *
         * Android busca una aplicación
         * capaz de mostrar mapas.
         */

        Intent intent =
                new Intent(
                        Intent.ACTION_VIEW,
                        uri
                );


        // Revisamos si existe una aplicación
        // que pueda realizar la acción

        if (intent.resolveActivity(
                getPackageManager()) != null) {

            startActivity(intent);

        } else {

            Toast.makeText(
                    MainActivity.this,
                    "No existe una aplicación de mapas instalada",
                    Toast.LENGTH_SHORT
            ).show();

        }

    }


    // =============================================
    // RESPUESTA DE LOS PERMISOS
    // =============================================

    @Override
    public void onRequestPermissionsResult(

            int requestCode,

            @NonNull String[] permissions,

            @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(
                requestCode,
                permissions,
                grantResults
        );


        // =========================================
        // PERMISO DE UBICACIÓN
        // =========================================

        if (requestCode == PERMISO_UBICACION) {

            if (grantResults.length > 0
                    &&
                    grantResults[0]
                            == PackageManager.PERMISSION_GRANTED) {

                Toast.makeText(
                        MainActivity.this,
                        "Permiso de ubicación aceptado",
                        Toast.LENGTH_SHORT
                ).show();

                obtenerUbicacion();

            } else {

                Toast.makeText(
                        MainActivity.this,
                        "Permiso de ubicación rechazado",
                        Toast.LENGTH_SHORT
                ).show();

            }

        }


        // =========================================
        // PERMISO DE CÁMARA
        // =========================================

        if (requestCode == PERMISO_CAMARA) {

            if (grantResults.length > 0
                    &&
                    grantResults[0]
                            == PackageManager.PERMISSION_GRANTED) {

                cambiarLinterna();

            } else {

                Toast.makeText(
                        MainActivity.this,
                        "Permiso de cámara rechazado",
                        Toast.LENGTH_SHORT
                ).show();

            }

        }

    }

}