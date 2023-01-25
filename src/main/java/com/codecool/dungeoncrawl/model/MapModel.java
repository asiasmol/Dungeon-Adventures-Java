package com.codecool.dungeoncrawl.model;


public class MapModel extends BaseModel{
    private int player_id;
    private int level;
    private int width;
    private int height;

    public MapModel(int game_id, int level, int width, int height) {
        this.player_id = game_id;
        this.level = level;
        this.width = width;
        this.height = height;
    }

    public int getPlayer_id() {
        return player_id;
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


    public void setPlayer_id(int player_id) {
        this.player_id = player_id;
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
