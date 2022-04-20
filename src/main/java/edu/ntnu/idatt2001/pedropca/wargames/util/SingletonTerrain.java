package edu.ntnu.idatt2001.pedropca.wargames.util;

/**
 * Singleton class that will help the communication of data from the GUI to classes in the Unit's hierarchy.
 * This class will share the current terrain to the units. The terrains are in the form of strings.
 * Class has two fields a string and an instance of itself.
 * @author Pedro Cardona
 * @version 1.0
 * @since 1.0-SNAPSHOT
 */
public class SingletonTerrain {

    //I know that this enum is very overkill, but I really want to implement
    // an enum object in my project. I know that the best solution is only define the terrain
    // directly in methods setTerrain by using strings but using enum is pretty cool.
    enum Terrain{
        FOREST,
        HILL,
        PLAINS,
        VOLCANO;
    }

    private static SingletonTerrain singletonTerrain = new SingletonTerrain();
    private String terrain;

    /**
     * Private constructor of this class that only can be called by the method
     * getSingletonArmies to prevent the creation of several instances at the same time.
     */
    private SingletonTerrain(){
    }

    /**
     * Method that check the content of the field singletonTerrain and make a new instance if and only if,
     * the singletonArmies is not defined yet. This prevents the creation of several instance of the singleton
     * at the time. Method returns singletonArmies at the end.
     * @return SingletonTerrain - The unique instance of singleton at the time.
     */
    public static SingletonTerrain getSingletonTerrain(){
        if(singletonTerrain==null) singletonTerrain = new SingletonTerrain();
        return singletonTerrain;
    }

    /**
     * Method that define forest as terrain of the battle.
     */
    public void setForestAsTerrain(){
        terrain = Terrain.FOREST.name();
    }

    /**
     * Method that define hill as terrain of the battle.
     */
    public void setHillsAsTerrain(){
        terrain = Terrain.HILL.name();
    }

    /**
     * Method that define plains as terrain of the battle.
     */
    public void setPlainsAsTerrain(){
        terrain = Terrain.PLAINS.name();
    }

    /**
     * Method that define volcano as terrain of the battle.
     */
    public void setVolcanoAsTerrain(){
        terrain = Terrain.VOLCANO.name();
    }

    public String getTerrain(){
        return terrain;
    }
}
