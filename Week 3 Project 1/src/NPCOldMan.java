import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Random;

public class NPCOldMan extends Entity{

    public NPCOldMan(GamePanel gp){
        super(gp);

        direction = "down";
        speed= 1;

        getNPCImage();
        setDialogue();
    }

    public void getNPCImage(){
        try{
            up1 = ImageIO.read(getClass().getResourceAsStream("/npc/old_man_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/npc/old_man_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/npc/old_man_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/npc/old_man_down_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/npc/old_man_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/npc/old_man_right_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/npc/old_man_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/npc/old_man_left_2.png"));
        }catch(IOException e){
            e.printStackTrace();
            System.out.println("Error getting sprites for NPCOldMan");
        }
    }

    @Override
    public void speak(){
        super.speak();
    }
    @Override
    public void setAction() {
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
    public void setDialogue(){
        dialogue[0] = "Hello, young one.";
        dialogue[1] = "So there has been nuclear blast";
        dialogue[2] = "Don't ask how or why.";
        dialogue[3] = "Just know the big one happened.";
    }
}
