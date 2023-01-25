package com.codecool.dungeoncrawl.logic.Objects;

import com.codecool.dungeoncrawl.gui.Main;
import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.logic.items.Key;
import com.codecool.dungeoncrawl.logic.map.Cell;
import com.codecool.dungeoncrawl.logic.map.CellType;

import java.util.ArrayList;
import java.util.Objects;

public class Stairs implements Entrance{

    @Override
    public void tryToEnter(Cell cell, ArrayList<Item> items) throws CloneNotSupportedException {
        goDown(cell);
    }

    public void goDown(Cell cell) throws CloneNotSupportedException {
        if (Objects.equals(cell.getType(), CellType.STAIRSDOWN )){
            cell.getGameMap().next();
        }
        if (Objects.equals(cell.getType(), CellType.STAIRSUP )){
            cell.getGameMap().previous();
        }
    }
}
