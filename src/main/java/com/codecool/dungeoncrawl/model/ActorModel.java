package com.codecool.dungeoncrawl.model;

public class ActorModel extends BaseModel{
    private int cell_id;
    private int health;

    public ActorModel(int cell_id, int health) {
        this.cell_id = cell_id;
        this.health = health;
    }


    public int getCell_id() {
        return cell_id;
    }

    public void setCell_id(int cell_id) {
        this.cell_id = cell_id;
    }

    public int getHealth() {
        return health;
    }
    public void setHealth(int health) {
        this.health = health;
    }

}
