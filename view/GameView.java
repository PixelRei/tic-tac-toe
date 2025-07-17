package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;

public class GameView extends JFrame{
    JButton[][] buttons;
    public GameView(){
        super("Tic Tac Toe");
        setSize(600, 750);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new GridLayout(2, 1));

        JLabel title = new JLabel("Welcome to Tic-Tac-Toe");
        title.setFont(new Font("Arial", Font.BOLD, 28));

        buttons = new JButton[3][3];

        JPanel header = new JPanel();
        JPanel board = new JPanel();

        EmptyBorder border1 = new EmptyBorder(100, 50, 50, 50);
        EmptyBorder border2 = new EmptyBorder(20, 50, 120, 50);

        header.setLayout(new FlowLayout(FlowLayout.CENTER));
        board.setLayout(new GridLayout(3,3));
        header.add(title);
        header.setBorder(border1);
        //adding buttons
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons.length; j++) {
                board.add(buttons[i][j] = new JButton());
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
    public static void main(String[] args){
        new GameView();
    }
}
