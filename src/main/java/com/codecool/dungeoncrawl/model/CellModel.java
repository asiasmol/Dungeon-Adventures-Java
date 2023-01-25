package com.codecool.dungeoncrawl.model;

import com.codecool.dungeoncrawl.logic.map.Cell;

public class CellModel {

    private int id;
    private int mapId;
    private int x;
    private int y;
    private String type;

    public CellModel(int mapId, int x, int y, String type) {
        this.mapId = mapId;
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public CellModel(int mapId, Cell cell) {
        this.mapId = mapId;
        this.x = cell.getX();
        this.y = cell.getY();
        this.type = cell.getChar();
    }

    public int getId() {
        return id;
    }

    public int getMapId() {
        return mapId;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getType() {
        return type;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMapId(int mapId) {
        this.mapId = mapId;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setType(String type) {
        this.type = type;
    }
}
