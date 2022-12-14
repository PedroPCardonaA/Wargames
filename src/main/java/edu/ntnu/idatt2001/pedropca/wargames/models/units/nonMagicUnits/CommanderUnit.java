package edu.ntnu.idatt2001.pedropca.wargames.models.units.nonMagicUnits;

import edu.ntnu.idatt2001.pedropca.wargames.models.units.Unit;

/**
 *
 * Class CommanderUnit that represents the cavalry  units in the war games.
 * This class conforms the Unit hierarchy and has the abstract class cavalryUnit as superclass.
 * This class has the same fields that CalvaryUnit.
 *
 * @author Pedro Cardona
 * @version 1.0
 * @since 1.0-SNAPSHOT
 */

public class CommanderUnit extends CavalryUnit{
    /**
     * Constructor of the class CommanderUnit. The signature of this constructor
     * takes all the fields of class the class CavalryUnit
     * except the field ATTACK_TYPE because it is pre-defined as "melee"
     * and field isCharging that is defined as true when the object is made.
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
    public CommanderUnit(String name, int health, int attack, int armor, int attackSpeedPerSecond, int hitRate, int criticRate,int criticDamage)
            throws IllegalArgumentException {
        super(name, health, attack, armor,attackSpeedPerSecond,hitRate,criticRate,criticDamage);
    }
    /**
     * Default constructor for class CommanderUnit. The signature of this constructor only takes
     * Variable for the field name and health. This constructor will be used for default
     * cavalry units.
     * @param name String name of the unit
     * @param health int health points of the unit
     */
    public CommanderUnit(String name, int health) throws IllegalArgumentException {
        super(name, health,25,15,1,90,25,175);
    }



    /**
     * Override method that just add help method to healthPointRegeneration to the method in the
     * abstract class Unit.
     * @param opponent Unit - opponent unit
     */
    @Override
    public void attack(Unit opponent){
        super.attack(opponent);
        this.healthPointRegeneration();
    }

    /**
     * Help method that simulates life regeneration by adding 5 points to
     * the total life points of the unt. This method will be called in method attack
     */
    private void healthPointRegeneration(){

        if(this.getHealth()>0 && this.getHealth()<this.getMaxHealth()-1) setHealth(this.getHealth()+2);
    }
}
