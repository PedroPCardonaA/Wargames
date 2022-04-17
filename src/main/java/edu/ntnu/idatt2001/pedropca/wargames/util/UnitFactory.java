package edu.ntnu.idatt2001.pedropca.wargames.util;

import edu.ntnu.idatt2001.pedropca.wargames.models.units.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Factory class that makes instances of the different classes in the
 * unit hierarchy as infantry, ranged, cavalry and commander.
 *
 * @author Pedro Cardona
 * @version 1.0
 * @since 1.0-SNAPSHOT
 */
public class UnitFactory {

    /**
     * Method that returns an instances of one of the classes' in the unit hierarchy based
     * on the unitType parameter. That parameter defined the class of the new unit from unit hierarchy.
     * @param name String name of the unit
     * @param health int health points of the unit
     * @param attack int attack points of the unit
     * @param armor int armor points of the unit
     * @param attackSpeedPerSecond int attack speed per second of the unit
     * @param hitRate int percent chance of not miss the attack
     * @param criticRate int percent chance of do a critical attack
     * @param criticDamage int percent that represent the damage from a critical attack in comparison
     *                      to a non-critical attack.
     * @return Unit - the new unit made by the factory.
     * @throws IllegalArgumentException this constructor may trow illegal argument exception
     * if the given parameters are not inside the defined areas.
     */
    public Unit createUnit(String unitType,String name, int health, int attack, int armor,
                           int attackSpeedPerSecond, int hitRate,
                           int criticRate, int criticDamage) throws IllegalArgumentException{
        switch (unitType){
            case "InfantryUnit":
                return new InfantryUnit(name,health,attack,armor,attackSpeedPerSecond,hitRate,criticRate,criticDamage);
            case "RangedUnit":
                return new RangedUnit(name,health,attack,armor,attackSpeedPerSecond,hitRate,criticRate,criticDamage);
            case "CavalryUnit":
                return new CavalryUnit(name,health,attack,armor,attackSpeedPerSecond,hitRate,criticRate,criticDamage);
            case "CommanderUnit":
                return new CommanderUnit(name,health,attack,armor,attackSpeedPerSecond,hitRate,criticRate,criticDamage);
            case "MagicianUnit":
                return new MagicianUnit(name,health,attack,armor,attackSpeedPerSecond,hitRate,criticRate,criticDamage);
            default:
                throw new IllegalArgumentException("Unknown unit type. Defined a correct unit type");
        }
    }

    /**
     * Method that returns an instances of one of the classes' in the unit hierarchy based
     * on the unitType parameter. That parameter defined the class of the new unit from unit hierarchy.
     * But this constructor use pre-defined values for attack ,armor, attackSpeedPerSecond, hitRate,
     * criticRate and criticDamage.
     * @param name String name of the unit
     * @param health int health points of the unit
     * @return Unit - the new unit made by the factory.
     * @throws IllegalArgumentException this constructor may trow illegal argument exception
     * if the given parameters are not inside the defined areas.
     */
    public Unit createUnit(String unitType,String name, int health) throws IllegalArgumentException{
        switch (unitType){
            case "InfantryUnit":
                return new InfantryUnit(name,health);
            case "RangedUnit":
                return new RangedUnit(name,health);
            case "CavalryUnit":
                return new CavalryUnit(name,health);
            case "CommanderUnit":
                return new CommanderUnit(name,health);
            case "MagicianUnit":
                return new MagicianUnit(name,health);
            default:
                throw new IllegalArgumentException("Unknown unit type. Defined a correct unit type");
        }
    }

    /**
     * Method that returns a list of instances of one of the classes' in the unit hierarchy based
     * on the unitType parameter. That parameter defined the class of the new units from unit hierarchy.
     * @param name String name of the unit
     * @param health int health points of the unit
     * @param attack int attack points of the unit
     * @param armor int armor points of the unit
     * @param attackSpeedPerSecond int attack speed per second of the unit
     * @param hitRate int percent chance of not miss the attack
     * @param criticRate int percent chance of do a critical attack
     * @param criticDamage int percent that represent the damage from a critical attack in comparison
     *                      to a non-critical attack.
     * @param numberOfUnits int number of new units
     * @return List<Unit> - the list of the new units made by the factory.
     * @throws IllegalArgumentException this constructor may trow illegal argument exception
     * if the given parameters are not inside the defined areas.
     */
    public List<Unit> createAListOfUnits(String unitType,String name, int health, int attack, int armor,
                                         int attackSpeedPerSecond, int hitRate,
                                         int criticRate, int criticDamage, int numberOfUnits) throws IllegalArgumentException{
        if(numberOfUnits<0) throw new IllegalArgumentException("The number of units to add cannot be lower than 0");
        List<Unit> units = new ArrayList<>();
        for(int i =0; i<numberOfUnits;i++){
            units.add(this.createUnit(unitType,name,health,attack,armor,attackSpeedPerSecond,hitRate,criticRate,criticDamage));
        }
        return units;
    }

    /**
     * Method that returns a list of instances of one of the classes' in the unit hierarchy based
     * on the unitType parameter. That parameter defined the class of the new units from unit hierarchy.
     * But this constructor use pre-defined values for attack ,armor, attackSpeedPerSecond, hitRate,
     * criticRate and criticDamage.
     * @param name String name of the unit
     * @param health int health points of the unit
     * @param numberOfUnits int number of new units
     * @return List<Unit> - the list of the new units made by the factory.
     * @throws IllegalArgumentException this constructor may trow illegal argument exception
     * if the given parameters are not inside the defined areas.
     */
    public List<Unit> createAListOfUnits(String unitType,String name, int health,int numberOfUnits) throws IllegalArgumentException{
        if(numberOfUnits<0) throw new IllegalArgumentException("The number of units to add cannot be lower than 0");
        List<Unit> units = new ArrayList<>();
        for(int i =0; i<numberOfUnits;i++){
            units.add(this.createUnit(unitType,name,health));
        }
        return units;
    }

}
