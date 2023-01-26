package com.codecool.dungeoncrawl.logic.map;

import com.codecool.dungeoncrawl.logic.Drawable;
import com.codecool.dungeoncrawl.logic.Objects.Door;
import com.codecool.dungeoncrawl.logic.Objects.Entrance;
import com.codecool.dungeoncrawl.logic.Objects.NoEntry;
import com.codecool.dungeoncrawl.logic.Objects.Stairs;
import com.codecool.dungeoncrawl.logic.actors.*;
import com.codecool.dungeoncrawl.logic.items.*;

public class Cell implements Drawable {

    private CellType type;
    private Actor actor;
    private Item item;
    private Entrance entrance;
    private GameMap gameMap;
    private final int x, y;

    public Cell(GameMap gameMap, int x, int y, CellType type) {
        this.gameMap = gameMap;
        this.x = x;
        this.y = y;
        this.type = type;
        this.entrance = new NoEntry();
    }
    //na potrzeby testu ↓
    public Cell(int x, int y,CellType type) {
        this.type = type;
        this.x = x;
        this.y = y;
    }

    public void setEntrance(Entrance entrance) {
        this.entrance = entrance;
    }

    public CellType getType() {
        return type;
    }

    public void setType(CellType type) {
        this.type = type;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public Item getItem() {
        return item;
    }

    public boolean isItemOnCell() {
        return getItem() != null;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Actor getActor() {
        return actor;
    }

    public Cell getNeighbor(int dx, int dy) {
        return gameMap.getCell(x + dx, y + dy);
    }

    public boolean hasNeighbor(int dx, int dy){
        // return gameMap.areCoordsOnMap(x + dx, y + dy)
        return x + dx >= 0
                && x + dx < gameMap.getWidth()
                && y + dy >= 0
                && y + dy < gameMap.getHeight();
    }

    @Override
    public String getTileName() {
        return type.getTileName();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public GameMap getGameMap() {
        return gameMap;
    }

    public void tryToEnter(Player player)  {
        entrance.tryToEnter(this, player.getInventory());
    }

    public boolean canPlayerMoveOn(boolean canWalkThroughWalls) {
        return (canWalkThroughWalls || !gameMap.getObstacles().contains(type)) && actor == null;
    }

    public boolean containsEnemyThatCanBeAttacked() {
        return actor != null && !gameMap.getObstacles().contains(type);
    }

    public String getChar() {
        if (actor != null) {
            if (actor instanceof Player) return "@";
            if (actor instanceof Backbone) return "b";
            if (actor instanceof Ghost) return "g";
            if (actor instanceof Defender) return "d";
            if (actor instanceof Boss) return "B";
        }
        if (item != null) {
            if (item instanceof Key) return "k";
            if (item instanceof Shield) return "s";
            if (item instanceof Axe) return "a";
            if (item instanceof HealthPotion) return "p"; // "p" nie było do tej pory
        }
        if (entrance instanceof Stairs) {
            if (type == CellType.STAIRSUP) return "H";
            if (type == CellType.STAIRSDOWN) return "h";
        }
        if (entrance instanceof Door) {
            if (type == CellType.OPEN) return "o";
            if (type == CellType.CLOSE) return "l";
        }
        if (type == CellType.WALL) return "#";
        if (type == CellType.FLOOR) return ".";
        if (type == CellType.FAKEWIN) return "c";
        if (type == CellType.WIN) return "w";
        return " ";
    }
}
