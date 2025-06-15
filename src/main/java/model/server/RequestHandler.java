package model.server;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import controllers.RequestType;
import controllers.ResponseOrErrorType;
import model.Post;
import model.User;
import model.dao.MassageDAOImpl;
import model.dao.PostDAOImpl;
import model.dao.UserDAOImpl;

public class RequestHandler implements Runnable {

    private Socket client;
    private DataInputStream in;
    private DataOutputStream out;
    private UserDAOImpl userDAOImpl;
    private PostDAOImpl postDAOImpl;
    private MassageDAOImpl massageDAOImpl;
    private String responseDataJson;
    private Connection connection;
    private ObjectMapper mapper;

    public RequestHandler(Socket client) {
        this.client = client;
        try {
            this.out = new DataOutputStream(client.getOutputStream());
            this.in = new DataInputStream(client.getInputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        MySQLConnector.makeConnection();
        connection = MySQLConnector.getConnection();
        userDAOImpl = new UserDAOImpl(connection);
        postDAOImpl = new PostDAOImpl(connection);
        massageDAOImpl = new MassageDAOImpl(connection);
        mapper = new ObjectMapper();
        int requestTypeOrdinal = -1;
        ResponseOrErrorType responseOrErrorType = ResponseOrErrorType.UNSUCCESSFUL;
        try {
            requestTypeOrdinal = in.read();
            RequestType requestType = RequestType.values()[requestTypeOrdinal];
            int requestDataLength = in.read();
            byte[] requestDataBytes = new byte[requestDataLength];
            in.read(requestDataBytes);
            System.out.println("byte Array in server is : " + requestDataBytes);
            String dataJson = new String(requestDataBytes);
            System.out.println("json String in server is : " + dataJson);
            User user = new User();
            int id = -1;
            Post post = new Post();
            switch (requestType) {
                case CREAT_USER:
                    user = (new ObjectMapper().readValue(dataJson, new TypeReference<User>() {}));
                    userDAOImpl.creatUser(user);
                    System.out.println("user updated is " + user.getUpdated());
                    if (user.getUpdated()) {
                        responseOrErrorType = ResponseOrErrorType.SUCCESSFUL;
                    } else {
                        responseOrErrorType = ResponseOrErrorType.USER_NOTFOUND;
                    }
                    break;
                case GET_USER_BY_EMAIL:
                    user = userDAOImpl.getUserByEmail(dataJson);
                    System.out.println("user id is " + user.getId());
                    responseDataJson = mapper.writeValueAsString(user);
                    if (user.getId() != -1) {
                        responseOrErrorType = ResponseOrErrorType.SUCCESSFUL;
                    } else {
                        responseOrErrorType = ResponseOrErrorType.USER_NOTFOUND;
                    }
                    break;
                case GET_USER:
                    id = (new ObjectMapper().readValue(dataJson, new TypeReference<Integer>() {}));
                    user = userDAOImpl.getUserById(id);
                    System.out.println("user id is " + user.getId());
                    responseDataJson = mapper.writeValueAsString(user);
                    if (user.getId() != -1) {
                        responseOrErrorType = ResponseOrErrorType.SUCCESSFUL;
                    } else {
                        responseOrErrorType = ResponseOrErrorType.USER_NOTFOUND;
                    }
                    break;
                case UPDATE_USER:
                    user = (new ObjectMapper().readValue(dataJson, new TypeReference<User>() {}));
                    userDAOImpl.updateUser(user);
                    System.out.println("user updated is " + user.getUpdated());
                    if (user.getUpdated()) {
                        responseOrErrorType = ResponseOrErrorType.SUCCESSFUL;
                    } else {
                        responseOrErrorType = ResponseOrErrorType.USER_NOTFOUND;
                    }
                    break;
                case GET_POST:
                    id = (new ObjectMapper().readValue(dataJson, new TypeReference<Integer>() {}));
                    post = postDAOImpl.getPost(id);
                    System.out.println("post id is " + post.getId());
                    responseDataJson = mapper.writeValueAsString(post);
                    if (post.getId() != -1) {
                        responseOrErrorType = ResponseOrErrorType.SUCCESSFUL;
                    } else {
                        responseOrErrorType = ResponseOrErrorType.USER_NOTFOUND;
                    }
                    break;
                case CREAT_POST:
                    post = (new ObjectMapper().readValue(dataJson, new TypeReference<Post>() {}));
                    postDAOImpl.creatPost(post);
                    System.out.println("post updated is " + post.getUpdated());
                    if (post.getUpdated()) {
                        responseOrErrorType = ResponseOrErrorType.SUCCESSFUL;
                    } else {
                        responseOrErrorType = ResponseOrErrorType.USER_NOTFOUND;
                    }
                    break;

            }

            out.write(responseOrErrorType.ordinal());
            byte[] responseDataBytes = responseDataJson.getBytes(StandardCharsets.UTF_8);
            out.writeInt(responseDataBytes.length);
            System.out.println("length = " + responseDataBytes.length);
            out.write(responseDataBytes);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}