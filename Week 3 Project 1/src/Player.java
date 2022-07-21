import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class Player extends Entity{

    KeyHandler keyH;
    public final int screenX;
    public final int screenY;

    DevToolKit kit = new DevToolKit();


    public Player(GamePanel gp, KeyHandler keyH){
        super(gp);
        this.gp = gp;
        this.keyH =keyH;

        attackHitBox.width= 36;
        attackHitBox.height= 36;

        screenX = gp.screenWidth/2 - gp.tileSize/2;
        screenY = gp.screenHeight/2 - gp.tileSize/2;

        collBox = new Rectangle(8,16,32,32); //puts collision box a little smaller and inside character
        collBoxDefX = collBox.x;
        collBoxDefY = collBox.y;

        setDefaultValues();
        getPlayerImage();
        getAttackImage();
    }

    public void setDefaultValues(){
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 4;
        direction = "down";
        //Player Status
        maxLife = 8;
        life = maxLife;
    }

    public void getPlayerImage(){
        try{
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/walk_up_play_1-1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/walk_up_play_2-1.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/walk_down_1_play-1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/walk_down_2_play-1.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/walk_right_1_play-1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/walk_right_2_play-1.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/walk_left_1_play-1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/walk_left_2_play-1.png"));

            up1 = kit.scaleImage(up1, gp.tileSize, gp.tileSize);
            up2 = kit.scaleImage(up2, gp.tileSize, gp.tileSize);
            down1 = kit.scaleImage(down1, gp.tileSize, gp.tileSize);
            down2 = kit.scaleImage(down2, gp.tileSize, gp.tileSize);
            right1 = kit.scaleImage(right1, gp.tileSize, gp.tileSize );
            right2 = kit.scaleImage(right2, gp.tileSize, gp.tileSize);
            left1 = kit.scaleImage(left1, gp.tileSize, gp.tileSize );
            left2 = kit.scaleImage(left2, gp.tileSize, gp.tileSize);
        }catch(IOException uhOh){
            uhOh.printStackTrace();
            System.out.println("Error getting player images");
        }
    }

    public void getAttackImage(){
        try{
            attackUp1 = ImageIO.read(getClass().getResourceAsStream("/player/attack_up1.png"));
            attackUp2 = ImageIO.read(getClass().getResourceAsStream("/player/attack_up2.png"));
            attackDown1 = ImageIO.read(getClass().getResourceAsStream("/player/attack_down1.png"));
            attackDown2 = ImageIO.read(getClass().getResourceAsStream("/player/attack_down2.png"));
            attackRight1 = ImageIO.read(getClass().getResourceAsStream("/player/attack_right1.png"));
            attackRight2 = ImageIO.read(getClass().getResourceAsStream("/player/attack_right2.png"));
            attackLeft1 = ImageIO.read(getClass().getResourceAsStream("/player/attack_left1.png"));
            attackLeft2 = ImageIO.read(getClass().getResourceAsStream("/player/attack_left2.png"));

            attackUp1 = kit.scaleImage(attackUp1, gp.tileSize, gp.tileSize * 2);
            attackUp2 = kit.scaleImage(attackUp2, gp.tileSize, gp.tileSize * 2);
            attackDown1 = kit.scaleImage(attackDown1, gp.tileSize, gp.tileSize * 2);
            attackDown2 = kit.scaleImage(attackDown2, gp.tileSize, gp.tileSize * 2);
            attackRight1 = kit.scaleImage(attackRight1, gp.tileSize * 2, gp.tileSize );
            attackRight2 = kit.scaleImage(attackRight2, gp.tileSize * 2, gp.tileSize);
            attackLeft1 = kit.scaleImage(attackLeft1, gp.tileSize * 2, gp.tileSize );
            attackLeft2 = kit.scaleImage(attackLeft2, gp.tileSize * 2, gp.tileSize);



        } catch (IOException uhOh){
            uhOh.printStackTrace();
            System.out.println("Error getting attack images");
        }
    }

    public void update(){
        if(attacking){attack();}

        else if(keyH.downPressed|| keyH.upPressed|| keyH.leftPressed|| keyH.rightPressed || keyH.enterPressed || keyH.spacePressed) {

        if(keyH.upPressed){
            direction = "up";
        }
        else if(keyH.leftPressed){
            direction = "left";
        }
        else if(keyH.downPressed){
            direction = "down";
        }
        else if(keyH.rightPressed){
            direction = "right";
        }


        collisionOn = false;
        gp.collHand.checkTile(this);
        int objIndex = gp.collHand.checkObject(this,true);
        int entIndex = gp.collHand.checkEnt(this,gp.ent);
        int mobIndex = gp.collHand.checkEnt(this,gp.mob);
        pickUp(objIndex);
        gp.eH.checkEvent();

        interactEnt(entIndex);
        mobHit(mobIndex);



        if(!collisionOn && !keyH.enterPressed && !keyH.spacePressed){
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

            gp.keyH.enterPressed = false;
            keyH.spacePressed = false;

            spriteCounter++;
            if(spriteCounter > 10){
                if(spriteNum == 1){
                    spriteNum = 2;
                } else if(spriteNum == 2){
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }

        if(invince){
            invinceCounter++;
            if(invinceCounter >= 60){
                invince = false;
                invinceCounter = 0;
            }
        }

    }

    public void pickUp(int ind){
        if(ind != 999){

        }
    }

    public void interactEnt(int ind){
        if(ind != 999){

            if(keyH.enterPressed){
                gp.gameState = gp.dialogueState;
                gp.ent[ind].speak();
                gp.ent[ind].dialogueIndex++;
            }

            keyH.enterPressed = false;
        }
        if(keyH.spacePressed){
            attacking = true;
        }
    }
    public void mobHit(int ind){
        if(ind != 999){
            if(!invince){
                life -= 1;
                invince = true;
            }
        }
    }

    public void attack(){
        spriteCounter++;
        if(spriteCounter <= 5){
            spriteNum = 1;
        } if(spriteCounter > 5 && spriteCounter <= 25){
            spriteNum = 2;

            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidWidth = collBox.width;
            int solidHeight = collBox.height;
            switch (direction){
                case "up" : worldY -= attackHitBox.height; break;
                case "down" : worldY += attackHitBox.height; break;
                case "left" : worldX -= attackHitBox.width; break;
                case "right" : worldX += attackHitBox.width; break;
            }
            collBox.width = attackHitBox.width;
            collBox.height = attackHitBox.height;

            int mobIndex = gp.collHand.checkEnt(this, gp.mob);

            worldX = currentWorldX;
            worldY = currentWorldY;
            collBox.width = solidWidth;
            collBox.height = solidHeight;

            damageMonster(mobIndex);

        }if(spriteCounter > 25){
            spriteNum = 1;
            spriteCounter = 0;
            attacking = false;
        }
    }
    public void draw(Graphics2D g2){
//        g2.setColor(Color.white);
//        g2.fillRect(x,y,gp.tileSize,gp.tileSize);

        BufferedImage image = null;
        int tempScreenX  = screenX;
        int tempScreenY = screenY;

        switch(direction) {
            case "up":
                if (!attacking) {
                    if (spriteNum == 1) {
                        image = up1;
                    }
                    if (spriteNum == 2) {
                        image = up2;
                    }
                }
                if(attacking){
                    tempScreenY -= gp.tileSize;
                    if(spriteNum == 1){
                        image = attackUp1;
                    }
                    if(spriteNum == 2){
                        image = attackUp2;
                    }
                }
                break;

            case "down" :
                if(!attacking) {
                    if (spriteNum == 1) {
                        image = down1;
                    }
                    if (spriteNum == 2) {
                        image = down2;
                    }
                }
                if(attacking){
                    if(spriteNum == 1){
                        image = attackDown1;
                    }
                    if(spriteNum == 2){
                        image = attackDown2;
                    }
                }
                break;
            case "left" :
                if(!attacking) {
                    if (spriteNum == 1) {
                        image = left1;
                    }
                    if (spriteNum == 2) {
                        image = left2;
                    }
                }
                if(attacking){
                    tempScreenX -= gp.tileSize;
                    if(spriteNum == 1){
                        image = attackLeft1;
                    }
                    if(spriteNum == 2){
                        image = attackLeft2;
                    }
                }
                break;
            case "right" :
                if(!attacking) {
                    if (spriteNum == 1) {
                        image = right1;
                    }
                    if (spriteNum == 2) {
                        image = right2;
                    }
                }
                if(attacking){
                    if(spriteNum == 1){
                        image = attackRight1;
                    }
                    if(spriteNum == 2){
                        image = attackRight2;
                    }
                }
                break;
            }
            if(invince){
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
            }

        g2.drawImage(image,tempScreenX,tempScreenY,null);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
    }

    public void damageMonster(int ind){
        Random randy = new Random();
        if( ind != 999){
            if(!gp.mob[ind].invince){
                gp.mob[ind].life -= randy.nextInt(3) + 1;
                gp.mob[ind].invince = true;

                if(gp.mob[ind].life <= 0){
                    gp.mob[ind] = null;
                }
            }
        }
    }
}
