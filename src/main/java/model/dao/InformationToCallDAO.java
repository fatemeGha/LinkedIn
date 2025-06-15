package model.dao;
import java.util.ArrayList;

import model.*;
public interface InformationToCallDAO {
    void creatInformationToCall(InformationToCall informationToCall);
    InformationToCall getInformationToCall(int informationToCallId);
    ArrayList<InformationToCall> getAllInformationToCalls();
    void updateInformationToCall(InformationToCall informationToCall);
    void deleteInformationToCall(int informationToCallId);
}

