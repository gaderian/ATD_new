package GUI;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 * Builds the GUI for the store buttons
 * <p>
 * Created by Zacke on 2015-12-08.
 */
public class Store {
    private int shopWidth = 3;
    private int buttonSize = 54;
    private int cellSpace = 20;
    private URL unitpath;
    private String cost = "";
    public CLayout c;
    public Rectangle[] button = new Rectangle[shopWidth];
    Board gameBoard;

    public Store(Board gameBoard, CLayout c) {
        this.gameBoard = gameBoard;
        this.c = c;
        define();
    }

    /**
     * Defines the store components
     */
    public void define() {
        for (int i = 0; i < button.length; i++) {
            button[i] = new Rectangle((c.game.myWidth / 2) - ((shopWidth * (buttonSize * 2 + cellSpace)) / 2) + (
                    (buttonSize * 2 + cellSpace) * i), 680, buttonSize * 2, buttonSize);
        }
    }

    /**
     * Draws images on the store components, which is then used as buttons via a listener
     * Also adds a small text above each icon how much a unit costs
     *
     * @param g
     */
    public void draw(Graphics g) {
        for (int i = 0; i < button.length; i++) {
            if (i == 0) {
                if (c.userinfo.canBuyUnit(1)) {
                    unitpath = getClass().getResource("airUnitButton.png");
                } else {
                    unitpath = getClass().getResource("airUnitCantBuy.png");
                }
                cost = "30";
            }
            if (i == 1) {
                if (c.userinfo.canBuyUnit(2)) {
                    unitpath = getClass().getResource("groundUnitButton.png");
                } else {
                    unitpath = getClass().getResource("groundUnitCantBuy.png");
                }
                cost = "30";
            }
            if (i == 2) {
                if (c.userinfo.canBuyUnit(3)) {
                    unitpath = getClass().getResource("teleporterUnitButton.png");
                } else {
                    unitpath = getClass().getResource("telepoterUnitCantBuy.png");
                }
                cost = "50";
            }
            g.setFont(new Font("Courier New", Font.BOLD, 14));
            g.setColor(new Color(255, 255, 255));
            g.drawImage(new ImageIcon(unitpath).getImage(), button[i].x, button[i].y, button[i].width, button[i]
                    .height, null);
            g.drawString(cost, button[i].x + buttonSize / 2 - cellSpace / 2, button[i].y - 4);
        }
    }
}
