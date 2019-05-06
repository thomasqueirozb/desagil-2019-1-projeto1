package br.pro.hashi.ensino.desagil.projeto1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        String contact = (intent.getStringExtra("contact"));

        if (contact != null){
            String contactString = contact.toString();
            TextView textView = findViewById(R.id.textView7);
            textView.setText(contactString);
        }

    }
    public void sendMessage(View view){
        Intent intent = new Intent(MainActivity.this, ComporNovaMensagemPreDefinida.class);
        startActivity(intent);
    }

    public void preDefined(View view){
        Intent intent = new Intent(MainActivity.this, Contact.class);
        startActivity(intent);
    }
}
