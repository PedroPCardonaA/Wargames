package edu.ntnu.idatt2001.pedropca;

/**
 *
 * Class edu.ntnu.idatt2001.pedropca.RangedUnit that represents the ranged units in the war games.
 * This class conforms the edu.ntnu.idatt2001.pedropca.Unit hierarchy and has the abstract class edu.ntnu.idatt2001.pedropca.Unit as superclass.
 * This class has the same fields that edu.ntnu.idatt2001.pedropca.Unit class.
 *
 * @author Pedro Cardona
 * @version 1.0
 * @since 1.0-SNAPSHOT
 */

public class RangedUnit extends Unit{
    /**
     * Constructor of the class edu.ntnu.idatt2001.pedropca.RangedUnit. The signature of this constructor
     * takes all the fields of class the class edu.ntnu.idatt2001.pedropca.Unit
     * except the field ATTACK_TYPE because it is pre-defined as "ranged".
     * @param NAME String name of the unit
     * @param health int health points of the unit
     * @param ATTACK int attack points of the unit
     * @param ARMOR int armor points of the unit
     * @param ATTACK_SPEED_PER_SECOND int attack speed per second of the unit
     * @param HIT_RATE int percent chance of not miss the attack
     * @param CRITIC_RATE int percent chance of do a critical attack
     * @param CRITIC_DAMAGE int percent that represent the damage from a critical attack in comparison
     *                      to a non-critical attack.
     * @throws IllegalArgumentException this constructor may trow illegal argument exception
     * if the given parameters are not inside the defined areas.
     */
    public RangedUnit(String NAME, int health, int ATTACK, int ARMOR, int ATTACK_SPEED_PER_SECOND, int HIT_RATE, int CRITIC_RATE, int CRITIC_DAMAGE)
            throws IllegalArgumentException {
        super(NAME,health,ATTACK,ARMOR,ATTACK_SPEED_PER_SECOND,"Ranged",HIT_RATE,CRITIC_RATE,CRITIC_DAMAGE);
    }

    /**
     * Default constructor for class edu.ntnu.idatt2001.pedropca.RangedUnit. The signature of this constructor only takes
     * Variable for the field NAME and health. This constructor will be used for default
     * ranged unit.
     * @param NAME String name of the unit
     * @param health int health points of the unit
     */
    public RangedUnit(String NAME, int health){
        super(NAME,health,15,8,3,"ranged", 65,15,150);
    }
    /**
     * Help method that overrides abstract method getAttackBonus from the class unit.
     * This method helps method getDamageDone from the class edu.ntnu.idatt2001.pedropca.Unit to get attack bonus.
     * This method will return the attack bonus of infantry units. The value of the bonus may change
     * depending on the opponent edu.ntnu.idatt2001.pedropca.Unit. If the opponent is an infantry unit. Method returns 7. Else,
     * the method returns 4.
     * @param opponent edu.ntnu.idatt2001.pedropca.Unit the opponent unit.
     * @return int the attack bonus.
     */

    @Override
    protected int getAttackBonus(Unit opponent) {
        if(opponent instanceof InfantryUnit)return 7;
        return 4;
    }

    /**
     * Help method that overrides abstract method getResistBonus from the class unit.
     * This method helps method getDamageDone from the class edu.ntnu.idatt2001.pedropca.Unit to get resist bonus.
     * This method will return the resist bonus of ranged units. The value of the bonus may change
     * depending on the opponent edu.ntnu.idatt2001.pedropca.Unit. If the opponent is another ranged unit, this method will return 1.
     * If the opponent is an infantry unit, this method will return 2;
     * And if the opponent is a cavalry unit, this method will return 0;
     * @param mainUnit edu.ntnu.idatt2001.pedropca.Unit the main unit that called method getDamageDone.
     * @return int the attack bonus.
     */

    @Override
    protected int getResistBonus(Unit mainUnit) {
        if(mainUnit instanceof InfantryUnit)return 2;
        if(mainUnit instanceof CavalryUnit) return 0;
        return 1;
    }

    /**
     * Help method that overrides abstract method clone from the class unit.
     * This method makes and return a deep copy of the unit that calls method.
     * It will help method getDamageDone from class edu.ntnu.idatt2001.pedropca.Unit and the abstract method getResistBonus()
     * @return edu.ntnu.idatt2001.pedropca.Unit Copy of th unit.
     */
    @Override
    protected Unit clone(){
        return new RangedUnit(this.getNAME(),this.getHealth(),
                this.getATTACK(),this.getARMOR(),this.getATTACK_SPEED_PER_SECOND(), this.getHIT_RATE(),
                this.getCRITIC_RATE(),this.getCRITIC_DAMAGE());
    }

}
