package br.pro.hashi.ensino.desagil.projeto1;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class PreDefMsgs extends AppCompatActivity {
    private String[] predef_msgs;
    private TextView[] textViews;
    private int msgsIdx;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_def_msgs);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab_up = findViewById(R.id.floatingActionButton2);
        fab_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                msgsIdx -= 1;
                setText();
            }
        });

        FloatingActionButton addNewMessage = findViewById(R.id.addnewmessage);
        addNewMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PreDefMsgs.this, NewPreDefMsg.class);
                startActivity(intent);
            }
        });

        FloatingActionButton fab_down = findViewById(R.id.floatingActionButton);
        fab_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                msgsIdx += 1;
                setText();
            }
        });


        LinkedList<String> tempLines = new LinkedList<>();

        // Falta criar o arquivo se não existir
        InputStream is = this.getResources().openRawResource(R.raw.mensagenspredefinidas);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        if (is != null) {
            try {
                String line;
                while ((line = br.readLine()) != null) {
                    tempLines.add(line);
                }
                is.close();
            } catch (IOException e) {
                System.err.format("IOException: %s%n", e);
            }
        }

        int nMsgs = tempLines.size();
        for (int counter = nMsgs; counter < 3; counter++){
            tempLines.add(" ");
        }
        predef_msgs = new String[tempLines.size()];
        predef_msgs = tempLines.toArray(predef_msgs);

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

        this.msgsIdx = this.msgsIdx % this.predef_msgs.length;

        for (int i=0; i<this.textViews.length; i++) {
            this.textViews[i].setText(this.predef_msgs[(this.msgsIdx + i) % this.predef_msgs.length]);
        }
    }
}