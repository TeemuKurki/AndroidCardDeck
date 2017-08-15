package com.teemukurki.carddeck;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.teemukurki.carddeck.bean.ActiveCardDeck;
import com.teemukurki.carddeck.bean.Card;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by teemu on 21.7.2017.
 */

public class ExpandableListViewAdapter extends BaseExpandableListAdapter {

    public static List<Card> activeCardDeckList = new ArrayList<Card>();

    String[] groupNames = {"Hearts", "Spades", "Diamonds", "Clubs"};

    List<String[]> childNames = generateCardDeckForList(groupNames);

    Context context;

    public ExpandableListViewAdapter(Context context){
        this.context = context;
    }

    @Override
    public int getGroupCount() {
        return groupNames.length;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return childNames.get(groupPosition).length;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupNames[groupPosition];
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childNames.get(groupPosition)[childPosition];
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View view, ViewGroup parent) {
        TextView txtView = new TextView(context);
        txtView.setText(groupNames[groupPosition]);
        txtView.setPadding(100,0,0,0);
        txtView.setTextSize(40);
        return txtView;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View view, final ViewGroup parent) {
        final CheckBox checkBox = new CheckBox(context);
        char suite = groupNames[groupPosition].charAt(0);
        String text = suite + childNames.get(groupPosition)[childPosition];
        checkBox.setText(suite + childNames.get(groupPosition)[childPosition]);
        checkBox.setPadding(120,10,10,0);
        checkBox.setTextSize(20);
        checkBox.setWidth(parent.getWidth());

        for (Card c : activeCardDeckList) {
            if(c.getId().equals(text.toLowerCase())){
                checkBox.setChecked(true);
            }
        }

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(checkBox.isChecked()){
                    activeCardDeckList.add(new Card(checkBox.getText().toString().toLowerCase(), groupNames[groupPosition], childNames.get(groupPosition)[childPosition], true));
                }
                else {
                    Iterator<Card> iter = activeCardDeckList.iterator();
                    while (iter.hasNext()){
                        if(iter.next().getId().equals(checkBox.getText().toString().toLowerCase())){
                            iter.remove();
                        };
                    }
                }
            }
        });
        return checkBox;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    public List<String[]> generateCardDeckForList(String[] groups){
        List<String[]> childrenList = new ArrayList<>();
        for (int i = 0; i < groups.length; i++){
            childrenList.add(generateOneChildGroup(i));
        }
        return childrenList;
    }

    public String[] generateOneChildGroup(int position){
        List<String> cards = new ArrayList<>();
        for (int i = 2; i < 11; i++){
            String value = String.valueOf(i);
            cards.add(value);
        }
        cards.add("J");
        cards.add("Q");
        cards.add("K");
        cards.add("A");
        return cards.toArray(new String[position]);
    }
}

