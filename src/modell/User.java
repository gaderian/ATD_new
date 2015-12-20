package modell;

import modell.unit.Unit;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

/**
 * Class that holds information about a user, including its score, credits, userName, map playing. Is also able to
 * update those variables
 * <p>
 * Created by juliawestman & joakimzakrisson on 2015-12-06.
 */
public class User {
    private int points;
    private int credits;
    private int income;
    private String userName;
    private String map;
    private URL coinImgUrl = getClass().getResource("coins.png");
    private URL healthImgUrl = getClass().getResource("health.png");

    public User(int credits, int income) {
        this.income = income;
        this.credits = (credits);
        this.credits = (credits);
    }

    public int getIncome() { return income; }

    public int getCredits() {
        return credits;
    }

    public int getScore() {
        return points;
    }

    public void setPoints(int pointsToSet) {
        points = pointsToSet;
    }

    public void setUserName(String name) {
        userName = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setMap(String map) {
        this.map = map;
    }

    public String getMap() {
        return map;
    }

    public void increaseCredits(int value) {
        this.credits = this.credits + value;
    }

    public void decreaseCredits(int value) {
        this.credits = this.credits - value;
    }

    public GraphicEvent generateGraphicEvent() {
        BufferedImage img;
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
        g.setFont(new Font("Arial", Font.BOLD, 18));
        g.drawString(String.valueOf(this.credits), 20, 14);
        g.drawString(String.valueOf(this.points), 20, 32);
        g.drawImage(coins, 0, 0, 18, 18, null);
        g.drawImage(health, 0, 18, 18, 18, null);

        return new GraphicEvent(999, new Position(25, 25), img);
    }
}
