package com.android.conceptosavanzados;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Correos extends AppCompatActivity{

    private EditText destino,asunto,escribir_correo;
    private Button enviarCorreo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.correo);
        setTitle("ConceptosAvanzadosEnAndroid");

        destino = (EditText)findViewById(R.id.correo_para);
        asunto = (EditText)findViewById(R.id.correo_asunto);
        escribir_correo = (EditText)findViewById(R.id.correo_escribir);

        enviarCorreo = (Button)findViewById(R.id.correo_enviar);
        enviarCorreo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviarEmail();

                //limpiar Campos:
                destino.setText("");
                asunto.setText("");
                escribir_correo.setText("");
            }
        });
    }

    protected void enviarEmail(){
        String[] destinos = {destino.getText().toString()};
        Intent emailIntent = new Intent(Intent.ACTION_SEND, Uri.parse("mailto:"));
        emailIntent.setType("message/rfc822");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, destinos);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT,asunto.getText().toString());
        emailIntent.putExtra(Intent.EXTRA_TEXT,escribir_correo.getText().toString());

        try{
            startActivity(Intent.createChooser(emailIntent, "Elija un Cliente de Correo Electronico"));
        }catch(Exception e){
            Toast.makeText(this, "Ningun Cliente de Correo Instalado.", Toast.LENGTH_LONG).show();
        }
    }
}
