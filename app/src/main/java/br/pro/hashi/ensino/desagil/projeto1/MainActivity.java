package br.pro.hashi.ensino.desagil.projeto1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }
    public void sendPreDefMessage(View view){
        Intent intent = new Intent(MainActivity.this, PreDefMsgs.class);
        startActivity(intent);
    }
    public void sendSMS(View view){
        Intent intent = new Intent(MainActivity.this, PreDefMsgs.class);
        startActivity(intent);
    }
    public void contactList(View view){
        Intent intent = new Intent(MainActivity.this, PreDefMsgs.class);
        startActivity(intent);
    }
}
