
/**
 *
 * Class InfantryUnit that represents the infantry units in the war games.
 * This class conforms the Unit hierarchy and has the abstract class Unit as superclass.
 * This class has the same fields that Unit class.
 *
 * @author Pedro Cardona
 * @version 1.0
 * @since 1.0
 */
public class InfantryUnit extends Unit{

    /**
     * Constructor of the class InfantryUnit that take all the fields of class the class Unit
     * except the field ATTACK_TYPE because it is pre-defined as "melee".
     * @param NAME
     * @param health
     * @param ATTACK
     * @param ARMOR
     * @param ATTACK_SPEED_PER_SECOND
     * @param HIT_RATE
     * @param CRITIC_RATE
     * @param CRITIC_DAMAGE
     * @throws IllegalArgumentException
     */
    public InfantryUnit(String NAME, int health, int ATTACK, int ARMOR, int ATTACK_SPEED_PER_SECOND, int HIT_RATE, int CRITIC_RATE, int CRITIC_DAMAGE)
            throws IllegalArgumentException {
        super(NAME, health, ATTACK, ARMOR,ATTACK_SPEED_PER_SECOND,"melee", HIT_RATE,CRITIC_RATE,CRITIC_DAMAGE);
    }

    public InfantryUnit(String NAME, int health) {
        super(NAME,health,15,10,1,"melee",85,20,135);
    }
    @Override
    protected int getAttackBonus(Unit opponent){
        if(opponent instanceof CavalryUnit)return 4;
        return 2;
    }
    @Override
    protected int getResistBonus(Unit mainUnit){
        if(mainUnit instanceof CavalryUnit)return 3;
        return 2;
    }



    @Override
    protected Unit clone(){
        return new RangedUnit(this.getNAME(),this.getHealth(),
                this.getATTACK(),this.getARMOR(),this.getATTACK_SPEED_PER_SECOND(), this.getHIT_RATE(),
                this.getCRITIC_RATE(),this.getCRITIC_DAMAGE());
    }
}
