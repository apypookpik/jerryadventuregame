/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package userinterface;

import javax.swing.JFrame;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
/**
 *
 * @author User
 */
public class gamewindow extends JFrame{
    
    private GameScreen gameScreen;
    
    public gamewindow(){
        super("jerry adventure game");
        setSize(600,175);
        setLocation(400,250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameScreen = new GameScreen();
        add(gameScreen);
        addKeyListener(gameScreen);
    }
    
    public void startGame(){
        gameScreen.startGame();
    }
    public static void main(String args[]){
        gamewindow gw = new gamewindow();
        gw.setVisible(true);
        gw.startGame();
    }
}
