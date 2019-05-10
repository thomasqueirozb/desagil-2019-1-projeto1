package br.pro.hashi.ensino.desagil.projeto1;

import android.database.MatrixCursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class DictionaryMorse extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionary_morse);
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

        List<String> messages = Arrays.asList("e", "t", "i", "a", "n", "m",
               "s", "u", "r", "w", "d", "k", "g", "o", "h", "v", "f", "l", "p",
                "j", "b", "x", "c", "y", "z", "q", "5", "4", "3", "2", "1", "6",
               "7", "8", "9", "0");


        ListView list = (ListView) findViewById(R.id.SCHEDULE);

        ArrayList<HashMap<String, String>> mylist = new ArrayList<HashMap<String, String>>();

        for(String element: messages) {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("train", translator.charToMorse(element.toCharArray()[0]));
            map.put("from", element);
            mylist.add(map);
        }

        SimpleAdapter mSchedule = new SimpleAdapter(this, mylist, R.layout.row,
                new String[] {"train", "from"}, new int[] {R.id.TRAIN_CELL, R.id.FROM_CELL});
        list.setAdapter(mSchedule);


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

}
