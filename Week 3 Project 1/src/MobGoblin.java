import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Random;

public class MobGoblin extends Entity{


    public MobGoblin(GamePanel gp) {
        super(gp);
        name = "Goblin";
        speed = 2;
        maxLife = 2;
        life = maxLife;
        collBox.x = 3;
        collBox.y = 3;
        collBox.width = 42;
        collBox.height = 39;
        collBoxDefX = collBox.x;
        collBoxDefY = collBoxDefY;
        entType = 2;
        getImage();
    }

    public void getImage(){
        try{
            up1 = ImageIO.read(getClass().getResourceAsStream("/monsters/goblin_up1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/monsters/goblin_up2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/monsters/goblin_down1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/monsters/goblin_down2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/monsters/goblin_right1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/monsters/goblin_right2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/monsters/goblin_left1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/monsters/goblin_left2.png"));
        }catch(IOException e){
            e.printStackTrace();
            System.out.println("Error getting sprites for MobGoblin");
        }
    }
    public void setAction(){
        actionLockCounter++;
        Random randy = new Random();

        if(actionLockCounter ==60) {
            int i = randy.nextInt(100) + 1;

            if(i <= 25){
                direction = "down";
            }
            if(i > 25 && i <= 50){
                direction = "up";
            }
            if(i > 50 && i <= 75){
                direction = "right";
            }
            if(i > 75 && i <= 100){
                direction = "left";
            }
            actionLockCounter = 0;

        }
    }

}
