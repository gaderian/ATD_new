package modell.tile;/*
 * java.tile.tile.TCross
 * 
 * Date 27/11- 2015
 * @author id12jwn
 */

import java.net.MalformedURLException;

import modell.Position;
import modell.unit.Unit;

/**
 * java.tile.tile.TCross a class that extends the java.tile.tile.PathTile class. The class
 * have a own image that will be used in the user interface.   
 */
public class TCross extends PathTile {

    private Position nextPos;
    private Position defaultNextPos= null;
    private String TCrossImg;

    /**
     * java.tile.tile.TCross is the constructor that will read in the image when
     * the program will create a object of the class java.tile.tile.Default
     */
    public TCross(Position p) throws MalformedURLException {
        super(p, "TCross.png");
    }

    public void setPosistions(Position posToSet){
        if(defaultNextPos==null){
            defaultNextPos = posToSet;
        } else {
            nextPos = posToSet;
        }
    }

    private String getTCrossImg(){
        Position nextPos = getNextPos();
        Position pos = getPosition();
        if(nextPos.getY() < pos.getY()){
            TCrossImg = "TCross.png";
        } else if (nextPos.getY() > pos.getY()){
            TCrossImg = "TCrossDown.png";
        } else if(nextPos.getX()> pos.getX()){
            TCrossImg = "TCrossRight.png";
        } else {
            TCrossImg = "TCrossLeft.png";
        }
       return TCrossImg;
    }


    //
    public void changeDirection(){

        if (defaultNextPos.equals(getNextPos())) {
            setNextPos(nextPos);
        } else {
            setNextPos(defaultNextPos);
        }

        String temp;
        temp = getTCrossImg();
        changeImgURL(temp);
    }

    @Override
    public void sendToPos(Position posToSend) {
        setPosistions(posToSend);
        setNextPos(defaultNextPos);
    }

    /**
     *
     */
    @Override
    public void landOn(Unit unit) {
        Position tempPos = getNextPos();
        unit.setNextTilePos(tempPos);
    }
}
