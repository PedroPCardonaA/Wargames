package edu.ntnu.idatt2001.pedropca.wargames.util;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EnumUnitTypeTest {

    @Nested
    class TestOfGetUnitTypeMethod{
        @Test
        void getInfantryUnitType(){
            assertEquals(EnumUnitType.INFANTRY,EnumUnitType.getUnitType("InfantryUnit"));
        }
        @Test
        void getHealerUnitType(){
            assertEquals(EnumUnitType.HEALER,EnumUnitType.getUnitType("HealerUnit"));
        }
        @Test
        void getMagicianUnitType(){
            assertEquals(EnumUnitType.MAGICIAN,EnumUnitType.getUnitType("MagicianUnit"));
        }
        @Test
        void getCavalryUnitType(){
            assertEquals(EnumUnitType.CAVALRY,EnumUnitType.getUnitType("CavalryUnit"));
        }
        @Test
        void getCommanderUnitType(){
            assertEquals(EnumUnitType.COMMANDER,EnumUnitType.getUnitType("CommanderUnit"));
        }
        @Test
        void getRangedUnitType(){
            assertEquals(EnumUnitType.RANGED,EnumUnitType.getUnitType("RangedUnit"));
        }
    }
}