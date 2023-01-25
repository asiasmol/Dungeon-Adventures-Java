package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.model.CellModel;
import com.codecool.dungeoncrawl.model.GameModel;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GameDaoJdbc implements GameDao {

    private DataSource dataSource;

    @Override
    public void add(GameModel game) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO game (player_id) VALUES (?)";
            PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setInt(1, game.getPlayerId());
            st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            rs.next();
            game.setId(rs.getInt(1));
        } catch (SQLException e) {
            throw new RuntimeException("Error while adding new game. ", e);
        }
    }

    @Override
    public void update(GameModel game) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "UPDATE game SET player_id = ? WHERE id = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, game.getPlayerId());
            st.setInt(2, game.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error by updating the game", e);
        }
    }

    @Override
    public GameModel get(int id) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT map_id, x, y FROM cell WHERE id = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (!rs.next()) {
                return null;
            }
            GameModel game = new GameModel(rs.getInt(2));
            game.setId(rs.getInt(1));
            return game;
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading cell with id = " + id, e);
        }
    }

    @Override
    public List<GameModel> getAll() {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT * FROM game";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            List<GameModel> games = new ArrayList<>();
            while (rs.next()) {
                GameModel game = new GameModel(rs.getInt(2));
                game.setId(rs.getInt(1));
                games.add(game);
            }
            return games;
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading all games. ", e);
        }
    }
}

