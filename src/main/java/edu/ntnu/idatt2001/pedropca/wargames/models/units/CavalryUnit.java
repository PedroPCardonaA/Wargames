package edu.ntnu.idatt2001.pedropca.wargames.models.units;

import edu.ntnu.idatt2001.pedropca.wargames.util.SingletonTerrain;

/**
 *
 * Class edu.ntnu.idatt2001.pedropca.CavalryUnit that represents the cavalry  units in the war games.
 * This class conforms the edu.ntnu.idatt2001.pedropca.Unit hierarchy and has the abstract class edu.ntnu.idatt2001.pedropca.Unit as superclass.
 * This class has the same fields that edu.ntnu.idatt2001.pedropca.Unit class with exception to boolean isCharging.
 * That field ,that is defined as true, is a unique feature of CalvaryUnit objects.
 *
 * @author Pedro Cardona
 * @version 1.0
 * @since 1.0
 */
public class CavalryUnit extends Unit{

    private Boolean isCharging = true;

    /**
     * Constructor of the class edu.ntnu.idatt2001.pedropca.CavalryUnit. The signature of this constructor
     * takes all the fields of class the class edu.ntnu.idatt2001.pedropca.Unit
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
    public CavalryUnit(String name, int health, int attack, int armor, int attackSpeedPerSecond, int hitRate, int criticRate, int criticDamage)
            throws IllegalArgumentException {
        super(name, health, attack, armor, attackSpeedPerSecond,"melee",hitRate,criticRate,criticDamage);
    }

    /**
     * Default constructor for class CalvaryUnit. The signature of this constructor only takes
     * Variable for the field name and health. This constructor will be used for default
     * cavalry units.
     * @param name String name of the unit
     * @param health int health points of the unit
     */

    public CavalryUnit(String name, int health) {
        super(name,health,20,12, 2,"melee",70,25,145);
    }
    /**
     * Help method that overrides abstract method getAttackBonus from the class unit.
     * This method helps method getDamageDone from the class edu.ntnu.idatt2001.pedropca.Unit to get attack bonus.
     * This method will return the attack bonus of infantry units. The value of the bonus may change
     * depending on the opponent edu.ntnu.idatt2001.pedropca.Unit and if this unit is charging.
     * If the opponent is a ranged unit and this unit is charging, method returns 8.
     * If the opponent is a ranged unit and this unit is not charging, method returns 6.
     * if the opponent is not a ranged unit and this unit is charging, method returns 4.
     * Else, method returns 2.
     * The plain terrain buffs the attack bonus.
     * @param opponent edu.ntnu.idatt2001.pedropca.Unit the opponent unit.
     * @return int the attack bonus.
     */

    @Override
    protected int getAttackBonus(Unit opponent) {
        int attackBonus = 0;
        if(isCharging && opponent instanceof RangedUnit){
            this.setCharging(false);
            attackBonus+=8;}
        else if(opponent instanceof RangedUnit){
            attackBonus+=6;}
        else if( isCharging){
            this.setCharging(false);
            attackBonus+=4;}
        else{
            attackBonus+=2;}
        if(SingletonTerrain.getSingletonTerrain().getTerrain().equalsIgnoreCase("Plains")) attackBonus+=3;
        return attackBonus;
    }

    /**
     * Help method that overrides abstract method getResistBonus from the class unit.
     * This method helps method getDamageDone from the class edu.ntnu.idatt2001.pedropca.Unit to get resist bonus.
     * This method will return the resist bonus of cavalry units. The value of the bonus may change
     * depending on the opponent edu.ntnu.idatt2001.pedropca.Unit. If the opponent is a ranged unit, this method will return 7.
     * If the opponent is an infantry unit, this method will return -2;
     * And if the opponent is another cavalry unit, this method will return 4;
     * The volcano forest de-buffs the resist bonus and the volcano terrain de-buffs the resist bonus.
     * @param mainUnit edu.ntnu.idatt2001.pedropca.Unit the main unit that called method getDamageDone.
     * @return int the attack bonus.
     */

    @Override
    protected int getResistBonus(Unit mainUnit) {
        int resistBonus = 0;
        if(mainUnit instanceof RangedUnit)resistBonus +=7;
        else if(mainUnit instanceof InfantryUnit) resistBonus += 2;
        else if(mainUnit instanceof MagicianUnit) resistBonus += 1;
        else resistBonus +=4;
        if(SingletonTerrain.getSingletonTerrain().getTerrain().equalsIgnoreCase("Forest")) resistBonus =0;
        if(SingletonTerrain.getSingletonTerrain().getTerrain().equalsIgnoreCase("Volcano")) resistBonus-=5;
        return resistBonus;
    }
    /**
     * Help method that overrides abstract method clone from the class unit.
     * This method makes and return a deep copy of the unit that calls method.
     * It will help method getDamageDone from class edu.ntnu.idatt2001.pedropca.Unit and the abstract method getResistBonus()
     * @return edu.ntnu.idatt2001.pedropca.Unit Copy of th unit.
     */
    @Override
    protected Unit clone(){
        return new CavalryUnit(this.getName(),this.getHealth(),
                this.getAttack(),this.getArmor(),this.getAttackSpeedPerSecond(), this.getHitRate(),
                this.getCriticRate(),this.getCriticDamage());
    }


    @Override
    public void attack(Unit opponent){
        super.attack(opponent);
        this.setCharging(false);
    }
    public void setCharging(Boolean charging) {
        isCharging = charging;
    }

    public Boolean getCharging() {
        return isCharging;
    }
}
