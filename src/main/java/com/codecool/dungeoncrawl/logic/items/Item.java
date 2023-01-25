package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Drawable;
import com.codecool.dungeoncrawl.logic.map.Cell;

public abstract class Item implements Drawable {

    private Cell cell;

    private int health = 0;
    private int damage = 0;

    public Item(Cell cell) {
        this.cell = cell;
        this.cell.setItem(this);
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public Cell getCell() {
        return cell;
    }


}
