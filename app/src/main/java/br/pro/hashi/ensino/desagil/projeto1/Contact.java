package br.pro.hashi.ensino.desagil.projeto1;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.LinkedList;

public class Contact extends AppCompatActivity {
    private String[] predef_msgs;
    private TextView[] textViews;
    private int msgsIdx;
    private CharSequence contact;
    private String number;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        hideNavigationBar();

        FloatingActionButton fab_up = findViewById(R.id.floatingActionButton2);
        fab_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                msgsIdx -= 1;
                setText();
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

        FloatingActionButton sendSMS = findViewById(R.id.backButton);
        sendSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                    Intent pre = new Intent(Contact.this, MainActivity.class);
                    startActivity(pre);
            }
        });


        LinkedList<String> tempLines = new LinkedList<>();

        // Falta criar o arquivo se não existir
        InputStream is = this.getResources().openRawResource(R.raw.contatos);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));


        InputStream us = getResources().openRawResource(R.raw.contact);
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        try {
            Reader reader = new BufferedReader(new InputStreamReader(us, "UTF-8"));
            int n;
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                us.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String jsonString = writer.toString();

        JSONObject object = null;
        try {
            object = new JSONObject(jsonString);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONArray keys = object.names ();
        for (int i = 0; i <keys.length(); i++){
            try {
                tempLines.add(keys.getString(i));
            } catch (JSONException e) {
                e.printStackTrace();
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

    public void setText() {
        if (this.msgsIdx < 0)
            this.msgsIdx += this.predef_msgs.length;

        this.msgsIdx = this.msgsIdx % this.predef_msgs.length;

        for (int i=0; i<3; i++) {
            this.textViews[i].setText(this.predef_msgs[(this.msgsIdx + i) % this.predef_msgs.length]);
        }
        TextView myTextView = findViewById(R.id.textView2);

        this.contact = myTextView.getText();

        InputStream us = getResources().openRawResource(R.raw.contact);
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        try {
            Reader reader = new BufferedReader(new InputStreamReader(us, "UTF-8"));
            int n;
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                us.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String jsonString = writer.toString();

        JSONObject object = null;

        try {
            object = new JSONObject(jsonString);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            this.number = object.getString(this.contact.toString());
            System.out.println(this.number);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void setContact(View view){
        Intent intent = new Intent(Contact.this, MainActivity.class);
        intent.putExtra("contact",this.contact);
        intent.putExtra("number", this.number);
        startActivity(intent);
    }
}