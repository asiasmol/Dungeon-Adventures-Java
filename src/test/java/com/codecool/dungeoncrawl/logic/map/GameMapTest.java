package com.codecool.dungeoncrawl.logic.map;

import static org.junit.jupiter.api.Assertions.*;

import com.codecool.dungeoncrawl.logic.actors.Actor;
import com.codecool.dungeoncrawl.logic.actors.Ghost;
import com.codecool.dungeoncrawl.logic.actors.Player;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class GameMapTest {

    @Test
    void testSetCell() {
        //given
        GameMap gameMap = new GameMap(2, 2, CellType.FLOOR);
        Cell cell = new Cell(1, 1, CellType.FLOOR);
        //when
        gameMap.setCell(cell);
        //then
        assertEquals(cell, gameMap.getCell(1, 1));
    }

    @Test
    void testGetCell() {
        //given
        GameMap gameMap = new GameMap(2, 2, CellType.FLOOR);
        Cell cell = new Cell(1, 1, CellType.FLOOR);
        gameMap.setCell(cell);
        //when
        Cell result = gameMap.getCell(1, 1);
        //then
        assertEquals(cell, result);
    }

    @Test
    void testSetPlayer(){
        //given
        GameMap gameMap = new GameMap(2, 2, CellType.FLOOR);
        Cell cell = new Cell(1, 1, CellType.FLOOR);
        Player player = new Player(cell);
        //when
        gameMap.setPlayer(player);
        //then
        assertEquals(player, gameMap.getPlayer());
    }

    @Test
    void testGetPlayer(){
        //given
        GameMap gameMap = new GameMap(2, 2, CellType.FLOOR);
        Cell cell = new Cell(1, 1, CellType.FLOOR);
        Player player = new Player(cell);
        gameMap.setPlayer(player);
        //when
        Player result = gameMap.getPlayer();
        //then
        assertEquals(player, result);
    }


    @Test
    void testGetHeight(){
        //given
        GameMap gameMap = new GameMap(2, 7, CellType.FLOOR);
        //when
        int height = gameMap.getHeight();
        //then
        assertEquals(7,height);
    }


    @Test
    void testGetObstacles(){
        //given
        GameMap gameMap = new GameMap(2, 2, CellType.FLOOR);
        //when
        List<CellType> result = List.of(CellType.WALL, CellType.CLOSE);
       //then
        assertEquals(result,gameMap.getObstacles());
    }

}