package controller;

import modell.*;
import org.xml.sax.SAXException;

import GUI.CLayout;
import GUI.MapInformation;
import GUI.UserInformation;
import java.awt.image.BufferedImage;
import java.io.IOException;
import modell.tile.Tile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Class:       controller.MainController
 *
 * Author:      Erik Moström
 * cs-user:     dv14emm
 * Date:        12/15/15
 */


public class MainController implements MapInformation, UserInformation {
    private Renderer renderer;
    private CLayout gui;
    private Game game;
    private MapFactory factory;
    private Timer timer;
    private Shop shop;
    private CurrentGraphicState graphicState;
    private Map map;

    public MainController(){
        createFactory();
        gui = new CLayout(this, this);
        int dimension = gui.getTileSize()*12;
        renderer = new Renderer(dimension, dimension);
        game = null;
    }

    /**
     * Makes one update step of the game.
     */
    public void update() {
        game.update();
        renderer.drawImage(graphicState.getCurrentGraphicState());
        BufferedImage img = renderer.getImage();
        gui.setBoardImage(img);
    }

    /**
     * Starts the game with an specified update interval
     * @param interval the update interval in milliseconds
     */
    public void startWithUpdateInterval(long interval){
        timer = new Timer();
        timer.schedule(new Task(this), interval, interval);
    }

    /**
     * Starts the game with an update interval of 1/10 of a second
     */
    public void start(){
        startWithUpdateInterval(2);
    }

    /**
     * Creates a factory containing all the maps.
     */
    private void createFactory() {
        try {
            factory = new MapFactory("levels.xml");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            //TODO exception handling
            e.printStackTrace();
        }
    }

    /**
     * This is
     * @param args
     */
    public static void main(String[] args) {
        MainController c = new MainController();
    }

    @Override
    public ArrayList<String> getLevelNames() {
        return factory.getMapNames();
    }

    @Override
    public HashMap<Position, Tile> getMap(String s) {
        map = factory.loadMap(s);
        game = new Game(map);
        shop = game.getShop();
        graphicState = game.getGraphicState();

        start();
        return map.getCompleteMap();
    }

    @Override
    public void buyUnit(int i) {
        shop.buyUnit(i);
    }

    @Override
    public boolean canBuyUnit(int i) {
        return shop.canBuyUnit(i);
    }

    @Override
    public String getUnitName(int i) {
        return null;
    }

    @Override
    public void updateUsername(String s) {
        game.getUser().setUserName(s);
    }

    @Override
    public boolean gameOver() {
        if (game != null) {
            return game.isLoss();
        }
        return false;
    }

    @Override
    public boolean gameWon() {
        if (game != null) {
            return game.isWon();
        }
        return false;
    }

    @Override
    public User getUser() {
        return game.getUser();
    }

    @Override
    public void killGame() {
        timer.cancel();
        game = null;
        shop = null;
        graphicState = null;
        map = null;

    }

    @Override
    public void pauseGame() {
        timer.cancel();
    }

    @Override
    public void resumeGame() {
        start();
    }

    @Override
    public void hasClicked(int x, int y) {
        game.clickAtPos(new Position(x,y));
    }

    /**
     * A task to send to the timer. It will only call the update of the main
     * controller.
     */
    private class Task extends TimerTask {
        private MainController controller;

        public Task(MainController c){
            controller = c;
        }

        @Override
        public void run() {
            controller.update();
        }
    }
}
