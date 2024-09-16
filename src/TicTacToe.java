import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToe {
    int boardWidth = 600;
    int boardHeight = 650; 

    
    JLabel textLabel = new JLabel();
    JPanel textPanel = new JPanel();
    JPanel boardPanel = new JPanel();
    
    JButton[][] board = new JButton[3][3];
    String player_X = "X";
    String player_O = "O";
    String currentPlayer = player_X;

    boolean gameOver = false;
    int turns = 0;

    
    public TicTacToe(){
        return;
    }

    public TicTacToe(int mode){
        //Single player mode
        if(mode == 1){
            JFrame frame = new JFrame("Tic Tac Toe (1 player)");
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
            currentPlayer = player_X;
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
                                    autoBot();
                                    textLabel.setText("  " + currentPlayer + "'s turn.");
                                }
                            }
                        }
                    });
                }
            }
        }

        //Two player mode
        if(mode == 2){
            //Frame
            JFrame frame = new JFrame("Tic Tac Toe (2 player)");
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

    void autoBot(){
        //X move 1 (8 possible)
        //If opponent doesn't have center, we take it. If they have center, we play top left corner
        if(turns == 1){
            if(board[1][1].getText() == ""){
                board[1][1].setText(player_O);
            }
            else{
                board[0][0].setText(player_O); 
            }
            turns ++;
        }  

        //X move 2 (6 possible)
        if(turns == 3){
            //we don't have mid
            if(board[1][1].getText() == player_X){
                //2c
                if(board[2][2].getText() == player_X){
                    board[0][2].setText(player_O);
                }
                //2o, we block
                else{
                    botCheck();
                }
            }
            //we have mid
            else{
                if(!botCheck()){
                    //pick a corner
                    int corners = cornerCount();
                    //pick any edge
                    if(corners == 0){
                        if(board[1][2].getText() == "X" && board[2][1].getText() == "X"){
                            board[2][2].setText(player_O);
                        }
                        else{
                            board[0][0].setText(player_O);
                        }
                    }
                    else if(corners == 2){
                        board[0][1].setText(player_O);
                    }
                    else{
                        if(board[0][1].getText() == ""){
                            if(board[2][1].getText() == ""){
                                board[0][1].setText(player_O);
                            }            
                            else{
                                board[1][0].setText(player_O);
                                } 
                        }
                        else{
                        board[1][0].setText(player_O);
                        } 
                    }
                }          
            }
            turns ++;
        }  
        //X move 3 (4 possible)
        //can win
        if(turns == 5){
            if(botWinCheck()){
                checkWinner();
            }
            else if(!botCheck()){
                int corners = cornerCount();
                //special case
                if(corners == 1){
                    if(board[0][0].getText() == player_X){
                        board[2][2].setText(player_O);
                    }
                    else if(board[2][2].getText() == player_X){
                        board[0][0].setText(player_O);
                    }
                    else if(board[2][0].getText() == player_X){
                        board[0][2].setText(player_O);
                    }
                    else{
                        board[2][0].setText(player_O);
                    }
                }
                else{
                    //pick any edge still available
                    if(board[0][1].getText() == ""){
                        board[0][1].setText(player_O);
                    }
                    else if(board[1][0].getText() == ""){
                        board[1][0].setText(player_O);
                    }
                    else if(board[1][2].getText() == ""){
                        board[1][2].setText(player_O);
                    }
                    else{
                        board[2][1].setText(player_O);
                    }
                }  
            }
            turns ++;
        } 
        //X move 4 (2 possible)
        if(turns == 7){
            boolean done = false;
            if(botWinCheck()){
                checkWinner();
            }
            else if(!botCheck()){
                //pick any of the 2 remaining tiles
                for(int i = 0; i < 3; i++){
                    for(int j = 0; j < 3; j++){
                        if(board[i][j].getText() == "" && !done){
                            board[i][j].setText(player_O);
                            done = true;
                        }
                    }
                }
            }
            checkWinner();
            turns ++;
        } 
    }

    boolean botWinCheck(){
        //horizontal 
        for(int r = 0; r < 3; r ++){
            //110
            if(board[r][0].getText() == board[r][1].getText() && board[r][0].getText() == "O" && board[r][2].getText() == ""){
                board[r][2].setText(player_O);
                return true;
            }
            //011
            if(board[r][1].getText() == board[r][2].getText() && board[r][1].getText() == "O" && board[r][0].getText() == ""){
                board[r][0].setText(player_O);
                return true;
            }
            //101 
            if(board[r][0].getText() == board[r][2].getText() && board[r][0].getText() == "O" && board[r][1].getText() == ""){
                board[r][1].setText(player_O);
                return true;
            }
        }
        //vertical
        for(int c = 0; c < 3; c ++){
            //110
            if(board[0][c].getText() == board[1][c].getText() && board[0][c].getText() == "O" && board[2][c].getText() == ""){
                board[2][c].setText(player_O);
                return true;
            }
            //011
            if(board[1][c].getText() == board[2][c].getText() && board[1][c].getText() == "O" && board[0][c].getText() == ""){
                board[0][c].setText(player_O);
                return true;
            }
            //101
            if(board[0][c].getText() == board[2][c].getText() && board[0][c].getText() == "O" && board[1][c].getText() == ""){
                board[1][c].setText(player_O);
                return true;
            }
        }
        //diagonal1
        //110
        if(board[0][0].getText() == board[1][1].getText() && board[0][0].getText() == "O" && board[2][2].getText() == ""){
            board[2][2].setText(player_O);
            return true;
        }
        
        //011
        if(board[1][1].getText() == board[2][2].getText() && board[1][1].getText() == "O" && board[0][0].getText() == ""){
            board[0][0].setText(player_O);
            return true;
        }
        //101
        if(board[0][0].getText() == board[2][2].getText() && board[0][0].getText() == "O" && board[1][1].getText() == ""){
            board[1][1].setText(player_O);
            return true;
        }
        //diagonal2
        //110
        if(board[0][2].getText() == board[1][1].getText() && board[0][2].getText() == "O" && board[2][0].getText() == ""){
            board[2][0].setText(player_O);
            return true;
        }
        
        //011
        if(board[1][1].getText() == board[2][0].getText() && board[1][1].getText() == "O" && board[0][2].getText() == ""){
            board[0][2].setText(player_O);
            return true;
        }
        //101
        if(board[0][2].getText() == board[2][0].getText() && board[0][2].getText() == "O" && board[1][1].getText() == ""){
            board[1][1].setText(player_O);
            return true;
        }
        return false;
    }

    boolean botCheck(){
        //horizontal 
        for(int r = 0; r < 3; r ++){
            //110
            if(board[r][0].getText() == board[r][1].getText() && board[r][0].getText() != "" && board[r][2].getText() == ""){
                board[r][2].setText(player_O);
                return true;
            }
            //011
            if(board[r][1].getText() == board[r][2].getText() && board[r][1].getText() != "" && board[r][0].getText() == ""){
                board[r][0].setText(player_O);
                return true;
            }
            //101 
            if(board[r][0].getText() == board[r][2].getText() && board[r][0].getText() != "" && board[r][1].getText() == ""){
                board[r][1].setText(player_O);
                return true;
            }
        }
        //vertical
        for(int c = 0; c < 3; c ++){
            //110
            if(board[0][c].getText() == board[1][c].getText() && board[0][c].getText() != "" && board[2][c].getText() == ""){
                board[2][c].setText(player_O);
                return true;
            }
            //011
            if(board[1][c].getText() == board[2][c].getText() && board[1][c].getText() != "" && board[0][c].getText() == ""){
                board[0][c].setText(player_O);
                return true;
            }
            //101
            if(board[0][c].getText() == board[2][c].getText() && board[0][c].getText() != "" && board[1][c].getText() == ""){
                board[1][c].setText(player_O);
                return true;
            }
        }
        //diagonal1
        //110
        if(board[0][0].getText() == board[1][1].getText() && board[0][0].getText() != "" && board[2][2].getText() == ""){
            board[2][2].setText(player_O);
            return true;
        }
        
        //011
        if(board[1][1].getText() == board[2][2].getText() && board[1][1].getText() != "" && board[0][0].getText() == ""){
            board[0][0].setText(player_O);
            return true;
        }
        //101
        if(board[0][0].getText() == board[2][2].getText() && board[0][0].getText() != "" && board[1][1].getText() == ""){
            board[1][1].setText(player_O);
            return true;
        }
        //diagonal2
        //110
        if(board[0][2].getText() == board[1][1].getText() && board[0][2].getText() != "" && board[2][0].getText() == ""){
            board[2][0].setText(player_O);
            return true;
        }
        
        //011
        if(board[1][1].getText() == board[2][0].getText() && board[1][1].getText() != "" && board[0][2].getText() == ""){
            board[0][2].setText(player_O);
            return true;
        }
        //101
        if(board[0][2].getText() == board[2][0].getText() && board[0][2].getText() != "" && board[1][1].getText() == ""){
            board[1][1].setText(player_O);
            return true;
        }
        return false;
    }    

    int cornerCount(){
        int count = 0;
        if(board[0][0].getText() != ""){
            count ++;
        }
        if(board[0][2].getText() != ""){
            count ++;
        }
        if(board[2][0].getText() != ""){
            count ++;
        }
        if(board[2][2].getText() != ""){
            count ++;
        }
        return count;
    }
}
