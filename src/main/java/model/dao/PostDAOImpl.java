package model.dao;
import java.util.ArrayList;
import java.sql.*;
import java.time.LocalDateTime;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import model.*;

public class PostDAOImpl implements PostDAO {
    private Connection connection;
    private ObjectMapper mapper;


    public PostDAOImpl(Connection connection) {
        this.connection = connection;
        mapper = new ObjectMapper();

    }

    @Override
    public void creatPost(Post post) {
        post.setUpdated(false);
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO posts (text,hashtaks,numLikes,numComments,comments,date,writer,token,image, likers) VALUES (?, ?, ? , ? ,? ,? ,? ,? ,?)");
                Statement idStatement = connection.createStatement()) {
            statement.setString(1, post.getText());
            statement.setString(2, post.getHashtaks());
            statement.setInt(3, post.getNumLikes());
            statement.setInt(4, post.getNumComments());
            statement.setString(5, post.getComments());
            statement.setString(6, post.getDate());
            statement.setString(7, post.getWriter());
            statement.setString(8, post.getToken());
            statement.setString(9, post.getImage());
            statement.setString(10, post.getLikers());
            statement.executeUpdate();
            post.setUpdated(true);
            ResultSet resultSet = idStatement.executeQuery("SELECT LAST_INSERT_ID()");

            if (resultSet.next()) {
                post.setId(resultSet.getInt(1));
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Post getPost(int id) {
        Post post = null;
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM posts WHERE id = ?")) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                post = new Post();
                post.setId(resultSet.getInt("id"));
                post.setText(resultSet.getString("text"));
                post.setHashtaks(resultSet.getString("hashtaks"));
                post.setNumLikes(resultSet.getInt("numLikes"));
                post.setNumComments(resultSet.getInt("numComments"));
                post.setDate(resultSet.getString("date"));
                post.setWriter(resultSet.getString("writer"));
                post.setComments(resultSet.getString("comments"));
                post.setLikers(resultSet.getString("likers"));
                post.setToken(resultSet.getString("token"));
                post.setImage(resultSet.getString("image"));
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return post;
    }

    @Override
    public void deletePost(int id) {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM posts WHERE id = ?")) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updatePost(Post post) {
        try (PreparedStatement statement = connection.prepareStatement(
                "UPDATE posts SET id= ?, text= ?, numLikes= ?, numComments = ?, comments = ?, date = ?, writer = ?, token = ?, image = ?, likers = ?, hashtaks = ?)")) {
                    statement.setString(1, post.getText());
                    statement.setString(2, post.getHashtaks());
                    statement.setInt(3, post.getNumLikes());
                    statement.setInt(4, post.getNumComments());
                    statement.setString(5, post.getComments());
                    statement.setString(6, post.getDate());
                    statement.setString(7, post.getWriter());
                    statement.setString(8, post.getToken());
                    statement.setString(9, post.getImage());
                    statement.setString(10, post.getLikers());
            statement.executeUpdate();
        } catch (SQLException  e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Post> getAllPosts() {
        ArrayList<Post> posts = new ArrayList<>();
       try(Statement statement = connection.createStatement();
       ResultSet resultSet = statement.executeQuery("SELECT * FROM posts")){
        while (resultSet.next()) {
            Post post = new Post();
            post.setId(resultSet.getInt("id"));
            post.setText(resultSet.getString("text"));
            post.setHashtaks(resultSet.getString("hashtaks"));
            post.setNumLikes(resultSet.getInt("numLikes"));
            post.setNumComments(resultSet.getInt("numComments"));
            post.setDate(resultSet.getString("date"));
            post.setWriter(resultSet.getString("writer"));
            post.setComments(resultSet.getString("comments"));
            post.setLikers(resultSet.getString("likers"));
            post.setToken(resultSet.getString("token"));
            post.setImage(resultSet.getString("image"));
            posts.add(post);
        }
    }
    catch(SQLException e){
        e.printStackTrace();
    }
    return posts;
}
}
