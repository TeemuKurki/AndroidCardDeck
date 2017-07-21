package com.teemukurki.carddeck;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;

import com.teemukurki.carddeck.bean.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by teemu on 28.6.2017.
 */

public class DeckMenuActivity extends AppCompatActivity {

    ExpandableListView deckExpandable;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deck_expandable);

        deckExpandable = (ExpandableListView) findViewById(R.id.deckExpandableView);

        ExpandableListViewAdapter adapter = new ExpandableListViewAdapter(DeckMenuActivity.this);

        deckExpandable.setAdapter(adapter);

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

}
