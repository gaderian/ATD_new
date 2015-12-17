package GUI;


import javax.swing.*;
import modell.User;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Zacke on 2015-11-26.
 */
public class GameGUI extends JPanel implements Runnable {
    //JPanel gamePanel = new JPanel();

 //   JPanel gamePanel = new JPanel();
    JLayeredPane gamePanel = new JLayeredPane();
    public Thread thread = new Thread(this);
    public boolean isFirst =true;
    private JButton sound;
    private JButton settings;
    public int myWidth, myHeight;
    JPanel lowerPanel = new JPanel();
    JLayeredPane LPane =new JLayeredPane();

    User u = new User(0,0);
    boolean gameOver = false;
    public String chosenMap;
    BufferedImage b;
    boolean firstWin = true;
    private CLayout c;
    public Board gameBoard;
    public Store store;
    public boolean gameStarted = false;

    public GameGUI(CLayout c) {
        this.c = c;
        thread.start();
    }

    public void paintComponent(Graphics g) {
        if(isFirst) {

            myWidth = getWidth();
            myHeight = getHeight();
            gameBoard = new Board(c, chosenMap);
            store = new Store(gameBoard, c);
            isFirst = false;
            gameStarted = true;
            firstWin = true;
        }
        g.setColor(new Color(50, 50, 50));
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(new Color(255, 255, 255));
        ((Graphics2D)g).setStroke(new BasicStroke(2));
        g.drawLine(gameBoard.block[0][0].x-1, 0, gameBoard.block[0][0].x-1, gameBoard.block[gameBoard.worldHeight-1][0].y + gameBoard.blockSize);
        g.drawLine(gameBoard.block[0][gameBoard.worldWidth-1].x + gameBoard.blockSize, 0, gameBoard.block[0][gameBoard.worldWidth-1].x + gameBoard.blockSize, gameBoard.block[gameBoard.worldHeight-1][0].y + gameBoard.blockSize);
        g.drawLine(gameBoard.block[0][0].x, gameBoard.block[gameBoard.worldHeight-1][0].y + gameBoard.blockSize, gameBoard.block[0][gameBoard.worldWidth-1].x + gameBoard.blockSize, gameBoard.block[gameBoard.worldHeight-1][0].y + gameBoard.blockSize);
        gameBoard.draw(g);
        g.setColor(new Color(0, 255, 255));
        store.draw(g);


        if (b != null) {
            g.drawImage(new ImageIcon(b).getImage(), (myWidth / 2) - (gameBoard.blockSize*(gameBoard.worldWidth/2)), 0, null);
        }

    }


    /**
     * Metod som kör gui tråden och målar om guit varje millisekund
     *
     * TODO Fixa wincondition!
     */
    public void run() {
        while(true) {

            repaint();

            if(gameStarted && firstWin) {
                // Wincondition
                if (c.userinfo.gameWon() || c.userinfo.gameOver()) {
                    c.userinfo.pauseGame();
                    firstWin = false;
                    c.showGameOver();
                    isFirst = true;
                }
            }

            try {
                Thread.sleep(1);
            } catch(Exception e) {

            }

        }
    }


    private JPanel buildLowerPanel() {
        JPanel lowerPanel = new JPanel();
        lowerPanel.setPreferredSize(new Dimension(55,25));
        lowerPanel.setBackground(new Color(56, 134, 96));

        sound = new JButton(new ImageIcon( getClass().getResource("sound.png") ));
        sound.addMouseListener(new SoundListener(sound, c));

        sound.setBorderPainted(false);
        sound.setContentAreaFilled(false);
        sound.setFocusPainted(false);

        lowerPanel.add(sound, new FlowLayout(FlowLayout.RIGHT));
        sound.setBounds(830,700,70,70);
        LPane.add(sound, new Integer(5));

        settings = new JButton(new ImageIcon( getClass().getResource("settings.png") ));
        settings.addMouseListener(new SettingListener(settings, c));

        settings.setBorderPainted(false);
        settings.setContentAreaFilled(false);
        settings.setFocusPainted(false);

        settings.setBounds(730,700,70,70);

        return lowerPanel;
    }

    public JLayeredPane getPanel() {

        lowerPanel = buildLowerPanel();

        gamePanel.setLayout(null);
        this.setBounds(0,0,900,900);
        gamePanel.add(sound, new Integer(1));

        gamePanel.add(settings, new Integer(2));

        gamePanel.add(this, new Integer(0));

        gamePanel.addMouseListener(new GameListener(c, this));

        return gamePanel;
    }

    public void setChosenMap(String s) {
        chosenMap = s;
    }

    public void drawImage(BufferedImage b) {
        this.b = b;
    }

    public void restart() {
        isFirst = true;
    }

}