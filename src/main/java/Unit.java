/**
 *
 * Abstract class unit that will be used as superclass to other classes.
 * Unit class represent the most basic elements of all units in the war games.
 * This class has a name, as private final string variable,
 * health, attack, and armor, as private int variables.
 *
 * @author Pedro Cardona
 * @version 1.0
 * @since 1.0
 */

public abstract class Unit {

    private final String NAME;
    private int health;
    private int attack;
    private int armor;

    /**
     *Constructor for Class unit that has name, health, attack and armor as parameter.
     * @param NAME String name of the unit.
     * @param health int health points of the unit.
     * @param attack int attack point of the unit.
     * @param armor int armor point of the unit.
     * @throws IllegalArgumentException Construct may throw illegal argument exception
     * if parameter name is empty or if the parameter health is negative.
     */
    public Unit(String NAME, int health, int attack, int armor) throws IllegalArgumentException {
        if (NAME.isEmpty())throw new IllegalArgumentException
                ("All unit must have a name. Define a name for the unit.");
        if(health<0)throw new IllegalArgumentException
                ("The health of a unit cannot be lower than 0. Define the health points above 0.");
        this.NAME = NAME.trim();
        this.health = health;
        this.attack = attack;
        this.armor = armor;
    }

    public String getNAME() {
        return NAME;
    }

    public int getHealth() {
        return health;
    }

    public int getAttack() {
        return attack;
    }

    public int getArmor() {
        return armor;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * Abstract method that will define the attack bonus of the unit.
     * This method must be defined in subclasses.
     * @return int attack bonus of the unit
     */
    public abstract int getAttackBonus(Unit opponent);
    /**
     * Abstract method that will define the resist bonus of the unit.
     * This method must be defined in subclasses.
     * @return int resist bonus of the unit
     */
    public abstract int getResistBonus();

    /**
     * Method attack that will calculate the total damage makes by the main unit
     * to the opponent unit and set the new health points into the opponent unit.
     * @param opponent
     */
    public void attack(Unit opponent){
        int newHealth= opponent.getHealth() -(this.getAttack()+this.getAttackBonus(opponent))+
                (opponent.getArmor()+opponent.getResistBonus());
        opponent.setHealth(Math.max(newHealth, 0));
    }

    @Override
    public String toString() {
        return "Name of unit: " + this.getNAME() + "\n" +
                "Current health points of unit: " + this.getHealth() + "\n" +
                "Attack points of the unit: " + this.getAttack() + "\n" +
                "Armor points of the unit: " + this.getArmor() + "\n";
    }

}
