package edu.ntnu.idatt2001.pedropca.wargames.models.units;

import edu.ntnu.idatt2001.pedropca.wargames.util.exceptions.EmptyInputException;
import edu.ntnu.idatt2001.pedropca.wargames.util.exceptions.NegativeNumberException;
import edu.ntnu.idatt2001.pedropca.wargames.util.exceptions.OverOneHundredPercentException;

import java.io.Serializable;
import java.util.Random;

/**
 *
 * Abstract class unit that will be used as superclass for other unit classes.
 * Unit class represent the most basic object in the units' hierarchy.
 * Object of this class (and its subclasses) will be shape the object for the class Army.
 * This class has name, health, ATTACK, ARMOR, ATTACK_SPEED_PER_SECOND,
 * HIT_RATE, CRITIC_RATE and CRITIC_DAMAGE as fields.
 *
 * @author Pedro Cardona
 * @version 1.0
 * @since 1.0-SNAPSHOT
 */

public abstract class Unit implements Serializable {

    private final String name;
    private int health;
    private final int attack;
    private final int armor;
    private final int attackSpeedPerSecond;
    private final String attackType;
    private final int hitRate;
    private final int criticRate;
    private final int criticDamage;
    private final int maxHealth;


    /**
     * Constructor for the class, whit name, health, attack, armor, attack speed, attack type,
     * hit rate, critic rate, critic damage as signature.
     *
     * @param name String name of the unit
     * @param health int health points of the unit
     * @param attack int attack points of the unit
     * @param armor int armor points of the unit
     * @param attackSpeedPerSecond int attack speed per second of the unit
     * @param attackType string attack type of the unit
     * @param hitRate int percent chance of not miss the attack
     * @param criticRate int percent chance of do a critical attack
     * @param criticDamage int percent that represent the damage from a critical attack in comparison
     *                      to a non-critical attack.
     * @throws IllegalArgumentException this constructor may trow illegal argument exception
     * if the given parameters are not inside the defined areas.
     */
    public Unit(String name, int health, int attack, int armor,
                int attackSpeedPerSecond, String attackType, int hitRate,
                int criticRate, int criticDamage) throws IllegalArgumentException {
        if (name.isEmpty())throw new EmptyInputException
                ("All unit must have a name. Define a name for the unit.");
        if(health<0)throw new NegativeNumberException
                ("The health points of a unit cannot be lower than 0. Define the health points above 0.");
        if(attack <0) throw new NegativeNumberException
                ("The attack points of a unit cannot be lower than 0. Define the attack points above 0.");
        if(armor <0) throw new NegativeNumberException
                ("The armor points of a unit cannot be lower than 0. Define the armor points above 0.");
        if(attackSpeedPerSecond <0) throw new NegativeNumberException
                ("The attack speed of a unit cannot be lower than 0. Define the attack speed above 0.");
        if(hitRate <0 ) throw new NegativeNumberException
                ("The hit rate of a unit cannot be lower than 0. Define the hit rate between 0 to 100.");
        if(hitRate >100) throw new OverOneHundredPercentException
                ("The hit rate of a unit cannot be above than 100. Define the hit rate between 0 to 100.");
        if(criticRate <0) throw new NegativeNumberException
                ("The critic rate of a unit cannot be lower than 0. Define the critical rate between 0 to 100.");
        if(criticRate >100) throw new OverOneHundredPercentException
                ("The critic rate of a unit cannot be above than 100. Define the critical rate between 0 to 100.");
        if(criticDamage < 100){ throw new IllegalArgumentException
                ("The critic damage must be above 100 because it represent how much extra damage is made in comparison of a norma damage. Define the critic damage above 100.");
        }
        this.name = name.trim();
        this.health = health;
        this.attack = attack;
        this.armor = armor;
        this.attackSpeedPerSecond = attackSpeedPerSecond;
        this.attackType = attackType;
        this.hitRate = hitRate;
        this.criticRate = criticRate;
        this.criticDamage = criticDamage;
        this.maxHealth = health;
    }


    public String getName() {
        return name;
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

    public int getAttackSpeedPerSecond() {
        return attackSpeedPerSecond;
    }

    public String getAttackType(){return attackType;}

    public int getHitRate(){return hitRate;}

    public int getCriticRate() {
        return criticRate;
    }

    public int getCriticDamage() {
        return criticDamage;
    }

    public int getMaxHealth() {
        return maxHealth;
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
     * @param opponent Unit the opponent unit.
     * @return int - attack bonus.
     */
    public abstract int getAttackBonus(Unit opponent);
    /**
     * Abstract method that return the bonus armor of the units.
     * Method will be defined in subclasses of units hierarchy.
     * It has a unit in the signature, because the bonus armor of a unit varies depending
     * on the unit type of the opponent.
     *
     * I wanted to make a deeper gameplay. That is why I made a " Rock Paper Scissors" game style
     * where some units have an obvious advantage over other and vice versa.
     * @param mainUnit Unit the opponent unit.
     * @return int - resist bonus.
     */
    public abstract int getResistBonus(Unit mainUnit);

    public abstract Unit clone();

    /**
     * Method attack that will calculate the total damage makes by the main unit
     * to the opponent unit and reducing the current health of the opponent by the damage done.
     * This method will also check if the attack hit or miss and if the attack is a critical
     * or non-critical by using the random class.
     * @param opponent edu.ntnu.idatt2001.pedropca.Unit opponent unit
     */
    public void attack(Unit opponent){
        Random random = new Random();
        if(random.nextInt(101)<this.getHitRate()) {
            int damage;
            if(random.nextInt(101)<this.getCriticRate()){
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
     * @param opponent Unit - the opponent unit
     * @return int damage from a critical attack.
     */

    private int criticAttack(Unit opponent){
        return (this.getDamageDone(opponent) * getCriticDamage() / 100 );
    }

    /**
     * Help private method that calculate the standard damage makes by units to
     * their opponents. This method follows the next formula:
     *
     * (attack + attackBonus) of the main unit - (armor + resistBonus) of the opponent unit
     * and the result is divided by tha attack speed of the main unit (This was included to have
     * a standard damage-done between units)
     *
     * @param opponent Unit - Enemy unit to be attacked
     * @return int- Total damage done.
     */

    private int getDamageDone(Unit opponent){
        return Math.max(((this.getAttack() + this.getAttackBonus(opponent)) -
                (opponent.getArmor() + opponent.getResistBonus(this.clone())) / this.getAttackSpeedPerSecond()),0);
    }

    /**
     * Method that return the difference between the maxHealth and the current health.
     * @return int - the difference between the mac health and the current health.
     */
    public int getMissingHealth(){
        return maxHealth-health;
    }


    @Override
    public String toString() {
        return String.format("%15S|%15S|%15S|%15S|%15S|%15S|%15S|%15S|%15S|"
                ,this.getName(),this.getHealth(),this.getAttackType(),
                this.getAttack(),this.getArmor(),this.getAttackSpeedPerSecond(),this.getHitRate(),this.getCriticRate(),this.getCriticDamage());
    }

}
