import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class StartMenu {
    int boardWidth = 600;
    int boardHeight = 650; 

    JFrame frame = new JFrame("Tic Tac Toe");
    JLabel textLabel = new JLabel();
    JPanel textPanel = new JPanel();
    JPanel gapPanel = new JPanel();
    JPanel gapPanel2 = new JPanel();
    JPanel buttonPanel = new JPanel();

    StartMenu(){
        //Frame
        frame.setVisible(true);
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        //Label
        textLabel.setBackground(Color.gray);
        textLabel.setForeground(Color.white);
        textLabel.setFont(new Font("Verdana", Font.BOLD, 70));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("Tic Tac Toe");
        textLabel.setOpaque(true);

        buttonPanel.setBackground(Color.gray);
        buttonPanel.setForeground(Color.white);
        buttonPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        textPanel.setBackground(Color.gray);
        textPanel.add(textLabel);
        c.gridy = 0;
        buttonPanel.add(textPanel, c);
        frame.add(buttonPanel);

        //One player
        JButton button1 = new JButton();  
        button1.setBackground(Color.darkGray);
        button1.setForeground(Color.white);
        button1.setPreferredSize(new Dimension(420, 100));
        button1.setFont(new Font("Verdana", Font.BOLD, 17));
        button1.setText("One Player");
        button1.setFocusable(false);
        c.gridy = 2;
        buttonPanel.add(button1, c);

        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                TicTacToe ticTacToe = new TicTacToe(1);
                frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            }
        });  

        //Gap
        gapPanel.setBackground(Color.gray);
        gapPanel.setPreferredSize(new Dimension(200, 60));
        c.gridy = 1;
        buttonPanel.add(gapPanel, c);
        gapPanel2.setBackground(Color.gray);
        gapPanel2.setPreferredSize(new Dimension(200, 55));
        c.gridy = 3;
        buttonPanel.add(gapPanel2, c);
        

        //Two player
        JButton button2 = new JButton();  
        button2.setBackground(Color.darkGray);
        button2.setForeground(Color.white);
        button2.setPreferredSize(new Dimension(420, 100));
        button2.setFont(new Font("Verdana", Font.BOLD, 17));
        button2.setText("Two Player");
        button2.setFocusable(false);
        c.gridy = 4;
        buttonPanel.add(button2, c);
        
         button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                TicTacToe ticTacToe = new TicTacToe(2);
                frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            }
        });  
    }
}
