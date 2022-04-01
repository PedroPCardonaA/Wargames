package edu.ntnu.idatt2001.pedropca.wargames.models.units;

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
        return new Random().nextInt(25);
    }

    @Override
    protected int getResistBonus(Unit mainUnit) {
        return new Random().nextInt(5);
    }

    @Override
    protected Unit clone() {
        return new edu.ntnu.idatt2001.pedropca.wargames.models.units.RangedUnit(this.getName(), this.getHealth(),
                this.getAttack(), this.getArmor(), this.getAttackSpeedPerSecond(), this.getHitRate(),
                this.getCriticRate(), this.getCriticDamage());
    }
}
