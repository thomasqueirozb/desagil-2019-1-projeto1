package br.pro.hashi.ensino.desagil.projeto1;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.support.v4.content.ContextCompat;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_SEND_SMS = 0;
    private String activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hideNavigationBar();

        activity = "main";

        String contact = getIntent().getStringExtra("contact");
        String number = getIntent().getStringExtra("number");



        Button sendPreDef = findViewById(R.id.button5);
        sendPreDef.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                if (!hasPermission()){
                    askPermission();
                    activity = "predef";
                } else {
                    Intent intent = new Intent(MainActivity.this, PreDefMsgs.class);
                    intent.putExtra("contact", contact);
                    intent.putExtra("number", number);
                    startActivity(intent);
                }
            }
        });


        Button sendSMS = findViewById(R.id.button3);
        sendSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                if (!hasPermission()){
                    askPermission();
                    activity = "sendsms";
                } else {
                    Intent intent = new Intent(MainActivity.this, composeSMS.class);
                    intent.putExtra("contact", contact);
                    intent.putExtra("number", number);
                    startActivity(intent);
                }
            }
        });


        if (contact != null){
            String contactString = contact.toString();
            TextView textView = findViewById(R.id.textView6);
            textView.setText(contactString);
        }

        if (!hasPermission()){
            askPermission();
        }
    }

    @Override
    protected void onResume(){
        super.onResume();
        hideNavigationBar();
    }

    private void hideNavigationBar(){
        this.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_FULLSCREEN |
                        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY |
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                        View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        );
    }

    public boolean hasPermission(){
        return (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED);
    }

    public void askPermission(){
        String[] permissions = new String[]{
                Manifest.permission.SEND_SMS,
        };
        ActivityCompat.requestPermissions(this, permissions, REQUEST_SEND_SMS);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        // Verifica se de fato é uma resposta ao pedido acima e se a
        // resposta foi positiva. As respostas estão armazenadas no
        // vetor grantResults, que pode estar vazio se o usuário
        // escolheu simplesmente ignorar o pedido e não responder nada.
        if (requestCode == REQUEST_SEND_SMS && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            switch (this.activity) {
                case "sendsms":
                    startActivity(new Intent(MainActivity.this, composeSMS.class));
                    break;
                case "predef":
                    startActivity(new Intent(MainActivity.this, PreDefMsgs.class));
                    break;
                case "main":
                    break;
                default:
                    break;
            }
        }
    }

    public void setContact(View view){
        Intent intent = new Intent(MainActivity.this, Contact.class);
        startActivity(intent);
    }

}
