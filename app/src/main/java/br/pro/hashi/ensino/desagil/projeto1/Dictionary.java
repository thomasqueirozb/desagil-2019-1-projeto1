package br.pro.hashi.ensino.desagil.projeto1;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Dictionary extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionary);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        hideNavigationBar();

        Button last = findViewById(R.id.lastactivity);
        last.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Translator translator = new Translator();

        List<String> messages = Arrays.asList("a", "b", "c", "d", "e", "f",
                "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
                "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
                "6", "7", "8", "9");


        ListView list = (ListView) findViewById(R.id.SCHEDULE);

        ArrayList<HashMap<String, String>> mylist = new ArrayList<HashMap<String, String>>();

        for(String element: messages) {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("train", element);
            map.put("from", translator.charToMorse(element.toCharArray()[0]));
            mylist.add(map);
        }

        SimpleAdapter mSchedule = new SimpleAdapter(this, mylist, R.layout.row,
                new String[] {"train", "from"}, new int[] {R.id.TRAIN_CELL, R.id.FROM_CELL});
        list.setAdapter(mSchedule);

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
        Intent intent = new Intent(Dictionary.this, Dictionary.class);
        startActivity(intent);
    }

}
