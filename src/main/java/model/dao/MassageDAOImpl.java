package model.dao;
import java.util.ArrayList;
import java.sql.*;
import java.time.LocalDateTime;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import model.*;


//// setId check beshe
public class MassageDAOImpl implements MassageDAO {
    private Connection connection;
    private ObjectMapper mapper;

    public MassageDAOImpl(Connection connection) {
        this.connection = connection;
        mapper = new ObjectMapper();

    }

    @Override
    public void creatMassage(Massage massage) {
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO massages (text,,sender,reciever,date,replyToMassage,image, token) VALUES (?, ?, ? , ? ,? ,?)");
                Statement idStatement = connection.createStatement()) {
            String jsonSender = mapper.writeValueAsString(massage.getSender());
            String jsonReciever = mapper.writeValueAsString(massage.getReciever());
            String jsonDate = mapper.writeValueAsString(massage.getDate());
            String jsonReplyToMassage = mapper.writeValueAsString(massage.getReplyToMassage());
            statement.setString(1, massage.getText());
            statement.setString(2, jsonSender);
            statement.setString(3, jsonReciever);
            statement.setString(4, jsonDate);
            statement.setString(5, jsonReplyToMassage);
            statement.setString(6, massage.getImage());
            statement.setString(7, massage.getToken());
            statement.executeUpdate();
            ResultSet resultSet = idStatement.executeQuery("SELECT LAST_INSERT_ID()");
            if (resultSet.next()) {
                massage.setId(resultSet.getInt(1));
            }
            resultSet.close();
        } catch (SQLException | JsonProcessingException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Massage getMassage(int id) {
        Massage massage = null;
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM massages WHERE id = ?")) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String jsonSender = resultSet.getString("sender");
                String jsonReciever = resultSet.getString("reciever");
                String jsonDate = resultSet.getString("date");
                String jsonReplyToMassage = resultSet.getString("replyToMassage");
                massage = new Massage();
                massage.setId(resultSet.getInt("id"));
                massage.setText(resultSet.getString("text"));
                massage.setSender(mapper.readValue(jsonSender, new TypeReference<User>() {
                }));
                massage.setReciever(mapper.readValue(jsonReciever, new TypeReference<User>() {
                }));
                massage.setDate(mapper.readValue(jsonDate, new TypeReference<LocalDateTime>() {
                }));
                massage.setReplyToMassage(mapper.readValue(jsonReplyToMassage, new TypeReference<Massage>() {
                }));
                massage.setToken(resultSet.getString("token"));
                massage.setImage(resultSet.getString("image"));
            }
            resultSet.close();
        } catch (SQLException | JsonProcessingException e) {
            e.printStackTrace();
        }
        return massage;
    }

    @Override
    public void deleteMassage(int id) {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM massages WHERE id = ?")) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateMassage(Massage massage) {
        try (PreparedStatement statement = connection.prepareStatement(
                "UPDATE massages SET id = ? ,  sender = ? , reciever = ? , date = ? , replyToMassage = ? , image = ? , token = ?,text = ?")) {
                        String jsonSender = mapper.writeValueAsString(massage.getSender());
                        String jsonReciever= mapper.writeValueAsString(massage.getReciever());
                        String jsonDate = mapper.writeValueAsString(massage.getDate());
                        String jsonReplyToMassage = mapper.writeValueAsString(massage.getReplyToMassage());
                        statement.setInt(1 , massage.getId());
                        statement.setString(2, jsonSender);
                        statement.setString(3, jsonReciever);
                        statement.setString(4, jsonDate);
                        statement.setString(5, jsonReplyToMassage);
                        statement.setString(6, massage.getImage());
                        statement.setString(7, massage.getToken());
                        statement.setString(8, massage.getText());
                        statement.executeUpdate();
        } catch (SQLException | JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Massage> getAllMassages() {
        ArrayList<Massage> massages = new ArrayList<>();
        try (Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM massages")) {
            while (resultSet.next()) {
                Massage massage = new Massage();
                String jsonSender = resultSet.getString("sender");
                String jsonReciever = resultSet.getString("reciever");
                String jsonDate = resultSet.getString("date");
                String jsonReplyToMassage = resultSet.getString("replyToMassage");
                massage.setId(resultSet.getInt("id"));
                massage.setText(resultSet.getString("text"));
                massage.setSender(mapper.readValue(jsonSender, new TypeReference<User>() {
                }));
                massage.setReciever(mapper.readValue(jsonReciever, new TypeReference<User>() {
                }));
                massage.setDate(mapper.readValue(jsonDate, new TypeReference<LocalDateTime>() {
                }));
                massage.setReplyToMassage(mapper.readValue(jsonReplyToMassage, new TypeReference<Massage>() {
                }));
                massage.setToken(resultSet.getString("token"));
                massage.setImage(resultSet.getString("image"));
                massages.add(massage);
            }
        } catch (SQLException | JsonProcessingException e) {
            e.printStackTrace();
        }
        return massages;
    }
}
