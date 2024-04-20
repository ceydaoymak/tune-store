import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.JOptionPane;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
public class BuyerForm extends JFrame {

    private JComboBox<String> tunesc;
    private JButton addToCart, play,showCart,Pay;
    private double totalCost;

    BuyerForm() {
        setTitle("tune sale");
        setSize(300, 150);
        setLayout(new GridLayout(3, 1));
        Music[] tunes = {
                new Music("Sopa", 10.0),
                new Music("Naber", 15.0),
                new Music("AltDudak", 20.0),
                new Music("BendenBirTaneDahaYok", 25.0),
                new Music("Kırmızı", 30.0)
        };

        tunesc = new JComboBox(tunes);
        addToCart = new JButton("Add to Cart");
        play = new JButton("Play the Music");
        showCart = new JButton("Show Cart");
        Pay=new JButton("Pay");

        add(new JLabel("Tunes:"));
        add(tunesc);
        add(addToCart);
        add(play);
        add(showCart);
        add(Pay);

        addToCart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Music selectedTune = (Music) tunesc.getSelectedItem();

                try (FileWriter fileWriter = new FileWriter("purchased_items.txt", true);
                     BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
                    bufferedWriter.write(selectedTune + "\n");
                    JOptionPane.showMessageDialog(BuyerForm.this, "Selected tune added to the cart.");
                    totalCost += selectedTune.getPrice();
                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(BuyerForm.this, "Error writing to file: " + ex.getMessage());
                }
            }
        });
        play.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Music selectedTune = (Music) tunesc.getSelectedItem();
                playMusic(selectedTune);
            }
        });
        showCart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showCartItems();
            }
        });
        clearCartFile();
        Pay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            openPayingForm();
            }
        });
    }
        private void clearCartFile() {

            try (FileWriter fileWriter = new FileWriter("purchased_items.txt", false)) {
                fileWriter.write("");
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error clearing cart file: " + e.getMessage());
            }
        }


    private void playMusic(Music selectedTune) {
        String desktopFilePath;
        if (selectedTune.getTitle().equals("Sopa")) {
            desktopFilePath = System.getProperty("user.home") + "/Desktop/tune1.wav";
        } else if (selectedTune.getTitle().equals("Naber")) {
            desktopFilePath = System.getProperty("user.home") + "/Desktop/tune2.wav";
        } else if (selectedTune.getTitle().equals("AltDudak")) {
            desktopFilePath = System.getProperty("user.home") + "/Desktop/tune3.wav";
        } else if (selectedTune.getTitle().equals("BendenBirTaneDahaYok")) {
            desktopFilePath = System.getProperty("user.home") + "/Desktop/tune4.wav";
        } else if (selectedTune.getTitle().equals("Kırmızı")) {
            desktopFilePath = System.getProperty("user.home") + "/Desktop/tune5.wav";
        } else {
            JOptionPane.showMessageDialog(this, "No music selected.");
            return;
        }

        try {
            File soundFile = new File(desktopFilePath);
            if (!soundFile.exists()) {
                JOptionPane.showMessageDialog(this, "The selected tune file does not exist on the desktop.");
                return;
            }
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error playing the selected tune: " + e.getMessage());
        }
    }

    private void showCartItems() {
        try (BufferedReader reader = new BufferedReader(new FileReader("purchased_items.txt"))) {
            StringBuilder cartItems = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                cartItems.append(line).append("\n");
            }
            JOptionPane.showMessageDialog(this, "Items in Cart:\n" + cartItems.toString()+ "\nTotal Cost: $" + totalCost);
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error reading cart items: " + ex.getMessage());
        }
    }
    void openPayingForm(){
        PayingForm p=new PayingForm();
        p.setVisible(true);
    }

}