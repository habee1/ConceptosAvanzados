package com.android.conceptosavanzados;



import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class Llamadas extends AppCompatActivity {

    private EditText numTelefono;
    private ImageButton llamar;
    private Button mostrar_marcar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.llamadas);
        setTitle("ConceptosAvanzadosEnAndroid");

        numTelefono = (EditText)findViewById(R.id.marcar_numero);

        llamar = (ImageButton)findViewById(R.id.llamarIBTN);

        mostrar_marcar = (Button)findViewById(R.id.mostrarymarcar);

        TelefonoListener telefonoListener = new TelefonoListener();
        TelephonyManager telephonyManager = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
        telephonyManager.listen(telefonoListener,PhoneStateListener.LISTEN_CALL_STATE);

        llamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    String uri = "tel: " + numTelefono.getText().toString();
                    Intent llamadaIntent = new Intent(Intent.ACTION_CALL, Uri.parse(uri));
                    startActivity(llamadaIntent);
                }catch(Exception e){
                    Toast.makeText(Llamadas.this, "La llamada ha fallado", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });

        mostrar_marcar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    String uri = "tel: " + numTelefono.getText().toString();
                    Intent mostrarmarcarIntent = new Intent(Intent.ACTION_DIAL, Uri.parse(uri));
                    startActivity(mostrarmarcarIntent);
                }catch(Exception e){
                    Toast.makeText(Llamadas.this, "La llamada ha fallado", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }

            }
        });
    }

    private class TelefonoListener extends PhoneStateListener{

        private boolean llamada = false;

        @Override
        public void onCallStateChanged(int state, String incomingNumber) {

            switch (state){
                case TelephonyManager.CALL_STATE_RINGING:
                    Toast.makeText(Llamadas.this, incomingNumber + "te llama", Toast.LENGTH_SHORT).show();
                    break;
                case TelephonyManager.CALL_STATE_OFFHOOK:
                    Toast.makeText(Llamadas.this, "En Llamada", Toast.LENGTH_SHORT).show();
                    llamada = true;
                    break;
                case TelephonyManager.CALL_STATE_IDLE:
                    if(llamada == true){
                        Toast.makeText(Llamadas.this, "Restaurar aplicación despues de llamar", Toast.LENGTH_SHORT).show();

                        //Restaura la Aplicacion:
                        Intent restaurarIntent = getBaseContext().getPackageManager().getLaunchIntentForPackage(getBaseContext()
                        .getPackageName());
                        restaurarIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(restaurarIntent);
                        llamada = false;//porque se acabo la llamada.
                    }
                    break;
                default:
                    break;
            }
        }
    }
}
