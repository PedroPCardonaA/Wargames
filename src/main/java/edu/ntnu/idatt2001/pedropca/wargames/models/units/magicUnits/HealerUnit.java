package edu.ntnu.idatt2001.pedropca.wargames.models.units.magicUnits;

import edu.ntnu.idatt2001.pedropca.wargames.models.units.nonMagicUnits.CavalryUnit;
import edu.ntnu.idatt2001.pedropca.wargames.models.units.nonMagicUnits.InfantryUnit;
import edu.ntnu.idatt2001.pedropca.wargames.models.units.Unit;
import edu.ntnu.idatt2001.pedropca.wargames.util.EnumTerrain;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * Class HealerUnit that represents the healer units in the war games.
 * This class conforms the MagiUnit hierarchy and has the abstract MagicUnit as superclass.
 * This class has the same fields that MagicUnit class.
 *
 * @author Pedro Cardona
 * @version 1.0
 * @since 1.0-SNAPSHOT
 */
//TODO: ADD TEST FOR THIS CLASS
public class HealerUnit extends MagicUnit{
    /**
     * Constructor of the class Magician. The signature of this constructor
     * takes all the fields of class the class Unit
     * except the field ATTACK_TYPE because it is pre-defined as "ranged",
     * and the mana that is defined as 200.
     * @param name String - name of the unit
     * @param health int - health points of the unit
     * @param attack int - attack points of the unit
     * @param armor int - armor points of the unit
     * @param attackSpeedPerSecond - int attack speed per second of the unit
     * @param hitRate - int percent chance of not miss the attack
     * @param criticRate - int percent chance of do a critical attack
     * @param criticDamage - int percent that represent the damage from a critical attack in comparison
     *                      to a non-critical attack.
     * @throws IllegalArgumentException this constructor may trow illegal argument exception
     * if the given parameters are not inside the defined areas.
     */
    public HealerUnit(String name, int health, int attack, int armor, int attackSpeedPerSecond, int hitRate, int criticRate, int criticDamage)
            throws IllegalArgumentException {
        super(name, health, attack, armor, attackSpeedPerSecond, "Ranged", hitRate, criticRate, criticDamage,200);
    }

    /**
     * Default constructor for class HealerUnit. The signature of this constructor only takes
     * Variable for the field name and health. This constructor will be used for default
     * ranged unit.
     * @param name String - name of the unit
     * @param health int - health points of the unit
     */
    public HealerUnit(String name, int health) throws IllegalArgumentException {
        super(name, health, 5, 5, 5, "Ranged", 80, 10, 125,200);
    }

    /**
     * Help method that overrides abstract method getAttackBonus from the class unit.
     * This method helps method getDamageDone from the class Unit to get attack bonus.
     * This method will return the attack bonus of healer units. The value of the bonus may change
     * depending on the opponent Unit. If the opponent is a cavalry unit. Method will return -5.
     * Else if the opponent is Magician unit, Method will return 25. Else the attack bonus will be zero.
     * The forest terrain and the volcano terrain de-buffs.
     * @param opponent Unit the opponent unit.
     * @return int the attack bonus.
     */
    @Override
    public int getAttackBonus(Unit opponent) {
        int attackBonus = 0;
        if(EnumTerrain.getTerrain() == EnumTerrain.VOLCANO) attackBonus -=5;
        if(EnumTerrain.getTerrain() == EnumTerrain.FOREST) attackBonus -=2;
        if(opponent instanceof CavalryUnit) attackBonus -= 5;
        if(opponent instanceof MagicianUnit) attackBonus += 25;
        return attackBonus;
    }

    /**
     * Help method that overrides abstract method getResistBonus from the class unit.
     * This method helps method getDamageDone from the class Unit to get resist bonus.
     * This method will return the resist bonus of ranged units. The value of the bonus may change
     * depending on the opponent Unit. If the opponent is another ranged unit, this method will return 1.
     * If the opponent is an infantry unit, this method will return 2;
     * And if the opponent is a cavalry unit, this method will return 0;
     * The volcano terrain de-buffs the resist bonus.
     * @param mainUnit Unit the main unit that called method getDamageDone.
     * @return int the attack bonus.
     */

    @Override
    public int getResistBonus(Unit mainUnit) {
        int resistBonus = 5;
        if(EnumTerrain.getTerrain() == EnumTerrain.VOLCANO) resistBonus -=5;
        if(mainUnit instanceof CavalryUnit) resistBonus -= 5;
        if(mainUnit instanceof InfantryUnit) resistBonus -= 2;
        if(mainUnit instanceof MagicianUnit) resistBonus += 15;
        return resistBonus;
    }

    /**
     * Help method that overrides abstract method clone from the class unit.
     * This method makes and return a deep copy of the unit that calls method.
     * It will help method getDamageDone from class Unit and the abstract method getResistBonus()
     * @return Unit Copy of th unit.
     */
    @Override
    public Unit clone() {
        return new HealerUnit(this.getName(), this.getHealth(),
                this.getAttack(), this.getArmor(), this.getAttackSpeedPerSecond(), this.getHitRate(),
                this.getCriticRate(), this.getCriticDamage());
    }

    /**
     * Method that overrides abstract method form MagicUnit Class.
     * This method represents a healing spell that is cast over allies.
     * Method gets the unit from the target with the highest difference between
     * maxHealth and current health and heals it by 30. Spell cost 30 mana points.
     * @param target list of units - target of the spell
     */
    @Override
    public void magicSpell(List<Unit> target) {
        Unit unitTarget= target.stream().sorted(Comparator.comparing(Unit::getMissingHealth).reversed()).collect(Collectors.toList()).get(0);
        if(!(unitTarget.getMissingHealth()==0)){
            unitTarget.setHealth(Math.min(unitTarget.getHealth()+30,unitTarget.getMaxHealth()));
            this.setMana(Math.max(this.getMana()-30,0));
        }
    }
}
