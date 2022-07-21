import javax.imageio.ImageIO;
import java.io.IOException;

public class SpeedWingObject extends Entity{

    public SpeedWingObject(GamePanel gp){
        super(gp);
        name = "Speed Wings";

        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/speedwings.png"));
            down1 = image;

        } catch(IOException uhOh){
            uhOh.printStackTrace();
            System.out.println("Something went wrong trying to load the item " + name);
        }
        collision = true;
    }

}
