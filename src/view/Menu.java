package view;

import javax.swing.*;
import java.awt.*;
import com.formdev.flatlaf.FlatLightLaf;

import model.Board;
import controller.GameController;

import java.io.InputStream;

public class Menu extends JFrame{
    public Menu(){
        setTitle("Menu");
        setSize(600, 750);
        setResizable(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 1));

        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }

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
        JLabel title = new JLabel("TRIS!");
        if (titleFont != null) {
            title.setFont(titleFont);
            title.setForeground(new Color(255, 255, 255));
        } else {
            title.setFont(new Font("Arial", Font.BOLD, 36));
        }

        JButton button = new JButton("Gioca!");
        button.addActionListener(e->{
            dispose();
            //new CPU(new Board(), new GameView());
            new GameController(new Board(), new GameView());
        });
        JLabel welcome = new JLabel("Benvenuto, pronto per giocare?");

        JPanel panel1 = new JPanel();
        panel1.add(title);
        panel1.setBackground(new Color(0, 128, 157));
        add(panel1);
        JPanel panel2 = new JPanel();
        panel2.add(welcome);
        panel2.setBackground(new Color(0, 128, 157));
        add(panel2);
        JPanel panel3 = new JPanel();
        panel3.add(button);
        panel3.setBackground(new Color(0, 128, 157));
        add(panel3);


        setVisible(true);
    }
}
