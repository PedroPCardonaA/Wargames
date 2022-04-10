package edu.ntnu.idatt2001.pedropca.wargames.models.units;

import edu.ntnu.idatt2001.pedropca.wargames.util.SingletonTerrain;

import java.util.Random;

public class MagicianUnit extends Unit{

    public MagicianUnit(String name, int health, int attack, int armor, int attackSpeedPerSecond, int hitRate, int criticRate, int criticDamage)
            throws IllegalArgumentException {
        super(name, health, attack, armor, attackSpeedPerSecond, "Ranged", hitRate, criticRate, criticDamage);
    }

    public MagicianUnit(String name, int health) {
        super(name, health, 15, 8, 3, "ranged", 65, 15, 150);
        }

    @Override
    protected int getAttackBonus(Unit opponent) {
        int attackBonus = new Random().nextInt(25);
        if(SingletonTerrain.getSingletonTerrain().getTerrain().equalsIgnoreCase("Volcano")) attackBonus+=5;
        return attackBonus;

    }

    @Override
    protected int getResistBonus(Unit mainUnit) {
        int resistBonus = new Random().nextInt(5);
        if(SingletonTerrain.getSingletonTerrain().getTerrain().equalsIgnoreCase("Volcano")) resistBonus+=5;
        return resistBonus;

    }

    @Override
    protected Unit clone() {
        return new edu.ntnu.idatt2001.pedropca.wargames.models.units.RangedUnit(this.getName(), this.getHealth(),
                this.getAttack(), this.getArmor(), this.getAttackSpeedPerSecond(), this.getHitRate(),
                this.getCriticRate(), this.getCriticDamage());
    }
}
