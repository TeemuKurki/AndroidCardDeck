package com.teemukurki.carddeck;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.teemukurki.carddeck.bean.Card;

import java.util.ArrayList;

/**
 * Created by teemu on 8.7.2017.
 */

/*
public class CustomListViewAdapter extends ArrayAdapter<Card> {
    private ArrayList<Card> cardList;

    public  CustomListViewAdapter(Context context, int textViewResourceId,
                                  ArrayList<Card> cardList){
        super(context,textViewResourceId,cardList);
        this.cardList = new ArrayList<Card>();
        this.cardList.addAll(cardList);
    }

    private Class ViewHolder{
        TextView lable;
        CheckBox cb;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        ViewHolder holder = null;
        if (convertView == null){
            LayoutInflater vi = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        }
    }
}*/
