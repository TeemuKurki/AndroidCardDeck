package com.teemukurki.carddeck.bean;

import java.util.List;

/**
 * Created by teemu on 5.8.2017.
 */

public class ActiveCardDeck {
    List<Card> activeList;

    public ActiveCardDeck(){

    }

    public ActiveCardDeck(List<Card> activeList) {
        this.activeList = activeList;
    }

    public List<Card> getActiveList() {
        return activeList;
    }

    public void setActiveList(List<Card> activeList) {
        this.activeList = activeList;
    }

    @Override
    public String toString() {
        return "ActiveCardDeck{" +
                "activeList=" + activeList +
                '}';
    }
}
