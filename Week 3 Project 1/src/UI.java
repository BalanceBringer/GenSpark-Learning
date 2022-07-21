import java.awt.*;
import java.awt.image.BufferedImage;

public class UI {

    GamePanel gp;
    Graphics2D g2;
    Font arial80 = new Font("Arial", Font.PLAIN, 40);
    public boolean messageOn = false;
    public String message = "";
    int messageTime = 0;

    public String currentDialogue = "";
    int commandNum = 0;
    BufferedImage haloFull, haloHalf, haloEmpty;
    public UI(GamePanel gp){
        this.gp = gp;
        //create HUD Object
        Entity halo = new HaloObject(gp);
        haloFull = halo.image;
        haloHalf = halo.image2;
        haloEmpty = halo.image3;
    }
    public void draw(Graphics2D g2){
        this.g2 = g2;

        g2.setFont(arial80);
        g2.setColor(Color.WHITE);
        if(gp.gameState == gp.playState){
            //do playstate stuff later
            drawPlayerHealth();
        } else if(gp.gameState == gp.pauseState){
            drawPlayerHealth();
            drawPauseScreen();
        } else if(gp.gameState == gp.dialogueState){
            drawPlayerHealth();
            drawDialogueScreen();
        } else if(gp.gameState == gp.titleState){
            drawTitleScreen();
        }
    }
    public void showMessage(String mess){
        message = mess;
        messageOn = true;
    }

    public void drawPauseScreen(){
        String pauseMess = "Paused";
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80f));
        int length = (int)g2.getFontMetrics().getStringBounds(pauseMess,g2).getWidth();
        int x = gp.screenWidth/2-length/2;
        int y = gp.screenHeight/2;
        g2.drawString(pauseMess,x,y);
    }
    public void drawDialogueScreen(){
        int x = gp.tileSize * 2;
        int y = gp.tileSize/2;
        int width = gp.screenWidth -(gp.tileSize)*4;
        int height = gp.tileSize*4;

        drawSubWindow(x,y,width,height);

        x += gp.tileSize;
        y += gp.tileSize;
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 30f));

        for(String line :currentDialogue.split("/n")){
            g2.drawString(line,x, y);
            y += 40;
        }

    }

    public void drawSubWindow(int x, int y, int width, int height){
        Color c = new Color(0,0,0,210);
        g2.setColor(c);
        g2.fillRoundRect(x,y,width,height,45,45);

        g2.setColor(Color.WHITE);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5,y +5,width-10, height-10, 45,45);
    }
    public void drawTitleScreen(){
        g2.setFont(new Font("Bradley Hand ITC",Font.BOLD,70));

        String title = "Adventure 2: Journey";
        int length = (int)g2.getFontMetrics().getStringBounds(title,g2).getWidth();
        int x = gp.screenWidth/2-length/2;
        int y = gp.tileSize * 3;

        g2.setColor(Color.darkGray);
        g2.drawString(title, x + 5, y + 5);

        g2.setColor(Color.WHITE);
        g2.drawString(title,x,y);

        x = gp.screenWidth/2 -(gp.tileSize*2)/2;
        y += 2 * gp.tileSize;
        g2.drawImage(gp.player.down1, x, y, gp.tileSize * 2, gp.tileSize * 2, null);

        //menu
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 30f));

        String text = "New Game";
        length = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
        x = (gp.screenWidth/2-length/2);
        y += gp.tileSize * 4;
        g2.drawString(text,x,y);
        if(commandNum == 0){
            g2.drawString(">",x - gp.tileSize, y);
        }

        text = "Load Game";
        length = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
        x = gp.screenWidth/2-length/2;
        y += gp.tileSize;
        g2.drawString(text,x,y);
        if(commandNum == 1){
            g2.drawString(">",x - gp.tileSize, y);
        }

        text = "Quit";
        length = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
        x = gp.screenWidth/2-length/2;
        y += gp.tileSize;
        g2.drawString(text,x,y);
        if(commandNum == 2){
            g2.drawString(">",x - gp.tileSize, y);
        }
    }
    public void drawPlayerHealth(){
        int x = gp.tileSize/2;
        int y = gp.tileSize/2;
        int i = 0;

        //Draws blank halos
        while(i < gp.player.maxLife/2){
            g2.drawImage(haloEmpty, x, y, null);
            i++;
            x += gp.tileSize + 5;
        }
        x = gp.tileSize/2;
        y = gp.tileSize/2;
        i = 0;

        //Draws actual health
        while(i < gp.player.life){
            g2.drawImage(haloHalf,x,y,null);
            i++;
            if(i < gp.player.life){
                g2.drawImage(haloFull,x,y,null);
                i++;
                x+=gp.tileSize + 5;
            }
        }
    }
}
