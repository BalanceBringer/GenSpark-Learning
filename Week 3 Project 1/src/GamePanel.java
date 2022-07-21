import javax.swing.JPanel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class GamePanel extends JPanel implements Runnable{

    //Tile Settings
    final int originalTileSize = 16; //16X16 tiles
    final int scale = 3;
    public final int tileSize = originalTileSize * scale; //48x48 tiles

    //Screen Settings
    public final int maxScreenCol = 20;
    public final int maxScreenRow = 14;
    public final int screenWidth = tileSize * maxScreenCol; // 768 px
    public final int screenHeight = tileSize * maxScreenRow; // 576 px

    //The game time
    Thread gameThread;

    int ups = 60;
    //Game State
    public int gameState;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;
    public final int titleState = 0;
    //World
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;

    MapMaker mappy = new MapMaker(this);


    //System
    EventHandler eH = new EventHandler(this);
    CollisionHandler collHand = new CollisionHandler(this);
    KeyHandler keyH = new KeyHandler(this);
    Sound sound = new Sound();
    //Entities list
    Player player = new Player(this,keyH);
    Entity ent[] = new Entity[30];
    Entity obj[] = new Entity[30];
    Entity mob[] = new Entity[30];
    ArrayList<Entity> entityList = new ArrayList<>();


    //UI
    UI ui = new UI(this);

    //Objects lists
    AssetSetter gOC = new AssetSetter(this);

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); // sets size of this jpanel
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);//draws everything in this component offscreen to save memory
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setUpGame(){
        gOC.setObject();
        gOC.setNPC();
        gOC.setMob();
        gameState = titleState;
    }

    public void startGameThread(){
        gameThread = new Thread(this);//Instantiate game panel thread
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000/ups;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while(gameThread != null){
            update();
            repaint();

            //tries to make the thread sleep
            try {
                double waitTime = nextDrawTime - System.nanoTime();
                waitTime = waitTime/1000000;

                if(waitTime < 0){
                    waitTime = 0;
                }
                Thread.sleep((long) waitTime);
                nextDrawTime +=drawInterval;
            } catch(InterruptedException uhOh){
                uhOh.printStackTrace();
                System.out.println("Error putting the thread to sleep");
            }
            System.out.println("Draw time = " + drawInterval);
        }
    }

    public void update(){
        if(gameState == playState) {
            player.update();
            for(int i = 0; i< ent.length; i++){
                if(ent[i] != null){
                    ent[i].update();
                }
            }
            for(int i = 0; i< mob.length; i++){
                if(mob[i] != null){
                    mob[i].update();
                    mob[i].update();
                }
            }
        }
        if(gameState == pauseState){
            System.out.println("Paused");
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        if(gameState == titleState) {
            ui.draw(g2);
        } else{
        mappy.draw(g2);

        //Add entities to list to render properly
        entityList.add(player);
        for(int i = 0; i < ent.length;i++){
            if(ent[i] != null){
                entityList.add(ent[i]);
            }
        }
            for(int i = 0; i < obj.length;i++){
                if(obj[i] != null){
                    entityList.add(obj[i]);
                }
            }
            for(int i = 0; i < mob.length;i++){
                if(mob[i] != null){
                    entityList.add(mob[i]);
                }
            }
            Collections.sort(entityList, new Comparator<Entity>() {
                @Override
                public int compare(Entity e1, Entity e2) {
                    int result = Integer.compare(e1.worldY, e2.worldY);
                    return result;
                }
            });
            //Draw entities
            for(int i = 0; i < entityList.size();i++){
                entityList.get(i).draw(g2);
            }
            entityList.clear();
        }
        ui.draw(g2);
        g2.dispose();

    }

    //sounds
    public void playSE(int i){
        sound.setFile(i);
        sound.play();
    }
}
