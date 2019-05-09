package br.pro.hashi.ensino.desagil.projeto1;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.PhoneNumberUtils;
import android.telephony.SmsManager;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.regex.Pattern;

public class PreDefMsgs extends AppCompatActivity implements ValueEventListener {
    private String[] predef_msgs = new String[]{
            "",
            "",
            ""
    };
    private TextView[] textViews;
    private int msgsIdx;
    private String contactName;
    private String phoneNumber;

    private void showToast(String text){
        Toast toast = Toast.makeText(this, text, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP, 0, 130);
        toast.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);;
        setContentView(R.layout.activity_pre_def_msgs);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        hideNavigationBar();

        Intent intent = getIntent();
        contactName = (intent.getStringExtra("contact"));
        phoneNumber = (intent.getStringExtra("number"));


        // SMS
        String SENT = "SMS_SENT";
        String DELIVERED = "SMS_DELIVERED";
        PendingIntent sentPI = PendingIntent.getBroadcast(this, 0, new Intent(SENT), 0);
        PendingIntent deliveredPI = PendingIntent.getBroadcast(this, 0,new Intent(DELIVERED), 0);


        // ---when the SMS has been sent---
        this.registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context arg0, Intent arg1) {
//                            System.out.println("SENT");

                switch(getResultCode()) {
                    case Activity.RESULT_OK:
                        showToast("Mensagem enviada para " + contactName);
                        break;
                    case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                        showToast("Erro ao enviar a mensagem");
                        break;
                    case SmsManager.RESULT_ERROR_NO_SERVICE:
                        showToast("Sem serviço");
                        break;
                    case SmsManager.RESULT_ERROR_NULL_PDU:
                        showToast("Erro com PDU");
                        break;
                    case SmsManager.RESULT_ERROR_RADIO_OFF:
                        showToast("Serviço celular desligado");
                        break;
                }
            }
        }, new IntentFilter(SENT));

        // ---when the SMS has been delivered---
        this.registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context arg0,Intent arg1) {
//                            System.out.println("DELIVERED");
                switch(getResultCode()) {
                    case Activity.RESULT_OK:
//                                    showToast("Mensagem recebida");
                        break;
//                                case Activity.RESULT_CANCELED:
//                                    break;
                    default:
//                                    showToast("Mensagem não recebida");
                        break;
                }
            }
        }, new IntentFilter(DELIVERED));


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

        FloatingActionButton sendSMS = findViewById(R.id.backButton);
        sendSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent pre = new Intent(PreDefMsgs.this, MainActivity.class);
                startActivity(pre);
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


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("msgPre");
        myRef.addValueEventListener(this);


        this.textViews = new TextView[3];

        this.textViews[0] = (TextView) findViewById(R.id.textView);
        this.textViews[1] = (TextView) findViewById(R.id.textView2);
        this.textViews[2] = (TextView) findViewById(R.id.textView3);


        this.msgsIdx = 0;

        setText();

        Pattern pattern = Pattern.compile("^\\s*$");

        FloatingActionButton sendButton = findViewById(R.id.fab);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String screenMsgString = predef_msgs[(msgsIdx + 1) % predef_msgs.length];
                if (pattern.matcher(screenMsgString).matches()) {
                    showToast("Mensagem vazia!");
                    return;
                }


                if (!PhoneNumberUtils.isGlobalPhoneNumber(phoneNumber)) {
                    showToast("Número inválido!");
                    return;
                }

                SmsManager manager = SmsManager.getDefault();
                manager.sendTextMessage(phoneNumber, null, screenMsgString, sentPI, deliveredPI);

                return;
            }
        });
    }

    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot){
        HashMap<String, String> msgs = (HashMap<String, String>) dataSnapshot.getValue();
        LinkedList<String> tempLines = new LinkedList<>();
        msgs.forEach((k, v) -> {
            tempLines.push(v);
        });
        int nMsgs = tempLines.size();
        for (int counter = nMsgs; counter < 3; counter++){
            tempLines.add(" ");
        }
        predef_msgs = new String[tempLines.size()];
        predef_msgs = tempLines.toArray(predef_msgs);
        setText();

    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

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

        for (int i=0; i<this.textViews.length; i++) {
            this.textViews[i].setText(this.predef_msgs[(this.msgsIdx + i) % this.predef_msgs.length]);
        }
    }
}