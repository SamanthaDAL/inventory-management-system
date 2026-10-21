import javax.swing.*;
public class Admin extends User{
    public Admin(){
        super();
    }
    public Admin(String uid, String upw){
        super(uid, upw);
    }
    public void userSignUp() {
        String input;
        input = JOptionPane.showInputDialog("Please enter your ID for your account:");
        super.setUserID(input);
        input = JOptionPane.showInputDialog("Please enter your password for your account:");
        super.setUserPassword(input);
        JOptionPane.showMessageDialog(null,"Your account is created!\nPlease proceed with login.");
    }
}