package modell;

/**
 * Created by dv14iht on 2015-12-16.
 */
public class PositionConverter {
    private static final int tileSize = 54;

    /**
     * Translate a tileCoordinate to a graphic coordinate (the middle coordinate of the tile).
     *
     * @param tilePos the position of the tile to get the middle position of.
     * @return the graphic coordinate.
     */
    public static Position tilePosConverter(Position tilePos){
        int tileX = tilePos.getX();
        int tileY = tilePos.getY();

        int middlePosX = (((tileX) * tileSize-1) + (tileSize / 2));
        int middlePosY = (((tileY) * tileSize-1) + (tileSize / 2));

        return new Position(middlePosX,middlePosY);
    }

    /**
     * Translate a graphic coordinate to a tileCoordinate.
     *
     * @param unitPos the position of the unit.
     * @return the coordinate of the tile the unit is on.
     */
    public static Position unitPosConverter(Position unitPos){
        int posX = unitPos.getX();
        int posY = unitPos.getY();

        int tilePosX = (posX / tileSize);
        int tilePosY = (posY / tileSize);

        return new Position(tilePosX,tilePosY);
    }
}
