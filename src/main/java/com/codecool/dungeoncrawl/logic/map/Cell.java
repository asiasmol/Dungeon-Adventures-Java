package com.codecool.dungeoncrawl.logic.map;

import com.codecool.dungeoncrawl.logic.Drawable;
import com.codecool.dungeoncrawl.logic.Objects.Entrance;
import com.codecool.dungeoncrawl.logic.Objects.NoEntry;
import com.codecool.dungeoncrawl.logic.actors.Actor;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.items.Item;

public class Cell implements Drawable {
    private CellType type;
    private Actor actor;
    private Item item;
    private Entrance entrance;
    private GameMap gameMap;
    private final int x, y;

    Cell(GameMap gameMap, int x, int y, CellType type) {
        this.gameMap = gameMap;
        this.x = x;
        this.y = y;
        this.type = type;
        this.entrance = new NoEntry();
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

    public void tryToEnter(Player player) throws CloneNotSupportedException {
        entrance.tryToEnter(this, player.getInventory());
    }

    public boolean canPlayerMoveOn(boolean canWalkThroughWalls) {
        return (canWalkThroughWalls || !gameMap.getObstacles().contains(type)) && actor == null;
    }

    public boolean containsEnemyThatCanBeAttacked() {
        return actor != null && !gameMap.getObstacles().contains(type);
    }
}
