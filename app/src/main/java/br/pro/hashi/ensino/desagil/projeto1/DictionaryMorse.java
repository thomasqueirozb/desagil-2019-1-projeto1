package br.pro.hashi.ensino.desagil.projeto1;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class DictionaryMorse extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionary_morse);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Translator translator = new Translator();

        ArrayList<ArrayList<String>> listOLists = new ArrayList<ArrayList<String>>();

        List<String> messages = Arrays.asList("e", "t", "i", "a", "n", "m",
               "s", "u", "r", "w", "d", "k", "g", "o", "h", "v", "f", "l", "p",
                "j", "b", "x", "c", "y", "z", "q", "5", "4", "3", "2", "1", "6",
               "7", "8", "9", "0");

        for (String element: messages) {
            ArrayList<String> singleList = new ArrayList<String>();
            singleList.add(element);
            singleList.add(translator.charToMorse(element.toCharArray()[0]));
            System.out.println(translator.charToMorse(element.toCharArray()[0]));
            listOLists.add(singleList);
        }

        System.out.println(listOLists);

        ListView listView;

        listView=(ListView)findViewById(R.id.listView);

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listOLists);

        listView.setAdapter(arrayAdapter);


   }

}
