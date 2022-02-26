package edu.ntnu.idatt2001.pedropca;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.security.spec.ECField;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ArmyTest {

    @Nested
    class TestingTheConstructor {
        @Test
        void makingAnArmyWithASupportedNameAndWithOutUnits() {
            try {
                Army army = new Army("Army");
            } catch (Exception e) {
                fail();
            }

        }

        @Test
        void makingAnArmyWithASupportedNameAndWithArmy() {
            try {
                List<Unit> unitMix = new ArrayList<>();
                for (int i =0;i<50;i++){
                    unitMix.add(new CavalryUnit("CAVALRY",100));
                    unitMix.add(new RangedUnit("Ranged",100));
                    unitMix.add(new InfantryUnit("Infantry",100));
                }
                Army army = new Army("Army",unitMix);
            } catch (Exception e) {
                fail();
            }
        }
        @Test
        void makingAnArmyWithOutNameAndWithOutUnits(){
            try {
                Army army = new Army("");
                fail();
            }catch (Exception e){
                assertEquals("The name of the army cannot be empty. Enter a name for the army.",e.getMessage());

            }
        }
        @Test
        void makingAnArmyWithOutNameAndWithArmy(){
            try {
                List<Unit> unitMix = new ArrayList<>();
                for (int i =0;i<50;i++){
                    unitMix.add(new CavalryUnit("CAVALRY",100));
                    unitMix.add(new RangedUnit("Ranged",100));
                    unitMix.add(new InfantryUnit("Infantry",100));
                }
                Army army = new Army("",unitMix);
                fail();
            } catch (Exception e) {
                assertEquals("The name of the army cannot be empty. Enter a name for the army.",e.getMessage());
            }
        }
        @Test
        void makingAnArmyWithNameAndWithArmyAsNull(){
            try {
                Army army = new Army("Army",null);
                fail();
            } catch (Exception e) {
                assertEquals("List of unit cannot be defined as null. Enter a correct unit list.",e.getMessage());
            }
        }
        @Test
        void makingAnArmyWithOutNameAndWithArmyAsNull(){
            try {
                Army army = new Army("",null);
                fail();
            } catch (Exception e) {
                assertEquals("The name of the army cannot be empty. Enter a name for the army.",e.getMessage());
            }
        }
    }

    @Nested
    class TestingAddMethod{
        @Test
        void addAInfantryUnitIntoTheArmy(){
            Army army = new Army( "Army");
            InfantryUnit infantryUnit = new InfantryUnit("Infantry",100);
            army.add(infantryUnit);
            assertTrue(army.getAllUnits().contains(infantryUnit));
        }
        @Test
        void addARangedUnitIntoTheArmy(){
            Army army = new Army( "Army");
            RangedUnit rangedUnit = new RangedUnit("Ranged",100);
            army.add(rangedUnit);
            assertTrue(army.getAllUnits().contains(rangedUnit));
        }
        @Test
        void addACavalryUnitIntoTheArmy(){
            Army army = new Army( "Army");
            CavalryUnit cavalryUnit = new CavalryUnit("Cavalry",100);
            army.add(cavalryUnit);
            assertTrue(army.getAllUnits().contains(cavalryUnit));
        }
        @Test
        void addACommanderUnitIntoTheArmy(){
            Army army = new Army( "Army");
            CommanderUnit commanderUnit = new CommanderUnit("Commander",100);
            army.add(commanderUnit);
            assertTrue(army.getAllUnits().contains(commanderUnit));
        }
    }
    @Nested
    class TestingOfMethodAddAll{
        @Test
        void AddAListOfInfantryUnits(){
            Army army = new Army( "Army");
            List<Unit> infantryList = new ArrayList<>();
            for(int i =0;i<50;i++){
                infantryList.add(new InfantryUnit("Infantry",100));
            }
            army.addAll(infantryList);
            assertTrue(army.getAllUnits().containsAll(infantryList));
        }
        @Test
        void AddAListOfRangedUnits(){
            Army army = new Army( "Army");
            List<Unit> rangedList = new ArrayList<>();
            for(int i =0;i<50;i++){
                rangedList.add(new RangedUnit("Ranged",100));
            }
            army.addAll(rangedList);
            assertTrue(army.getAllUnits().containsAll(rangedList));
        }
        @Test
        void AddAListOfCavalryUnits(){
            Army army = new Army( "Army");
            List<Unit> cavalryList = new ArrayList<>();
            for(int i =0;i<50;i++){
                cavalryList.add(new CavalryUnit("CAVALRY",100));
            }
            army.addAll(cavalryList);
            assertTrue(army.getAllUnits().containsAll(cavalryList));
        }
        @Test
        void AddAListOfMixedUnits(){
            Army army = new Army( "Army");
            List<Unit> mixedList = new ArrayList<>();
            for(int i =0;i<50;i++){
                mixedList.add(new CavalryUnit("CAVALRY",100));
                mixedList.add(new RangedUnit("Ranged",100));
                mixedList.add(new InfantryUnit("Infantry",100));
            }
            mixedList.add(new CommanderUnit("Commander",100));
            army.addAll(mixedList);
            assertTrue(army.getAllUnits().containsAll(mixedList));
            assertEquals(151,army.getAllUnits().size());
        }
    }
    @Nested
    class TestingMethodRemove{
        @Test
        void removeAInfantryUnitForArmy(){
            Army army = new Army( "Army");
            Unit infantry = new InfantryUnit("Infantry",100);
            army.add(infantry);
            army.add(new InfantryUnit("Infantry",100));
            army.add(new InfantryUnit("Infantry",100));
            army.remove(army.getRandom());
            assertEquals(2,army.getAllUnits().size());
            assertTrue(army.getAllUnits().contains(infantry));
        }
        @Test
        void removeARangedUnitForArmy(){
            Army army = new Army( "Army");
            Unit ranged = new RangedUnit("Ranged",100);
            army.add(new InfantryUnit("Infantry",100));
            army.add(ranged);
            army.add(new CavalryUnit("Cavalry", 100));
            army.remove(ranged);
            assertFalse(army.getAllUnits().contains(ranged));
        }
        @Test
        void removeACavalryUnitForArmy(){
            Army army = new Army( "Army");
            Unit cavalry = new CavalryUnit("Cavalry", 100);
            army.add(new InfantryUnit("Infantry",100));
            army.add(new RangedUnit("Ranged",100));
            army.add(cavalry);
            army.remove(cavalry);
            assertFalse(army.getAllUnits().contains(cavalry));
        }
    }

    @Nested
    class TestingMethodHasUnit{
        @Test
        void CheckIfAnArmyWithOutUnitHasUnit(){
            Army army = new Army("Army");
            assertFalse(army.hasUnit());
        }
        @Test
        void CheckIfAnArmyWithUnitHasUnit(){
            Army army = new Army("Army");
            for(int i =0;i<50;i++){
                army.add(new CavalryUnit("CAVALRY",100));
                army.add(new RangedUnit("Ranged",100));
                army.add(new InfantryUnit("Infantry",100));
            }
            assertTrue(army.hasUnit());
        }
    }

    @Nested
    class TestingMethodGetAllUnits{
        @Test
        void getAllUnitsOfAnArmyWithOutUnits(){
            Army army = new Army("Army");
            assertEquals(0,army.getAllUnits().size());
        }
        @Test
        void getAllUnitsOfAnArmyWithUnits(){
            Army army = new Army("Army");
            for(int i =0;i<50;i++){
                army.add(new CavalryUnit("CAVALRY",100));
                army.add(new RangedUnit("Ranged",100));
                army.add(new InfantryUnit("Infantry",100));
            }
            assertEquals(150,army.getAllUnits().size());
        }
    }


    @Nested
    class TestingOfTheMethodGetRandom{
        @Test
        void getRandomUnitFromArmy(){
            Army army = new Army( "Army");
            List<Unit> mixedList = new ArrayList<>();
            for(int i =0;i<50;i++){
                mixedList.add(new CavalryUnit("CAVALRY",100));
                mixedList.add(new RangedUnit("Ranged",100));
                mixedList.add(new InfantryUnit("Infantry",100));
            }
            mixedList.add(new CommanderUnit("Commander",100));
            army.addAll(mixedList);
            assertNotNull(army.getRandom());
        }
        @Test
        void getRandomInfantryUnitFromArmy() {
            Army army = new Army( "Army");
            List<Unit> mixedList = new ArrayList<>();
            for(int i =0;i<50;i++){
                mixedList.add(new InfantryUnit("Infantry",100));
            }
            army.addAll(mixedList);
            assertTrue(army.getAllUnits().contains(army.getRandom()));
            assertTrue(army.getRandom() instanceof InfantryUnit);
        }
        @Test
        void getRandomRangedUnitFromArmy(){
            Army army = new Army( "Army");
            List<Unit> mixedList = new ArrayList<>();
            for(int i =0;i<50;i++){
                mixedList.add(new RangedUnit("Ranged",100));
            }
            army.addAll(mixedList);
            assertTrue(army.getAllUnits().contains(army.getRandom()));
            assertTrue(army.getRandom() instanceof RangedUnit);
        }
        @Test
        void getRandomCavalryUnitFromArmy(){
            Army army = new Army( "Army");
            List<Unit> mixedList = new ArrayList<>();
            for(int i =0;i<50;i++){
                mixedList.add(new CavalryUnit("Cavalry",100));
            }
            army.addAll(mixedList);
            assertTrue(army.getAllUnits().contains(army.getRandom()));
            assertTrue(army.getRandom() instanceof CavalryUnit);
        }
    }
    @Nested
    class TestingOfMethodToString{
        @Test
        void getTheToStringOfABlankArmy(){
            Army army = new Army("Horde");
            String sb = String.format("%90S", "Horde") + "\n" +
                    String.format("%15S|%15S|%15S|%15S|%15S|%15S|%15S|%15S|%15S|"
                            , "NAME", "HEALTH", "ATTACK TYPE", "ATTACK", "ARMOR", "ATTACK SPEED /s", "HIT RATE", "CRITIC RATE", "CRITIC DAMAGE %") +
                    "\n";
            assertEquals(sb,army.toString());
        }
        @Test
        void getTheToStringOfAFullArmy(){
            Army army = new Army("Horde");
            army.add(new InfantryUnit("Infantry",100));
            army.add(new RangedUnit("Ranged",100));
            army.add(new CavalryUnit("Cavalry",100));
            String sb = String.format("%90S", "Horde") + "\n" +
                    String.format("%15S|%15S|%15S|%15S|%15S|%15S|%15S|%15S|%15S|"
                            , "NAME", "HEALTH", "ATTACK TYPE", "ATTACK", "ARMOR", "ATTACK SPEED /s", "HIT RATE", "CRITIC RATE", "CRITIC DAMAGE %") +
                    "\n" + new InfantryUnit("Infantry",100)+ "\n" + new RangedUnit("Ranged",100)
                    + "\n" + new CavalryUnit("Cavalry",100) + "\n";
            assertEquals(sb,army.toString());
        }
    }
    @Nested
    class  TestingTheMethodEquals{
        @Test
        void UseEqualsMethodWithTwoArmiesWithDifferentNames(){
            Army horde = new Army("Horde");
            Army alliance = new Army("Alliance");
            assertNotEquals(horde, alliance);
        }
        @Test
        void UseEqualsMethodWithTwoArmiesWithSameNames(){
            Army horde = new Army("Horde");
            Army Horde1 = new Army("Horde");
            assertEquals(horde, Horde1);
        }
        @Test
        void UseEqualsMethodWithAnArmyAndNull(){
            Army horde = new Army("Horde");
            assertNotEquals(horde, null);
        }
    }
    @Nested
    class TestingOfMethodHashCode{
        @Test
        void CheckHashCodeBetweenToArmyWithTheSameName() {
            Army horde = new Army("Horde");
            Army horde2 = new Army("Horde");
            assertEquals(horde.hashCode(),horde2.hashCode());
        }
        @Test
        void CheckHashCodeBetweenToArmyWithDifferentName() {
            Army horde = new Army("Horde");
            Army alliance = new Army("Alliance");
            assertNotEquals(horde.hashCode(),alliance.hashCode());
        }
    }

}