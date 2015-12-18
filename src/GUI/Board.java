package GUI;


import modell.Position;
import java.awt.*;
import modell.tile.Tile;
import java.util.HashMap;

/**
 * Class: Board
 * GUI class for drawing the game board for the selected level, which in turn consists of several blocks
 *
 * Created by Joakim on 2015-12-07.
 */
public class Board {
    public int worldWidth = 12;
    public int worldHeight = 12;
    public int blockSize = 54;
    public Block[][] block;
    private CLayout c;
    private String map;
    private String tile;
    HashMap<Position, Tile> HM;

    public Board(CLayout c, String map) {
        this.c = c;
        this.map = map;
        define();
    }

    public void define() {
        block = new Block[worldHeight][worldWidth];
        HM = c.mapinfo.getMap(map);

        for (int y=0;y<block.length;y++) {
            for (int x = 0; x < block[0].length; x++) {
                block[y][x] = new Block((c.game.myWidth / 2) - ((worldWidth * blockSize) / 2) + (x * blockSize), (y * blockSize), blockSize, blockSize, 0);
            }
        }
    }

    /**
     * Draws the blocks the game board consists of, it checks which tile is suppose to be in which position and draws it
     * there as a block
     *
     * @param g
     */
    public void draw(Graphics g) {

        Tile t;
        for (int y=0;y<block.length;y++) {
            for(int x=0;x<block[0].length;x++) {

                if(HM.get(new Position(x, y)) != null) {
                    tile = HM.get(new Position(x, y)).toString();
                    t = HM.get(new Position(x, y));
                } else {
                    tile = "";
                    t=null;
                }

                if(t != null){
                    if(Tile.class.isAssignableFrom(t.getClass())) {
                        block[y][x].draw(g, t.getImageURL());
                    }
                    /*
                    if(PathTile.class.isAssignableFrom(t.getClass())) {
                        block[y][x].draw(g, t.getImageURL());
                    } else if(TowerTile.class.isAssignableFrom(t.getClass())) {
                        block[y][x].draw(g, t.getImageURL());
                    }*/
                } else {
                    block[y][x].draw(g, getClass().getResource("defaultTile.png"));
                }
            }
            }
    }

}