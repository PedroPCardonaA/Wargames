package edu.ntnu.idatt2001.pedropca.wargames.util;

import edu.ntnu.idatt2001.pedropca.wargames.models.Army;
import edu.ntnu.idatt2001.pedropca.wargames.models.units.*;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class FileArmyHandlerTest {

    @Nested
    class WriteArmyTest {
        @Nested
        class Positive {
            @Test
            void saveAnArmyWithUnitsInACsvFile(){
                try {
                    Army army = new Army( "Army");
                    List<Unit> mixedList = new ArrayList<>();
                    for(int i =0;i<50;i++){
                        mixedList.add(new CavalryUnit("CAVALRY",100));
                        mixedList.add(new RangedUnit("Ranged",100));
                        mixedList.add(new InfantryUnit("Infantry",100));
                    }
                    mixedList.add(new CommanderUnit("Commander",100));
                    army.addAll(mixedList);
                    FileArmyHandler.writeAFile(army, "src/main/resources/Armies","ArmyWithUnits.csv");
                }catch (Exception e){
                    fail();
                }
            }
            @Test
            void saveAnArmyWithoutUnitsInACsvFile(){
                try {
                    FileArmyHandler.writeAFile(new Army("Army"), "src/main/resources/Armies","ArmyWithoutUnits.csv");
                }catch (Exception e){
                    fail();
                }
            }
            @Test
            void saveAnArmyWithUnitsInATxtFile(){
                try {
                    Army army = new Army( "Army");
                    List<Unit> mixedList = new ArrayList<>();
                    for(int i =0;i<50;i++){
                        mixedList.add(new CavalryUnit("CAVALRY",100));
                        mixedList.add(new RangedUnit("Ranged",100));
                        mixedList.add(new InfantryUnit("Infantry",100));
                    }
                    mixedList.add(new CommanderUnit("Commander",100));
                    army.addAll(mixedList);
                    FileArmyHandler.writeAFile(army, "src/main/resources/Armies","ArmyWithUnits.txt");
                }catch (Exception e){
                    fail();
                }
            }
            @Test
            void saveAnArmyWithoutUnitsInTxtFile(){
                try {
                    FileArmyHandler.writeAFile(new Army("Army"), "src/main/resources/Armies","ArmyWithoutUnits.txt");
                }catch (Exception e){
                    fail();
                }
            }
            @Test
            void saveAnArmyWithAFunnyName(){
                try {
                    FileArmyHandler.writeAFile(new Army("XDFGSJSJYD¤#¤##2&%¤"), "src/main/resources/Armies","XDFGSJSJYD¤#¤##2&%¤");
                }catch (Exception e){
                    fail();
                }
            }
        }
        @Nested
        class Negative {
            @Test
            void tryingToSaveAnArmyWithOutPath(){
                try {
                    FileArmyHandler.writeAFile(new Army("Army"), "","XDFGSJSJYD¤#¤##2&%¤");
                    fail();
                }catch (Exception e){
                    assertEquals("The path of file cannot be empty. Define a path of the file.",e.getMessage());
                }
            }
            @Test
            void tryingToSaveAnArmyWithOutName(){
                try {
                    FileArmyHandler.writeAFile(new Army("Army"), "src/main/resources/Armies","");
                    fail();
                }catch (Exception e){
                    assertEquals("The name of file cannot be empty. Define the name of the file.",e.getMessage());
                }
            }
            @Test
            void tryingToSaveAnArmyWithADotInTheName(){
                try {
                    FileArmyHandler.writeAFile(new Army("File.Text"), "src/main/resources/Armies","File.Text");
                    fail();
                }catch (Exception e){
                    assertEquals("The name of the file cannot contain a '.'. Define a correct name.",e.getMessage());
                }
            }
            @Test
            void tryingToSaveAnArmyWithASlashInTheName(){
                try {
                    FileArmyHandler.writeAFile(new Army("File/Text"), "src/main/resources/Armies","File/Text");
                    fail();
                }catch (Exception e){
                    assertEquals("The name of the file cannot contain a '/'. Define a correct name.",e.getMessage());
                }
            }
            @Test
            void tryingToSaveAnArmyWithADoubleBackSlashInTheName(){
                try {
                    FileArmyHandler.writeAFile(new Army("File\\Text"), "src/main/resources/Armies","File\\Text");
                    fail();
                }catch (Exception e){
                    assertEquals("The name of the file cannot contain a '\\'. Define a correct name.",e.getMessage());
                }
            }
        }
    }
    @Nested
    class ReadArmyTest{
        @Nested
        class Positive{
            @Test
            void readAnArmyWithUnitsFromACsvFile(){
                try {
                    Army army = new Army( "Army");
                    List<Unit> mixedList = new ArrayList<>();
                    for(int i =0;i<50;i++){
                        mixedList.add(new CavalryUnit("CAVALRY",100));
                        mixedList.add(new RangedUnit("Ranged",100));
                        mixedList.add(new InfantryUnit("Infantry",100));
                    }
                    mixedList.add(new CommanderUnit("Commander",100));
                    army.addAll(mixedList);
                    FileArmyHandler.writeAFile(army, "src/main/resources/Armies","ArmyWithUnits.csv");
                    Army readFile =FileArmyHandler.readArmy("src/main/resources/Armies/ArmyWithUnits.csv");
                    assertEquals(army.getName(),readFile.getName());
                    assertEquals(army.getAllUnits().size(),readFile.getAllUnits().size());
                }catch (Exception e){
                    fail();
                }
            }
            @Test
            void readAnArmyWithoutUnitsFromACsvFile(){
                try {
                    Army army = new Army( "Army");
                    FileArmyHandler.writeAFile(army, "src/main/resources/Armies","ArmyWithoutUnits.csv");
                    Army readFile =FileArmyHandler.readArmy("src/main/resources/Armies/ArmyWithoutUnits.csv");
                    assertEquals(army.getName(),readFile.getName());
                    assertEquals(army.getAllUnits().size(),readFile.getAllUnits().size());
                }catch (Exception e){
                    fail();
                }
            }
            @Test
            void readAnArmyWithUnitsFromATxtFile(){
                try {
                    Army army = new Army( "Army");
                    List<Unit> mixedList = new ArrayList<>();
                    for(int i =0;i<50;i++){
                        mixedList.add(new CavalryUnit("CAVALRY",100));
                        mixedList.add(new RangedUnit("Ranged",100));
                        mixedList.add(new InfantryUnit("Infantry",100));
                    }
                    mixedList.add(new CommanderUnit("Commander",100));
                    army.addAll(mixedList);
                    FileArmyHandler.writeAFile(army, "src/main/resources/Armies","ArmyWithUnits.txt");
                    Army readFile =FileArmyHandler.readArmy("src/main/resources/Armies/ArmyWithUnits.txt");
                    assertEquals(army.getName(),readFile.getName());
                    assertEquals(army.getAllUnits().size(),readFile.getAllUnits().size());
                }catch (Exception e){
                    fail();
                }
            }
            @Test
            void readAnArmyWithoutUnitsFromATxtFile(){
                try {
                    Army army = new Army( "Army");
                    FileArmyHandler.writeAFile(army, "src/main/resources/Armies","ArmyWithoutUnits.txt");
                    Army readFile =FileArmyHandler.readArmy("src/main/resources/Armies/ArmyWithoutUnits.txt");
                    assertEquals(army.getName(),readFile.getName());
                    assertEquals(army.getAllUnits().size(),readFile.getAllUnits().size());
                }catch (Exception e){
                    fail();
                }
            }
        }

        @Nested
        class Negative{
            @Test
            void tryingReadAnArmyWithoutPath(){
                try {
                    FileArmyHandler.readArmy("");
                    fail();
                }catch (Exception e){
                    assertEquals("The path of file cannot be empty. Define a path of the file",e.getMessage());
                }
            }
            @Test
            void tryingReadAnArmyFromAOtherFileType(){
                try {
                    FileArmyHandler.readArmy("src/main/resources/Armies/ArmyWithoutUnits.docx");
                    fail();
                }catch (Exception e){
                    assertEquals("The defined file is not a .csv (Comma separated value) or .txt (Serializable.)" +
                            " Define " + "A correct file.",e.getMessage());
                }
            }
        }
    }
}