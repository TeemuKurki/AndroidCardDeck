package com.teemukurki.carddeck;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by teemu on 21.7.2017.
 */

public class ExpandableListViewAdapter extends BaseExpandableListAdapter {

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
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View view, ViewGroup parent) {
        final TextView txtView = new TextView(context);
        char suite = groupNames[groupPosition].charAt(0);
        txtView.setText(suite + childNames.get(groupPosition)[childPosition]);
        txtView.setPadding(120,10,10,0);
        txtView.setTextSize(20);
        txtView.setWidth(parent.getWidth());

        txtView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, txtView.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        return txtView;
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
        Log.d("generatedDeckSize",String.valueOf(childrenList.size()));
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
        Log.d("cardsListSize", String.valueOf(cards.size()));
        return cards.toArray(new String[position]);
    }
}

