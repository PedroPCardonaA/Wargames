package edu.ntnu.idatt2001.pedropca.wargames.util;

import edu.ntnu.idatt2001.pedropca.wargames.models.units.*;
import edu.ntnu.idatt2001.pedropca.wargames.models.units.magicUnits.MagicianUnit;
import edu.ntnu.idatt2001.pedropca.wargames.models.units.nonMagicUnits.CavalryUnit;
import edu.ntnu.idatt2001.pedropca.wargames.models.units.nonMagicUnits.CommanderUnit;
import edu.ntnu.idatt2001.pedropca.wargames.models.units.nonMagicUnits.InfantryUnit;
import edu.ntnu.idatt2001.pedropca.wargames.models.units.nonMagicUnits.RangedUnit;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UnitFactoryTest {

    @Nested
    class CreateUnitTest{
        @Nested
        class Positive{
            @Test
            void createAnInfantryUnit(){
                UnitFactory factory = new UnitFactory();
                Unit infantry = factory.createUnit(EnumUnitType.getUnitType("InfantryUnit"),"Infantry",100);
                assertTrue(infantry instanceof InfantryUnit);
            }
            @Test
            void createACavalryUnit(){
                UnitFactory factory = new UnitFactory();
                Unit infantry = factory.createUnit(EnumUnitType.getUnitType("CavalryUnit"),"Cavalry",100);
                assertTrue(infantry instanceof CavalryUnit);
            }
            @Test
            void createARangedUnit(){
                UnitFactory factory = new UnitFactory();
                Unit infantry = factory.createUnit(EnumUnitType.getUnitType("RangedUnit"),"Ranged",100);
                assertTrue(infantry instanceof RangedUnit);
            }
            @Test
            void createAMagicianUnit(){
                UnitFactory factory = new UnitFactory();
                Unit infantry = factory.createUnit(EnumUnitType.getUnitType("MagicianUnit"),"Magician",100);
                assertTrue(infantry instanceof MagicianUnit);
            }
            @Test
            void createACommanderUnit(){
                UnitFactory factory = new UnitFactory();
                Unit infantry = factory.createUnit(EnumUnitType.getUnitType("CommanderUnit"),"Commander",100);
                assertTrue(infantry instanceof CommanderUnit);
            }
        }
    }

    @Nested
    class CreateAListOfUnitsTest{
        @Nested
        class Positive{
            @Test
            void createAListWithInfantryUnits(){
                UnitFactory factory = new UnitFactory();
                List<Unit> units = factory.createAListOfUnits(EnumUnitType.getUnitType("InfantryUnit"),"Infantry",100,100);
                assertEquals(100,units.size());
                assertTrue(units.get(12) instanceof InfantryUnit);
            }
            @Test
            void createAListWithCavalryUnits(){
                UnitFactory factory = new UnitFactory();
                List<Unit> units = factory.createAListOfUnits(EnumUnitType.getUnitType("CavalryUnit"),"Cavalry",100,100);
                assertEquals(100,units.size());
                assertTrue(units.get(25) instanceof CavalryUnit);
            }
            @Test
            void createAListWithRangedUnits(){
                UnitFactory factory = new UnitFactory();
                List<Unit> units = factory.createAListOfUnits(EnumUnitType.getUnitType("RangedUnit"),"Ranged",100,100);
                assertEquals(100,units.size());
                assertTrue(units.get(23) instanceof RangedUnit);
            }
            @Test
            void createAListWithMagicianUnits(){
                UnitFactory factory = new UnitFactory();
                List<Unit> units = factory.createAListOfUnits(EnumUnitType.getUnitType("MagicianUnit"),"Magician",100,100);
                assertEquals(100,units.size());
                assertTrue(units.get(69) instanceof MagicianUnit);
            }
            @Test
            void createAListWithCommanderUnits(){
                UnitFactory factory = new UnitFactory();
                List<Unit> units = factory.createAListOfUnits(EnumUnitType.getUnitType("CommanderUnit"),"Commander",100,100);
                assertTrue(units.get(99) instanceof CommanderUnit);
                assertEquals(100,units.size());
            }
        }
        @Nested
        class Negative{
            @Test
            void creatingANegativeNumberOfUnits(){
                try {
                    UnitFactory factory = new UnitFactory();
                    factory.createAListOfUnits(EnumUnitType.getUnitType("CommanderUnit"),"Commander",100,-100);
                }catch (Exception e){
                    assertEquals("The number of units to add cannot be lower than 0",e.getMessage());
                }
            }
        }
    }

}