package com.codecool.dungeoncrawl.logic.map;

import com.codecool.dungeoncrawl.gui.Main;
import com.codecool.dungeoncrawl.logic.actors.Actor;
import com.codecool.dungeoncrawl.logic.actors.Player;

import java.util.*;

public class GameMap {
    private int width;
    private int height;
//    private List<GameMap> maps = new ArrayList<>();
//    private int level = 0;
    private Cell[][] cells;
    private final List<CellType> obstacles = Arrays.asList(CellType.WALL, CellType.CLOSE);
    private Player player;
    Main main;

    public GameMap(Main mapLoader,int width, int height, CellType defaultCellType) {
//        maps.add((GameMap) this.clone());
        this.main = mapLoader;
        this.width = width;
        this.height = height;
        cells = new Cell[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                cells[x][y] = new Cell(this, x, y, defaultCellType);
            }
        }
    }

    public Cell getCell(int x, int y) {
        return cells[x][y];
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public List<CellType> getObstacles() {
        return obstacles;
    }

    public List<Actor> getMobs() {
        List<Actor> mobs = new ArrayList<>();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (cells[x][y].getActor() != null
                        && ! (cells[x][y].getActor() instanceof Player)) {
                    mobs.add(cells[x][y].getActor());
                }
            }
        }
        return mobs;
    }

    public void removeDeadMobs(){
        List<Actor> mobs = getMobs();
        for (Actor mob: mobs){
            if (mob.getHealth() <= 0) mob.getCell().setActor(null);
        }
    }

    public boolean isPlayerOnCoords(int x, int y){
        return player.getX() == x && player.getY() == y;
    }

//    public void next() throws CloneNotSupportedException {
//        level++;
//        if (level >= maps.size()){
//            maps.add(mapLoader.loadMap(level));
//        }
//        width = maps.get(level).getWidth();
//        height = maps.get(level).getHeight();
//        cells = maps.get(level).getCells();
//
//
//        Player newPlayer = maps.get(level).getPlayer();
//        player.setCell(newPlayer.getCell());
//    }

    private Cell[][] getCells() {
        return cells;
    }

//    public void previous(){
//        level--;
//        width = maps.get(level).getWidth();
//        height = maps.get(level).getHeight();
//        cells = maps.get(level).getCells();
//
//        Player newPlayer = maps.get(level).getPlayer();
//        player.setCell(newPlayer.getCell());
//    }

    public Main getMain() {
        return main;
    }

    public boolean areCoordsOnMap(int i, int j) {
        return i >= 0 && i < width && j >= 0 && j < height;
    }
}
