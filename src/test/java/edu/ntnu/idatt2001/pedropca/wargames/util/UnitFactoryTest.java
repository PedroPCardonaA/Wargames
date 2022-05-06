package edu.ntnu.idatt2001.pedropca.wargames.util;

import edu.ntnu.idatt2001.pedropca.wargames.models.units.*;
import edu.ntnu.idatt2001.pedropca.wargames.models.units.magicUnits.MagicianUnit;
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
                Unit infantry = factory.createUnit("InfantryUnit","Infantry",100);
                assertTrue(infantry instanceof InfantryUnit);
            }
            @Test
            void createACavalryUnit(){
                UnitFactory factory = new UnitFactory();
                Unit infantry = factory.createUnit("CavalryUnit","Cavalry",100);
                assertTrue(infantry instanceof CavalryUnit);
            }
            @Test
            void createARangedUnit(){
                UnitFactory factory = new UnitFactory();
                Unit infantry = factory.createUnit("RangedUnit","Ranged",100);
                assertTrue(infantry instanceof RangedUnit);
            }
            @Test
            void createAMagicianUnit(){
                UnitFactory factory = new UnitFactory();
                Unit infantry = factory.createUnit("MagicianUnit","Magician",100);
                assertTrue(infantry instanceof MagicianUnit);
            }
            @Test
            void createACommanderUnit(){
                UnitFactory factory = new UnitFactory();
                Unit infantry = factory.createUnit("CommanderUnit","Commander",100);
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
                List<Unit> units = factory.createAListOfUnits("InfantryUnit","Infantry",100,100);
                assertEquals(100,units.size());
                assertTrue(units.get(12) instanceof InfantryUnit);
            }
            @Test
            void createAListWithCavalryUnits(){
                UnitFactory factory = new UnitFactory();
                List<Unit> units = factory.createAListOfUnits("CavalryUnit","Cavalry",100,100);
                assertEquals(100,units.size());
                assertTrue(units.get(25) instanceof CavalryUnit);
            }
            @Test
            void createAListWithRangedUnits(){
                UnitFactory factory = new UnitFactory();
                List<Unit> units = factory.createAListOfUnits("RangedUnit","Ranged",100,100);
                assertEquals(100,units.size());
                assertTrue(units.get(23) instanceof RangedUnit);
            }
            @Test
            void createAListWithMagicianUnits(){
                UnitFactory factory = new UnitFactory();
                List<Unit> units = factory.createAListOfUnits("MagicianUnit","Magician",100,100);
                assertEquals(100,units.size());
                assertTrue(units.get(69) instanceof MagicianUnit);
            }
            @Test
            void createAListWithCommanderUnits(){
                UnitFactory factory = new UnitFactory();
                List<Unit> units = factory.createAListOfUnits("CommanderUnit","Commander",100,100);
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
                    factory.createAListOfUnits("CommanderUnit","Commander",100,-100);
                }catch (Exception e){
                    assertEquals("The number of units to add cannot be lower than 0",e.getMessage());
                }
            }
        }
    }
}