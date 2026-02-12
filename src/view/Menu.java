package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import com.formdev.flatlaf.FlatLightLaf;

import model.Board;
import controller.GameController;

import java.io.InputStream;

public class Menu extends JFrame{
    public Menu(){
        setTitle("Menu");
        setSize(600, 750);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 1));

        ImageIcon icon = new ImageIcon(getClass().getResource("/resources/tictactoe-icon.png"));
        setIconImage(icon.getImage());

        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }
        //creating the fonts
        Font titleFont = null;
        try (InputStream is = getClass().getResourceAsStream("RebellionSquad-ZpprZ.ttf")) {
            if (is == null) {
                System.err.println("Font non trovato!");
            } else {
                titleFont = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(Font.BOLD, 60);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        EmptyBorder borderTitle = new EmptyBorder(60, 50, 50, 50);
        EmptyBorder borderText = new EmptyBorder(90, 170, 0, 150);

        JLabel title = new JLabel("TRIS!");
        title.setBorder(borderTitle);

        if (titleFont != null) {
            title.setFont(titleFont);
            title.setForeground(new Color(255, 255, 255));
        } else {
            title.setFont(new Font("Arial", Font.BOLD, 36));
        }

        Font textFont = null;
        try (InputStream is = getClass().getResourceAsStream("Rushfordclean-rgz89.otf")) {
            if (is == null) {
                System.err.println("Font non trovato!");
            } else {
                textFont = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(Font.BOLD, 30);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        JButton button = new JButton("Gioca!");
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.addActionListener(e->{
            dispose();
            new GameController(new Board(), new GameView());
        });


        JTextArea welcome = new JTextArea("Benvenuto nel gioco!\n   Premi per iniziare");
        welcome.setFont(textFont);
        welcome.setForeground(new Color(255, 255, 255));
        welcome.setEditable(false);
        welcome.setFocusable(false);
        welcome.setOpaque(false);
        welcome.setBorder(null);


        JPanel panel1 = new JPanel();
        panel1.add(title);
        panel1.setBackground(new Color(0, 128, 157));
        add(panel1);

        JPanel panel2 = new JPanel();
        panel2.add(welcome);
        panel2.setLayout(new GridLayout(2, 1));
        panel2.setBackground(new Color(0, 128, 157));
        panel2.setBorder(borderText);
        add(panel2);

        JPanel panel3 = new JPanel();
        panel3.add(button);
        panel3.setBackground(new Color(0, 128, 157));
        add(panel3);


        setVisible(true);
    }
}
