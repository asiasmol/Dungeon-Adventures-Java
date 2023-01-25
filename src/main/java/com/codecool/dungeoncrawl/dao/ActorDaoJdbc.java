package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.model.ActorModel;
import com.codecool.dungeoncrawl.model.PlayerModel;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ActorDaoJdbc implements ActorDao {

    private DataSource dataSource;
    @Override
    public void add(ActorModel actor) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO actor (id,cell_id, health) VALUES (?, ?, ?)";
            PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setInt(2, actor.getCell_id());
            st.setInt(3, actor.getHealth());
            st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            rs.next();
            actor.setId(rs.getInt(1));
        } catch (SQLException e) {
            throw new RuntimeException("Error while adding new actor. ", e);
        }
    }

    @Override
    public void update(ActorModel actor) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "UPDATE actor SET cell_id = ?, health = ? WHERE id = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, actor.getCell_id());
            st.setInt(2, actor.getHealth());
            st.setInt(3, actor.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error by updating the actor", e);
        }

    }

    @Override
    public ActorModel get(int id) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT cell_id, health FROM actor WHERE id = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (!rs.next()) {
                return null;
            }
            ActorModel actor = new ActorModel(rs.getInt(2),
                    rs.getInt(3));
            actor.setId(id);
            return actor;
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading actor with id = " + id, e);
        }
    }

    @Override
    public List<ActorModel> getAll(int cell_id) {
            try (Connection conn = dataSource.getConnection()) {
                String sql = "SELECT * FROM actor WHERE cell_id = ?";
                PreparedStatement st = conn.prepareStatement(sql);
                st.setInt(1, cell_id);
                ResultSet rs = st.executeQuery();
                List<ActorModel> actors = new ArrayList<>();
                while (rs.next()){
                    ActorModel actor = new ActorModel(rs.getInt(2), rs.getInt(3));
                    actor.setId(rs.getInt(1));
                    actors.add(actor);
                }
                return actors;
            } catch (SQLException e) {
                throw new RuntimeException("Error while reading all actors", e);
            }
    }
}
