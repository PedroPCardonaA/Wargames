package edu.ntnu.idatt2001.pedropca.wargames.util;

import edu.ntnu.idatt2001.pedropca.wargames.models.Army;

import java.util.ArrayList;
import java.util.List;

/**
 * Singleton class that wil help the communication between controller classes.
 * This class will share a list of Armies that will be used in the different scenes of the gui,
 * and it has two fields a list fo armies and an instance of itself.
 * @author Pedro Cardona
 * @version 1.0
 * @since 1.0-SNAPSHOT
 */
public class SingletonArmies {
    private static SingletonArmies singletonArmies = new SingletonArmies();
    private final List<Army> listOfArmies;

    /**
     * Private constructor of this class that only can be called by the method
     * getSingletonArmies to prevent the creation of several instances at the same time.
     */
    private SingletonArmies(){
        listOfArmies = new ArrayList<>();
    }

    /**
     * Method that check the content of the field singletonArmies and make a new instance if and only if,
     * the singletonArmies is not defined yet. This prevents the creation of several instance of the singleton
     * at the time. Method returns singletonArmies at the end.
     * @return SingletonArmies - The unique instance of singleton at the time.
     */
    public static SingletonArmies getSingletonArmies(){
        if(singletonArmies== null) singletonArmies = new SingletonArmies();
        return singletonArmies;
    }

    /**
     * Method that returns an army of the armies' list by using the method get
     * from list and a defined index that defines the position of the army in the list.
     * @param index int - index of the army.
     * @return Army - an army from the list of the army.
     */
    public Army getArmy(int index){
        return listOfArmies.get(index);
    }

    /**
     * Method that add a new army into the list of armies.
     * @param army Army - army to be added into the list of armies.
     */
    public void putArmy(Army army){
        this.listOfArmies.add(army);
    }

    /**
     * Method that remove a defined army from the list of armies.
     * @param army Army - army to be removed from the list of armies.
     */
    public void removeArmy(Army army){
        this.listOfArmies.remove(army);
    }

    /**
     * Method that remove all the armies from the list of armies.
     */
    public void setEmptySingleton(){
        this.listOfArmies.clear();
    }
}
