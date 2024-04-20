import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class PayingForm extends JFrame {
    private JTextField cardNo;
    private JButton submit;

    PayingForm() {
        setTitle("tune sale");
        setSize(300, 150);
        cardNo = new JTextField();
        submit = new JButton("submit");
        setLayout(new GridLayout(5,1));

        add(new JLabel("Enter your credit card number:"));
        add(cardNo);

        add(submit);
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String creditCard = cardNo.getText();
                if (isvalid(creditCard)) {
                    if (!creditCard.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Payment completed successfully!!", "Bildirim", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Please enter a valid card number", "Warning", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
            public boolean isvalid(String cardNo) {
                return cardNo.length() == 16;
            }
        });
    }
}

