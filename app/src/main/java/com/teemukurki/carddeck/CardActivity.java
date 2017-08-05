package com.teemukurki.carddeck;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.teemukurki.carddeck.bean.ActiveCardDeck;
import com.teemukurki.carddeck.bean.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CardActivity extends AppCompatActivity {

    private float touchDownX, touchUpX, imageviewOrigX;
    static final int MIN_SWIPE_DISTANCE = 150;
    public List<Card> cardDeck = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);

        //final Button customDeck = (Button) findViewById(R.id.customDeck);
        //final TextView textView = (TextView) findViewById(R.id.textView);

        final ImageView imageView = (ImageView) findViewById(R.id.imageView);

        /*customDeck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardDeck.clear();
                Log.d("CARDACTIVITY", ExpandableListViewAdapter.activeCardDeckList.toString());
                cardDeck = ExpandableListViewAdapter.activeCardDeckList;
            }
        });*/

        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = MotionEventCompat.getActionMasked(event);

                    switch(action) {
                        case (MotionEvent.ACTION_DOWN):
                            imageviewOrigX = v.getX();
                            touchDownX = event.getRawX();
                            break;
                        case (MotionEvent.ACTION_MOVE):
                            if(touchDownX > event.getRawX()){
                                v.setX(event.getRawX() - (touchDownX - imageviewOrigX));
                                float rotation = touchDownX - event.getRawX();
                                v.setRotation(-(rotation / 10));
                            }
                            break;
                        case (MotionEvent.ACTION_UP):
                            touchUpX = event.getRawX();

                            float deltaX = touchDownX - touchUpX;
                            Log.d("View", String.valueOf(v.getX()));
                            Log.d("Event RawX", String.valueOf(event.getRawX()));
                            Log.d("Event X", String.valueOf(event.getX()));

                            if(deltaX > MIN_SWIPE_DISTANCE){
                                Thread thread = new Thread(){
                                    @Override
                                    public void run() {
                                        try{
                                            runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    getNewCard(cardDeck, imageView);
                                                }
                                            });
                                            //sleep(1000);
                                        }
                                        catch (Exception e){
                                            e.printStackTrace();
                                        }

                                    }
                                };
                                thread.start();
                            }
                            v.setX(imageviewOrigX);
                            v.setRotation(0);
                            break;
                        default :
                            break;

                    }
                return true;
            }
        });


       final Button newDeck = (Button) findViewById(R.id.newDeck);
        newDeck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cardDeck != null){
                    cardDeck.clear();
                    cardDeck.addAll(generateCardDeck());
                    imageView.setImageResource(R.drawable.black_joker);
                    //textView.setText("Deck Reset");
                }
            }
        });
    }

    protected void onResume(){
        super.onResume();

        cardDeck = ExpandableListViewAdapter.activeCardDeckList;
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        Intent intent;
        switch (item.getItemId()){
            case R.id.deckMenu:
                intent = new Intent(CardActivity.this, DeckMenuActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void getNewCard(final List<Card> cardDeck, final ImageView imageView){
        if(cardDeck == null || cardDeck.size() <= 0){
            imageView.setImageResource(R.drawable.red_joker);
            return;
        }
        Random rng = new Random();
        int cardIndex = rng.nextInt(cardDeck.size());
        int cardViewId = getResources().getIdentifier(cardDeck.get(cardIndex).getId(), "drawable", getPackageName());
        //textView.setText(cardDeck.get(cardIndex).toString());
        Log.d("TEEMU", cardDeck.get(cardIndex).toString());
        imageView.setImageResource(cardViewId);
        cardDeck.remove(cardIndex);
    };

    public List<Card> generateCardDeck(){
        List<String> suites = new ArrayList<>();
        suites.add("hearts");
        suites.add("spades");
        suites.add("diamonds");
        suites.add("clubs");

        List<Card> cardDeck = new ArrayList<Card>();

        for (String suit : suites) {
            for (int i = 2; i < 11; i++){
                String value = String.valueOf(i);
                cardDeck.add(new Card((suit.charAt(0)+value), suit, value, true));
            }
            cardDeck.add(new Card((suit.charAt(0)+"j"), suit, "J", true));
            cardDeck.add(new Card((suit.charAt(0)+"q"), suit, "Q", true));
            cardDeck.add(new Card((suit.charAt(0)+"k"), suit, "K", true));
            cardDeck.add(new Card((suit.charAt(0)+"a"), suit, "A", true));
        }
        return cardDeck;
    }

}
