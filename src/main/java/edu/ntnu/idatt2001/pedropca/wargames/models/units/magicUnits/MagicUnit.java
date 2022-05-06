package edu.ntnu.idatt2001.pedropca.wargames.models.units.magicUnits;

import edu.ntnu.idatt2001.pedropca.wargames.models.units.Unit;

import java.util.List;

public abstract class MagicUnit extends Unit {

    private int mana;
    private final int maxMana;

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

    abstract public void magicAttack(List<Unit> target);

    @Override
    public void attack(Unit opponent) {
        super.attack(opponent);
        mana+=5;
    }
}
