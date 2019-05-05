package br.pro.hashi.ensino.desagil.projeto1;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ComporNovaMensagemPreDefinida extends AppCompatActivity {
//  Morse and Screen MSGS
    private String morseMsgString = "";
    private String screenMsgString = "";

    private void showToast(String text){
        Toast toast = Toast.makeText(this, text, Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compor_nova_mensagem_pre_definida);
        Toolbar toolbar = findViewById(R.id.toolbar);

        Translator translator = new Translator();

//      Botoes
        FloatingActionButton backButton = findViewById(R.id.backButton);
        FloatingActionButton morseButton = findViewById(R.id.morseButton);
        FloatingActionButton backspaceButton = findViewById(R.id.backspaceButton);
        FloatingActionButton sendButton = findViewById(R.id.sendButton);
        TextView screenMsg = findViewById(R.id.mensagemTela);
        TextView morseMsg = findViewById(R.id.morseMsg);
        screenMsg.setText("");
        morseMsg.setText("");

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ComporNovaMensagemPreDefinida.this, MensagensPredefinidas.class);
                startActivity(intent);
            }
        });

        morseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                morseMsgString += ".";
                morseMsg.append(morseMsgString);
            }
        });

        morseButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                morseMsgString += "-";
                morseMsg.append(morseMsgString);
                return true;
            }
        });

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (morseMsgString == "") {
                    screenMsgString += " ";
                    screenMsg.append(screenMsgString);
                }
                else {
                    if (translator.getCodes().contains(morseMsgString)) {
                        screenMsgString += morseMsgString;
                        screenMsg.append(String.valueOf(translator.morseToChar(morseMsgString)));
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
                    Intent intent = new Intent(ComporNovaMensagemPreDefinida.this, MensagensPredefinidas.class);
                    startActivity(intent);
                    return false;
                }
            }
        });

        backspaceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (screenMsgString == "") {
                    if (morseMsgString == "") {
                        showToast("Morse Invalido!");
                    }
                    else {
                        morseMsgString = morseMsgString.substring(0, morseMsgString.length() - 1);
                        morseMsg.setText(morseMsgString);
                    }
                }
                else {
                    screenMsgString = screenMsgString.substring(0, screenMsgString.length() - 1);
                    screenMsg.setText(screenMsgString);

                }
            }
        });
    }
}