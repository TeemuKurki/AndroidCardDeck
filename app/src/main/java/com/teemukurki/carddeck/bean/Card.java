package com.teemukurki.carddeck.bean;

/**
 * Created by teemu on 26.6.2017.
 */

public class Card {
    private String id, suit, value;
    private boolean active;

    public Card(){

    }
    public Card(String id, String suit, String value, Boolean active){
        super();
        this.id = id;
        this.suit = suit;
        this.value = value;
        this.active = active;

    }

    public void setId(String id){
        this.id = id;
    }

    public String getId(){
        return id;
    }

    public void setSuit(String suit){
        this.suit = suit;
    }

    public String getSuit(){
        return suit;
    }

    public void setValue(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }

    public void setActive(){
        this.active = active;
    }

    public boolean getActive(){
        return active;
    }

    public String toString(){
        return id+" "+suit+" "+value+" "+String.valueOf(active);
    }

}
