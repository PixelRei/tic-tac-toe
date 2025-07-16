package model;

public class Board{
    private char[][] grid;
    private char currentPlayer;
    public Board(){
        this.grid = new char[3][3];//three rows and three columns
    }
    public char getCurrentPlayer(){
        return this.currentPlayer;
    }
    public char getCell(int row, int column){
        return this.grid[row][column];
    }
    public void setCurrentPlayer(char player){
        if(player == 'X' || player == 'O'){
            currentPlayer = player;
        }
    }
    public boolean makeMove(int row, int column){
        if(grid[row][column] == ' '){
            grid[row][column] = currentPlayer;
            return true;
        }else return false;
    }
    public void switchPlayer(){
        if(currentPlayer == 'X'){
            currentPlayer = 'O';
        }else{
            currentPlayer = 'X';
        }
    }
    public void reset(){
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                grid[i][j] = ' ';
            }
        }
    }
    //win, draw conditions
    public boolean isWin(){
    char p = currentPlayer;
    for (int i = 0; i < 3; i++) {
        if (grid[i][0] == p && grid[i][1] == p && grid[i][2] == p) return true;
        if (grid[0][i] == p && grid[1][i] == p && grid[2][i] == p) return true;
    }
    if (grid[0][0] == p && grid[1][1] == p && grid[2][2] == p) return true;
    if (grid[0][2] == p && grid[1][1] == p && grid[2][0] == p) return true;
    return false;   
    }
    public boolean isDraw(){
        for(int i=0; i<3; i++){
            if(grid[i][0] == ' ' || )
        }
    }
}