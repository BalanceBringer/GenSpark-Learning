public class CollisionHandler {

    GamePanel gp;

    public CollisionHandler(GamePanel gp) {
        this.gp = gp;
    }

    public void checkTile(Entity target){

        int entLeftWorldX = target.worldX + target.collBox.x;
        int entRightWorldX = target.worldX + target.collBox.x + target.collBox.width;
        int entTopWorldY = target.worldY + target.collBox.y;
        int entBotWorldY = target.worldY + target.collBox.y + target.collBox.height;

        int entLeftWorldCol = entLeftWorldX/gp.tileSize;
        int entRightWorldCol = entRightWorldX/gp.tileSize;
        int entTopRow = entTopWorldY/ gp.tileSize;
        int entBotRow = entBotWorldY/gp.tileSize;

        int tileNum1, tileNum2;

        switch(target.direction){
            case "up" :
                entTopRow = (entTopWorldY-target.speed) / gp.tileSize;
                tileNum1 = gp.mappy.mapTileNum[entLeftWorldCol][entTopRow];
                tileNum2 = gp.mappy.mapTileNum[entRightWorldCol][entTopRow];

                if(gp.mappy.tile[tileNum1].collision || gp.mappy.tile[tileNum2].collision){
                    target.collisionOn = true;
                }

                break;
            case "down" :
                entBotRow = (entBotWorldY + target.speed) / gp.tileSize;
                tileNum1 = gp.mappy.mapTileNum[entLeftWorldCol][entBotRow];
                tileNum2 = gp.mappy.mapTileNum[entRightWorldCol][entBotRow];

                if(gp.mappy.tile[tileNum1].collision || gp.mappy.tile[tileNum2].collision){
                    target.collisionOn = true;
                }
                break;
            case "right" :
                entRightWorldCol = (entRightWorldX+target.speed) / gp.tileSize;
                tileNum1 = gp.mappy.mapTileNum[entRightWorldCol][entTopRow];
                tileNum2 = gp.mappy.mapTileNum[entRightWorldCol][entBotRow];

                if(gp.mappy.tile[tileNum1].collision || gp.mappy.tile[tileNum2].collision){
                    target.collisionOn = true;
                }
                break;
            case "left" :
                entLeftWorldCol = (entLeftWorldX-target.speed) / gp.tileSize;
                tileNum1 = gp.mappy.mapTileNum[entLeftWorldCol][entTopRow];
                tileNum2 = gp.mappy.mapTileNum[entLeftWorldCol][entBotRow];

                if(gp.mappy.tile[tileNum1].collision || gp.mappy.tile[tileNum2].collision){
                    target.collisionOn = true;
                }
                break;
        }

    }
    public int checkObject(Entity ent, boolean player){
        int index = 999;

        for(int i = 0; i < gp.obj.length; i++){
            if(gp.obj[i] !=  null){
                //find entities x and y
                ent.collBox.x = ent.worldX + ent.collBox.x;
                ent.collBox.y = ent.worldY + ent.collBox.y;
                //finds objects x and y
                gp.obj[i].collBox.x = gp.obj[i].worldX + gp.obj[i].collBox.x;
                gp.obj[i].collBox.y = gp.obj[i].worldY + gp.obj[i].collBox.y;

                switch(ent.direction){
                    case "up" :
                        ent.collBox.y -= ent.speed;
                        break;
                    case "down" :
                        ent.collBox.y += ent.speed;
                        break;
                    case "left" :
                        ent.collBox.x -= ent.speed;
                        break;
                    case "right" :
                        ent.collBox.x += ent.speed;
                        break;
                }
                if(ent.collBox.intersects(gp.obj[i].collBox)){
                    if(gp.obj[i].collision){
                        ent.collisionOn = true;
                    }
                    if(player){
                        index = i;
                    }
                }
                ent.collBox.x = ent.collBoxDefX;
                ent.collBox.y = ent.collBoxDefY;
                gp.obj[i].collBox.x = gp.obj[i].collBoxDefX;
                gp.obj[i].collBox.y = gp.obj[i].collBoxDefY;
            }
        }

        return index;
    }
    public int checkEnt(Entity ent, Entity[] target){
        int index = 999;

        for(int i = 0; i < target.length; i++){
            if(target[i] !=  null){
                //find entities x and y
                ent.collBox.x = ent.worldX + ent.collBox.x;
                ent.collBox.y = ent.worldY + ent.collBox.y;
                //finds objects x and y
                target[i].collBox.x = target[i].worldX + target[i].collBox.x;
                target[i].collBox.y = target[i].worldY + target[i].collBox.y;

                switch(ent.direction){
                    case "up" :
                        ent.collBox.y -= ent.speed;
                        break;
                    case "down" :
                        ent.collBox.y += ent.speed;
                        break;
                    case "left" :
                        ent.collBox.x -= ent.speed;
                        break;
                    case "right" :
                        ent.collBox.x += ent.speed;
                        break;
                }
                if(ent.collBox.intersects(target[i].collBox)){
                    if(target[i] != ent) {
                        index = i;
                        ent.collisionOn = true;
                    }

                }
                ent.collBox.x = ent.collBoxDefX;
                ent.collBox.y = ent.collBoxDefY;
                target[i].collBox.x = target[i].collBoxDefX;
                target[i].collBox.y = target[i].collBoxDefY;
            }
        }

        return index;
    }
    public boolean checkPlayer(Entity ent){

        boolean contactPlayer = false;
        //find entities x and y
        ent.collBox.x = ent.worldX + ent.collBox.x;
        ent.collBox.y = ent.worldY + ent.collBox.y;
        //finds objects x and y
        gp.player.collBox.x = gp.player.worldX + gp.player.collBox.x;
        gp.player.collBox.y = gp.player.worldY + gp.player.collBox.y;

        switch(ent.direction){
            case "up" :
                ent.collBox.y -= ent.speed;
                break;
            case "down" :
                ent.collBox.y += ent.speed;
                break;
            case "left" :
                ent.collBox.x -= ent.speed;
                break;
            case "right" :
                ent.collBox.x += ent.speed;
                break;
        }
        if(ent.collBox.intersects(gp.player.collBox)){

            ent.collisionOn = true;
            contactPlayer = true;


        }
        ent.collBox.x = ent.collBoxDefX;
        ent.collBox.y = ent.collBoxDefY;
        gp.player.collBox.x = gp.player.collBoxDefX;
        gp.player.collBox.y = gp.player.collBoxDefY;
        return contactPlayer;

    }
}
