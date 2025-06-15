package model.dao;
import java.util.ArrayList;

import model.*;
public interface JobDAO {
    public Job creatJob(Job Job);
    public Job getJob(int jobId);
    public ArrayList<Job> getAllJobs();
    public void updateJob(Job job);
    public void deleteJob(int jobId);
}
