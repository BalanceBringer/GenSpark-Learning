import javax.swing.*;

public class Main {
    public static void main(String[] args){

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Adventure 2: Electric Boogaloo");

        //Game panel
        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        window.pack();//Super important, sets the panel to preferred size

        window.setLocationRelativeTo(null); //displays window at the center of the screen
        window.setVisible(true);

        gamePanel.setUpGame();
        gamePanel.startGameThread();


    }
}
