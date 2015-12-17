package GUI;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 * Class: Store
 * main.GUI class for drawing the store buttons
 *
 * Created by Zacke on 2015-12-08.
 */
public class Store {
    private int shopWidth = 3;
    private int buttonSize = 54;
    private int cellSpace = 20;
    private int margin = 30;
    private int iconSize = 20;
    private int iconSpace = 6;
    private int iconTextY = 15;
    private URL unitpath;
    private String cost = "";

    public CLayout c;



    public Rectangle[] button = new Rectangle[shopWidth];
    public Rectangle buttonHealth;
    public Rectangle buttonCoins;
    public Rectangle buttonIncome;
    Board gameBoard;

    public Store(Board gameBoard, CLayout c) {
        this.gameBoard = gameBoard;
        this.c = c;
        define();
    }

    public void define() {
        for(int i = 0; i<button.length;i++) {
            button[i] = new Rectangle((c.game.myWidth/2) - ((shopWidth*(buttonSize*2+cellSpace))/2) + ((buttonSize*2+cellSpace)*i), 680, buttonSize * 2, buttonSize);
        }

        buttonHealth = new Rectangle(gameBoard.block[0][0].x-1, button[0].y, iconSize, iconSize);
        buttonCoins = new Rectangle(gameBoard.block[0][0].x-1, button[0].y + button[0].height-iconSize, iconSize, iconSize);
        //buttonIncome = new Rectangle(GameGUI.gameBoard.block[0][0].x-1, button[0].y + (button[0].height-iconSize*2), iconSize, iconSize);
    }

    /**
     * Draws the components for the store
     *
     * TODO Bort med hårdkodandet
     *
     * @param g
     */
    public void draw(Graphics g) {
        for(int i = 0; i<button.length;i++) {
            if(i == 0) {
                if(c.userinfo.canBuyUnit(1)) {
                    unitpath = getClass().getResource("airUnitButton.png");
                } else {
                    unitpath = getClass().getResource("airUnitCantBuy.png");
                }
                cost = "30";
            }
            if(i == 1) {
                if(c.userinfo.canBuyUnit(2)) {
                    unitpath = getClass().getResource("groundUnitButton.png");
                } else {
                    unitpath = getClass().getResource("groundUnitCantBuy.png");
                }
                cost = "30";
            }
            if(i == 2) {
                if(c.userinfo.canBuyUnit(3)) {
                    unitpath = getClass().getResource("teleporterUnitButton.png");
                } else {
                    unitpath = getClass().getResource("telepoterUnitCantBuy.png");
                }
                cost = "50";
            }

            g.setFont(new Font("Courier New", Font.BOLD, 14));
            g.setColor(new Color(255, 255, 255));
            g.drawImage(new ImageIcon(unitpath).getImage(), button[i].x, button[i].y, button[i].width, button[i].height, null);
            g.drawString(cost, button[i].x + buttonSize/2 - cellSpace/2, button[i].y - 4);

            //g.fillRect(button[i].x, button[i].y, button[i].width, button[i].height);

            //button[i].

        }
        /*
        g.fillRect(buttonHealth.x, buttonHealth.y, buttonHealth.width, buttonHealth.height);
        g.fillRect(buttonCoins.x, buttonCoins.y, buttonCoins.width, buttonCoins.height);
        g.setFont(new Font("Courier New", Font.BOLD, 14));
        g.setColor(new Color(255, 255, 255));
        g.drawString("100", buttonHealth.x + buttonHealth.width + iconSpace, buttonHealth.y + iconTextY);
        g.drawString("100", buttonCoins.x + buttonCoins.width + iconSpace, buttonCoins.y + iconTextY);
        */
        //g.fillRect(buttonIncome.x, buttonIncome.y, buttonIncome.width, buttonIncome.height);

    }

/*
    public void draw(Graphics g, String tilepath) {

        g.drawImage(new ImageIcon(tilepath).getImage(), x, y, width, height, null);

    }

    */


}
