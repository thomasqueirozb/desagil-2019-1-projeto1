package br.pro.hashi.ensino.desagil.projeto1;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button sendPreDef = findViewById(R.id.button5);
        sendPreDef.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent pre = new Intent(MainActivity.this,PreDefMsgs.class);
                startActivity(pre);
            }
        });

        Button sendSMS = findViewById(R.id.button3);
        sendSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent pre = new Intent(MainActivity.this,composeSMS.class);
                startActivity(pre);
            }
        });

        String[] permissions = new String[]{
                Manifest.permission.SEND_SMS,
        };
        ActivityCompat.requestPermissions(this, permissions, 0);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        // Verifica se de fato é uma resposta ao pedido acima e se a
        // resposta foi positiva. As respostas estão armazenadas no
        // vetor grantResults, que pode estar vazio se o usuário
        // escolheu simplesmente ignorar o pedido e não responder nada.
        System.out.println("Callback");
        if (requestCode == 0 && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            // Se foi positiva, podemos iniciar a SMSActivity.
        }
    }
}
