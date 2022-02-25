package edu.ntnu.idatt2001.pedropca;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * edu.ntnu.idatt2001.pedropca.Army class that represents an army in the war games.
 * The armies are composed by a final field NAME that contains the name of the army
 * and by a final list with all the units tha the army has. Objects of this class
 * will be used to simulate a battle between armies in the war games.
 *
 * @author Pedro Cardona
 * @version 1.0
 * @since 1.0-SNAPSHOT
 */
public class Army {
    private final String name;
    private final List<Unit> units;

    /**
     * Constructor of class edu.ntnu.idatt2001.pedropca.Army that has a String variable and a list of object units as signature.
     * This constructor will be used when the list of units is defined before the army object.
     * @param name name of the army
     * @param units list of units
     * @throws IllegalArgumentException this constructor may throw illegal argument exception
     * if the string variable name is empty or if the units list is defined as null
     */

    public Army(String name, List<Unit> units) throws IllegalArgumentException{
        if(name.isEmpty()) throw new IllegalArgumentException("The name of the army cannot be empty. Enter a name for the army.");
        if(units == null) throw new IllegalArgumentException("List of unit cannot be defined as null. Enter a correct unit list.");
        this.name = name.trim();
        this.units = units;
    }

    /**
     * Constructor of class edu.ntnu.idatt2001.pedropca.Army that has a String variable as signature.
     * This constructor will be used when the list of units is not defined before the army object.
     * @param name name of the army
     * @throws IllegalArgumentException this constructor may throw illegal argument exception
     * if the string variable name is empty
     */

    public Army(String name){
        if(name.isEmpty()) throw new IllegalArgumentException("The name of the army cannot be empty. Enter a name for the army.");
        this.name = name;
        units = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    /**
     * Method that add a defined unit into list UNITS and ergo into the army.
     * This method has on object of class edu.ntnu.idatt2001.pedropca.Unit as signature.
     * @param unit edu.ntnu.idatt2001.pedropca.Unit to be added into the army.
     */
    public void add(Unit unit){
        this.units.add(unit);
    }

    /**
     * Method that add a defined list of units into list UNITS and ergo into the army.
     * This method has a list of objects of class edu.ntnu.idatt2001.pedropca.Unit as signature.
     * @param units List of units to be added into army
     */
    public void addAll(List<Unit> units){
        this.units.addAll(units);
    }

    /**
     * Method that remove a defined unit from the list UNITS and ergo from the army.
     * @param unit edu.ntnu.idatt2001.pedropca.Unit to be removed from the army.
     */
    public void remove(Unit unit){
        this.units.remove(unit);
    }

    /**
     * Method that checks if the list UNITS has elements inside or in other words is not empty.
     * @return boolean true if army has unit or false if not.
     */
    public boolean hasUnit(){
        return (!this.units.isEmpty());
    }

    /**
     * Method that returns current list of units of the army
     * @return current list of units
     */
    public List<Unit> getAllUnits() {
        return this.units;
    }

    /**
     * Method that return a random unit from the list by using Random class.
     * Using av Random class is necessary to get a random integer that represents an index of the list,
     * and return the unit related to that index
     * @return edu.ntnu.idatt2001.pedropca.Unit related to the random index
     */
    public Unit getRandom(){
        // This method may throw a nullPointerException when list UNITS is empty
        // , but the method that call this method checks it already. That is why I think
        // to throw a NullPointerException is unnecessary.
        return this.units.get(new Random().nextInt(this.units.size()));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%90S",this.getName())).append("\n");
        sb.append(String.format("%10S|%10S|%10S|%10S|%10S|%10S|%10S|%10S|%10S"
                ,"NAME","HEALTH","ATTACK TYPE","ATTACK","ARMOR","ATTACK SPEED (PER SECOND)","HIT RATE", "CRITIC RATE", "CRITIC DAMAGE (%)")).append("\n");
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
                                       // to object of class edu.ntnu.idatt2001.pedropca.Army. This way makes more sense that use of HashCode
                                       // that use of HashCode to differentiate objects for me.
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
