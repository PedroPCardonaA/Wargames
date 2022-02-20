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
    private final String NAME;
    private final List<Unit> UNITS;

    /**
     * Constructor of class edu.ntnu.idatt2001.pedropca.Army that has a String variable and a list of object units as signature.
     * This constructor will be used when the list of units is defined before the army object.
     * @param NAME name of the army
     * @param UNITS list of units
     * @throws IllegalArgumentException this constructor may throw illegal argument exception
     * if the string variable name is empty or if the units list is defined as null
     */

    public Army(String NAME, List<Unit> UNITS) throws IllegalArgumentException{
        if(NAME.isEmpty()) throw new IllegalArgumentException("The name of the army cannot be empty. Enter a name for the army.");
        if(UNITS == null) throw new IllegalArgumentException("List of unit cannot be defined as null. Enter a correct unit list.");
        this.NAME = NAME.trim();
        this.UNITS = UNITS;
    }

    /**
     * Constructor of class edu.ntnu.idatt2001.pedropca.Army that has a String variable as signature.
     * This constructor will be used when the list of units is not defined before the army object.
     * @param NAME name of the army
     * @throws IllegalArgumentException this constructor may throw illegal argument exception
     * if the string variable name is empty
     */

    public Army(String NAME){
        if(NAME.isEmpty()) throw new IllegalArgumentException("The name of the army cannot be empty. Enter a name for the army.");
        this.NAME=NAME;
        UNITS = new ArrayList<>();
    }

    public String getNAME() {
        return NAME;
    }

    /**
     * Method that add a defined unit into list UNITS and ergo into the army.
     * This method has on object of class edu.ntnu.idatt2001.pedropca.Unit as signature.
     * @param unit edu.ntnu.idatt2001.pedropca.Unit to be added into the army.
     */
    public void add(Unit unit){
        this.UNITS.add(unit);
    }

    /**
     * Method that add a defined list of units into list UNITS and ergo into the army.
     * This method has a list of objects of class edu.ntnu.idatt2001.pedropca.Unit as signature.
     * @param units List of units to be added into army
     */
    public void addAll(List<Unit> units){
        this.UNITS.addAll(units);
    }

    /**
     * Method that remove a defined unit from the list UNITS and ergo from the army.
     * @param unit edu.ntnu.idatt2001.pedropca.Unit to be removed from the army.
     */
    public void remove(Unit unit){
        this.UNITS.remove(unit);
    }

    /**
     * Method that checks if the list UNITS has elements inside or in other words is not empty.
     * @return boolean true if army has unit or false if not.
     */
    public boolean hasUnit(){
        return (!this.UNITS.isEmpty());
    }

    /**
     * Method that returns current list of units of the army
     * @return current list of units
     */
    public List<Unit> getAllUnits() {
        return this.UNITS;
    }

    /**
     * Method that return a random unit from the list by using Random class.
     * Using av Random class is necessary to get a random integer that represents an index of the list,
     * and return the unit related to that index
     * @return edu.ntnu.idatt2001.pedropca.Unit related to the random index
     */
    public Unit getRandom(){
        Random random = new Random();
        return this.UNITS.get(random.nextInt(this.UNITS.size()));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%90S",this.getNAME())).append("\n");
        sb.append(String.format("%20S|%20S|%20S|%20S|%20S|%20S|%20S|%20S|%20S"
                ,"NAME","HEALTH","ATTACK TYPE","ATTACK","ARMOR","ATTACK SPEED (PER SECOND)","HIT RATE", "CRITIC RATE", "CRITIC DAMAGE (%)")).append("\n");
        for(Unit unit:UNITS){
            sb.append(unit.toString()).append("\n");
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Army)) return false;
        Army army = (Army) o;
        return NAME.equals(army.NAME); // I defined string variable NAME as key value to differentiate
                                       // to object of class edu.ntnu.idatt2001.pedropca.Army. This way makes more sense that use of HashCode
                                       // to differentiate objects for me.
    }

    @Override
    public int hashCode() {
        return Objects.hash(NAME);
    }
}
