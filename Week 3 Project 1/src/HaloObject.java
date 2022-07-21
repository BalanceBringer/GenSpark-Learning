import javax.imageio.ImageIO;
import java.io.IOException;

public class HaloObject extends Entity{


    DevToolKit kit = new DevToolKit();
    public HaloObject(GamePanel gp){
        super(gp);
        name = "Halo";


        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/life_halo_full.png"));
            image2 = ImageIO.read(getClass().getResourceAsStream("/objects/life_halo_half.png"));
            image3 = ImageIO.read(getClass().getResourceAsStream("/objects/life_halo_empty.png"));
            down1 = image;

            image = kit.scaleImage(image,gp.tileSize,gp.tileSize);
            image2 = kit.scaleImage(image2,gp.tileSize,gp.tileSize);
            image3 = kit.scaleImage(image3,gp.tileSize,gp.tileSize);

        } catch(IOException uhOh){
            uhOh.printStackTrace();
            System.out.println("Something went wrong trying to load the item " + name);
        }
        collision = true;
    }

}
