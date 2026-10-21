public abstract class User {
    private String userID;
    private String userPassword;
    public User(){
        this.userID="";
        this.userPassword="";
    }
    public User(String uid, String upw){
        this.userID=uid;
        this.userPassword=upw;
    }
    public void setUserID(String uid) {
        this.userID = uid;
    }
    public void setUserPassword(String upw) {
        this.userPassword = upw;
    }
    public String getUserID() {
        return userID;
    }
    public String getUserPassword() {
        return userPassword;
    }
    public abstract void userSignUp();
}