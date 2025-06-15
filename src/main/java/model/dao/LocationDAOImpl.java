package model.dao;
import java.util.ArrayList;

import model.*;

import java.sql.*;


public class LocationDAOImpl implements LocationDAO {

    private Connection connection;

    public LocationDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void creatLocation(Location location) {
        try (PreparedStatement statement = connection.prepareStatement(
            "INSERT INTO massages (id , city , country , token) VALUES (? ,? , ? , ?)");
            Statement idStatement = connection.createStatement()) {
            statement.setInt(1, location.getId());
            statement.setString(2, location.getCity());
            statement.setString(3, location.getCountry());
            statement.setString(4, location.getToken());
            statement.executeUpdate();
            ResultSet resultSet = idStatement.executeQuery("SELECT LAST_INSERT_ID()");
            if (resultSet.next()) {
                location.setId(resultSet.getInt(1));
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Location getLocation(int id) {
        Location location = null;
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM locations WHERE id = ?")) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                location = new Location();
                location.setId(resultSet.getInt("id"));
                location.setCity(resultSet.getString("city"));
                location.setToken(resultSet.getString("token"));
                location.setCountry(resultSet.getString("country"));
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return location;
    }

    @Override
    public ArrayList<Location> getAllLocations() {
        ArrayList<Location> locations = new ArrayList<>();
        try (Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM massages")) {
            while (resultSet.next()) {
                Location location = new Location();
                location.setId(resultSet.getInt("id"));
                location.setCity(resultSet.getString("city"));
                location.setToken(resultSet.getString("token"));
                location.setCountry(resultSet.getString("country"));
                locations.add(location);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return locations;
    }
    

    @Override
    public void updateLocation(Location location) {
        try (PreparedStatement statement = connection.prepareStatement(
                "UPDATE locations SET id = ? ,  city = ? , country = ? , token = ?")) {
                    statement.setInt(1, location.getId());
                    statement.setString(2, location.getCity());
                    statement.setString(3, location.getCountry());
                    statement.setString(4, location.getToken());
                    statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    

    @Override
    public void deleteLocation(int id) {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM locations WHERE id = ?")) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
