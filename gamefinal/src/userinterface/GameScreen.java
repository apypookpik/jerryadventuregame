/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package userinterface;


import java.applet.AudioClip;
import java.applet.Applet;
import java.awt.Graphics;
import javax.swing.JPanel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import objectgame.Cactus;
import objectgame.Clounds;
import objectgame.EnemiesManager;
import objectgame.Land;
import objectgame.MainCharacter;
import util.Resource;
/**
 *
 * @author User
 */
public class GameScreen extends JPanel implements Runnable, KeyListener {
    public static final int GAME_FIRST_STATE = 0;
    public static final int GAME_PLAY_STATE = 1;
    public static final int GAME_OVER_STATE = 2;
    public static final float GRAVITY = 0.1f;
    public static final float GROUNDY = 110; //by pixel
    
    private MainCharacter mainCharacter;
    private Thread thread;
    private Land land;
    private Clounds clouds;
    private EnemiesManager enemiesManager;
    private int score;
    
    private int gameState = GAME_FIRST_STATE;
    
    private BufferedImage imageGameOverText;
    
    private AudioClip scoreUpSound;
    
    public GameScreen(){
        thread = new Thread(this);
        mainCharacter = new MainCharacter();
        mainCharacter.setX(50);
        mainCharacter.setY(60);
        land = new Land(this);
        clouds = new Clounds();
        enemiesManager = new EnemiesManager(mainCharacter, this);
        imageGameOverText = Resource.getResourceImage("data/gameover_text.png");
        
        try{
            scoreUpSound = Applet.newAudioClip(new URL("file","","data/scoreup.wav"));
        } catch (MalformedURLException ex){
            Logger.getLogger(GameScreen.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
    
    public void startGame(){
        thread.start();
    }
    
    @Override
    public void run(){
        while(true){
            try {
                update();
                repaint();
                Thread.sleep(20);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public void update(){
        switch(gameState) {
            case GAME_PLAY_STATE:
                mainCharacter.update();
                land.update();
                clouds.update();
                enemiesManager.update();
                if(!mainCharacter.getAlive()){
                    gameState = GAME_OVER_STATE;
                }
                break;
        }
    }
    
    public void plusScore(int score){
        this.score += score;
        scoreUpSound.play();
    }
    
    @Override
    public void paint(Graphics g){
        //coordination x,y,width,height of the regtangle
        g.setColor(Color.decode("#f7f7f7"));
        g.fillRect(0, 0, getWidth(), getHeight());
        //g.setColor(Color.red);
        //g.drawLine(0,(int) GROUNDY, getWidth(), (int)GROUNDY);
        
        switch(gameState) {
            case GAME_FIRST_STATE:
                mainCharacter.draw(g);
                break;
            case GAME_PLAY_STATE:
                clouds.draw(g);
                land.draw(g);
                mainCharacter.draw(g);
                enemiesManager.draw(g);
                g.drawString("SCORE" + String.valueOf(score), 500,20);
                break;
            case GAME_OVER_STATE:
                clouds.draw(g);
                land.draw(g);
                mainCharacter.draw(g);
                enemiesManager.draw(g);
                g.drawImage(imageGameOverText,190,50,null);
                break;
        }
    }
    
    private void resetGame(){
        mainCharacter.setAlive(true);
        mainCharacter.setX(50);
        mainCharacter.setY(60);
        enemiesManager.reset();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //mainCharacter.jump();
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        //System.out.println("Key Releaesd");
        switch(e.getKeyCode()){
            case KeyEvent.VK_SPACE:
                if(gameState == GAME_FIRST_STATE){
                    gameState = GAME_PLAY_STATE;
                } else if(gameState == GAME_PLAY_STATE){
                    mainCharacter.jump();
                } else if(gameState == GAME_OVER_STATE){
                    resetGame();
                    gameState = GAME_PLAY_STATE;
                }
                break;
        }
    }
    
}
