
/**
 *
 * Class CavalryUnit that represents the cavalry  units in the war games.
 * This class conforms the Unit hierarchy and has the abstract class Unit as superclass.
 * This class has the same fields that Unit class with exception to boolean isCharging.
 * That field ,that is defined as true, is a unique feature of CalvaryUnit objects.
 *
 * @author Pedro Cardona
 * @version 1.0
 * @since 1.0
 */
public class CavalryUnit extends Unit{

    private Boolean isCharging = true;

    /**
     * Constructor of the class CavalryUnit. The signature of this constructor
     * takes all the fields of class the class Unit
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
    public CavalryUnit(String NAME, int health, int ATTACK, int ARMOR, int ATTACK_SPEED_PER_SECOND, int HIT_RATE, int CRITIC_RATE, int CRITIC_DAMAGE)
            throws IllegalArgumentException {
        super(NAME, health, ATTACK, ARMOR, ATTACK_SPEED_PER_SECOND,"melee",HIT_RATE,CRITIC_RATE,CRITIC_DAMAGE);
    }

    /**
     * Default constructor for class CalvaryUnit. The signature of this constructor only takes
     * Variable for the field NAME and health. This constructor will be used for default
     * cavalry units.
     * @param NAME String name of the unit
     * @param health int health points of the unit
     */

    public CavalryUnit(String NAME, int health) {
        super(NAME,health,20,12, 2,"melee",70,25,145);
    }
    /**
     * Help method that overrides abstract method getAttackBonus from the class unit.
     * This method helps method getDamageDone from the class Unit to get attack bonus.
     * This method will return the attack bonus of infantry units. The value of the bonus may change
     * depending on the opponent Unit and if this unit is charging.
     * If the opponent is a ranged unit and this unit is charging, method returns 8.
     * If the opponent is a ranged unit and this unit is not charging, method returns 6.
     * if the opponent is not a ranged unit and this unit is charging, method returns 4.
     * Else, method returns 2.
     * @param opponent Unit the opponent unit.
     * @return int the attack bonus.
     */

    @Override
    protected int getAttackBonus(Unit opponent) {
        if(isCharging && opponent instanceof RangedUnit){
            this.setCharging(false);
            return 8;}
        else if(opponent instanceof RangedUnit){
            return 6;}
        else if( isCharging){
            this.setCharging(false);
            return 4;}
        else{
            return 2;}
    }

    /**
     * Help method that overrides abstract method getResistBonus from the class unit.
     * This method helps method getDamageDone from the class Unit to get resist bonus.
     * This method will return the resist bonus of cavalry units. The value of the bonus may change
     * depending on the opponent Unit. If the opponent is a ranged unit, this method will return 7.
     * If the opponent is an infantry unit, this method will return -2;
     * And if the opponent is another cavalry unit, this method will return 4;
     * @param mainUnit Unit the main unit that called method getDamageDone.
     * @return int the attack bonus.
     */

    @Override
    protected int getResistBonus(Unit mainUnit) {
        if(mainUnit instanceof RangedUnit)return 7;
        if(mainUnit instanceof InfantryUnit) return -2;
        return 4;
    }
    /**
     * Help method that overrides abstract method clone from the class unit.
     * This method makes and return a deep copy of the unit that calls method.
     * It will help method getDamageDone from class Unit and the abstract method getResistBonus()
     * @return Unit Copy of th unit.
     */
    @Override
    protected Unit clone(){
        return new CavalryUnit(this.getNAME(),this.getHealth(),
                this.getATTACK(),this.getARMOR(),this.getATTACK_SPEED_PER_SECOND(), this.getHIT_RATE(),
                this.getCRITIC_RATE(),this.getCRITIC_DAMAGE());
    }

    public void setCharging(Boolean charging) {
        isCharging = charging;
    }


}
