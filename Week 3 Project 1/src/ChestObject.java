import javax.imageio.ImageIO;
import java.io.IOException;

public class ChestObject extends Entity{

    public ChestObject(GamePanel gp){
        super(gp);
        name = "Closed Chest";

        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/closed_chest.png"));

        } catch(IOException uhOh){
            uhOh.printStackTrace();
            System.out.println("Something went wrong trying to load the item " + name);
        }
        collision = true;
    }

}
