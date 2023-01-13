package com.codecool.dungeoncrawl.logic.Objects;

import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.logic.items.Key;
import com.codecool.dungeoncrawl.logic.map.Cell;
import com.codecool.dungeoncrawl.logic.map.CellType;
import com.codecool.dungeoncrawl.logic.map.GameMap;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

public class Door implements  Entrance{



    public void tryOpen(Cell cell, ArrayList<Item> items) {
        if (Objects.equals(cell.getType(),CellType.CLOSE )){
            Iterator<Item> it = items.iterator();
            while (it.hasNext()){
                Item i = it.next();
                if(i instanceof Key){
                    cell.setType(CellType.OPEN);
                    it.remove();
                }
            }
        }
    }

    @Override
    public void tryToEnter(Cell cell, ArrayList<Item> items) {
        tryOpen(cell,  items);
    }
}
