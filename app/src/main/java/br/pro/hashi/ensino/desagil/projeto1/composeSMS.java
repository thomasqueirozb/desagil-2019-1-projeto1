package br.pro.hashi.ensino.desagil.projeto1;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.telephony.PhoneNumberUtils;
import android.telephony.SmsManager;
import android.app.PendingIntent;
import java.util.regex.Pattern;

public class composeSMS extends AppCompatActivity {

        private String morseMsgString = "";
        private String screenMsgString = "";
        private String contactName;
        private String phoneNumber;

        private void showToast(String text){
            Toast toast = Toast.makeText(this, text, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.TOP, 0, 130);
            toast.show();
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_compose_sms);

            hideNavigationBar();

            Translator translator = new Translator();

            Intent intent = getIntent();
            contactName = (intent.getStringExtra("contact"));
            phoneNumber = (intent.getStringExtra("number"));


//      Botoes

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


            // SmsManager sms = SmsManager.getDefault();
            // sms.sendTextMessage(phoneNumber, null,message,sentPI, deliveredPI);

            // Botoes

            FloatingActionButton backButton = findViewById(R.id.backButton);
            FloatingActionButton morseButton = findViewById(R.id.morseButton);
            FloatingActionButton backspaceButton = findViewById(R.id.backspaceButton);
            FloatingActionButton sendButton = findViewById(R.id.sendButton);
            FloatingActionButton dictionaryButton = findViewById(R.id.dictionaryButton);
            FloatingActionButton morseDictionary = findViewById(R.id.floatingActionButton4);

            TextView screenMsg = findViewById(R.id.mensagemTela);
            TextView morseMsg = findViewById(R.id.morseMsg);
            screenMsg.setText("");
            morseMsg.setText("");

            backButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(composeSMS.this, MainActivity.class);
                    startActivity(intent);
                }
            });

            dictionaryButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(composeSMS.this, Dictionary.class);
                    startActivity(intent);
                }
            });

            morseDictionary.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(composeSMS.this, DictionaryMorse.class);
                    startActivity(intent);
                }
            });

            morseButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    morseMsgString += ".";
                    morseMsg.setText(morseMsgString);
                }
            });

            morseButton.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    morseMsgString += "-";
                    morseMsg.setText(morseMsgString);
                    return true;
                }
            });

            sendButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (morseMsgString == "") {
                        screenMsgString += " ";
                        screenMsg.setText(screenMsgString);
                    }
                    else {
                        if (translator.getCodes().contains(morseMsgString)) {
                            screenMsgString += String.valueOf(translator.morseToChar(morseMsgString));
                            screenMsg.setText(screenMsgString);
                            morseMsgString = "";
                            morseMsg.setText(morseMsgString);
                        }
                        else {
                            showToast("Morse Invalido!");
                        }
                    }
                }
            });

            Pattern pattern = Pattern.compile("^\\s*$");

            sendButton.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    if (pattern.matcher(screenMsgString).matches()) {
                        showToast("Mensagem vazia!");
                        return false;
                    }


                    if (!PhoneNumberUtils.isGlobalPhoneNumber(phoneNumber)) {
                        showToast("Número inválido!");
                        return false;
                    }

                    SmsManager manager = SmsManager.getDefault();
                    manager.sendTextMessage(phoneNumber, null, screenMsgString, sentPI, deliveredPI);

                    screenMsgString = "";
                    screenMsg.setText("");
                    return true;
                }
            });

            backspaceButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (morseMsgString == "") {
                        if (screenMsgString == "") {
                            showToast("Mensagem vazia!");
                        }
                        else {
                            int len = screenMsgString.length();
                            if (len == 1) {
                                screenMsgString = "";
                                screenMsg.setText(screenMsgString);
                            }
                            else {
                                screenMsgString = screenMsgString.substring(0, len - 1);
                                screenMsg.setText(screenMsgString);
                            }
                        }
                    }
                    else {
                        int len = morseMsgString.length();
                        if (len == 1) {
                            morseMsgString = "";
                            morseMsg.setText(morseMsgString);
                        }
                        else {
                            morseMsgString = morseMsgString.substring(0, len - 1);
                            morseMsg.setText(morseMsgString);
                        }
                    }
                }
            });
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

    public void checkDictionary(View view){
        Intent intent = new Intent(composeSMS.this, DictionaryMorse.class);
        startActivity(intent);
    }
}
