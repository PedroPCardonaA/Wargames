package edu.ntnu.idatt2001.pedropca.wargames.util;

import java.util.Arrays;

/**
 * Enum class that contains the different types of units that "Wargames" application support til now.
 * The Purpose of this class is to limit the possible units that can be made from the factory class UnitFactory.
 *
 * @author Pedro Cardona
 * @version 1.0
 * @since 1.0-SNAPSHOT
 */
public enum EnumUnitType {
    INFANTRY("InfantryUnit"),
    HEALER("HealerUnit"),
    MAGICIAN("MagicianUnit"),
    CAVALRY("CavalryUnit"),
    COMMANDER("CommanderUnit"),
    RANGED("RangedUnit");

    private final String unitType;

    /**
     * Constructor of the enum class, that define the String field unitType based on the signature.
     * @param unitType String - Unit type
     */
    EnumUnitType(String unitType) {
        this.unitType = unitType;
    }

    /**
     * Static method that compare the actual string contained into unitType field, with
     * a sting defined in the signature. If they are equals, method will return the correspondent
     * enum value with the correct unit type.
     * @param nameString String - name of the unit type to be tested.
     * @return EnumUnitType value- the correspondent enum value with the correct unit type.
     */
    public static EnumUnitType getUnitType(String nameString){
        return Arrays.stream(EnumUnitType.class.getEnumConstants()).
                filter(enumUnitType -> enumUnitType.unitType.equals(nameString)).findFirst().orElse(null);
    }
}
