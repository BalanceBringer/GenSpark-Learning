import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MapMaker {

    GamePanel gp;
    Tile[] tile;
    int mapTileNum[][];

    public MapMaker(GamePanel gp){
        this.gp=gp;
        tile = new Tile[10];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        loadMap("/maps/world01.txt");
    }
    public void getTileImage(){

            setup(0, "grass1", false);
            setup(1, "stonewall1", true);
            setup(2, "water1", true);
            setup(3, "earth1", false);
            setup(4, "tree1", true);
            setup(5, "sand1", false);

    }

    public void setup(int index, String imageName, boolean collision){
        DevToolKit kit = new DevToolKit();

        try{
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/"+ imageName +".png"));
            tile[index].image = kit.scaleImage(tile[index].image,gp.tileSize, gp.tileSize);
            tile[index].collision = collision;
        } catch(IOException uhOh){
            uhOh.printStackTrace();
            System.out.println("Something went wrong loading tiles");
        }

    }


    public void loadMap(String filePath) {


        try {
            InputStream input = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(input));

            int col = 0;
            int row = 0;

            while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
                String line = br.readLine();
                while (col < gp.maxWorldCol) {
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                    if (col == gp.maxWorldCol) {
                        col = 0;
                        row++;
                    }
                }
                br.close();
            }
         catch (Exception uhOh) {
            uhOh.printStackTrace();
            System.out.println("Something went wrong reading map form from file");
        }
    }



    public void draw(Graphics2D g2){
       int locWorldCol = 0;
       int locWorldRow = 0;

       while(locWorldCol < gp.maxWorldCol &&locWorldRow < gp.maxWorldRow){
           int tileNum = mapTileNum[locWorldCol][locWorldRow];
           int locWorldX = locWorldCol * gp.tileSize;
           int locWorldY = locWorldRow * gp.tileSize;
           int locScreenX = locWorldX - gp.player.worldX + gp.player.screenX;
           int locScreenY = locWorldY - gp.player.worldY + gp.player.screenY;

           if(locWorldX > gp.player.worldX -gp.player.screenX - gp.tileSize && locWorldX < gp.player.worldX + gp.player.screenX + gp.tileSize && locWorldY  > gp.player.worldY - gp.player.screenY - gp.tileSize && locWorldY < gp.player.worldY + gp.player.screenY + gp.tileSize){
               g2.drawImage(tile[tileNum].image,locScreenX,locScreenY ,null);
           }

           locWorldCol++;

           if(locWorldCol == gp.maxWorldCol){
               locWorldCol = 0;

               locWorldRow++;

           }
       }
    }
}
