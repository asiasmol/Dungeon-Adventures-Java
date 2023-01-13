package com.codecool.dungeoncrawl.logic.Objects;

import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.logic.map.Cell;

import java.util.ArrayList;

public class NoEntry implements Entrance {
    @Override
    public void tryToEnter(Cell cell, ArrayList<Item> items) {

    }
}
