package view;

import javax.swing.*;
import java.awt.*;

public class GameView extends JFrame{
    JButton[][] buttons;
    public GameView(){
        super("Tic Tac Toe");
        setSize(600, 750);
        setResizable(false);
        setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        setLayout(new GridLayout(2, 1));

        JLabel title = new JLabel("Welcome to Tic-Tac-Toe");
        buttons = new JButton[3][3];

        JPanel header = new JPanel();
        JPanel board = new JPanel();
        board.setLayout(new GridLayout(3,3));
        header.add(title);
        //adding buttons
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons.length; j++) {
                board.add(buttons[i][j] = new JButton());
            }
        }
        add(header);
        add(board);

        setVisible(true);
    }
    public static void main(String[] args){
        new GameView();
    }
}
