package com.teemukurki.carddeck;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.FragmentManager;
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
import android.widget.Toast;

import com.teemukurki.carddeck.bean.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CardActivity extends AppCompatActivity {

    private float touchDownX, touchUpX, imageviewOrigX;
    static final int MIN_SWIPE_DISTANCE = 150;
    public List<Card> cardDeck = null;

    private Boolean deckViewExtended = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);

        final RelativeLayout darkLayer = (RelativeLayout) findViewById(R.id.darkLayer);
        darkLayer.setVisibility(View.GONE);

        final ImageView imageView = (ImageView) findViewById(R.id.imageView);

        cardDeck = ExpandableListViewAdapter.activeCardDeckList;

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
                }
            }
        });

        final Button deckList = (Button) findViewById(R.id.deckList);

        final float deckListBtnPos = deckList.getX();

        deckList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CardDeckFragment cardDeckFragment = new CardDeckFragment();
                FragmentManager fragmentManager = getSupportFragmentManager();

                if(!deckViewExtended){
                    fragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right).replace(R.id.deckListView, cardDeckFragment).commit();
                    darkLayer.setVisibility(View.VISIBLE);
                    newDeck.setVisibility(View.INVISIBLE);
                    deckList.animate().translationX(deckListBtnPos + 350).start();
                    deckViewExtended = true;
                }
                else if(deckViewExtended){
                    try {
                        fragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right).remove(getSupportFragmentManager().findFragmentById(R.id.deckListView)).commit();
                    }catch (NullPointerException e){
                        Log.e("CardActivity", "ERROR! Boolean deckViewExtended was true. Setting it to false");
                        deckViewExtended = false;
                    }
                    darkLayer.setVisibility(View.GONE);
                    newDeck.setVisibility(View.VISIBLE);
                    deckList.animate().translationX(deckListBtnPos).start();
                    deckViewExtended = false;
                }
            }
        });
    }

    public void getNewCard(final List<Card> cardDeck, final ImageView imageView){
        if(cardDeck == null || cardDeck.size() <= 0){
            imageView.setImageResource(R.drawable.red_joker);
            return;
        }
        Random rng = new Random();
        int cardIndex = rng.nextInt(cardDeck.size());
        int cardViewId = getResources().getIdentifier(cardDeck.get(cardIndex).getId(), "drawable", getPackageName());
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
