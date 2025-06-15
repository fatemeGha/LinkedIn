package model.dao;
import java.util.ArrayList;

import model.*;
public interface MassageDAO {
    void creatMassage(Massage massage);
    Massage getMassage(int massageId);
    ArrayList<Massage> getAllMassages();
    void updateMassage(Massage massage);
    void deleteMassage(int massageId);
}
