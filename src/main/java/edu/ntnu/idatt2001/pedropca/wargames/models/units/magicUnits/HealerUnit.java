package edu.ntnu.idatt2001.pedropca.wargames.models.units.magicUnits;

import edu.ntnu.idatt2001.pedropca.wargames.models.units.nonMagicUnits.CavalryUnit;
import edu.ntnu.idatt2001.pedropca.wargames.models.units.nonMagicUnits.InfantryUnit;
import edu.ntnu.idatt2001.pedropca.wargames.models.units.Unit;
import edu.ntnu.idatt2001.pedropca.wargames.util.EnumTerrain;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

//TODO: ADD JAVADOC FOR THIS CLASS
//TODO: ADD TEST FOR THIS CLASS
public class HealerUnit extends MagicUnit{
    public HealerUnit(String name, int health, int attack, int armor, int attackSpeedPerSecond, int hitRate, int criticRate, int criticDamage)
            throws IllegalArgumentException {
        super(name, health, attack, armor, attackSpeedPerSecond, "Ranged", hitRate, criticRate, criticDamage,200);
    }

    public HealerUnit(String name, int health) throws IllegalArgumentException {
        super(name, health, 5, 5, 5, "Ranged", 80, 10, 125,200);
    }

    @Override
    public int getAttackBonus(Unit opponent) {
        int attackBonus = 0;
        if(EnumTerrain.getTerrain() == EnumTerrain.VOLCANO) attackBonus -=5;
        if(EnumTerrain.getTerrain() == EnumTerrain.FOREST) attackBonus -=2;
        if(opponent instanceof CavalryUnit) attackBonus -= 5;
        if(opponent instanceof MagicianUnit) attackBonus += 25;
        return attackBonus;
    }

    @Override
    public int getResistBonus(Unit mainUnit) {
        int resistBonus = 5;
        if(EnumTerrain.getTerrain() == EnumTerrain.VOLCANO) resistBonus -=5;
        if(mainUnit instanceof CavalryUnit) resistBonus -= 5;
        if(mainUnit instanceof InfantryUnit) resistBonus -= 5;
        if(mainUnit instanceof MagicianUnit) resistBonus += 15;
        return resistBonus;
    }

    @Override
    public Unit clone() {
        return new HealerUnit(this.getName(), this.getHealth(),
                this.getAttack(), this.getArmor(), this.getAttackSpeedPerSecond(), this.getHitRate(),
                this.getCriticRate(), this.getCriticDamage());
    }

    @Override
    public void magicAttack(List<Unit> target) {
        Unit unitTarget= target.stream().sorted(Comparator.comparing(Unit::getMissingHealth)).collect(Collectors.toList()).get(0);
        unitTarget.setHealth(Math.min(unitTarget.getHealth()+30,unitTarget.getMaxHealth()));
        this.setMana(Math.max(this.getMana()-30,0));
    }
}
