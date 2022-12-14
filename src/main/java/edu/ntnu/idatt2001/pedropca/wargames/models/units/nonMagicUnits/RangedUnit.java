package edu.ntnu.idatt2001.pedropca.wargames.models.units.nonMagicUnits;

import edu.ntnu.idatt2001.pedropca.wargames.models.units.Unit;
import edu.ntnu.idatt2001.pedropca.wargames.models.units.magicUnits.MagicianUnit;
import edu.ntnu.idatt2001.pedropca.wargames.util.EnumTerrain;

/**
 *
 * Class RangedUnit that represents the ranged units in the war games.
 * This class conforms the Unit hierarchy and has the abstract class Unit as superclass.
 * This class has the same fields that Unit class.
 *
 * @author Pedro Cardona
 * @version 1.0
 * @since 1.0-SNAPSHOT
 */

public class RangedUnit extends Unit {
    /**
     * Constructor of the class RangedUnit. The signature of this constructor
     * takes all the fields of class the class Unit
     * except the field ATTACK_TYPE because it is pre-defined as "ranged".
     * @param name String - name of the unit
     * @param health int - health points of the unit
     * @param attack int - attack points of the unit
     * @param armor int - armor points of the unit
     * @param attackSpeedPerSecond int - attack speed per second of the unit
     * @param hitRate int - percent chance of not miss the attack
     * @param criticRate int - percent chance of do a critical attack
     * @param criticDamage int - percent that represent the damage from a critical attack in comparison
     *                      to a non-critical attack.
     * @throws IllegalArgumentException this constructor may trow illegal argument exception
     * if the given parameters are not inside the defined areas.
     */
    public RangedUnit(String name, int health, int attack, int armor, int attackSpeedPerSecond, int hitRate, int criticRate, int criticDamage)
            throws IllegalArgumentException {
        super(name,health,attack,armor,attackSpeedPerSecond,"Ranged",hitRate,criticRate,criticDamage);
    }

    /**
     * Default constructor for class RangedUnit. The signature of this constructor only takes
     * Variable for the field name and health. This constructor will be used for default
     * ranged unit.
     * @param name String name of the unit
     * @param health int health points of the unit
     */
    public RangedUnit(String name, int health)throws IllegalArgumentException{
        super(name,health,15,8,3,"ranged", 65,15,150);
    }
    /**
     * Help method that overrides abstract method getAttackBonus from the class unit.
     * This method helps method getDamageDone from the class Unit to get attack bonus.
     * This method will return the attack bonus of ranged units. The value of the bonus may change
     * depending on the opponent Unit. If the opponent is an infantry unit. the attack bonus becomes 7. Else,
     * the attack bonus becomes4.
     * The forest terrain de-buffs the attack bonus and the hill terrain buffs the attack bonus.
     * @param opponent Unit - the opponent unit.
     * @return int - the attack bonus.
     */

    @Override
    public int getAttackBonus(Unit opponent) {
        int attackBonus = 0;
        if(opponent instanceof InfantryUnit)attackBonus +=7;
        else attackBonus +=4;
        if(EnumTerrain.getTerrain()==EnumTerrain.HILL) attackBonus+=3;
        if(EnumTerrain.getTerrain()==EnumTerrain.FOREST) attackBonus -=2;
        return attackBonus;
    }

    /**
     * Help method that overrides abstract method getResistBonus from the class unit.
     * This method helps method getDamageDone from the class Unit to get resist bonus.
     * This method will return the resist bonus of ranged units. The value of the bonus may change
     * depending on the opponent Unit. If the opponent is another ranged unit, the resist bonus becomes 1.
     * If the opponent is an infantry unit, the resist bonus becomes 2;
     * And if the opponent is a cavalry unit, the resist bonus becomes 0;
     * The volcano terrain de-buffs the resist bonus.
     * @param mainUnit Unit - the main unit that called method getDamageDone.
     * @return int - the resist bonus.
     */

    @Override
    public int getResistBonus(Unit mainUnit) {
        int resistBonus = 0;
        if(mainUnit instanceof InfantryUnit)resistBonus+= 2;
        if(mainUnit instanceof RangedUnit || mainUnit instanceof MagicianUnit)resistBonus+=1;
        if(EnumTerrain.getTerrain()==EnumTerrain.VOLCANO) resistBonus -=5;
        return resistBonus;
    }

    /**
     * Help method that overrides abstract method clone from the class unit.
     * This method makes and return a deep copy of the unit that calls method.
     * It will help method getDamageDone from class Unit and the abstract method getResistBonus()
     * @return Unit - Copy of th unit.
     */
    @Override
    public Unit clone(){
        return new RangedUnit(this.getName(),this.getHealth(),
                this.getAttack(),this.getArmor(),this.getAttackSpeedPerSecond(), this.getHitRate(),
                this.getCriticRate(),this.getCriticDamage());
    }

}
