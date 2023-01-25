package com.codecool.dungeoncrawl.model;

public class MapModel extends BaseModel{
    private int game_id;
    private int level;
    private int width;
    private int height;

    public MapModel(int game_id, int level, int width, int height) {
        this.game_id = game_id;
        this.level = level;
        this.width = width;
        this.height = height;
    }

    public int getGame_id() {
        return game_id;
    }

    public int getLevel() {
        return level;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }


    public void setGame_id(int game_id) {
        this.game_id = game_id;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }



}
