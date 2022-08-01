import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class MyFrame extends JFrame {
    public int wrong;

    public MyFrame(int numWrong) throws IOException {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500,500);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        getImage();
        wrong = numWrong;
    }

    public BufferedImage image0, image1, image2, image3, image4, image5, image6;
public void getImage() {
    try {
        image0 = ImageIO.read(getClass().getResourceAsStream("/image/image1.png"));
        image1 = ImageIO.read(getClass().getResourceAsStream("/image/image2.png"));
        image2 = ImageIO.read(getClass().getResourceAsStream("/image/image3.png"));
        image3 = ImageIO.read(getClass().getResourceAsStream("/image/image4.png"));
        image4 = ImageIO.read(getClass().getResourceAsStream("/image/image5.png"));
        image5 = ImageIO.read(getClass().getResourceAsStream("/image/image6.png"));
        image6 = ImageIO.read(getClass().getResourceAsStream("/image/image7.png"));

    } catch (IOException e) {
        e.printStackTrace();
        System.out.println("Error getting images for hangman");
    }
}

    public void paint(Graphics g){
        Graphics2D g2 = (Graphics2D)g;

        switch (wrong){
            case 0:
                g2.drawImage(image0,0,0,500,500,null);
                break;
            case 1:
                g2.drawImage(image1,0,0,500,500,null);
                break;
            case 2:
                g2.drawImage(image2,0,0,500,500,null);
                break;
            case 3:
                g2.drawImage(image3,0,0,500,500,null);
                break;
            case 4:
                g2.drawImage(image4,0,0,500,500,null);
                break;
            case 5:
                g2.drawImage(image5,0,0,500,500,null);
                break;
            case 6:
                g2.drawImage(image6,0,0,500,500,null);
                break;
        }

    }
}
