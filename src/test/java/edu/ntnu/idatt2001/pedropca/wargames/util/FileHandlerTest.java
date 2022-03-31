package edu.ntnu.idatt2001.pedropca.wargames.util;

import edu.ntnu.idatt2001.pedropca.wargames.models.Army;
import edu.ntnu.idatt2001.pedropca.wargames.models.units.CavalryUnit;
import edu.ntnu.idatt2001.pedropca.wargames.models.units.CommanderUnit;
import edu.ntnu.idatt2001.pedropca.wargames.models.units.InfantryUnit;
import edu.ntnu.idatt2001.pedropca.wargames.models.units.RangedUnit;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class FileHandlerTest {
    @Nested
    class TestingMethodReadAFileArmy{
        @Nested
        class Positive{
            @Test
            void readingAFileWithAMixedArmy() throws Exception {
                Army army = FileHandler.readArmy("src/main/resources/Armies/TestingArmy.csv");
                assertEquals("Army",army.getName());
                assertEquals(151,army.getAllUnits().size());
                assertEquals(1,army.getCommanderUnits().size());
            }
            @Test
            void readingAFIleWithAnUnitlessArmy() throws Exception {
                Army army = FileHandler.readArmy("src/main/resources/Armies/TestingArmyWithoutUnits.csv");
                assertEquals("Army",army.getName());
                assertEquals(0,army.getAllUnits().size());
            }
        }
        @Nested
        class Negative{
            @Test
            void tryReadAnNonExistentArmy(){
                try {
                    Army army = FileHandler.readArmy("src/main/resources/Armies/TestingArmyWithoutUnitsNonExistent.csv");
                    fail();
                }catch (Exception e) {}
            }
            @Test
            void tryReadANotCSVArmyFile(){
                try {
                    Army army = FileHandler.readArmy("src/main/resources/Armies/Army.txt");
                    fail();
                }catch (Exception e) {
                    assertEquals("The defined file is not a .csv (Comma separated value). Define " +
                            "A correct file.",e.getMessage());
                }
            }
            @Test
            void tryReadAFileWIthOutPath(){
                try {
                    Army army = FileHandler.readArmy("");
                    fail();
                }catch (Exception e) {
                    assertEquals("The path of file cannot be empty. Define a path of the file",e.getMessage());
                }
            }
            @Test
            void tryReadACorruptedArmyFileWithoutName(){
                try {
                    Army army = FileHandler.readArmy("src/main/resources/Armies/ArmyWithOutName.csv");
                    fail();
                } catch (Exception e){
                    assertEquals("The data of the file was corrupted or is not compatible" +
                                    "with this program. \nThe error was: " + "The name of the army cannot be empty. Enter a name for the army. In the line 1 of the file."
                            ,e.getMessage());
                }
            }

            @Test
            void tryReadACorruptedArmyFileThatMissAUnit(){
                assertThrows(Exception.class, ()->{
                    Army army = FileHandler.readArmy("src/main/resources/Armies/ArmyThatMissesAUnit.csv");
                });
            }
            @Test
            void tryReadACorruptedArmyFileThatDoesNotContainsStringAndNotInt(){
                assertThrows(IllegalArgumentException.class,()->{
                    Army army = FileHandler.readArmy("src/main/resources/Armies/ArmyWithCorruptedUnits.csv");
                });
            }
            @Test
            void tryReadACorruptedArmyFileThatDoesNotHaveUnitType(){
                try {
                    Army army = FileHandler.readArmy("src/main/resources/Armies/ArmyWithoutUnitType.csv");
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }
                assertThrows(IllegalArgumentException.class,()->{
                    Army army = FileHandler.readArmy("src/main/resources/Armies/ArmyWithoutUnitType.csv");
                });
            }
        }
    }

    @Nested
    class TestingMethodCreateAFileArmy{
        @Nested
        class positiveTesting{
            @Test
            void savingAFileOfAMixedArmy() throws IOException {
                Army army = new Army( "Army");
                for(int i =0;i<50;i++){
                    army.add(new CavalryUnit("Raider",100));
                    army.add(new RangedUnit("Ranged",100));
                    army.add(new InfantryUnit("Footman",100));
                }
                army.add(new CommanderUnit("King",100));
                //This part format works on Windows. Not sure, if it will work on MAC or Linux
                FileHandler.WriteAFile(army,"src/main/resources/Armies","TestingArmyWithAMixedArmy");
            }
            @Test
            void savingAFileOfAnArmyWithOutUnits() throws IOException{
                Army blankArmy = new Army("Army");
                FileHandler.WriteAFile(blankArmy,"src/main/resources/Armies","TestingArmyWithoutUnits");
            }
            @Test
            void overWriteAPreviouslyFileWithANewArmy(){
                try {
                    Army firstArmy = new Army("First Army");
                    FileHandler.WriteAFile(firstArmy,"src/main/resources/Armies","OverWrittenArmy");
                    Army controlArmy = FileHandler.readArmy("src/main/resources/Armies/"+"OverWrittenArmy.csv");
                    assertEquals("First Army",controlArmy.getName());
                    Army secondArmy = new Army("Second Army");
                    FileHandler.WriteAFile(secondArmy,"src/main/resources/Armies","OverWrittenArmy");
                    controlArmy = FileHandler.readArmy("src/main/resources/Armies/"+"OverWrittenArmy.csv");
                    assertEquals("Second Army",controlArmy.getName());
                }catch (Exception e){
                    fail();
                }
            }
            @Test
            void savingArmyWithSpecialCharacterInTheName(){
                try {
                    Army armyWithAFunnyName = new Army("Funny@£$€/");
                    FileHandler.WriteAFile(armyWithAFunnyName,"src/main/resources/Armies","Funny@£$€");
                }catch (Exception e){
                    fail();
                }
            }
        }
        @Nested
        class Negative{
            @Test
            void savingAnArmyWithOutADefinedPath(){
                try {
                    Army armyWithoutDefinedPath = new Army("Funny@£$€/");
                    FileHandler.WriteAFile(armyWithoutDefinedPath,"","Funny@£$€");
                    fail();
                } catch (IOException e) {
                    assertEquals("The path of file cannot be empty. Define a path of the file.",e.getMessage());
                }
            }
            void savingAnArmyWithOutADefinedFileName(){
                try {
                    Army armyWithoutADefinedName = new Army("Funny@£$€/");
                    FileHandler.WriteAFile(armyWithoutADefinedName,"src/main/resources/Armies/","");
                    fail();
                } catch (IOException e) {
                    assertEquals("The name of file cannot be empty. Define the name of the file.",e.getMessage());
                }
            }
            @Test
            void savingAnArmyWithADotInTheName(){
                try {
                    Army armyWithADotInTheName = new Army("Funny@£$€/");
                    FileHandler.WriteAFile(armyWithADotInTheName,"src/main/resources/Armies/","Army.Test");
                    fail();
                } catch (IOException e) {
                    assertEquals("The name of the file cannot contain a '.'. Define a correct name.",e.getMessage());
                }
            }
            @Test
            void savingAnArmyWithASlashInTheName(){
                try {
                    Army armyWithASlashInTheName = new Army("Funny@£$€/");
                    FileHandler.WriteAFile(armyWithASlashInTheName,"src/main/resources/Armies/","Army/test");
                    fail();
                } catch (IOException e) {
                    assertEquals("The name of the file cannot contain a '/'. Define a correct name.",e.getMessage());
                }
            }
            @Test
            void savingAnArmyWithABackSlashInTheName(){
                try {
                    Army armyWithABackSlashInTheName = new Army("Funny@£$€/");
                    FileHandler.WriteAFile(armyWithABackSlashInTheName,"src/main/resources/Armies/","Army\\test");
                    fail();
                } catch (IOException e) {
                    assertEquals("The name of the file cannot contain a '\\'. Define a correct name.",e.getMessage());
                }
            }
        }
    }
}