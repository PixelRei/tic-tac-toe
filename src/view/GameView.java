package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import com.formdev.flatlaf.FlatLightLaf;
import java.io.InputStream;

import java.awt.*;

public class GameView extends JFrame{
    JButton[][] buttons;
    public GameView(){
        super("Tic Tac Toe");

        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }

        setSize(600, 750);
        setResizable(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new GridLayout(2, 1));

        Font buttonFont = new Font("Segoe UI", Font.BOLD, 40);
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

        buttons = new JButton[3][3];
        JPanel header = new JPanel();
        JPanel board = new JPanel();

        EmptyBorder border1 = new EmptyBorder(85, 50, 50, 50);
        EmptyBorder border2 = new EmptyBorder(20, 50, 120, 50);

        header.setLayout(new FlowLayout(FlowLayout.CENTER));
        board.setLayout(new GridLayout(3,3));

        header.setBackground(new Color(0, 128, 157)); 
        board.setBackground(new Color(0, 128, 157));

        header.add(title);
        header.setBorder(border1);
        //adding buttons
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons.length; j++) {
                board.add(buttons[i][j] = new JButton());
                buttons[i][j].setBackground(Color.WHITE);
                buttons[i][j].setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
                buttons[i][j].setForeground(new Color(30, 30, 30));
                buttons[i][j].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
        }
        //Font bubble = Font.createFont();
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
                buttons[i][j].setFont(buttonFont);
                buttons[i][j].setFocusPainted(false);//removes the focus
            }
        }
        board.setBorder(border2);
        add(header);
        add(board);

        setVisible(true);
    }
    public JButton getButton(int row, int col){
        return buttons[row][col];
    }
}
