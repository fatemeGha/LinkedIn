package model;
import java.util.*;
public class InformationToCall {
    private int id;
    private String userLink;
    private String address;
    private String email;
    private String phoneNumber;
    private HashMap<String , Boolean> typePhone;
    private String communicationChannel;
    private String token;

    public InformationToCall(){
        this.typePhone = new HashMap<>();
        typePhone.put("mobilePhone" , false);
        typePhone.put("homePhone" , false);
        typePhone.put("workPhone" , false);
    }
    public String getAddress() {
        return address;
    }public void setAddress(String address) {
        this.address = address;
    }
    public int getId() {
        return id;
    }public void setId(int id) {
        this.id = id;
    }public String getToken() {
        return token;
    }public void setToken(String token) {
        this.token = token;
    }
    public HashMap<String, Boolean> getTypePhone() {
        return typePhone;
    }public void setTypePhone(HashMap<String, Boolean> typePhone) {
        this.typePhone = typePhone ;
    }
    public String getCommunicationChannel() {
        return communicationChannel;
    }public void setCommunicationChannel(String communicationChannel) {
        this.communicationChannel = communicationChannel;
    }public String getEmail() {
        return email;
    }public void setEmail(String email) {
        this.email = email;
    }public String getPhoneNumber() {
        return phoneNumber;
    }public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }public String getUserLink() {
        return userLink;
    }public void setUserLink(String userLink) {
        this.userLink = userLink;
    }public void editTypePhone(String type) {
        Set<String> keySet = typePhone.keySet();
        for(String key: keySet){
            if(key.compareTo(type) == 0){
                typePhone.replace(key, true);
            }
        }
    }public String getStringTypePhone() {
        if(typePhone.containsValue(true)){
            Set<String> keySet = typePhone.keySet();
        for(String key: keySet){
            if(typePhone.get(key)){
                return key;
            }
        }
        }
        return null;
    }public boolean checkHaveTypePhone(String type) {
        if(typePhone.containsValue(true)){
            return true;
    }
        return false;
    }

    public static boolean checkAddressLength(String address){
        return(address.length()<= 220);
    }
    public static boolean checkFieldLength(String field){
        return(field.length()<= 40);
    }


}
