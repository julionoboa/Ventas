package dao.impl;

import dao.AireDAO;
import model.Aire;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AireDAOImplementation implements AireDAO {
    String url = "jdbc:mysql://localhost:3306/tienda";
    String username = "root";
    String password = "root";
    @Override
    public void insert(Aire aire) {
        try (Connection con = DriverManager.getConnection(url,username,password)){
            String sql = "INSERT INTO aire (marcaAire, tipoAire, cantidadAire, precioAire) VALUES (?, ?, ?, ?)";
            try (PreparedStatement ps = con.prepareStatement(sql)){
                ps.setString(1, aire.marcaAire);
                ps.setString(2,aire.tipoAire);
                ps.setInt(3,aire.cantidadAire);
                ps.setDouble(4,aire.precioAire);

                ps.executeUpdate();
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void update(Aire aire) {
        try(Connection con = DriverManager.getConnection(url, username, password)){
            String sql = "UPDATE Aire SET cantidadAire = ? WHERE idAire = ?";
            try(PreparedStatement ps = con.prepareStatement(sql)){
                ps.setInt(1, aire.cantidadAire);
                ps.setInt(2, aire.idAire);

                ps.executeUpdate();
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

    }

    @Override
    public int delete(Aire aire) {
        int affectedRows = 0;

        try(Connection con = DriverManager.getConnection(url, username, password)){
            String sql = "DELETE FROM Aire WHERE idAire = ?";
            try(PreparedStatement ps = con.prepareStatement(sql)){
                ps.setInt(1, aire.idAire);

                affectedRows = ps.executeUpdate();
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return affectedRows;
    }

    @Override
    public Aire getByID(int id) {
        try(Connection con = DriverManager.getConnection(url, username, password)){
            String sql = "SELECT * FROM Aire WHERE idAire = ?";
            try(PreparedStatement ps = con.prepareStatement(sql)){
                ps.setInt(1, id);
                ResultSet rs =ps.executeQuery();

                while (rs.next()){
                    int idAire = rs.getInt(1);
                    String marcaAire = rs.getString(2);
                    String tipoAire = rs.getString(3);
                    int cantidadAire = rs.getInt(4);
                    double precioAire = rs.getDouble(5);

                    Aire aire = new Aire();
                    aire.idAire = idAire;
                    aire.marcaAire = marcaAire;
                    aire.tipoAire = tipoAire;
                    aire.cantidadAire = cantidadAire;
                    aire.precioAire = precioAire;

                    return aire;
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Aire> showAll() {
        List<Aire> aireList = new ArrayList<>();

        try(Connection con = DriverManager.getConnection(url, username, password)){
            String sql = "SELECT * FROM aire";

            try(PreparedStatement ps = con.prepareStatement(sql)){

                ResultSet rs = ps.executeQuery();

                while (rs.next()){
                    int idAire = rs.getInt(1);
                    String marcaAire = rs.getString(2);
                    String tipoAire = rs.getString(3);
                    int cantidadAire = rs.getInt(4);
                    double precioAire = rs.getDouble(5);

                    Aire aire = new Aire();
                    aire.idAire = idAire;
                    aire.marcaAire = marcaAire;
                    aire.tipoAire = tipoAire;
                    aire.cantidadAire = cantidadAire;
                    aire.precioAire = precioAire;

                    aireList.add(aire);
                }

            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return aireList;
    }

    @Override
    public void refresh(Aire entity, int t) {

    }
}

