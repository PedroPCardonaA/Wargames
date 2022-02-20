package edu.ntnu.idatt2001.pedropca;

import java.util.Random;

/**
 *
 * Abstract class unit that will be used as superclass to other unit classes.
 * edu.ntnu.idatt2001.pedropca.Unit class represent the most basic object in the units' hierarchy.
 * Object of this class (and its subclasses) will be shape the object for the class edu.ntnu.idatt2001.pedropca.Army.
 * This class has NAME, health, ATTACK, ARMOR, ATTACK_SPEED_PER_SECOND,
 * HIT_RATE, CRITIC_RATE and CRITIC_DAMAGE as fields.
 *
 * @author Pedro Cardona
 * @version 1.0
 * @since 1.0-SNAPSHOT
 */

public abstract class Unit {

    private final String NAME;
    private int health;
    private final int ATTACK;
    private final int ARMOR;
    private final int ATTACK_SPEED_PER_SECOND;
    private final String ATTACK_TYPE;
    private final int HIT_RATE;
    private final int CRITIC_RATE;
    private final int CRITIC_DAMAGE;


    /**
     * Constructor for the class, whit name, health, attack, armor, attack speed, attack type,
     * hit rate, critic rate, critic damage.
     *
     * @param NAME String name of the unit
     * @param health int health points of the unit
     * @param ATTACK int attack points of the unit
     * @param ARMOR int armor points of the unit
     * @param ATTACK_SPEED_PER_SECOND int attack speed per second of the unit
     * @param ATTACK_TYPE string attack type of the unit
     * @param HIT_RATE int percent chance of not miss the attack
     * @param CRITIC_RATE int percent chance of do a critical attack
     * @param CRITIC_DAMAGE int percent that represent the damage from a critical attack in comparison
     *                      to a non-critical attack.
     * @throws IllegalArgumentException this constructor may trow illegal argument exception
     * if the given parameters are not inside the defined areas.
     */
    public Unit(String NAME, int health, int ATTACK, int ARMOR,
                int ATTACK_SPEED_PER_SECOND, String ATTACK_TYPE, int HIT_RATE,
                int CRITIC_RATE, int CRITIC_DAMAGE) throws IllegalArgumentException {
        if (NAME.isEmpty())throw new IllegalArgumentException
                ("All unit must have a name. Define a name for the unit.");
        if(health<0)throw new IllegalArgumentException
                ("The health points of a unit cannot be lower than 0. Define the health points above 0.");
        if(ATTACK<0) throw new IllegalArgumentException
                ("The attack points of a unit cannot be lower than 0. Define the attack points above 0.");
        if(ARMOR<0) throw new IllegalArgumentException
                ("The armor points of a unit cannot be lower than 0. Define the armor points above 0.");
        if(ATTACK_SPEED_PER_SECOND<0) throw new IllegalArgumentException
                ("The attack speed of a unit cannot be lower than 0. Define the attack speed above 0.");
        if(HIT_RATE <0 ) throw new IllegalArgumentException
                ("The hit rate of a unit cannot be lower than 0. Define the hit rate between 0 to 100.");
        if(HIT_RATE >100) throw new IllegalArgumentException
                ("The hit rate of a unit cannot be above than 100. Define the hit rate between 0 to 100.");
        if(CRITIC_RATE<0) throw new IllegalArgumentException
                ("The critic rate of a unit cannot be lower than 0. Define the hit rate between 0 to 100.");
        if(CRITIC_RATE>100) throw new IllegalArgumentException
                ("The critic rate of a unit cannot be above than 100. Define the hit rate between 0 to 100.");
        if(CRITIC_DAMAGE < 100){ throw new IllegalArgumentException
                ("The critic damage must be above 100 because it represent how much extra damage is made in comparison of a norma damage. Define the critic damage above 100.");
        }
        this.NAME = NAME.trim();
        this.health = health;
        this.ATTACK = ATTACK;
        this.ARMOR = ARMOR;
        this.ATTACK_SPEED_PER_SECOND=ATTACK_SPEED_PER_SECOND;
        this.ATTACK_TYPE = ATTACK_TYPE;
        this.HIT_RATE = HIT_RATE;
        this.CRITIC_RATE = CRITIC_RATE;
        this.CRITIC_DAMAGE = CRITIC_DAMAGE;
    }


    public String getNAME() {
        return NAME;
    }

    public int getHealth() {
        return health;
    }

    public int getATTACK() {
        return ATTACK;
    }

    public int getARMOR() {
        return ARMOR;
    }

    public int getATTACK_SPEED_PER_SECOND() {
        return ATTACK_SPEED_PER_SECOND;
    }

    public String getATTACK_TYPE(){return ATTACK_TYPE;}

    public int getHIT_RATE(){return HIT_RATE;}

    public int getCRITIC_RATE() {
        return CRITIC_RATE;
    }

    public int getCRITIC_DAMAGE() {
        return CRITIC_DAMAGE;
    }

    /**
     * Mutator method that change the content of field health
     * @param health int new health of the unit
     * @throws IllegalArgumentException this method may throw an illegal argument exception
     * if the new health is lower than 0.
     */
    public void setHealth(int health)throws IllegalArgumentException {
        if(health<0) throw new IllegalArgumentException("The health points of a unit cannot be lower than 0. Define the health points above 0.");
        this.health = health;
    }

    /**
     * Abstract method that return the bonus attack of the units.
     * Method will be defined in subclasses of units hierarchy.
     * It has a unit in the signature, because the bonus attack of a unit varies depending
     * on the unit type of the opponent.
     *
     * I wanted to make a deeper gameplay. That is why I made a " Rock Paper Scissors" game style
     * where some units have an obvious advantage over other and vice versa.
     * @param opponent edu.ntnu.idatt2001.pedropca.Unit the opponent unit.
     * @return
     */
    protected abstract int getAttackBonus(Unit opponent);
    /**
     * Abstract method that return the bonus armor of the units.
     * Method will be defined in subclasses of units hierarchy.
     * It has a unit in the signature, because the bonus armor of a unit varies depending
     * on the unit type of the opponent.
     *
     * I wanted to make a deeper gameplay. That is why I made a " Rock Paper Scissors" game style
     * where some units have an obvious advantage over other and vice versa.
     * @param mainUnit edu.ntnu.idatt2001.pedropca.Unit the opponent unit.
     * @return
     */
    protected abstract int getResistBonus(Unit mainUnit);

    protected abstract Unit clone();

    /**
     * Method attack that will calculate the total damage makes by the main unit
     * to the opponent unit and reducing the current health of the opponent by the damage done.
     * This method will also check if the attack hit or miss and if the attack is a critical
     * or non-critical by using the random class.
     * @param opponent edu.ntnu.idatt2001.pedropca.Unit opponent unit
     */
    public void attack(Unit opponent){
        Random random = new Random();
        if(random.nextInt(101)<this.getHIT_RATE()) {
            int damage = 0;
            if(random.nextInt(101)<this.getCRITIC_RATE()){
                damage = this.criticAttack(opponent);
            } else {
                damage = this.getDamageDone(opponent);
            }
            opponent.setHealth(Math.max(opponent.getHealth()-damage,0));
        }
    }

    /**
     * Help private method that calculate the total damage with help of
     * help method getDamageDone.
     * @param opponent
     * @return int damage from a critical attack.
     */

    private int criticAttack(Unit opponent){
        return (this.getDamageDone(opponent) * getCRITIC_DAMAGE() / 100 );
    }

    /**
     * Help private method that calculate the standard damage makes by units to
     * their opponents. This method follows the next formula:
     *
     * (attack + attackBonus) of the main unit - (armor + resistBonus) of the opponent unit
     * and the result is divided by tha attack speed of the main unit (This was included to have
     * a standard damage-done between units)
     *
     * @param opponent
     * @return
     */

    private int getDamageDone(Unit opponent){
        return ( ((this.getATTACK() + this.getAttackBonus(opponent)) -
                (opponent.getARMOR() + opponent.getResistBonus(this.clone())))) / this.getATTACK_SPEED_PER_SECOND();
    }


    @Override
    public String toString() {
        return String.format("%10S|%10S|%10S|%10S|%10S|%10S|%10S|%10S|%10S|"
                ,this.getNAME(),this.getHealth(),this.getATTACK_TYPE(),
                this.getATTACK(),this.getARMOR(),this.getATTACK_SPEED_PER_SECOND(),this.getHIT_RATE(),this.getCRITIC_RATE(),this.getCRITIC_DAMAGE());
    }

}
