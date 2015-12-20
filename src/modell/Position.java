package modell;

import static java.lang.Math.sqrt;

/**
 * A class for holding the coordinates (x, y) of a position in a matrix.
 * The matrix should have (0, 0) in the upper left corner.
 *
 * @author dv14emm
 */
public class Position {
    private int x;
    private int y;

    /**
     * @param x Will be the x coordinate of the Position
     * @param y Will be the y coordinate of the Position
     */
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the x coordinate
     *
     * @return the x coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * Returns the y coordinate.
     *
     * @return the y coordinate
     */
    public int getY() {
        return y;
    }

    /**
     * Method for calculating the distance between this position and another.
     * Will return the distance as an integer, it will be rounded up.
     *
     * @param p the Position to calculate the distance to.
     * @return the distance rounded up to the closest int.
     */
    public int getDistance(Position p) {

        int xDistance = p.getX() - x;
        if (xDistance < 0) {
            xDistance = xDistance * -1;
        }

        int yDistance = p.getY() - y;
        if (yDistance < 0) {
            yDistance = yDistance * -1;
        }

        double tmp = sqrt(xDistance * xDistance + yDistance * yDistance);
        return (int) Math.ceil(tmp);

    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + x;
        result = prime * result + y;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Position other = (Position) obj;
        if (x != other.x)
            return false;
        if (y != other.y)
            return false;
        return true;
    }
}
