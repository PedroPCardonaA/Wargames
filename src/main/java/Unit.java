import java.util.Random;

/**
 *
 * Abstract class unit that will be used as superclass to other classes.
 * Unit class represent the most basic elements of all units in the war games.
 * This class has a name, as private final string variable,
 * health, attack, armor, as private int variables.
 *
 * @author Pedro Cardona
 * @version 1.0
 * @since 1.0
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
     *Constructor for Class unit that has name, health, ATTACK and ARMOR as parameter.
     * @param NAME String name of the unit.
     * @param health int health points of the unit.
     * @param ATTACK int ATTACK points of the unit.
     * @param ARMOR int ARMOR points of the unit.
     * @param ATTACK_SPEED_PER_SECOND int number of attacks of a unit per second
     * @throws IllegalArgumentException Construct may throw illegal argument exception
     * if parameter name is empty or if the parameter health is negative.
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
        if(HIT_RATE <0 || HIT_RATE >100) throw new IllegalArgumentException
                ("The hit ratio of a unit cannot be lower than 0 or higher than 100. Define the hit ratio between 0 to 100");
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

    public void setHealth(int health)throws IllegalArgumentException {
        if(health<0) throw new IllegalArgumentException("The health points of a unit cannot be lower than 0. Define the health points above 0.");
        this.health = health;
    }

    /**
     * Abstract method that will define the attack bonus of the unit.
     * This method must be defined in subclasses.
     * @return int attack bonus of the unit
     */
    protected abstract int getAttackBonus(Unit opponent);
    /**
     * Abstract method that will define the resist bonus of the unit.
     * This method must be defined in subclasses.
     * @return int resist bonus of the unit
     */
    protected abstract int getResistBonus(Unit mainUnit);

    protected abstract Unit clone();

    /**
     * Method attack that will calculate the total damage makes by the main unit
     * to the opponent unit and set the new health points into the opponent unit.
     * @param opponent
     */
    public void attack(Unit opponent){
        Random random = new Random();
        if(random.nextInt(101)<this.getHIT_RATE()) {
            if(random.nextInt(101)<this.getCRITIC_RATE()){
                this.criticAttack(opponent);
            } else {
                this.nonCriticAttack(opponent);
            }
        }
    }

    private void criticAttack(Unit opponent){
        opponent.setHealth(Math.max((opponent.getHealth() - (this.getDamageDone(opponent) * getCRITIC_DAMAGE() / 100 )), 0));
    }

    private void nonCriticAttack(Unit opponent){
        opponent.setHealth(Math.max(opponent.getHealth() - getDamageDone(opponent), 0));
    }

    private int getDamageDone(Unit opponent){
        return ( ((this.getATTACK() + this.getAttackBonus(opponent)) +
                (opponent.getARMOR() + opponent.getResistBonus(this.clone())))) / this.getATTACK_SPEED_PER_SECOND();
    }


    @Override
    public String toString() {
        return String.format("%20S|%20S|%20S|%20S|%20S|%20S|%20S|%20S|%20S|"
                ,this.getNAME(),this.getHealth(),this.getATTACK_TYPE(),
                this.getATTACK(),this.getARMOR(),this.getATTACK_SPEED_PER_SECOND(),this.getHIT_RATE(),this.getCRITIC_RATE(),this.getCRITIC_DAMAGE());
    }

}
