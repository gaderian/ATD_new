package modell.unit;

import modell.Position;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.net.URL;

/**
 * Class: AirUnit

 * Author: Isak Hjelt

 * cs-user: dv14iht

 * Date: 2015-11-26
 */

public class AirUnit extends Unit {

    private static int price = 30;
    private static URL imgUrl = Unit.class.getResource("airUnit.png");

    public AirUnit(Position pos, int id) {
        super(pos, id);
        super.health = 120;
        super.price = this.price;
        super.speed = 48;
        super.name = "AirUnuit";
        super.pos = pos;
        super.flying = true;
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
