/**
 *
 * Class CommanderUnit that represents the cavalry  units in the war games.
 * This class conforms the Unit hierarchy and has the abstract class cavalryUnit as superclass.
 * This class has the same fields that CalvaryUnit.
 *
 * @author Pedro Cardona
 * @version 1.0
 * @since 1.0
 */

public class CommanderUnit extends CavalryUnit{
    /**
     * Constructor of the class CommanderUnit. The signature of this constructor
     * takes all the fields of class the class CavalryUnit
     * except the field ATTACK_TYPE because it is pre-defined as "melee"
     * and field isCharging that is defined as true when the object is made.
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
    public CommanderUnit(String NAME, int health, int ATTACK, int ARMOR, int ATTACK_SPEED_PER_SECOND, int HIT_RATE, int CRITIC_RATE,int CRITIC_DAMAGE)
            throws IllegalArgumentException {
        super(NAME, health, ATTACK, ARMOR,ATTACK_SPEED_PER_SECOND,HIT_RATE,CRITIC_RATE,CRITIC_DAMAGE);
    }
    /**
     * Default constructor for class CommanderUnit. The signature of this constructor only takes
     * Variable for the field NAME and health. This constructor will be used for default
     * cavalry units.
     * @param NAME String name of the unit
     * @param health int health points of the unit
     */
    public CommanderUnit(String NAME, int health) {
        super(NAME, health,25,15,1,90,25,175);
    }

    /**
     * Help method that simulates life regeneration by adding 5 points to
     * the total life points of the unt. This method will be called in method attack
     */
    private void healthPointRegeneration(){
        setHealth(this.getHealth()+5);
    }

    /**
     * Override method that just add help method to healthPointRegeneration to the method in the
     * abstract class Unit.
     * @param opponent Unit opponent unit
     */
    @Override
    public void attack(Unit opponent){
        super.attack(opponent);
        this.healthPointRegeneration();
    }
}
