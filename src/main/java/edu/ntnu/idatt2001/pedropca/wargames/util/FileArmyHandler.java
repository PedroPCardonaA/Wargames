package edu.ntnu.idatt2001.pedropca.wargames.util;
import edu.ntnu.idatt2001.pedropca.wargames.models.Army;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * Static fileHandler class that is used to read local files that contains a
 * representation of an instance of the class Army. Files can be CSV ( Comma separated value)
 * or TXT files that holds a serializable of the army.
 * @author Pedro Cardona
 * @version 1.0
 * @since 1.0-SNAPSHOT
 */
public class FileArmyHandler {

    /**
     * Static method that returns an object of the class army from a file by calling the
     * help method readArmyFromSerializable if the files has txt extension or
     * help method readArmyFromCsv if the file ha csv extension.
     * @param pathOfFile String - Path of the file
     * @return Army - an instance of army class from the file
     * @throws IOException if it happens an error by reading the file
     * @throws ClassNotFoundException if the txt file does not contain data from a serializable class
     * @throws IllegalArgumentException if the data in csv is corrupted.
     */
    public static Army readArmy(String pathOfFile) throws IOException, ClassNotFoundException, IllegalArgumentException{
        if(pathOfFile.isBlank()){
            throw new IOException("The path of file cannot be blank. Define a path of the file");
        }
        if(!pathOfFile.toLowerCase().endsWith(".csv") && !pathOfFile.toLowerCase().endsWith(".txt")){
            throw new IOException("The defined file is not a .csv (Comma separated value) or .txt (Serializable.)" +
                    " Define " + "A correct file.");
        }
        if(pathOfFile.toLowerCase().endsWith(".csv")) return readArmyFromCsv(pathOfFile);
        else return readArmyFromSerializable(pathOfFile);
    }

    /**
     * Help method that return an instance of army class by reading a txt file
     * that contains a serialized army.
     * @param pathOfFile String - Path of the file
     * @return Army - Army from the file.
     * @throws IOException if it happens an error by reading the file
     * @throws ClassNotFoundException if the txt file does not contain data from a serializable class
     */
    private static Army readArmyFromSerializable(String pathOfFile) throws IOException, ClassNotFoundException {
        try (FileInputStream fis = new FileInputStream(pathOfFile); ObjectInputStream ois = new ObjectInputStream(fis)) {
            return (Army) ois.readObject();
        } catch (Exception e) {
            throw new IOException("Reading the army from the txt file:" + pathOfFile + " failed!" + " It was corrupted");
        }

    }

    /**
     * Help method that returns an instance of the army class by reading a csv file.
     * @param pathOfFile String - Path of the file
     * @return Army - an instance of army class from the file
     * @throws IllegalArgumentException if the data in csv is corrupted.
     */
    private static Army readArmyFromCsv(String pathOfFile) throws  IOException{
        Army readArmy = new Army("readArmy");
        UnitFactory factory = new UnitFactory();
        Path file = Path.of(pathOfFile);
        final int[] numberOfLine={1};
        try(Stream<String> lines = Files.lines(file)) {
            lines.forEach(line->{
                String [] info = line.split(",");
                if(info.length==1) readArmy.setName(info[0]);
                else readArmy.add(factory.createUnit(Objects.requireNonNull(EnumUnitType.getUnitType(info[0])),info[1],Integer.parseInt(info[2]),Integer.parseInt(info[3]),Integer.parseInt(info[4]),Integer.parseInt(info[5]),Integer.parseInt(info[6]),Integer.parseInt(info[7]),Integer.parseInt(info[8])));
                numberOfLine[0]++;
            });
            return readArmy;
        }catch (Exception e){
            throw new IOException("The data of the file was corrupted or is not compatible" +
                    " with this program. \nThe error was: " + e.getMessage() +" In the line " +numberOfLine[0] +" of the file.");
        }
    }

    /**
     * Static method that save an instance of the class army, and all the units,
     * as a csv file if the name of the file ends with csv (Commas separated value) extension
     * or as a txt (Serialized) if the name of the file ends with txt extension, to
     * be opened and user later by this application.
     * This method will be called from the WarGamesApp and combine with class FileChooser of javaFx.
     * @param army Army -  Instance of the class army to be saved.
     * @param pathOfFile String - Defined path of the new file.
     * @param fileName String - Defined name of the new file.
     * @throws IOException If it is an error by manipulating the new file.
     */
    public static void writeAFile(Army army ,String pathOfFile, String fileName) throws IOException {
        boolean b = !fileName.toLowerCase().endsWith(".csv") && !fileName.toLowerCase().endsWith(".txt");
        if (pathOfFile.isBlank())
            throw new IOException("The path of file cannot be blank. Define a path of the file.");

        if (fileName.isBlank())
            throw new IOException("The name of file cannot be blank. Define the name of the file.");

        if (fileName.contains(".") && b)
            throw new IOException("The name of the file cannot contain a '.'. Define a correct name.");

        if (fileName.contains("/"))
            throw new IOException("The name of the file cannot contain a '/'. Define a correct name.");

        if (fileName.contains("\\"))
            throw new IOException("The name of the file cannot contain a '\\'. Define a correct name.");

        if (fileName.contains("?"))
            throw new IOException("The name of the file cannot contain a '?'. Define a correct name.");

        if (b) fileName += ".csv";

        if (fileName.toLowerCase().endsWith(".csv")) {
            WriteAFileAsCsv(army, pathOfFile, fileName);
        } else {
            writeAFileWithSerializable(army, pathOfFile, fileName);
        }
    }

    /**
     * Help method that stores the name and all the units into a csv file in a defined
     * location in the computer, to be opened, read and used later.
     * @param armyToWrite Army -  Instance of the class army to be saved.
     * @param pathOfFile String - Defined path of the new file.
     * @param fileName String - Defined name of the new file.
     * @throws IOException If it is an error by manipulating the new file.
     */
    public static void WriteAFileAsCsv(Army armyToWrite, String pathOfFile, String fileName) throws IOException{
        Path path = Paths.get(pathOfFile+"\\"+fileName);
        List<String> data  = new ArrayList<>();
        if(Files.exists(path)) throw new IOException("This file already exists. Try with other name");
        else {
            Path finishDocument = Files.createFile(path);
            data.add(armyToWrite.getName());
            armyToWrite.getAllUnits().forEach(unit ->{
                data.add(unit.getClass().getSimpleName()+","+ unit.getName()+","+unit.getHealth()+","+
                        unit.getAttack()+","+unit.getArmor()+","+unit.getAttackSpeedPerSecond()+","+
                        unit.getHitRate()+","+unit.getCriticRate()+","+unit.getCriticDamage()+"");
            });
            Files.write(finishDocument,data);

        }

    }

    /**
     * Help method that stores the name and all the units into a txt file in a defined
     * location in the computer, to be opened, read and used later.
     * @param army Army -  Instance of the class army to be saved.
     * @param pathOfFile String - Defined path of the new file.
     * @param fileName String - Defined name of the new file.
     * @throws IOException If it is an error by manipulating the new file.
     */
    private static void writeAFileWithSerializable(Army army, String pathOfFile, String fileName) throws IOException {
        FileOutputStream fos = new FileOutputStream(pathOfFile+"\\"+fileName);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(army);
        fos.close();
        oos.close();
    }
}
