package edu.ntnu.idatt2001.pedropca.wargames.models.units.magicUnits;

import edu.ntnu.idatt2001.pedropca.wargames.models.units.Unit;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class HealerUnit extends MagicUnit{
    public HealerUnit(String name, int health, int attack, int armor, int attackSpeedPerSecond, String attackType, int hitRate, int criticRate, int criticDamage, int mana) throws IllegalArgumentException {
        super(name, health, attack, armor, attackSpeedPerSecond, "Ranged", hitRate, criticRate, criticDamage,200);
    }

    @Override
    public int getAttackBonus(Unit opponent) {
        return 0;
    }

    @Override
    public int getResistBonus(Unit mainUnit) {
        return 0;
    }

    @Override
    protected Unit clone() {
        return null;
    }

    @Override
    public void magicAttack(List<Unit> target) {
        Unit unitTarget= target.stream().sorted(Comparator.comparing(Unit::getMissingHealth)).collect(Collectors.toList()).get(0);
        unitTarget.setHealth(Math.min(unitTarget.getHealth()+30,unitTarget.getMaxHealth()));
        this.setMana(Math.max(this.getMana()-30,0));
    }
}
