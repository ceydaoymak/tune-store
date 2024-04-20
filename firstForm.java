import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class firstForm extends JFrame{
    private JButton tuneBuyer;
    private JButton staff;

    firstForm() {
        setTitle("Entrence");
        setSize(300, 300);
        setLayout(new GridLayout(5, 1));

        staff = new JButton("Staff");
        tuneBuyer = new JButton("Tune Buyer");

        add(staff);
        add(tuneBuyer);


        staff.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openLoginForm("staff");
            }
        });

        tuneBuyer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openLoginForm("tune buyer");
            }
        });
    }
        private void openLoginForm(String userType){
            loginForm l= new loginForm(userType);
            l.setVisible(true);

        }
    }

