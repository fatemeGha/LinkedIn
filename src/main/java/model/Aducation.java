package model;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Aducation {
    private String instituteName;
    private String reshte;
    private LocalDateTime startSchooling; 
    private LocalDateTime graduation;
    private int grade; 
    private String description;
    private String activityDescription;
    private ArrayList<String> skils;
    private String editProfile;

    public Aducation(){

    }
    public String getDescription() {
        return description;
    }public void setDescription(String description) {
        this.description = description;
    }public String getEditprofile() {
        return editProfile;
    }public void setEditprofile(String editprofile) {
        this.editProfile = editprofile;
    }public LocalDateTime getGraduation() {
        return graduation;
    }public void setGraduation(LocalDateTime graduation) {
        this.graduation = graduation;
    }public int getGrade() {
        return grade;
    }public void setGrade(int grade) {
        this.grade = grade;
    }public String getReshte() {
        return reshte;
    }public void setReshte(String reshte) {
        this.reshte = reshte;
    }public String getInstituteName() {
        return instituteName;
    }public void setInstituteName(String instituteName) {
        this.instituteName = instituteName;
    }public String getActivityDescription() {
        return activityDescription;
    }public void setActivityDescription(String activityDescription) {
        this.activityDescription = activityDescription;
    }public ArrayList<String> getSkils() {
        return skils;
    }public void setSkils(ArrayList<String> skils) {
        this.skils = skils;
    }public LocalDateTime getStartSchooling() {
        return startSchooling;
    }public void setStartSchooling(LocalDateTime startSchooling) {
        this.startSchooling = startSchooling;
    }public static boolean checkFieldLength(String field){
        return(field.length()<= 40);
    }public static boolean checkDescriptionLength(String description){
        return(description.length()<= 1000);
    }public static boolean checkActivityDescriptionLength(String activityDescription){
        return(activityDescription.length()<= 500);
    }public boolean checkActivityDescriptionLength(){
        return(skils.size() < 5);
    }





}
