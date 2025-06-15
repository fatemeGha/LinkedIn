package model.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import model.*;
import model.dao.UserDAOImpl;

public class Server {

    public static Connection connection;

    public static void main(String[] args) throws IOException {

        try  {

            ServerSocket socketServer = new ServerSocket(8080);
            ExecutorService executorService = Executors.newCachedThreadPool();
            //MySQLConnector.makeConnection();
            //connection = MySQLConnector.getConnection();
            System.out.println("server is waiting....");
            //User user = new UserDAOImpl(connection).getUserByEmail("fatm.ghanbari@gmail.com");
            //System.out.println("user is " +  user.getId());
            while (true) {
                Socket socket = socketServer.accept();
                System.out.println("A client found");
                RequestHandler requestHandler = new RequestHandler(socket);
                executorService.execute(requestHandler);
            }
        
        } catch (Exception e) {
            e.printStackTrace();
        }
        

        
    }
}