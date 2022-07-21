import javax.imageio.ImageIO;
import java.io.IOException;

public class DoorObject extends Entity{

    public DoorObject(GamePanel gp){
        super(gp);
        name = "Closed Door";

        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/closed_door1.png"));
            down1 = image;

        } catch(IOException uhOh){
            uhOh.printStackTrace();
            System.out.println("Something went wrong trying to load the item " + name);
        }
        collision = true;
    }

}
