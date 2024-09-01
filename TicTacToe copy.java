import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToe {
    int boardWidth = 600;
    int boardHeight = 650; 

    JFrame frame = new JFrame("Tic Tac Toe");
    JLabel textLabel = new JLabel();
    JPanel textPanel = new JPanel();
    JPanel boardPanel = new JPanel();
    
    JButton[][] board = new JButton[3][3];
    String player_X = "X";
    String player_O = "O";
    String currentPlayer = player_X;

    boolean gameOver = false;
    int turns = 0;

    TicTacToe(){
        //Frame
        frame.setVisible(true);
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        //Label
        textLabel.setBackground(Color.gray);
        textLabel.setForeground(Color.white);
        textLabel.setFont(new Font("Verdana", Font.BOLD, 50));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("  Tic Tac Toe");
        textLabel.setOpaque(true);
        textPanel.setBackground(Color.gray);
        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel, BorderLayout.WEST);
        
        //retry button
        JButton retryButton = new JButton();  
        retryButton.setBackground(Color.gray);
        retryButton.setForeground(Color.white);
        retryButton.setFont(new Font("Verdana", Font.BOLD, 17));
        retryButton.setText("Reset");
        retryButton.setFocusable(false);
        textPanel.add(retryButton, BorderLayout.EAST);
        frame.add(textPanel, BorderLayout.NORTH);

        retryButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                textLabel.setText("  Tic Tac Toe");
                for(int r = 0; r < 3; r ++){
                    for(int c = 0; c < 3; c ++){
                        board[r][c].setForeground(Color.white);
                        board[r][c].setText("");
                    }
                }
                currentPlayer = player_X;
                gameOver = false;
                turns = 0;
            }
        });  

        //Playing Grid
        boardPanel.setLayout(new GridLayout(3,3));
        boardPanel.setBackground(Color.darkGray);
        frame.add(boardPanel);
       
        for(int r = 0; r < 3; r++){
            for(int c = 0; c < 3; c++){
                JButton tile = new JButton();
                board[r][c] = tile;
                boardPanel.add(tile);

                tile.setBackground(Color.darkGray);
                tile.setForeground(Color.white);
                tile.setFont(new Font("Verdana", Font.BOLD, 120));
                tile.setFocusable(false);

                tile.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e){
                        if(gameOver) return;
                        JButton tile = (JButton) e.getSource();
                        if(tile.getText() == ""){
                            tile.setText(currentPlayer);
                            turns ++;
                            checkWinner();
                            if(!gameOver){
                                currentPlayer = currentPlayer == player_X ? player_O : player_X;
                                textLabel.setText("  " + currentPlayer + "'s turn.");
                            }
                        }
                    }
                });
            }
        }
    }

    public TicTacToe(int i) {
        //TODO Auto-generated constructor stub
    }

    void setWinner(JButton tile){
        tile.setForeground(Color.green);
        textLabel.setText("  " + currentPlayer + " won.");
    }

    void checkWinner(){
        //horizontal 
        for(int r = 0; r < 3; r++){
            if(board[r][0].getText() == "") continue;
            if(board[r][0].getText() == board[r][1].getText() && board[r][1].getText() == board[r][2].getText()){
                for(int i = 0; i < 3; i++){
                    setWinner(board[r][i]);
                }
                gameOver = true;
                return;
            }
        }

        //vertical
        for(int c = 0; c < 3; c ++){
            if(board[0][c].getText() == "") continue;
            if(board[0][c].getText() == board[1][c].getText() && board[1][c].getText() == board[2][c].getText()){
                for(int i = 0; i < 3; i++){
                    setWinner(board[i][c]);
                }
                gameOver = true;
                return;
            }
        }

        //diagonal1
        if (board[0][0].getText() == board[1][1].getText() && 
        board[1][1].getText() == board[2][2].getText() &&
        board[0][0].getText() != ""){
            for(int i = 0; i < 3; i ++){
                setWinner(board[i][i]);
            }
            gameOver = true;
            return;
        }

        //diagonal2
        if (board[0][2].getText() == board[1][1].getText() && 
        board[1][1].getText() == board[2][0].getText() &&
        board[0][2].getText() != ""){
            for(int i = 0; i < 3; i ++){
                setWinner(board[i][2-i]);
            }
            gameOver = true;
            return;
        }

        //tie
        if(turns == 9){
            for(int r = 0; r < 3; r ++){
                for(int c = 0; c < 3; c ++){
                    board[r][c].setForeground(Color.red);
                }
            }
            textLabel.setText("  Tie.");
            gameOver = true;
            return;
        }
    }

    public void TicTacToe(int i) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'TicTacToe'");
    }
}
