package model.dao;
import java.util.ArrayList;

import model.*;
public interface UserDAO {

    public void creatUser(User user);
    public User getUserByEmail(String email);
    public ArrayList<User> getAllUsers();
    public void updateUser(User user);
    public void deleteUser(int userId);
    public User getUserById(int id);

    
}

