package edu.ntnu.idatt2001.pedropca.wargames.util;

/**
 * Enum class that controls the current terrain of the battle by limiting
 * the possible terrain in four options, forest, hill, plains and volcano.
 * Default is when the terrain has not been defined yet.
 * Class has a field terrain that contains an object of EnumTerrain.
 *
 * @author Pedro Cardona
 * @version 1.0
 * @since 1.0-SNAPSHOT
 */
public enum EnumTerrain {
    FOREST,
    HILL,
    PLAINS,
    VOLCANO,
    DEFAULT;
    private static EnumTerrain terrain = DEFAULT;

    public static void setForest(){
        terrain = FOREST;
    }
    public static void setHill(){
        terrain = HILL;
    }
    public static void setPlains(){
        terrain = PLAINS;
    }
    public static void setVolcano(){
        terrain = VOLCANO;
    }
    public static EnumTerrain getTerrain(){
        return terrain;
    }

}
