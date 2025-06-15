package controllers;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SecureCacheResponse;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import model.Massage;
import model.Post;
import model.User;
import model.dao.MassageDAOImpl;
import model.dao.PostDAOImpl;
import model.dao.UserDAOImpl;
import model.server.MySQLConnector;

public class Request {
    

    private UserDAOImpl userDAOImpl;
    private PostDAOImpl postDAOImpl;
    private MassageDAOImpl massageDAOImpl;
    private Connection connection;
    private  String dataJson;
    private  RequestType requestType;
    private  String responseDataJson;
    private ObjectMapper mapper;

    public Request(){
        MySQLConnector.makeConnection();
        connection = MySQLConnector.getConnection();
        userDAOImpl = new UserDAOImpl(connection);
        postDAOImpl = new PostDAOImpl(connection);
        massageDAOImpl = new MassageDAOImpl(connection);
        this.mapper = new ObjectMapper();
    }

    public Request(Object requestData , RequestType requestType){
        this.mapper = new ObjectMapper();
        this.requestType = requestType;
        if( requestData instanceof String){
            dataJson = (String)requestData;
        }
        else{
        try {
            dataJson = mapper.writeValueAsString(requestData);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }}
        

        this.run();

    }

    public void run() {
        try(Socket socket = new Socket("localhost" , 8080)) {
            DataInputStream in =new DataInputStream(socket.getInputStream()) ;
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            socket.getOutputStream().write(requestType.ordinal());
            System.out.println("dataJson = " + dataJson);
            String requestDataJson = dataJson ;
            byte[] requestDataBytes = requestDataJson.getBytes(StandardCharsets.UTF_8);
            System.out.println("byte Array = " + requestDataBytes);
            out.write(requestDataBytes.length);
            out.write(requestDataBytes);


            int responseTypeOrdinal = in.read();
            ResponseOrErrorType responseType = ResponseOrErrorType.values()[responseTypeOrdinal];
            int requestDataLength = in.readInt();
            System.out.println("length = " + requestDataLength);
            byte[] responseDataBytes = new byte[requestDataLength];
            in.read(responseDataBytes);

            switch(responseType){
                case SUCCESSFUL: 
                responseDataJson = new String(responseDataBytes);
                break;

            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String getResponseData() {
        return responseDataJson;
    }

    public String getDataJson() {
        return dataJson;
    }
    public void setDataJson(String dataJson) {
        this.dataJson = dataJson;
    }
    public User getUserByEmail(String email){
        User user = userDAOImpl.getUserByEmail(email);
        return user;
    }
    public void creatUser(User user){
        userDAOImpl.creatUser(user);
    }
    public ArrayList<User> getAllUsers(){
        ArrayList<User> users = userDAOImpl.getAllUsers();
        return users;
    }
    public void updateUser(User user){
        userDAOImpl.updateUser(user);
    }
    public void deleteUser(int userId){
        userDAOImpl.deleteUser(userId);
    }
    public User getUser(int id){
        User user = userDAOImpl.getUserById(id);
        return user;
    }
    public void creatPost(Post post){
        postDAOImpl.creatPost(post);
    }
    public ArrayList<Post> getAllPosts(){
        ArrayList<Post> users = postDAOImpl.getAllPosts();
        return users;
    }
    public void updatePost(Post user){
        postDAOImpl.updatePost(user);
    }
    public void deletePost(int id){
        postDAOImpl.deletePost(id);
    }
    public Post getPost(int id){
        Post post = postDAOImpl.getPost(id);
        return post;
    }public void creatUser(Massage massage){
        massageDAOImpl.creatMassage(massage);
    }
    public ArrayList<Massage> getAllMassages(){
        ArrayList<Massage> users = massageDAOImpl.getAllMassages();
        return users;
    }
    public void updateMassage(Massage massage){
        massageDAOImpl.updateMassage(massage);
    }
    public void deleteMassage(int id){
        massageDAOImpl.deleteMassage(id);
    }
    public Massage getMassage(int id){
        Massage massage = massageDAOImpl.getMassage(id);
        return massage;
    }
}

