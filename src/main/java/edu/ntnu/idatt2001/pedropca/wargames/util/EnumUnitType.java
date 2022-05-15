package edu.ntnu.idatt2001.pedropca.wargames.util;

public enum EnumUnitType {
    INFANTRY("InfantryUnit"),
    HEALER("HealerUnit"),
    MAGICIAN("MagicianUnit"),
    CAVALRY("CavalryUnit"),
    COMMANDER("CommanderUnit"),
    RANGED("RangedUnit");

    private final String unitType;

    EnumUnitType(String unitType) {
        this.unitType = unitType;
    }

    public static EnumUnitType getUnitType(String nameString){
        for(EnumUnitType enumUnitType : values()){
            if(enumUnitType.unitType.equals(nameString)) return enumUnitType;
        }
        return null;
    }
}
