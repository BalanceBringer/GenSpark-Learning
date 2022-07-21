public class EventHandler {

    GamePanel gp;
    EventRect eventRect[][];
    int previousX, previousY;
    boolean eventOn = true;

    public EventHandler(GamePanel gp){
        this.gp = gp;
        eventRect = new EventRect[gp.maxWorldCol][gp.maxWorldRow];
        int col = 0;
        int row = 0;
        while(col < gp.maxWorldCol && row < gp.maxWorldRow){
            eventRect[col][row] = new EventRect();
            eventRect[col][row].x = 23;
            eventRect[col][row].y = 23;
            eventRect[col][row].width = gp.tileSize;
            eventRect[col][row].height = gp.tileSize;
            eventRect[col][row].eventRectDefX =eventRect[col][row].x;
            eventRect[col][row].eventRectDefY=eventRect[col][row].y;
            col++;
            if(col == gp.maxWorldCol){
                col = 0;
                row++;
            }
        }

    }

    public void checkEvent(){

        //event reset
        int xDist = Math.abs(gp.player.worldX - previousX);
        int yDist = Math.abs(gp.player.worldY - previousY);
        int dist = Math.max(xDist, yDist);
        if(dist > gp.tileSize){
            eventOn = true;
        }
        if(eventOn) {
            if (hit(25, 15, "right")) {
                damagePit(25, 15, gp.dialogueState);
            }
            if (hit(23, 7, "up")) {
                healingSpot(25, 15, gp.dialogueState);
            }
        }

    }


    public boolean hit(int col, int row, String reqDirection){
        boolean hit = false;

        gp.player.collBox.x = gp.player.worldX + gp.player.collBox.x;
        gp.player.collBox.y = gp.player.worldY + gp.player.collBox.y;

        eventRect[col][row].x = col * gp.tileSize;
        eventRect[col][row].y = row * gp.tileSize;

        if(gp.player.collBox.intersects(eventRect[col][row]) && eventRect[col][row].eventDone == false){
            if(gp.player.direction.equals(reqDirection) || reqDirection.equals("any")){
                hit = true;
                previousX = gp.player.worldX;
                previousY = gp.player.worldY;
            }
        }

        gp.player.collBox.x = gp.player.collBoxDefX;
        gp.player.collBox.y = gp.player.collBoxDefY;
        eventRect[col][row].x = eventRect[col][row].eventRectDefX;
        eventRect[col][row].y = eventRect[col][row].eventRectDefY;

        return hit;
    }

    public void damagePit(int col, int row,int gameState){
        gp.gameState = gameState;
        gp.ui.currentDialogue = "You step on a lego! Ow!";
        gp.player.life-= 3;
        eventOn = false;
        //eventRect[col][row].eventDone = true;
    }

    public void healingSpot(int col, int row,int gameState){
        if(gp.keyH.enterPressed && gp.player.life < gp.player.maxLife && !eventRect[col][row].eventDone){
            gp.gameState = gameState;
            gp.ui.currentDialogue = "You get some blue flex seal. Relief!";
            gp.player.life++;
            eventRect[col][row].eventDone = true;

        }

    }

}
