package model;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User {
    private String password;
    private String profilePhoto;
    private String backgroundPhoto;
    private String additionalName;
    private String jobId;
    private String aducationId;
    private String profession;
    private String header;
    private String firstName;
    private String lastName;
    private String email;
    private int id;
    private String token;
    private String locationId;
    private String followersId;
    private String followingsId;
    private String connectsId;
    private String postsId;
    private String blocksId;
    private String intentionToHire;
    private boolean updated;

    public User(){
        //followers = new ArrayList<User>();
        //followings = new ArrayList<User>();
        //connects = new ArrayList<User>();
        //posts = new ArrayList<Post>();
        //blocks = new ArrayList<User>();
        this.id = -1;
        this.firstName = "defult name";
        this.profilePhoto = "defaultAvatar.jpg";

    }

    public void setUpdated(Boolean updated) {
        this.updated = updated;
    }
    public boolean getUpdated() {
        return updated;
    }
    
    public String getBlocks() {
        return blocksId;
    }public void setBlocks(String blocks) {
        this.blocksId = blocks;
    }
    public String getAdditionalName() {
        return additionalName;
    }public void setAdditionalName(String additionalName) {
        this.additionalName = additionalName;
    }public String getAducation() {
        return aducationId;
    }public void setAducation(String aducationId) {
        this.aducationId = aducationId;
    }
    public String getBackgroundPhoto() {
        return backgroundPhoto;
    }public void setBackgroundPhoto(String backgroundPhoto) {
        this.backgroundPhoto = backgroundPhoto;
    }public String getIntentionToHire() {
        return intentionToHire;
    }public void setIntentionToHire(String intentionToHire) {
        this.intentionToHire = intentionToHire;
    }public String getJob() {
        return jobId;
    }public void setJob(String job) {
        this.jobId = job;
    }public String getProfession() {
        return profession;
    }public void setProfession(String profession) {
        this.profession = profession;
    }public String getProfilePhoto() {
        return profilePhoto;
    }public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }
    public String getPosts() {
        return postsId;
    }
    public void setPosts(String posts) {
        this.postsId = posts;
    }
    public String getConnects() {
        return connectsId;
    }
    public void setConnects(String connects) {
        this.connectsId = connects;
    }
    public String getLocation() {
        return locationId;
    }

    public void setLocation(String location) {
        this.locationId = location;
    }

    public String getFollowers() {
        return followersId;
    }

    public void setFollowers(String followers) {
        this.followersId = followers;
    }

    public String getFollowings() {
        return followingsId;
    }

    public void setFollowings(String followings) {
        this.followingsId = followings;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int userId) {
        this.id = userId;
    }
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
/* 
    public void addFollowing(User follow){
        followings.add(follow);
        follow.addFollower(this);
    }
    public void addFollower(User follower){
        followers.add(follower);
    }
    public void removeFollowing(User follow){
        followings.remove(follow);
        follow.removeFollower(this);
    }
    public void addBlock(User blocked){
        blocks.add(blocked);
    }
    public void removeBlock(User blocked){
        blocks.remove(blocked);
    }
    
    public void removeFollower(User follower){
        followers.remove(follower);
    }
    public void addPost(Post post){
        posts.add(post);
    }
*/
    public static boolean checkEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    public static boolean checkPass(String pass){
        boolean hasLetter = false;
        boolean hasNumber = false;
        for(char c : pass.toCharArray()){
            if(Character.isLetter(c)){
                hasLetter = true;
            }
            else if(Character.isDigit(c)){
                hasNumber = true;
            }
        }
        return pass.matches("[a-zA-Z0-9]") && hasLetter && hasNumber;
    }
    public static boolean checkRepeatPass(String pass , String repeatPass){
        return (pass.compareTo(repeatPass) == 0);
    }
    public static boolean checkPassLength(String pass){
        return (pass.length() >= 8);
    }
    public static boolean checkFirstNameLength(String firstName){
        return(firstName.length()<= 20 );
    }
    public static boolean checkLastNameLength(String lastName){
        return(lastName.length() <= 40);
    }
    public static boolean checkName(String firstName , String lastName){
        return (firstName.matches("[a-zA-Z]") && lastName.matches("[a-zA-Z]"));
    }
    public static boolean checkAdditionalNameLength(String additionalName){
        return(additionalName.length() <= 40);
    }
    public static boolean checkHeaderLength(String header){
        return(header.length()<= 220);
    }
    public static boolean checkProfessionLength(String profession){
        return(profession.length()<= 60);
    }

    
    //////////////////////
    public static boolean checkProfilePhotoSize(String profilePhoto){
        return false;
    }
    public static boolean checkBackGroundPhotoSize(String backgroundPhoto){
        return false;
    }



    
}
