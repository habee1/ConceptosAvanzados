package com.android.conceptosavanzados;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class Mensajeria extends AppCompatActivity{

    private EditText numTelefono,mensaje;
    private ImageButton enviar;
    private Button enviarSendto,enviarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mensajeria);
        setTitle("ConceptosAvanzadosEnAndroid");

        numTelefono = (EditText)findViewById(R.id.telefono);
        mensaje = (EditText)findViewById(R.id.escribir_mensaje);

        enviar = (ImageButton)findViewById(R.id.enviar);

        enviarSendto = (Button)findViewById(R.id.enviar_sendto);
        enviarView = (Button)findViewById(R.id.enviar_view);

        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviarMensaje();
            }
        });

        enviarSendto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mensajeSendto();
            }
        });

        enviarView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mensajeView();
            }
        });

    }

    public void enviarMensaje(){
        try{
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(numTelefono.getText().toString(),null,
                    mensaje.getText().toString(),null,null);
            Toast.makeText(this, "Su mensaje fue enviado correctamente", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Toast.makeText(this, "Mensaje no enviado", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    public void mensajeSendto(){
        Uri uri = Uri.parse("smsto:"+numTelefono.getText().toString());
        Intent mensajeIntent = new Intent(Intent.ACTION_SENDTO,uri);
        mensajeIntent.putExtra("sms_body",mensaje.getText().toString());
        try{
            startActivity(mensajeIntent);
        }catch(Exception e){
            Toast.makeText(this, "Mensaje no enviado", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    public void mensajeView(){
        Intent viewIntent = new Intent(Intent.ACTION_VIEW);
        viewIntent.setType("vnd.android-dir/mms-sms");//captura los datos
        viewIntent.putExtra("address",numTelefono.getText().toString());
        viewIntent.putExtra("sms_body",mensaje.getText().toString());
        try{
            startActivity(viewIntent);
        }catch (Exception e){
            Toast.makeText(this, "Mensaje no enviado", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }
}
