package model.dao;
import java.util.ArrayList;
import model.*;

public interface AducationDAO {
    Aducation creatAducation(Aducation aducation);
    Aducation getAducation(int aducationId);
    ArrayList<Aducation> getAllAducations();
    void updateAducation(Aducation aducation);
    void deleteAducation(int aducationId);
}
