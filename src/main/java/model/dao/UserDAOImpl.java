package model.dao;
import java.util.ArrayList;

import model.*;

import java.sql.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


public class UserDAOImpl implements UserDAO {
    private Connection connection;
    private ObjectMapper mapper;

    public UserDAOImpl(Connection connection) {
        this.connection = connection;
        mapper = new ObjectMapper();
    }

    @Override
    public void creatUser(User user) {
        user.setUpdated(false);
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO users (email ,job, password, firstName, lastName , additionalName , aducation , professtion , profilePhoto , backgroundPhoto , header , token , location , followers , followings , connects , posts , intentionToHire , blocks) VALUES (?, ?, ?, ? , ? ,? ,? ,? ,? ,? ,?,?,?,?,?,?,?,?,?)");
                Statement idStatement = connection.createStatement()) {
        
            statement.setString(1, user.getJob());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getFirstName());
            statement.setString(5, user.getLastName());
            statement.setString(6, user.getAdditionalName());
            statement.setString(7, user.getAducation());
            statement.setString(8, user.getProfession());
            statement.setString(9, user.getProfilePhoto());
            statement.setString(10, user.getBackgroundPhoto());
            statement.setString(11, user.getHeader());
            statement.setString(12, user.getLocation());
            statement.setString(13, user.getFollowers());
            statement.setString(14, user.getFollowings());
            statement.setString(15, user.getConnects());
            statement.setString(16, user.getPosts());
            statement.setString(17, user.getIntentionToHire());
            statement.setString(18, user.getBlocks());
            statement.executeUpdate();
            user.setUpdated(true);
            ResultSet resultSet = idStatement.executeQuery("SELECT LAST_INSERT_ID()");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public User getUserById(int id) {
        User user = new User();
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE id = ?")) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setJob(resultSet.getString("job"));
                user.setPassword(resultSet.getString("password"));
                user.setEmail(resultSet.getString("email"));
                user.setFirstName(resultSet.getString("firstName"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAdditionalName(resultSet.getString("additionalName"));
                user.setAducation(resultSet.getString("aducation"));
                user.setProfession(resultSet.getString("professtion"));
                user.setProfilePhoto(resultSet.getString("profilePhoto"));
                user.setBackgroundPhoto(resultSet.getString("backgroundPhoto"));
                user.setHeader(resultSet.getString("header"));
                user.setToken(resultSet.getString("token"));
                user.setLocation(resultSet.getString("location"));
                user.setFollowers(resultSet.getString("followers"));
                user.setFollowings(resultSet.getString("followings"));
                user.setConnects(resultSet.getString("connects"));
                user.setPosts(resultSet.getString("posts"));
                user.setIntentionToHire(resultSet.getString("intentionToHire"));
                user.setBlocks(resultSet.getString("blocks"));

            }
            resultSet.close();
        } catch (SQLException  e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User getUserByEmail(String email) {
        User user = new User() ;
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE email = ?")) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String jsonFollowers = resultSet.getString("followers");
                String jsonFollowings = resultSet.getString("followings");
                String jsonConnects = resultSet.getString("connects");
                String jsonPosts = resultSet.getString("posts");
                String jsonBlocks = resultSet.getString("blocks");
                String jsonJob = resultSet.getString("job");
                String jsonAducation = resultSet.getString("aducation");
                String jsonLocation = resultSet.getString("location");
                user.setId(resultSet.getInt("id"));
                user.setJob(resultSet.getString("job"));
                user.setPassword(resultSet.getString("password"));
                user.setEmail(resultSet.getString("email"));
                user.setFirstName(resultSet.getString("firstName"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAdditionalName(resultSet.getString("additionalName"));
                user.setAducation(resultSet.getString("aducation"));
                user.setProfession(resultSet.getString("professtion"));
                user.setProfilePhoto(resultSet.getString("profilePhoto"));
                user.setBackgroundPhoto(resultSet.getString("backgroundPhoto"));
                user.setHeader(resultSet.getString("header"));
                user.setToken(resultSet.getString("token"));
                user.setLocation(resultSet.getString("location"));
                user.setFollowers(resultSet.getString("followers"));
                user.setFollowings(resultSet.getString("followings"));
                user.setConnects(resultSet.getString("connects"));
                user.setPosts(resultSet.getString("posts"));
                user.setIntentionToHire(resultSet.getString("intentionToHire"));
                user.setBlocks(resultSet.getString("blocks"));

            }
            resultSet.close();
        } catch (SQLException  e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void deleteUser(int id) {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM users WHERE id = ?")) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateUser(User user) {
        user.setUpdated(false);
        try (PreparedStatement statement = connection.prepareStatement(
                "UPDATE users SET job= ?, password= ?, email= ?, firstName = ?, lastName = ?, additionalName = ?, aducation = ?, professtion = ?, profilePhoto = ?, backgroundPhoto = ?, header = ?, token = ?, location= ? , followers = ?, followings = ?, connects = ?, posts = ?, intentionToHire = ?)")) {
            statement.setString(1, user.getJob());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getFirstName());
            statement.setString(5, user.getLastName());
            statement.setString(6, user.getAdditionalName());
            statement.setString(7, user.getAducation());
            statement.setString(8, user.getProfession());
            statement.setString(9, user.getProfilePhoto());
            statement.setString(10, user.getBackgroundPhoto());
            statement.setString(11, user.getHeader());
            statement.setString(12, user.getLocation());
            statement.setString(13, user.getFollowers());
            statement.setString(14, user.getFollowings());
            statement.setString(15, user.getConnects());
            statement.setString(16, user.getPosts());
            statement.setString(17, user.getIntentionToHire());
            statement.setString(18, user.getBlocks());
            //statement.setInt(6, user.getId());
            statement.executeUpdate();
            user.setUpdated(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<User> getAllUsers() {
       ArrayList<User> users = new ArrayList<>();
       try(Statement statement = connection.createStatement();
       ResultSet resultSet = statement.executeQuery("SELECT * FROM users")){
        while (resultSet.next()) {
            String jsonFollowers = resultSet.getString("followers");
            String jsonFollowings = resultSet.getString("followings");
            String jsonConnects = resultSet.getString("connects");
            String jsonPosts = resultSet.getString("posts");
            String jsonBlocks = resultSet.getString("blocks");
            String jsonJob = resultSet.getString("job");
            String jsonAducation = resultSet.getString("aducation");
            String jsonLocation = resultSet.getString("location"); 
            User user = new User();
            user.setId(resultSet.getInt("id"));
                user.setJob(resultSet.getString("job"));
                user.setPassword(resultSet.getString("password"));
                user.setEmail(resultSet.getString("email"));
                user.setFirstName(resultSet.getString("firstName"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAdditionalName(resultSet.getString("additionalName"));
                user.setAducation(resultSet.getString("aducation"));
                user.setProfession(resultSet.getString("professtion"));
                user.setProfilePhoto(resultSet.getString("profilePhoto"));
                user.setBackgroundPhoto(resultSet.getString("backgroundPhoto"));
                user.setHeader(resultSet.getString("header"));
                user.setToken(resultSet.getString("token"));
                user.setLocation(resultSet.getString("location"));
                user.setFollowers(resultSet.getString("followers"));
                user.setFollowings(resultSet.getString("followings"));
                user.setConnects(resultSet.getString("connects"));
                user.setPosts(resultSet.getString("posts"));
                user.setIntentionToHire(resultSet.getString("intentionToHire"));
                user.setBlocks(resultSet.getString("blocks"));
            users.add(user);
        }
       }
       catch(SQLException e){
            e.printStackTrace();
       }
       return users;
    }

}
