package model;

import java.util.Date;
import java.util.HashMap;

public class Job {
    ///تغییر get , set برای مجموعه ها
    
    private String title; //عنوان
    private HashMap<String , Boolean> type;
    private String workPlace;
    private HashMap<String , Boolean> workPlaceType;
    private String faaliat; //check box
    private Date startWorking; //must use date picker
    private Date endWorking;    // date picker
    private String details;
    private String skills; // less 5
    private String editprofile;  //check box

    public Job(){
        type.put("remote" , false);
        type.put("hibrid" , false);
        type.put("onSite" , false);
        workPlaceType.put("fullTime" , false);
        workPlaceType.put("partTime" , false);
        workPlaceType.put("selfEmployment" , false);
        workPlaceType.put("contract" , false);
        workPlaceType.put("apprenticeship" , false);
        workPlaceType.put("paidApprenticeship" , false);
        workPlaceType.put("freelance" , false);
        workPlaceType.put("Seasonal" , false);
    }
    public String getDetails() {
        return details;
    }public void setDetails(String details) {
        this.details = details;
    }public String getEditprofile() {
        return editprofile;
    }public void setEditprofile(String editprofile) {
        this.editprofile = editprofile;
    }public Date getEndWorking() {
        return endWorking;
    }public void setEndWorking(Date endWorking) {
        this.endWorking = endWorking;
    }public String getFaaliat() {
        return faaliat;
    }public void setFaaliat(String faaliat) {
        this.faaliat = faaliat;
    }public String getSkills() {
        return skills;
    }public void setSkills(String skills) {
        this.skills = skills;
    }public Date getStartWorking() {
        return startWorking;
    }public void setStartWorking(Date startWorking) {
        this.startWorking = startWorking;
    }public String getTitle() {
        return title;
    }public void setTitle(String title) {
        this.title = title;
    }public HashMap<String, Boolean> getType() {
        return type;
    }public void setType(HashMap<String, Boolean> type) {
        this.type = type;
    }public String getWorkPlace() {
        return workPlace;
    }public void setWorkPlace(String workPlace) {
        this.workPlace = workPlace;
    }public HashMap<String, Boolean> getWorkPlaceType() {
        return workPlaceType;
    }public void setWorkPlaceType(HashMap<String, Boolean> workPlaceType) {
        this.workPlaceType = workPlaceType;
    }








}
