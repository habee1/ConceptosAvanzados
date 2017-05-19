package com.android.conceptosavanzados;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

//falta un boton en el actionbar para que haga back y vuelva a la interfaz principal.
//tambien en la actividad principal debe tener un boton para salir de la aplicación.

public class MainActivity extends AppCompatActivity {

    private Button llamadas,correos,mensajes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("ConceptosAvanzadosEnAndroid");

        llamadas = (Button)findViewById(R.id.llamadasBTN);
        correos = (Button)findViewById(R.id.correosBTN);
        mensajes = (Button)findViewById(R.id.mensajesBTN);

        llamadas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent llamadasIntent = new Intent(MainActivity.this, Llamadas.class);
                startActivity(llamadasIntent);
            }
        });

        correos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent correosIntent = new Intent(MainActivity.this, Correos.class);
                startActivity(correosIntent);
            }
        });

        mensajes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mensajesIntent = new Intent(MainActivity.this, Mensajeria.class);
                startActivity(mensajesIntent);
            }
        });
    }
}
