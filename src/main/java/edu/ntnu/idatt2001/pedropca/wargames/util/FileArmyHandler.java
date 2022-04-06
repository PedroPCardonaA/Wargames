package edu.ntnu.idatt2001.pedropca.wargames.util;

import com.opencsv.CSVWriter;
import edu.ntnu.idatt2001.pedropca.wargames.models.Army;
import edu.ntnu.idatt2001.pedropca.wargames.models.units.CavalryUnit;
import edu.ntnu.idatt2001.pedropca.wargames.models.units.CommanderUnit;
import edu.ntnu.idatt2001.pedropca.wargames.models.units.InfantryUnit;
import edu.ntnu.idatt2001.pedropca.wargames.models.units.RangedUnit;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileArmyHandler {

    public static Army readArmy(String pathOfFile) throws IOException, ClassNotFoundException {
        if(pathOfFile.isEmpty()){
            throw new IOException("The path of file cannot be empty. Define a path of the file");
        }
        if(!pathOfFile.toLowerCase().endsWith(".csv") && !pathOfFile.toLowerCase().endsWith(".txt")){
            throw new IOException("The defined file is not a .csv (Comma separated value) or .txt (Serializable.)" +
                    " Define " + "A correct file.");
        }
        if(pathOfFile.toLowerCase().endsWith(".csv")) return readArmyFromCsv(pathOfFile);
        else return readArmyFromSerializable(pathOfFile);
    }

    private static Army readArmyFromSerializable(String pathOfFile) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(pathOfFile);
        ObjectInputStream ois = new ObjectInputStream(fis);
        return (Army) ois.readObject();
    }

    private static Army readArmyFromCsv(String pathOfFile) throws IOException, IllegalArgumentException{
        Army readArmy = new Army("readArmy");
        List<List<String>> data = new ArrayList<>();
        FileReader fr = new FileReader(pathOfFile);
        BufferedReader br = new BufferedReader(fr);
        String line = br.readLine();
        UnitFactory factory = new UnitFactory();
        int numberOfLine= 1;
        while (line !=null){
            List<String> lineData = Arrays.asList(line.split(","));
            data.add(lineData);
            line = br.readLine();
        }
        try {
            for(int i=0;i<data.size();i++){
                if(i==0){
                    readArmy.setName(data.get(i).get(0));
                } else {
                    readArmy.add(factory.createUnit(data.get(i).get(0),data.get(i).get(1),Integer.parseInt(data.get(i).get(2)),Integer.parseInt(data.get(i).get(3)),Integer.parseInt(data.get(i).get(4)),Integer.parseInt(data.get(i).get(5)),Integer.parseInt(data.get(i).get(6)),Integer.parseInt(data.get(i).get(7)),Integer.parseInt(data.get(i).get(8))));
                    numberOfLine++;
                }
            }
        }catch (Exception e){
            throw new IllegalArgumentException("The data of the file was corrupted or is not compatible" +
                    "with this program. \nThe error was: " + e.getMessage() +" In the line " +numberOfLine +" of the file.");
        }
        br.close();
        fr.close();
        return readArmy;
    }

    public static void writeAFile(Army army ,String pathOfFile, String fileName) throws IOException {
        boolean b = !fileName.toLowerCase().endsWith(".csv") && !fileName.toLowerCase().endsWith(".txt");
        if (pathOfFile.isEmpty())
            throw new IOException("The path of file cannot be empty. Define a path of the file.");

        if (fileName.isEmpty())
            throw new IOException("The name of file cannot be empty. Define the name of the file.");


        if (fileName.contains(".") && b)
            throw new IOException("The name of the file cannot contain a '.'. Define a correct name.");

        if (fileName.contains("/"))
            throw new IOException("The name of the file cannot contain a '/'. Define a correct name.");

        if (fileName.contains("\\"))
            throw new IOException("The name of the file cannot contain a '\\'. Define a correct name.");

        if (b) fileName += ".csv";

        if (fileName.toLowerCase().endsWith(".csv")) {
            WriteAFileAsCsv(army, pathOfFile, fileName);
        } else {
            writeAFileWithSerializable(army, pathOfFile, fileName);
        }
    }

    /**
     * Static method that stores the name and all the units into a csv file in a defined
     * location in the computer, to be opened, read and used later.
     * This method will be called from the GUI and combine with class FileChooser of javaFx.
     * @param pathOfFile String with the wanted path to be store the army.
     * @param fileName String with the name of the new csv file.
     * @throws IOException This method may throw a IOException if a
     * problem happens while making,opening, writing or closing the file.
     */
    private static void WriteAFileAsCsv(Army armyToWrite, String pathOfFile, String fileName) throws IOException {
        File file = new File(pathOfFile+"\\"+fileName);
        FileWriter armyFile = new FileWriter(file);
        CSVWriter writer = new CSVWriter(armyFile, ',',
                CSVWriter.NO_QUOTE_CHARACTER,
                CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                CSVWriter.DEFAULT_LINE_END);
        List<String[]> data = new ArrayList<>();
        data.add(new String[]{armyToWrite.getName()});
        armyToWrite.getAllUnits().forEach(unit ->{
            String[] names= unit.getClass().toString().split("\\.");
            data.add(new String[]{names[names.length-1],unit.getName(),unit.getHealth()+"",
                    unit.getAttack()+"",unit.getArmor()+"",unit.getAttackSpeedPerSecond()+"",
                    unit.getHitRate()+"",unit.getCriticRate()+"",unit.getCriticDamage()+""});
        });
        writer.writeAll(data);
        writer.close();
    }

    private static void writeAFileWithSerializable(Army army, String pathOfFile, String fileName) throws IOException {
        FileOutputStream fos = new FileOutputStream(pathOfFile+"\\"+fileName);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(army);
        fos.close();
        oos.close();
    }
}
