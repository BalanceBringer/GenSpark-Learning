import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class GamePanelTest {
    @org.junit.jupiter.api.Test

    void setUpGame() {
        GamePanel gp = new GamePanel();
        boolean gameStarted = false;
        gp.setUpGame();
        if(gp.gameState== gp.titleState){
           gameStarted = true;
        }
        assertEquals(true, gameStarted);
    }

    @org.junit.jupiter.api.Test
    void startGameThread() {
        boolean gameThreadStarted = false;
        GamePanel gp = new GamePanel();
        gp.startGameThread();

        if(gp.gameThread != null){
            gameThreadStarted = true;
        }
        assertEquals(true, gameThreadStarted);
    }

    @org.junit.jupiter.api.Test
    void run() {
        GamePanel gp = new GamePanel();
        gp.run();

    }

    @org.junit.jupiter.api.Test
    void update() {
        GamePanel gp = new GamePanel();
        gp.update();
        System.out.println("Game State: " + gp.gameState);
    }

    @org.junit.jupiter.api.Test
    void paintComponent() {
        GamePanel gp = new GamePanel();
        gp.repaint();

    }

}