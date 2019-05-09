package br.pro.hashi.ensino.desagil.projeto1;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NewPreDefMsg extends AppCompatActivity {
    //  Morse and Screen MSGS
    private String morseMsgString = "";
    private String screenMsgString = "";

    private void showToast(String text){
        Toast toast = Toast.makeText(this, text, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP, 0, 130);
        toast.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_pre_def_msg);

        hideNavigationBar();

        Translator translator = new Translator();

//      Botoes
        FloatingActionButton backButton = findViewById(R.id.backButton);
        FloatingActionButton morseButton = findViewById(R.id.morseButton);
        FloatingActionButton backspaceButton = findViewById(R.id.backspaceButton);
        FloatingActionButton sendButton = findViewById(R.id.sendButton);
        FloatingActionButton dictionaryButton = findViewById(R.id.floatingActionButton3);
        TextView screenMsg = findViewById(R.id.mensagemTela);
        TextView morseMsg = findViewById(R.id.morseMsg);
        screenMsg.setText("");
        morseMsg.setText("");

// Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();



        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NewPreDefMsg.this, PreDefMsgs.class);
                startActivity(intent);
            }
        });

        dictionaryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NewPreDefMsg.this, Dictionary.class);
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

        sendButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (screenMsgString == "") {
                    showToast("Mensagem Vazia!");
                    return false;
                }
                else {
                    myRef.child("msgPre").push().setValue(screenMsgString).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            showToast("Mensagem adicionada");
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    showToast("Falha ao adicionar a mensagem");
                                    // Write failed
                                    // ...
                                }
                            });;
                    screenMsgString = "";
                    screenMsg.setText(screenMsgString);
                    return false;
                }
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
        Intent intent = new Intent(NewPreDefMsg.this, Dictionary.class);
        startActivity(intent);
    }
}
