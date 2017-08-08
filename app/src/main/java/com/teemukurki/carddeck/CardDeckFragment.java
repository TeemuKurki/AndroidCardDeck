package com.teemukurki.carddeck;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class CardDeckFragment extends Fragment {


    public CardDeckFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.d("CardDeckFragmet", "onCreateView");
        View view = inflater.inflate(R.layout.fragment_card_deck, container, false);

        ExpandableListView deckExpandable = (ExpandableListView) view.findViewById(R.id.deckExpandableView);

        ExpandableListViewAdapter adapter = new ExpandableListViewAdapter(getContext());

        deckExpandable.setAdapter(adapter);

        return view;
    }

}
