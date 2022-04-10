package edu.ntnu.idatt2001.pedropca.wargames.util;

public class SingletonTerrain {
    private static SingletonTerrain singletonTerrain = new SingletonTerrain();
    private String terrain;

    private SingletonTerrain(){
    }

    public static SingletonTerrain getSingletonTerrain(){
        if(singletonTerrain==null) singletonTerrain = new SingletonTerrain();
        return singletonTerrain;
    }

    public void setForestAsTerrain(){
        terrain = EnumTerrain.Terrain.FOREST.name();
    }
    public void setHillsAsTerrain(){
        terrain = EnumTerrain.Terrain.HILL.name();
    }
    public void setPlainsAsTerrain(){
        terrain = EnumTerrain.Terrain.PLAINS.name();
    }
    public void setVolcanoAsTerrain(){
        terrain = EnumTerrain.Terrain.VOLCANO.name();
    }

    public String getTerrain(){
        return terrain;
    }
}
