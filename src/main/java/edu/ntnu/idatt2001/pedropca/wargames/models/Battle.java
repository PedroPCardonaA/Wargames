package edu.ntnu.idatt2001.pedropca.wargames.models;

import edu.ntnu.idatt2001.pedropca.wargames.models.units.nonMagicUnits.CavalryUnit;
import edu.ntnu.idatt2001.pedropca.wargames.models.units.Unit;
import edu.ntnu.idatt2001.pedropca.wargames.models.units.magicUnits.HealerUnit;
import edu.ntnu.idatt2001.pedropca.wargames.models.units.magicUnits.MagicUnit;
import edu.ntnu.idatt2001.pedropca.wargames.models.units.magicUnits.MagicianUnit;

import java.util.ArrayList;
import java.util.List;

/**
 * Class Battle that represents a battle between two armies in the war games.
 * This class will be the core of the war games because th entire project goes around
 * the simulation of a battle. This class has two final armies as fields.
 */

public class Battle {
    private final Army army1;
    private final Army army2;


    /**
     * Constructor of the class Battle. This constructor has two object from class Army as signature.
     * @param army1 army - First army of the battle
     * @param army2 army - Second army of the battle
     * @throws NullPointerException if one of the armies is defined as null. It is not 100% necessary,
     * but it is wanted to throw custom error message.
     */
    public Battle(Army army1, Army army2) throws NullPointerException{
        if(army1==null||army2==null) throw new NullPointerException("One of the armies is not defined. Enter a correct army");

        this.army1 = new Army(army1);
        this.army2 = new Army(army2);
    }

    /**
     * Method that simulate the battle between two armies and returns the winner between them.
     * To return the winner, this method gets help of the help method singular battle.
     * @return Army - the winner army of the battle.
     */
    public Army simulate(){
        while (this.bothArmiesHaveUnits()){
            this.singularBattle();
        }
        return this.checkWinnerArmy();
    }

    /**
     * Help method that represent an iteration of the simulate method.
     * This gets one random unit for each army, calls setChargeToCavalryUnit and combatBetweenUnits.
     */
    public void singularBattle(){
        Unit unitFromArmy1 = army1.getRandom();
        Unit unitFromArmy2 = army2.getRandom();
        this.setChargeToCavalryUnit(unitFromArmy1,unitFromArmy2);
        this.combatBetweenUnits(unitFromArmy1,unitFromArmy2);
    }

    /**
     * Help method that set the boolean field isCharging to true if the units that will
     * fight are instances of class CavalryUnit. It is important because isCharging changes
     * the return value of method getAttackBonus.
     * @param unitFromArmy1 Unit - Unit from the army number one that will be checked
     * @param unitFromArmy2 Unit - Unit from the army number two that will be checked
     */
    private void setChargeToCavalryUnit(Unit unitFromArmy1, Unit unitFromArmy2){
        if(unitFromArmy1 instanceof CavalryUnit)((CavalryUnit) unitFromArmy1).setCharging(true);
        if(unitFromArmy2 instanceof CavalryUnit)((CavalryUnit) unitFromArmy2).setCharging(true);
    }

    /**
     * Help method that simulate a single combat between two defined units, that will
     * remove the defeat unit after the combat. This method gets help from some help method
     * as magicAttack, rangedVSMelee, sameAttackType and removeDefeatedUnitArmy.
     * @param unitFromArmy1 Unit - Unit from the first army that will fight.
     * @param unitFromArmy2 Unit - Unit from the second army that will fight.
     */
    private void combatBetweenUnits(Unit unitFromArmy1,Unit unitFromArmy2){
        this.magicAttack(unitFromArmy1);
        this.magicAttack(unitFromArmy2);
        if(unitFromArmy1.getAttackType().equals("ranged")&& unitFromArmy2.getAttackType().equals("melee")){
            this.rangedVSMelee(unitFromArmy1,unitFromArmy2);
        }
        else if(unitFromArmy2.getAttackType().equals("ranged")&&unitFromArmy1.getAttackType().equals("melee")){
            this.rangedVSMelee(unitFromArmy2,unitFromArmy1);
        }
        else {
            this.sameAttackType(unitFromArmy1,unitFromArmy2);
        }
        this.removeDefeatedUnitFromArmy(this.getDefeatedUnit(unitFromArmy1,unitFromArmy2));
    }

    /**
     * Help method that "cast" a magic spell from a magic unit. Method check if the unit from the signatures
     * is an object from the magicUnit hierarchy and if unit has enough mana. If yes, unit cast the correct spell.
     * @param unit Unit - Unit that try to cast a spell
     */
    private void magicAttack(Unit unit){
        if(unit instanceof MagicUnit){
            if(unit instanceof HealerUnit){
                if(army1.getAllUnits().contains(unit) &&this.checkManaOfHealer((HealerUnit) unit)) ((HealerUnit) unit).magicSpell(army1.getAllUnits());
                if(army2.getAllUnits().contains(unit) &&this.checkManaOfHealer((HealerUnit) unit)) ((HealerUnit) unit).magicSpell(army2.getAllUnits());
            }
            if(unit instanceof MagicianUnit){
                if(army1.getAllUnits().contains(unit) &&this.checkManaOfMagician((MagicianUnit) unit)) ((MagicianUnit) unit).magicSpell(army2.getAllUnits());
                if(army2.getAllUnits().contains(unit) &&this.checkManaOfMagician((MagicianUnit) unit)) ((MagicianUnit) unit).magicSpell(army1.getAllUnits());
            }
        }
    }

    /**
     * Help method that check the current mana of a Healer unit.
     * @param magicUnit MagicUnit - MagicUnit to be tested.
     * @return boolean - true if mana of the unit is over 29, false else.
     */
    private boolean checkManaOfHealer(MagicUnit magicUnit){
        return magicUnit.getMana()>=30;
    }

    /**
     * Help method that check the current mana of a magician unit.
     * @param magicUnit MagicUnit - MagicUnit to be tested.
     * @return boolean - true if mana of the unit is over 29, false else.
     */
    private boolean checkManaOfMagician(MagicUnit magicUnit){
        return magicUnit.getMana()>=50;
    }

    /**
     * Help method that simulate a combat between two units with the same attack type.
     * This method is composed by a combat loop until one of the units get defeated.
     * @param unitFromArmy1 Unit - Unit from the first army that will fight.
     * @param unitFromArmy2 Unit -Unit from the second army that will fight.
     */
    private void sameAttackType(Unit unitFromArmy1, Unit unitFromArmy2) {
        while (unitFromArmy1.getHealth() > 0 && unitFromArmy2.getHealth() > 0) {
            unitFromArmy1.attack(unitFromArmy2);
            unitFromArmy2.attack(unitFromArmy1);
        }
    }

    /**
     * Help method that simulate a combat between a ranged unit against a melee unit.
     * This method is composed by two loops. The for loop represents the ranged advantage of ranged units
     * against melee units. The second loop represents the normal combat until
     * one of the units get defeated.
     * @param rangedUnit Unit - the ranged unit in the combat.
     * @param meleeUnit Unit - the melee unit in the combat.
     */
    // I think this is the way best to represent the advantage of a ranged unit against a melee unit.
    // It is possible to represent this advantage with a buff, that is reducing, in the getResistMethod
    // but that way is not realistic enough.
    private void rangedVSMelee(Unit rangedUnit, Unit meleeUnit){
        for(int i = 0; i<1; i++){
            rangedUnit.attack(meleeUnit);
        }
        while (rangedUnit.getHealth() > 0 && meleeUnit.getHealth() > 0){
            rangedUnit.attack(meleeUnit);
            meleeUnit.attack(rangedUnit);
        }
    }

    /**
     * Help method that checks the health points of both unit and return the unit
     * that does not have more health points ( The defeated unit).
     * @param unitFromArmy1 Unit - unit from the first army that will fight.
     * @param unitFromArmy2 Unit - unit from the second army that will fight.
     * @return The defeated unit
     */
    private Unit getDefeatedUnit(Unit unitFromArmy1,Unit unitFromArmy2){
        if(unitFromArmy1.getHealth()==0) return unitFromArmy1;
        if(unitFromArmy2.getHealth()==0) return unitFromArmy2;
        return null;
    }

    /**
     * Help method that removes the defeated unit defined by the help method getDefeatedUnit.
     * @param defeatedUnit Unit - the defeated unit from the method getDefeatedUnit.
     */
    private void removeDefeatedUnitFromArmy(Unit defeatedUnit){
        if(army1.getAllUnits().contains(defeatedUnit)) army1.removeUnit(defeatedUnit);
        if(army2.getAllUnits().contains(defeatedUnit)) army2.removeUnit(defeatedUnit);
    }


    /**
     * Help method that compare the numbers of units in the both armies returning the winner army.
     * This method returns the winner army when the enemy army does nor have more units or
     * null when both armies get empty of units at the same time.
     * @return The winner army or value null.
     */
    public Army checkWinnerArmy(){
        if(army1.hasUnit()&&!army2.hasUnit()){
            return army1;
        }
        else if(!army1.hasUnit()&& army2.hasUnit()){
            return army2;
        }
        else return null;
    }

    /**
     * Help method that check if the both armies have unit.
     * @return boolean. True if both armies have units, false else.
     */
    public boolean bothArmiesHaveUnits(){
        return (army1.hasUnit() && army2.hasUnit());
    }

    @Override
    public String toString() {
        return "Battle between " + army1.getName() + " and " + army2.getName() + ".";
    }

    public Army getArmy1() {
        return army1;
    }

    public Army getArmy2() {
        return army2;
    }

    /**
     *  Circumstantial method that do the same that the normal singularBattle method but
     *  this return a list with the Unit that fight to be printed in the GUI later.
     * @return List<Unit> - List of units that were involved in the singular battle.
     */
    public List<Unit> singularBattleForSlowAnimation() {
        List<Unit> units = new ArrayList<>();
        Unit unitFromArmy1 = army1.getRandom();
        Unit unitFromArmy2 = army2.getRandom();
        units.add(unitFromArmy1);
        units.add(unitFromArmy2);
        this.setChargeToCavalryUnit(unitFromArmy1,unitFromArmy2);
        this.combatBetweenUnits(unitFromArmy1,unitFromArmy2);
        return units;
    }
}
