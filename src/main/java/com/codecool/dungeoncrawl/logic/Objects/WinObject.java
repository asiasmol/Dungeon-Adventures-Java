package com.codecool.dungeoncrawl.logic.Objects;
import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.logic.map.Cell;
import com.codecool.dungeoncrawl.logic.map.CellType;

import java.util.ArrayList;
import java.util.Objects;
public class WinObject  implements Entrance{

    public void tryToEnter(Cell cell, ArrayList<Item> items) {
        checkWin(cell);
    }
    public static void checkWin( Cell cell) {
        if (Objects.equals(cell.getType(), CellType.WIN)) {
//            cell.getGameMap().getMain().win();
        }
    }
}
