package modell;

import modell.unit.Unit;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

/**
 * Created by juliawestman on 2015-12-06.
 */
public class User {
    private int points;
    private int credits;
    private int income;
    private String userName;
    private String map;
    private String currentDate;

    private URL coinImgUrl = getClass().getResource("coins.png");
    private URL healthImgUrl = getClass().getResource("health.png");

    public User (int credits, int income) {
        this.credits = credits;
        this.income = income;

    }

/* TODO create increase/decrease methods instead of get/set for certain values */
    public void setCurrentDate(){
        currentDate =  new java.sql.Date(System.currentTimeMillis()).toString();
    }

    public String getCurrentDate() {
        return currentDate;
    }

    public void setCredits(int creditsToSet){
        credits = creditsToSet;
    }

    public int getCredits(){
        return credits;
    }

    public void setIncome(int incomeToSet){
        income = incomeToSet;
    }

    public int getIncome(){
        return income;
    }

    public int getScore(){
        return  points;
    }

    public void setPoints(int pointsToSet){
        points = pointsToSet;
    }

    public void setUserName(String name){
        userName = name;
    }

    public String getUserName(){
        return userName;
    }

    public void setMap(String map) {
        this.map = map;
    }

    public String getMap() {
        return map;
    }

    public void increaseCredits(int value){
        this.credits = this.credits+value;
    }

    public GraphicEvent generateGraphicEvent() {
        BufferedImage img = null;
        BufferedImage coins = null;
        BufferedImage health = null;
        try {
            coins = ImageIO.read(coinImgUrl);
            health = ImageIO.read(healthImgUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*Make the image*/
        img = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = img.createGraphics();
        g.setColor(new Color(255, 244, 61));
        g.setStroke(new BasicStroke(2));
        g.setFont(new Font("Arial",Font.BOLD,18));
        g.drawString(String.valueOf(this.credits),20,14);
        g.drawString(String.valueOf(this.points),20,32);
        g.drawImage(coins,0,0,18,18,null);
        g.drawImage(health,0,18,18,18,null);


        return new GraphicEvent(999,new Position(25,25),img);
    }

    public void decreaseCredits(int value){
        this.credits = this.credits - value;
    }


}
