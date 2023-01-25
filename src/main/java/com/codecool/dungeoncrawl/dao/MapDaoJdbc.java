package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.model.MapModel;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MapDaoJdbc implements MapDao{

    private DataSource dataSource;

    public MapDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    public void add(MapModel map) {
        try(Connection conn = dataSource.getConnection()){
            String sql = "INSERT INTO map (player_id, level, width, height) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1,map.getPlayer_id());
            statement.setInt(2,map.getLevel());
            statement.setInt(3,map.getWidth());
            statement.setInt(4,map.getHeight());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            map.setId(resultSet.getInt(1));
        } catch (
    SQLException e) {
        throw new RuntimeException(e);
    }
    }

    @Override
    public void update(MapModel map) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "UPDATE map SET player_id = ?, level = ?, width = ?, height = ? WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, map.getPlayer_id());
            statement.setInt(2, map.getLevel());
            statement.setInt(3, map.getWidth());
            statement.setInt(4, map.getHeight());
            statement.setInt(5, map.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public MapModel get(int id) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT player_id,level,width,height FROM map WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) return null;
            MapModel map = new MapModel(resultSet.getInt(2), resultSet.getInt(3), resultSet.getInt(4), resultSet.getInt(5));
            map.setId(id);
            return map;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<MapModel> getAll(int player_id) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT * FROM map WHERE player_id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, player_id);
            ResultSet resultSet = statement.executeQuery();
            List<MapModel> maps = new ArrayList<>();
            while (resultSet.next()){
                MapModel map = new MapModel(player_id, resultSet.getInt(3), resultSet.getInt(4), resultSet.getInt(5));
                map.setId(resultSet.getInt(1));
                maps.add(map);
            }
            return maps;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
