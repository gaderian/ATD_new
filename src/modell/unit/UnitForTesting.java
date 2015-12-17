package modell.unit;


/*
 * Class: UnitForTesting

 * Author: Isak Hjelt

 * cs-user: dv14iht

 * Date: 2015-11-26
 */


import modell.Position;

public class UnitForTesting extends Unit {

    public UnitForTesting(Position pos, int id) {

        super(pos, id);
        super.health = 1;
        super.price = 30;
        super.speed = 50;
        super.name = "GroundUnit";
        super.pos = pos;
        super.flying = false;
    }

}
