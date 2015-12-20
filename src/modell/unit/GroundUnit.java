package modell.unit;

import modell.Position;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.net.URL;

/**
 * Class: GroundUnit

 * Author: Isak Hjelt

 * cs-user: dv14iht

 * Date: 2015-11-26
 */

public class GroundUnit extends Unit {

    private static int price = 30;
    private static URL imgUrl = Unit.class.getResource("groundUnit.png");

    public GroundUnit(Position pos, int id) {

        super(pos, id);
        super.health = 150;
        super.price = price;
        super.speed = 47;
        super.name = "GroundUnit";
        super.pos = pos;
        super.flying = false;
        super.imagePath = imgUrl;
        try {
            super.image = ImageIO.read(imgUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * A static function to get the price of the unit.
     *
     * @return the price of the unit.
     */
    public static int getPrice() {
        return price;
    }

}
