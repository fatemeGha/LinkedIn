package model.dao;
import java.util.ArrayList;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.HashMap;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import model.*;

public class InformationToCallDAOImpl implements InformationToCallDAO{

    private Connection connection;
    private ObjectMapper mapper;

    public InformationToCallDAOImpl(Connection connection) {
        this.connection = connection;
        mapper = new ObjectMapper();

    }
    @Override
    public void creatInformationToCall(InformationToCall informationToCall) {
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO informationToCalls (id,,userLink,email,phoneNumber,communicationChannel,typePhone,token, address) VALUES (?,?, ?, ? , ? ,?,?)");
                Statement idStatement = connection.createStatement()) {
            String jsonTypePhone = mapper.writeValueAsString(informationToCall.getTypePhone());
            statement.setInt(1, informationToCall.getId());
            statement.setString(2, informationToCall.getUserLink());
            statement.setString(3, informationToCall.getEmail());
            statement.setString(4, informationToCall.getPhoneNumber());
            statement.setString(5, informationToCall.getCommunicationChannel());
            statement.setString(6, jsonTypePhone);
            statement.setString(7, informationToCall.getToken());
            statement.setString(8, informationToCall.getAddress());
            statement.executeUpdate();
            ResultSet resultSet = idStatement.executeQuery("SELECT LAST_INSERT_ID()");
            if (resultSet.next()) {
                informationToCall.setId(resultSet.getInt(1));
            }
            resultSet.close();
        } catch (SQLException | JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public InformationToCall getInformationToCall(int id) {
        InformationToCall informationToCall = null;
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM informationToCalls WHERE id = ?")) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String jsonTypePhone = resultSet.getString("typePhone");
                informationToCall = new InformationToCall();
                informationToCall.setId(resultSet.getInt("id"));
                informationToCall.setEmail(resultSet.getString("email"));
                informationToCall.setTypePhone(mapper.readValue(jsonTypePhone, new TypeReference<HashMap<String , Boolean>>() {}));
                informationToCall.setToken(resultSet.getString("token"));
                informationToCall.setPhoneNumber(resultSet.getString("phoneNumber"));
                informationToCall.setUserLink(resultSet.getString("userLink"));
                informationToCall.setAddress(resultSet.getString("address"));
                informationToCall.setCommunicationChannel(resultSet.getString("communicationChannel"));
            }
            resultSet.close();
        } catch (SQLException | JsonProcessingException e) {
            e.printStackTrace();
        }
        return informationToCall;
    }

    @Override
    public ArrayList<InformationToCall> getAllInformationToCalls() {
        ArrayList<InformationToCall> informationToCalls = new ArrayList<>();
        try (Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM informationToCalls")) {
            while (resultSet.next()) {
                InformationToCall informationToCall = new InformationToCall();
                String jsonInformationToCall = resultSet.getString("informationToCall");
                informationToCall.setId(resultSet.getInt("id"));
                informationToCall.setEmail(resultSet.getString("email"));
                informationToCall.setTypePhone(mapper.readValue(jsonInformationToCall, new TypeReference<HashMap<String , Boolean>>() {
                }));                
                informationToCall.setUserLink(resultSet.getString("userLink"));
                informationToCall.setToken(resultSet.getString("token"));
                informationToCall.setPhoneNumber(resultSet.getString("image"));
                informationToCall.setAddress(resultSet.getString("address"));
                informationToCall.setCommunicationChannel(resultSet.getString("communicationChannel"));
                informationToCalls.add(informationToCall);
            }
        } catch (SQLException | JsonProcessingException e) {
            e.printStackTrace();
        }
        return informationToCalls;
    }

    @Override
    public void updateInformationToCall(InformationToCall informationToCall) {
        try (PreparedStatement statement = connection.prepareStatement(
            "UPDATE informationToCalls SET id = ? , userLink = ?, email = ? , phoneNumber = ? , communicationChannel = ? , typePhone = ? , token = ? , address = ?")) {
                String jsonTypePhone = mapper.writeValueAsString(informationToCall.getTypePhone());
                statement.setInt(1, informationToCall.getId());
                statement.setString(2, informationToCall.getUserLink());
                statement.setString(3, informationToCall.getEmail());
                statement.setString(4, informationToCall.getPhoneNumber());
                statement.setString(5, informationToCall.getCommunicationChannel());
                statement.setString(6, jsonTypePhone);
                statement.setString(7, informationToCall.getToken());
                statement.setString(8, informationToCall.getAddress());
                statement.executeUpdate();
    } catch (SQLException | JsonProcessingException e) {
        e.printStackTrace();
    }
    }

    @Override
    public void deleteInformationToCall(int id) {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM informationToCalls WHERE id = ?")) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
