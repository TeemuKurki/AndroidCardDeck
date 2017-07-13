package com.teemukurki.carddeck;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.teemukurki.carddeck.bean.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by teemu on 28.6.2017.
 */

public class DeckMenuActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deck);

        ListView deckOptions = (ListView) findViewById(R.id.deckOptions);

        List<String> list = generateCardDeckForList();

        final ArrayAdapter adapter = new ArrayAdapter(this, R.layout.listview_row,list);
        deckOptions.setAdapter(adapter);

    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        Intent intent;
        switch (item.getItemId()){
            case R.id.main:
                finish();
                /*
                intent = new Intent(DeckMenuActivity.this, CardActivity.class);
                startActivity(intent);
                */
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public List<String> generateCardDeckForList(){
        List<String> suites = new ArrayList<>();
        suites.add("hearts");
        suites.add("spades");
        suites.add("diamonds");
        suites.add("clubs");

        List<String> cardDeck = new ArrayList<String>();

        for (String suit : suites) {
            for (int i = 2; i < 11; i++){
                String value = String.valueOf(i);
                cardDeck.add(suit +" "+ value);
            }
            cardDeck.add(suit + " j");
            cardDeck.add(suit + " q");
            cardDeck.add(suit + " k");
            cardDeck.add(suit + " a");
        }
        return cardDeck;
    }
}
