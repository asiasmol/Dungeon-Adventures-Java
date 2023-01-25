package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.model.MapModel;

import java.util.List;

public interface MapDao {
    void add(MapModel model);
    void update(MapModel model);
    MapModel get(int id);
    List<MapModel> getAll(int game_id);

}
