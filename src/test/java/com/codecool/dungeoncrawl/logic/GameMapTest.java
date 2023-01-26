package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.gui.Main;
import com.codecool.dungeoncrawl.logic.map.Cell;
import com.codecool.dungeoncrawl.logic.map.CellType;
import com.codecool.dungeoncrawl.logic.map.GameMap;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameMapTest {

    @Test
    void testGetCell(){
        //given
        Main main = new Main();
        GameMap gameMap = new GameMap(main, 1,1,CellType.FLOOR);
        Cell cell = new Cell(gameMap, 1, 1, CellType.FLOOR);
        gameMap.setCell(cell);
        //when
        Cell result = gameMap.getCell(1,1);
        //then
        assertEquals(cell,result);
    }

}