package edu.ntnu.idatt2001.pedropca.wargames.util;

import edu.ntnu.idatt2001.pedropca.wargames.models.Army;

import java.util.List;

public class SingletonArmies {
    private static SingletonArmies singletonArmies = new SingletonArmies();
    private List<Army> listOfArmies;

    private SingletonArmies(){}

    public static SingletonArmies getSingletonArmies(){
        if(singletonArmies== null) singletonArmies = new SingletonArmies();
        return singletonArmies;
    }

    public Army getArmy(int index){
        return listOfArmies.get(index);
    }

    public void putArmy(Army army){
        this.listOfArmies.add(army);
    }

    public void removeArmy(Army army){
        this.listOfArmies.remove(army);
    }

    public void emptyGlobe(){
        this.listOfArmies.clear();
    }
}
