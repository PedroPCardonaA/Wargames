package edu.ntnu.idatt2001.pedropca.wargames.models.units.magicUnits;

import edu.ntnu.idatt2001.pedropca.wargames.models.units.Unit;
import edu.ntnu.idatt2001.pedropca.wargames.util.EnumTerrain;

import java.util.List;
import java.util.Random;

/**
 *
 * Class edu.ntnu.idatt2001.pedropca.MagicianUnit that represents the ranged units in the war games.
 * This class conforms the edu.ntnu.idatt2001.pedropca.Unit hierarchy and has the abstract class edu.ntnu.idatt2001.pedropca.Unit as superclass.
 * This class has the same fields that edu.ntnu.idatt2001.pedropca.Unit class.
 *
 * @author Pedro Cardona
 * @version 1.0
 * @since 1.0-SNAPSHOT
 */
public class MagicianUnit extends MagicUnit {

    /**
     * Constructor of the class edu.ntnu.idatt2001.pedropca.Magician. The signature of this constructor
     * takes all the fields of class the class edu.ntnu.idatt2001.pedropca.Unit
     * except the field ATTACK_TYPE because it is pre-defined as "ranged".
     * @param name String name of the unit
     * @param health int health points of the unit
     * @param attack int attack points of the unit
     * @param armor int armor points of the unit
     * @param attackSpeedPerSecond int attack speed per second of the unit
     * @param hitRate int percent chance of not miss the attack
     * @param criticRate int percent chance of do a critical attack
     * @param criticDamage int percent that represent the damage from a critical attack in comparison
     *                      to a non-critical attack.
     * @throws IllegalArgumentException this constructor may trow illegal argument exception
     * if the given parameters are not inside the defined areas.
     */
    public MagicianUnit(String name, int health, int attack, int armor, int attackSpeedPerSecond, int hitRate, int criticRate, int criticDamage)
            throws IllegalArgumentException {
        super(name, health, attack, armor, attackSpeedPerSecond, "Ranged", hitRate, criticRate, criticDamage,100);
    }

    /**
     * Default constructor for class edu.ntnu.idatt2001.pedropca.MagicianUnit. The signature of this constructor only takes
     * Variable for the field name and health. This constructor will be used for default
     * ranged unit.
     * @param name String name of the unit
     * @param health int health points of the unit
     */
    public MagicianUnit(String name, int health)throws IllegalArgumentException {
        super(name, health, 15, 8, 3, "ranged", 65, 15, 150,200);
        }

    /**
     * Help method that overrides abstract method getAttackBonus from the class unit.
     * This method helps method getDamageDone from the class edu.ntnu.idatt2001.pedropca.Unit to get attack bonus.
     * This method is based on the luck. But the terrain "Volcano" buffs the attack bonus.
     * @param opponent edu.ntnu.idatt2001.pedropca.Unit the opponent unit.
     * @return int the attack bonus.
     */
    @Override
    public int getAttackBonus(Unit opponent) {
        int attackBonus = new Random().nextInt(26);
        if(EnumTerrain.getTerrain() == EnumTerrain.VOLCANO) attackBonus+=5;
        return attackBonus;

    }

    /**
     * Help method that overrides abstract method getResistBonus from the class unit.
     * This method helps method getDamageDone from the class edu.ntnu.idatt2001.pedropca.Unit to get resist bonus.
     * This method is based on the luck. But the terrain "Volcano" buffs the attack bonus.
     * @param mainUnit edu.ntnu.idatt2001.pedropca.Unit the opponent unit.
     * @return int the resist bonus.
     */
    @Override
    public int getResistBonus(Unit mainUnit) {
        int resistBonus = new Random().nextInt(6);
        if(EnumTerrain.getTerrain() == EnumTerrain.VOLCANO) resistBonus+=5;
        return resistBonus;

    }

    /**
     * Help method that overrides abstract method clone from the class unit.
     * This method makes and return a deep copy of the unit that calls method.
     * It will help method getDamageDone from class edu.ntnu.idatt2001.pedropca.Unit and the abstract method getResistBonus()
     * @return edu.ntnu.idatt2001.pedropca.Unit Copy of th unit.
     */
    @Override
    protected Unit clone() {
        return new MagicianUnit(this.getName(), this.getHealth(),
                this.getAttack(), this.getArmor(), this.getAttackSpeedPerSecond(), this.getHitRate(),
                this.getCriticRate(), this.getCriticDamage());
    }

    @Override
    public void magicAttack(List<Unit> target) {
        target.forEach(unit -> unit.setHealth(Math.max(unit.getHealth()-2,0)));
        this.setMana(Math.max(this.getMana()-50,0));
    }
}

