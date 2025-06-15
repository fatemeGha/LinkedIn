package model.dao;
import java.util.ArrayList;

import model.*;
public interface PostDAO {
    public void creatPost(Post post);
    public Post getPost(int postId);
    public ArrayList<Post> getAllPosts();
    public void updatePost(Post post);
    public void deletePost(int postId);
}
