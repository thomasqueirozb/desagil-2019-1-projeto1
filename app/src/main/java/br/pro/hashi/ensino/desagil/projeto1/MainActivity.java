package br.pro.hashi.ensino.desagil.projeto1;

import android.content.Intent;
import android.os.Bundle;
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
    }
}
