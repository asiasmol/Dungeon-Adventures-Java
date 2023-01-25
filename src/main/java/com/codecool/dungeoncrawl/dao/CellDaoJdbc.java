package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.model.CellModel;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CellDaoJdbc implements CellDao{

    private DataSource dataSource;

    @Override
    public void add(CellModel cell) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO cell (map_id, x, y, type) VALUES (?, ?, ?, ?)";
            PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setInt(1, cell.getMapId());
            st.setInt(2, cell.getX());
            st.setInt(3, cell.getY());
            st.setString(4, cell.getType());
            st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            rs.next();
            cell.setId(rs.getInt(1));
        } catch (SQLException e) {
            throw new RuntimeException("Error while adding new cell. ", e);
        }
    }

    @Override
    public void update(CellModel cell) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "UPDATE cell SET map_id = ?, x = ?, y = ?, type = ? WHERE id = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, cell.getMapId());
            st.setInt(2, cell.getX());
            st.setInt(3, cell.getY());
            st.setString(4, cell.getType());
            st.setInt(5, cell.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error by updating the author", e);
        }
    }

    @Override
    public CellModel get(int id) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT map_id, x, y FROM cell WHERE id = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (!rs.next()) return null;
            CellModel cell = new CellModel(rs.getInt(1),
                    rs.getInt(2),
                    rs.getInt(3),
                    rs.getString(4));
            cell.setId(id);
            return cell;
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading cell with id = " + id, e);
        }
    }

    @Override
    public List<CellModel> getAll(int mapId) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT * FROM cell WHERE map_id = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, mapId);
            ResultSet rs = st.executeQuery();
            List<CellModel> cells = new ArrayList<>();
            while (rs.next()){
                CellModel cell = new CellModel(mapId, rs.getInt(3),
                        rs.getInt(4),
                        rs.getString(5));
                cell.setId(rs.getInt(1));
                cells.add(cell);
            }
            return cells;
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading all authors", e);
        }
    }
}
