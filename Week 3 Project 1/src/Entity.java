import org.w3c.dom.css.Rect;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {

    GamePanel gp;
    public int worldX, worldY, speed;
    public BufferedImage image, image2, image3;
    String name;
    public boolean collision = false;

    public BufferedImage up1,up2,down1,down2,right1,right2,left1,left2, attackUp1, attackUp2, attackDown1, attackDown2, attackRight1, attackRight2, attackLeft1, attackLeft2;;
    public String direction = "down";

    public int spriteCounter = 0;
    public int spriteNum = 1;
    public int spriteCounter2 = 0;
    public int spriteNum2 = 1;

    //Collision
    public Rectangle collBox = new Rectangle(8,16, 32,32);
    public int collBoxDefX, collBoxDefY;
    public boolean collisionOn = false;
    public int actionLockCounter = 0;

    //Invincibility frames
    public int invinceCounter = 0;
    public boolean invince = false;

    //Entity Typing
    public int entType;

    //attacking
    boolean attacking = false;
    Rectangle attackHitBox = new Rectangle(0,0,0,0);

    //Dialogue var
    String dialogue[] = new String[30];
    int dialogueIndex = 0;

    //Player Status
    public int maxLife, life;

    public Entity(GamePanel gp){
        this.gp = gp;
    }

    public void draw(Graphics2D g2){
        int locScreenX = worldX - gp.player.worldX + gp.player.screenX;
        int locScreenY = worldY - gp.player.worldY + gp.player.screenY;

        if(worldX  > gp.player.worldX - gp.player.screenX - gp.tileSize&&
                worldX  < gp.player.worldX + gp.player.screenX + gp.tileSize&&
                worldY  > gp.player.worldY - gp.player.screenY - gp.tileSize&&
                worldY  < gp.player.worldY + gp.player.screenY + gp.tileSize){
            BufferedImage image = null;

            switch(direction){
                case "up": if(spriteNum == 1){
                    image = up1;
                } else {
                    image = up2;
                }
                    break;
                case "down" : if(spriteNum ==1){
                    image = down1;
                } else{
                    image = down2;
                }
                    break;
                case "left" : if(spriteNum==1){
                    image = left1;
                } else{
                    image = left2;
                }
                    break;
                case "right" : if(spriteNum ==1){
                    image = right1;
                } else {
                    image = right2;
                }
            }
            if(invince){
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
            }
            g2.drawImage(image,locScreenX,locScreenY ,gp.tileSize,gp.tileSize,null);
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
        }

    }
    public void setAction(){

    }
    public void speak(){
        if(dialogue[dialogueIndex] == null){
            dialogueIndex = 0;
        }
        gp.ui.currentDialogue = dialogue[dialogueIndex];


        switch(gp.player.direction) {
            case "up":
                this.direction = "down";
                break;
            case "down":
                this.direction = "up";
                break;
            case "left":
                this.direction = "right";
                break;
            case "right":
                this.direction = "left";
                break;
        }
    }
    public void update(){
        setAction();

        collisionOn = false;
        gp.collHand.checkTile(this);
        gp.collHand.checkObject(this, false);
        gp.collHand.checkPlayer(this);
        gp.collHand.checkEnt(this, gp.mob);
        gp.collHand.checkEnt(this, gp.ent);
        boolean contactPlayer = gp.collHand.checkPlayer(this);

        if(this.entType == 2 && contactPlayer){
            if(!gp.player.invince){
                gp.player.life--;
                gp.player.invince = true;
            }
        }

        if(!collisionOn){
            switch(direction){
                case "up" : worldY -= speed;
                    break;
                case "down" : worldY += speed;
                    break;
                case "left" : worldX -= speed;
                    break;
                case "right" : worldX += speed;
                    break;
            }
        }

        spriteCounter++;
        if(spriteCounter > 10){
            if(spriteNum == 1){
                spriteNum = 2;
            } else if(spriteNum == 2){
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
        if(invince){
            invinceCounter++;
            if(invinceCounter >= 40){
                invince = false;
                invinceCounter = 0;
            }
        }
    }

}
