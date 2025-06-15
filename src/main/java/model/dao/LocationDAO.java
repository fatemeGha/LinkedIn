package model.dao;
import java.util.ArrayList;

import model.*;
public interface LocationDAO {
    void creatLocation(Location location);
    Location getLocation(int LocationId);
    ArrayList<Location> getAllLocations();
    void updateLocation(Location location);
    void deleteLocation(int locationId);
}
