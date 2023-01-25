package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.model.ActorModel;
import com.codecool.dungeoncrawl.model.PlayerModel;

import java.util.List;

public interface ActorDao {

    void add(ActorModel actor);
    void update(ActorModel actor);
    ActorModel get(int id);
    List<ActorModel> getAll(int cell_id);
}
