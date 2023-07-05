package dao.impl;

import dao.VentaDAO;
import model.Venta;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VentaDAOImplementation implements VentaDAO {
    String url = "jdbc:mysql://localhost:3306/tienda";
    String username = "root";
    String password = "root";

    @Override
    public void insert(Venta venta) {
        try (Connection con = DriverManager.getConnection(url, username, password)) {
            String sql = "INSERT INTO venta (marcaVenta, tipoVenta, cantidadVenta, totalVenta, idAire) VALUES (?, ?, ?, ?, ?)";
            String sql2 = "UPDATE aire SET cantidadAire = (cantidadAire - ?) WHERE idAire = ?";
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, venta.marcaVenta);
                ps.setString(2, venta.tipoVenta);
                ps.setInt(3, venta.cantidadVenta);
                ps.setDouble(4, venta.totalVenta);
                ps.setInt(5, venta.idAire);

                ps.executeUpdate();
            }
            try (PreparedStatement ps1 = con.prepareStatement(sql2)) {
                ps1.setInt(1, venta.cantidadVenta);
                ps1.setInt(2, venta.idAire);

                ps1.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Venta venta) {

        try (Connection con = DriverManager.getConnection(url, username, password)) {
            String sql = "UPDATE venta SET cantidadVenta = ? WHERE idVenta = ?";
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, venta.cantidadVenta);
                ps.setInt(2, venta.idVenta);

                ps.executeUpdate();

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int delete(Venta venta) {
        int affectedRows = 0;

        try (Connection con = DriverManager.getConnection(url, username, password)) {
            String sql = "DELETE FROM venta WHERE idVenta = ?";
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, venta.idVenta);

                affectedRows = ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return affectedRows;
    }

    @Override
    public Venta getByID(int id) {
        try (Connection con = DriverManager.getConnection(url, username, password)) {
            String sql = "SELECT * FROM venta WHERE idVenta = ?";
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    int idVenta = rs.getInt(1);
                    String marcaVenta = rs.getString(2);
                    String tipoVenta = rs.getString(3);
                    int cantidadVenta = rs.getInt(4);
                    double totalVenta = rs.getDouble(5);
                    int idAire = rs.getInt(6);

                    Venta venta = new Venta();
                    venta.idVenta = idVenta;
                    venta.marcaVenta = marcaVenta;
                    venta.tipoVenta = tipoVenta;
                    venta.cantidadVenta = cantidadVenta;
                    venta.totalVenta = totalVenta;
                    venta.idAire = idAire;

                    return venta;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Venta> showAll() {
        List<Venta> ventaList = new ArrayList<>();

        try (Connection con = DriverManager.getConnection(url, username, password)) {
            String sql = "SELECT * FROM venta";

            try (PreparedStatement ps = con.prepareStatement(sql)) {

                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    int idVenta = rs.getInt(1);
                    String marcaVenta = rs.getString(2);
                    String tipoVenta = rs.getString(3);
                    int cantidadVenta = rs.getInt(4);
                    double totalVenta = rs.getDouble(5);
                    int idAire = rs.getInt(6);

                    Venta venta = new Venta();
                    venta.idVenta = idVenta;
                    venta.marcaVenta = marcaVenta;
                    venta.tipoVenta = tipoVenta;
                    venta.cantidadVenta = cantidadVenta;
                    venta.totalVenta = totalVenta;
                    venta.idAire = idAire;

                    ventaList.add(venta);
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ventaList;
    }

    @Override
    public void refresh(Venta venta, int t) {
        try (Connection con = DriverManager.getConnection(url, username, password)) {
            String sql = "UPDATE aire SET cantidadAire = (cantidadAire + ?) WHERE idAire = ?";
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, t);
                ps.setInt(2, venta.idAire);

                ps.executeUpdate();

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
