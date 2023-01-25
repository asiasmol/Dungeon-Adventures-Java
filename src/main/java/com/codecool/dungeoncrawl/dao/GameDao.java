package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.model.GameModel;

import java.util.List;

public interface GameDao {

    void add(GameModel game);

    void update(GameModel game);

    GameModel get(int id);

    List<GameModel> getAll();
}
