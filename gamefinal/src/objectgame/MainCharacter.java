/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objectgame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import static userinterface.GameScreen.GRAVITY;
import static userinterface.GameScreen.GROUNDY;
import util.Animation;
import util.Resource;

/**
 *
 * @author User
 */
public class MainCharacter {
    private float x = 0;
    private float y = 0;
    private float speedY = 0;
    private Animation characterRun;
    private Rectangle rect;
    private boolean isAlive = true;
    
    public MainCharacter() {
        characterRun = new Animation(200);
        characterRun.addFrame(Resource.getResourceImage("data/jerry4edit.png"));
        characterRun.addFrame(Resource.getResourceImage("data/jerryrun.png"));
        rect = new Rectangle();
    }
    
    public void update(){
        characterRun.update();
        //all this line code for jumping
        if(y>=GROUNDY - characterRun.getFrame().getHeight()){
                    speedY = 0;
                    y = GROUNDY-characterRun.getFrame().getHeight();
                } else {
                    speedY+=GRAVITY;
                    y+=speedY;
                }
        rect.x = (int) x;
        rect.y = (int) y;
        rect.width = characterRun.getFrame().getWidth();
        rect.height = characterRun.getFrame().getHeight();
    }
    
    public Rectangle getBound(){
        return rect;
    }
    
    public void draw(Graphics g){
        g.setColor(Color.black);
        //g.drawRect((int) x, (int) y, characterRun.getFrame().getWidth(), characterRun.getFrame().getHeight());
        g.drawImage(characterRun.getFrame(), (int) x,(int) y,null);
    }
    
    public void jump(){
        speedY = -4;
        y += speedY;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getSpeedY() {
        return speedY;
    }

    public void setSpeedY(float speedY) {
        this.speedY = speedY;
    }
    
    public void secAlive(boolean alive){
        isAlive = alive;
    }
    
    public boolean getAlive(){
        return isAlive;
    }

    public void setAlive(boolean b) {

    }
}
