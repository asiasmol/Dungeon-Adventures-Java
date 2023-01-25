package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Objects.WinObject;
import com.codecool.dungeoncrawl.logic.map.Cell;
import com.codecool.dungeoncrawl.logic.items.Item;
import java.util.ArrayList;
import java.util.List;


public class Player extends Actor {
    String name = "player";
    private static final List<String> NAMES = List.of("piotr", "adrian", "galyna", "karolina");
    boolean canWalkThroughWalls;

    ArrayList<Item> items = new ArrayList<>();

    public Player(Cell cell) {
        super(cell);
        health = 50;
        damage = 20;
    }


    public void move(int dx, int dy) throws CloneNotSupportedException {
        Cell nextCell = cell.getNeighbor(dx, dy);
        if (nextCell == null) {
            return;
        }
        if (nextCell.containsEnemyThatCanBeAttacked()) {
            fight(nextCell.getActor());
        }
        if (nextCell.canPlayerMoveOn(canWalkThroughWalls)) {
            changeCell(dx, dy);
        }
        nextCell.tryToEnter(this);
        cell.getGameMap().getMobs().forEach(Actor::move);
    }

    public ArrayList<Item> getInventory() {
        return items;
    }

    public String getTileName() {
        return "player";
    }

    @Override
    public void move() {}

    public void pickUpItem(){
        if (cell.getItem()==null) return;
        items.add(cell.getItem());
        damage += cell.getItem().getDamage();
        health += cell.getItem().getHealth();
        cell.setItem(null);
    }

    public void setName(String name) {
        this.name = name;
        if (NAMES.contains(name.toLowerCase())) {
            canWalkThroughWalls = true;
        }
    }

    public String getName() {
        return name;
    }

    public boolean CanWalkThroughWalls() {
        return canWalkThroughWalls;
    }

    public void setAttributes(ArrayList<Item> items, int health, int damage, String name, boolean canWalkThroughWalls){
        this.health = health;
        this.damage = damage;
        this.items = items;
        this.name = name;
        this.canWalkThroughWalls = canWalkThroughWalls;
    }
}
