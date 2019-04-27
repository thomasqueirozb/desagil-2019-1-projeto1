package br.pro.hashi.ensino.desagil.projeto1;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.util.LinkedList;

public class Mensagens_Predefinidas extends AppCompatActivity {
    private String[] predef_msgs = {
            "Mensagem",
            "Pre",
            "Definida",
            "Eu",
            "Sou",
            "Muito",
            "Bonito"
    };

    private TextView[] textViews;

    private int msgsIdx;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mensagens__predefinidas);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab_up = findViewById(R.id.floatingActionButton2);
        fab_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                msgsIdx -= 1;
                setText();

                // Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });

        FloatingActionButton fab_down = findViewById(R.id.floatingActionButton);
        fab_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                msgsIdx += 1;
                setText();

                // Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });


        this.textViews = new TextView[3];

        this.textViews[0] = (TextView) findViewById(R.id.textView);
        this.textViews[1] = (TextView) findViewById(R.id.textView2);
        this.textViews[2] = (TextView) findViewById(R.id.textView3);


        this.msgsIdx = 0;

        setText();
    }

    public void setText() {
        if (this.msgsIdx < 0)
            this.msgsIdx += this.predef_msgs.length;

        this.msgsIdx %= this.predef_msgs.length;

        for (int i=0; i<3; i++) {
            this.textViews[i].setText(this.predef_msgs[(this.msgsIdx + i) % this.predef_msgs.length]);
        }
    }
}
