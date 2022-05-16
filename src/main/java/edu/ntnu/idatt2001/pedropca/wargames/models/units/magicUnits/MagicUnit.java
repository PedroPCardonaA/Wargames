package edu.ntnu.idatt2001.pedropca.wargames.models.units.magicUnits;

import edu.ntnu.idatt2001.pedropca.wargames.models.units.Unit;

import java.util.List;

/**
 * Abstract class MagicUnit that will be used as a super class for other magic unit.
 * MagicUnit represent the most basic object in the sub-hierarchy of magic units.
 * This class is also in the Unit's hierarchy and inherits form the Super class Unit.
 * This class has two new field in comparison to Unit Class. Mana and maxMana.
 * Mana is the fuel for the magic spells of the magicUnits.
 * @author Pedro Cardona
 * @version 1.0
 * @since 1.0-SNAPSHOT
 */
public abstract class MagicUnit extends Unit {

    private int mana;
    private final int maxMana;

    /**
     *Constructor of the abstract class MagicUnit that contains the same elements in the signature
     * than super class Unit, adding filed mana.
     * @param name String name of the unit
     * @param health int health points of the unit
     * @param attack int attack points of the unit
     * @param armor int armor points of the unit
     * @param attackSpeedPerSecond int attack speed per second of the unit
     * @param hitRate int percent chance of not miss the attack
     * @param criticRate int percent chance of do a critical attack
     * @param criticDamage int percent that represent the damage from a critical attack in comparison
     *                      to a non-critical attack.
     * @param mana int
     * @throws IllegalArgumentException this constructor may trow illegal argument exception
     * if the given parameters are not inside the defined areas.
     */
    public MagicUnit(String name, int health, int attack, int armor, int attackSpeedPerSecond, String attackType, int hitRate, int criticRate, int criticDamage, int mana) throws IllegalArgumentException {
        super(name, health, attack, armor, attackSpeedPerSecond, attackType, hitRate, criticRate, criticDamage);
        this.mana = mana;
        this.maxMana = mana;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public int getMaxMana() {
        return maxMana;
    }

    /**
     * Abstract method that "Cast" ( Make a numerical representation of) the different magic spells
     * of the different MagicUnit.
     * Method will be defined in subclasses of magic units' hierarchy.
     * The signature of the method contains a list<Unit> target, it could be an ally army or an enemy army.
     * @param target list of units - target of the spell
     */
    abstract public void magicSpell(List<Unit> target);

    /**
     * Method that overrides the attack method from the class Unit, adding a mana regeneration feature.
     * @param opponent Unit - opponent unit
     */
    @Override
    public void attack(Unit opponent) {
        super.attack(opponent);
        this.setMana(Math.min(this.getMana()+5,this.getMaxMana()));
    }
}
