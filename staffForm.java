import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class staffForm extends JFrame {
    private JComboBox<Music> tune;
    private JTextField newPrice;
    private JButton set;

    staffForm() {
        setTitle("Staff Panel");
        setSize(300, 150);
        setLayout(new GridLayout(3, 1));

        Music[] musicList = {
                new Music("Sopa", 10.0),
                new Music("Naber", 15.0),
                new Music("AltDudak", 20.0),
                new Music("BendenBirTaneDahaYok", 25.0),
                new Music("Kırmızı", 30.0)
        };

        tune = new JComboBox<>(musicList);
        newPrice = new JTextField();
        set = new JButton("Set Price");

        add(new JLabel("Tune:"));
        add(tune);
        add(new JLabel("New Price:"));
        add(newPrice);
        add(set);

        set.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Music selectedMusic = (Music) tune.getSelectedItem();
                double price = Double.parseDouble(newPrice.getText());
                selectedMusic.setPrice(price);
                JOptionPane.showMessageDialog(staffForm.this, "Price updated successfully!");
            }
        });
    }
}
