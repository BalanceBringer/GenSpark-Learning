import javax.imageio.ImageIO;
import java.io.IOException;

public class KeyObject extends Entity {

    public KeyObject(GamePanel gp){
        super(gp);
        name = "Key";

        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/key.png"));
            down1 = image;

        } catch(IOException uhOh){
            uhOh.printStackTrace();
            System.out.println("Something went wrong trying to load the item " + name);
        }
        collision = true;
    }

}
