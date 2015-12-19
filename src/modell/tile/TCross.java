package modell.tile;/*
 * TCross
 * 
 * Date 27/11- 2015
 * @author id12jwn
 */

import modell.Position;
import modell.unit.Unit;

import java.net.MalformedURLException;

/**
 * TCross a class that extends the class PathTile class. The class
 * have a own image that will be used in the user interface.   
 */
public class TCross extends PathTile {

    private Position nextPos;
    private Position defaultNextPos= null;
    private String TCrossImg;

    /**
     * TCross is the constructor that will read in the image when
     * the program will create a object
     */
    public TCross(Position p) throws MalformedURLException {
        super(p, "TCross.png");
    }

    /**
     * setPositions is a method that will set the positions that
     * the user can chose between to send the units to
     * @param posToSet
     */
    public void setPosistions(Position posToSet){
        if(defaultNextPos==null){
            defaultNextPos = posToSet;
        } else {
            nextPos = posToSet;
        }
    }

    /**
     * getTCrossImg is a method that will check were the user
     * wants to send theirs units to
     * @return, the imgage with the right arrow
     */
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


    /**
     * changeDriecion is a method that will change the units
     * direction when the user choose a diffrent way
     */
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

    /**
     * sendToPos will set the next psotion that the method landOn
     * will send the units to
     *
     * @param posToSend, The posistion that the unit will be send to
     */
    @Override
    public void sendToPos(Position posToSend) {
        setPosistions(posToSend);
        setNextPos(defaultNextPos);
    }

    /**
     * landOn is a method that will set the next position that the
     * unit will go to, depending on the path that the user will
     * choose.
     */
    @Override
    public void landOn(Unit unit) {
        Position tempPos = getNextPos();
        unit.setNextTilePos(tempPos);
    }
}
