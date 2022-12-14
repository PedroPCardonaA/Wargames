package edu.ntnu.idatt2001.pedropca.wargames.models;

import edu.ntnu.idatt2001.pedropca.wargames.models.units.Unit;
import edu.ntnu.idatt2001.pedropca.wargames.models.units.magicUnits.*;
import edu.ntnu.idatt2001.pedropca.wargames.models.units.nonMagicUnits.*;
import edu.ntnu.idatt2001.pedropca.wargames.util.EnumUnitType;
import edu.ntnu.idatt2001.pedropca.wargames.util.exceptions.BlankInputException;
import edu.ntnu.idatt2001.pedropca.wargames.util.UnitFactory;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Army class that represents an army in the war games.
 * The armies are composed by a final field NAME that contains the name of the army
 * and by a final list with all the units tha the army has. Objects of this class
 * will be used to simulate a battle between armies in the war games.
 *
 * @author Pedro Cardona
 * @version 1.0
 * @since 1.0-SNAPSHOT
 */


public class Army implements Serializable{
    private String name;
    private final List<Unit> units;

    /**
     * Constructor of class Army that has a String variable and a list of object units as signature.
     * This constructor will be used when the list of units is defined before the army object.
     * @param name String - name of the army
     * @param units List<Unit> - list of units
     * @throws BlankInputException this constructor may throw illegal argument exception
     * if the string variable name is blank or if the units list is defined as null
     */

    public Army(String name, List<Unit> units) throws BlankInputException {
        if(name.isBlank()) throw new BlankInputException("The name of the army cannot be blank. Enter a name for the army.");
        if(units == null) throw new BlankInputException("List of unit cannot be defined as null. Enter a correct unit list.");
        this.name = name.trim();
        this.units = units;
    }

    /**
     * Constructor of class Army that has a String variable as signature.
     * This constructor will be used when the list of units is not defined before the army object.
     * @param name String - name of the army
     * @throws BlankInputException this constructor may throw illegal argument exception
     * if the string variable name is blank
     */

    public Army(String name) throws BlankInputException {
        if(name.isBlank()) throw new BlankInputException("The name of the army cannot be blank. Enter a name for the army.");
        this.name = name;
        units = new ArrayList<>();
    }

    /**
     * Copy constructor that makes a new instance of this class
     * based on another army object.
     * @param army Army - army to be copied.
     */
    public Army(Army army){
        // Method may throw a nullPointException here if th army in the signature
        this.name = army.getName();
        this.units = new ArrayList<>();
        UnitFactory factory = new UnitFactory();
        army.getAllUnits().forEach(unit ->{
            units.add(factory.createUnit(Objects.requireNonNull(EnumUnitType.getUnitType(unit.getClass().getSimpleName())),unit.getName(),unit.getHealth(),
                    unit.getAttack(),unit.getArmor(),unit.getAttackSpeedPerSecond(),
                    unit.getHitRate(),unit.getCriticRate(),unit.getCriticDamage()));
        });
    }

    public String getName() {
        return name;
    }

    /**
     * Method that add a defined unit into list UNITS and ergo into the army.
     * This method has on object of class Unit as signature.
     * @param unit Unit - Unit to be added into the army.
     */
    public void add(Unit unit){
        this.units.add(unit);
    }

    /**
     * Method that add a defined list of units into list UNITS and ergo into the army.
     * This method has a list of objects of Unit as signature.
     * @param units List<Unit> - list of units to be added into army
     */
    public void addAll(List<Unit> units){
        this.units.addAll(units);
    }

    /**
     * Method that remove a defined unit from the list UNITS and ergo from the army.
     * @param unit Unit - Unit to be removed from the army.
     */
    public void removeUnit(Unit unit){
        this.units.remove(unit);
    }

    /**
     * Method that checks if the list UNITS has elements inside or in other words is not empty.
     * @return boolean - true if army has unit or false if not.
     */
    public boolean hasUnit(){
        return (!this.units.isEmpty());
    }

    /**
     * Method that returns current list of units of the army
     * @return List<Unit> - current list of units
     */
    public List<Unit> getAllUnits() {
        return this.units;
    }

    /**
     * Method that return a random unit from the list by using Random class.
     * Using av Random class is necessary to get a random integer that represents an index of the list,
     * and return the unit related to that index
     * @return Unit - Unit related to the random index
     */
    public Unit getRandom(){
        // This method may throw a nullPointerException when list UNITS is empty
        // , but the method that call this method checks it already. That is why I think
        // to throw a NullPointerException is unnecessary.
        return this.units.get(new Random().nextInt(this.units.size()));
    }

    /**
     * Method getInfantryUnits that return a list of all infantry units in the army.
     * @return List<Unit> - List with all the infantry units in the army.
     */
    public List<Unit> getInfantryUnits(){
        return units.stream().filter(unit -> unit instanceof InfantryUnit).collect(Collectors.toList());
    }

    /**
     * Method getCavalryUnits that return a list of all cavalry units in the army.
     * @return List<Unit> - List with all the cavalry units in the army.
     */
    public List<Unit> getCavalryUnits(){
        return units.stream().filter(unit -> unit instanceof CavalryUnit &&!(unit instanceof CommanderUnit)).collect(Collectors.toList());
    }

    /**
     * Method getRangedUnits that return a list of all Ranged units in the army.
     * @return List<Unit> - List with all the ranged units in the army.
     */
    public List<Unit> getRangedUnits(){
        return units.stream().filter(unit -> unit instanceof RangedUnit).collect(Collectors.toList());
    }

    /**
     * Method getCommanderUnits that return a list of all Commander units in the army.
     * @return List<Unit> - List with all the commander units in the army.
     */
    public List<Unit> getCommanderUnits(){
        return units.stream().filter(unit -> unit instanceof CommanderUnit).collect(Collectors.toList());
    }

    /**
     * Method getMagicianUnits that return a list of all Magician units in the army.
     * @return List<Unit> - List with all the magician units in the army.
     */
    public List<Unit> getMagicianUnits(){
        return units.stream().filter(unit -> unit instanceof MagicianUnit).collect(Collectors.toList());
    }

    /**
     * Method getMagicianUnits that return a list of all Healer units in the army.
     * @return List<Unit> - List with all the Healers units in the army.
     */
    public List<Unit> getHealerUnits(){
        return units.stream().filter(unit -> unit instanceof HealerUnit).collect(Collectors.toList());
    }

    /**
     * Void method that remove all units from the army.
     */
    public void removeAllUnits(){
        units.forEach(this::removeUnit);
    }


    /**
     * Set method tha defines a new for the army
     * @param name String - New name of the army
     * @throws BlankInputException If the String name is blank
     */
    public void setName(String name) throws BlankInputException {
        if(name.isBlank()) throw new BlankInputException("The name of the army cannot be blank. Enter a name for the army.");
        this.name = name.trim();
    }

    /**
     * Method that returns a single unit that matched the filter name.
     * @param nameOfUnit String - name of the unit to search
     * @return Unit - Unit with a name that matches the filter string
     */

    public Unit returnAUnitByName(String nameOfUnit){
        return units.stream().filter(unit -> unit.getName().equals(nameOfUnit)).findFirst().orElse(null);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%90S",this.getName())).append("\n");
        sb.append(String.format("%15S|%15S|%15S|%15S|%15S|%15S|%15S|%15S|%15S|"
                ,"NAME","HEALTH","ATTACK TYPE","ATTACK","ARMOR","ATTACK SPEED /s","HIT RATE", "CRITIC RATE", "CRITIC DAMAGE %")).append("\n");
        for(Unit unit: units){
            sb.append(unit.toString()).append("\n");
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Army)) return false;
        Army army = (Army) o;
        return name.equals(army.name); // I defined string variable NAME as key value to differentiate
                                       // to object of class edu.ntnu.idatt2001.pedropca.Army. This way makes more sense
                                       // that use of HashCode to differentiate objects for me.
                                       // This equal method may generate bug when to armies battle each other
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
