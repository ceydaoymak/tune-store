import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class loginForm extends JFrame {
    private JTextField userName;
    private JPasswordField password;
    private JButton login;

    loginForm(String userType) {
        setTitle("Login");
        setSize(300, 150);
        setLayout(new GridLayout(3, 1));

        userName = new JTextField();
        password = new JPasswordField();
        login= new JButton("Login");

        add(new JLabel("Username:"));
        add(userName);
        add(new JLabel("Password:"));
        add(password);
        add(login);

        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            login();
            }

         private void login(){
             String Username = userName.getText();
             String Password = new String(password.getPassword());
             boolean loginSuccessful = validatelogin(Username, Password);
             if (loginSuccessful) {
                 if(userType=="staff" && loginSuccessful){
                     openStaffForm();

                 } else {
                     openBuyerForm();
                 }
             } else {
                 JOptionPane.showMessageDialog(null, "Invalid username or password", "Login Error", JOptionPane.ERROR_MESSAGE);
             }


         }
            private boolean validatelogin(String username, String password) {
                return username.equals("ceyda") && password.equals("ceyda123");
            }

        });

    }
    private void openStaffForm(){
        staffForm s=new staffForm();
        s.setVisible(true);
    }
    private void openBuyerForm(){
        BuyerForm b=new BuyerForm();
        b.setVisible(true);
    }
}

