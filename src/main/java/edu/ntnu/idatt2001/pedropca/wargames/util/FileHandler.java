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

public class FileHandler {

    /**
     * Static method that read an army from a csv file and change the name and all the units
     * base on the army in the file.
     * This method will be called from the GUI and combine with class FolderChooser of javaFx.
     * @param pathOfFile String - Path og file to be open
     */
    public static Army readArmy(String pathOfFile) throws IOException {
        if(pathOfFile.isEmpty()){
            throw new IOException("The path of file cannot be empty. Define a path of the file");
        }
        if(!pathOfFile.toLowerCase().endsWith(".csv")){
            throw new IOException("The defined file is not a .csv (Comma separated value). Define " +
                    "A correct file.");
        }
        Army readArmy = new Army("readArmy");
        List<List<String>> data = new ArrayList<>();
        FileReader fr = new FileReader(pathOfFile);
        BufferedReader br = new BufferedReader(fr);
        String line = br.readLine();
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
                    String unitType = data.get(i).get(0);
                    switch (unitType){
                        case "InfantryUnit":
                            readArmy.add(new InfantryUnit(data.get(i).get(1),Integer.parseInt(data.get(i).get(2)),Integer.parseInt(data.get(i).get(3)),Integer.parseInt(data.get(i).get(4)),Integer.parseInt(data.get(i).get(5)),Integer.parseInt(data.get(i).get(6)),Integer.parseInt(data.get(i).get(7)),Integer.parseInt(data.get(i).get(8))));
                            break;
                        case  "RangedUnit":
                            readArmy.add(new RangedUnit(data.get(i).get(1),Integer.parseInt(data.get(i).get(2)),Integer.parseInt(data.get(i).get(3)),Integer.parseInt(data.get(i).get(4)),Integer.parseInt(data.get(i).get(5)),Integer.parseInt(data.get(i).get(6)),Integer.parseInt(data.get(i).get(7)),Integer.parseInt(data.get(i).get(8))));
                            break;
                        case "CavalryUnit":
                            readArmy.add(new CavalryUnit(data.get(i).get(1),Integer.parseInt(data.get(i).get(2)),Integer.parseInt(data.get(i).get(3)),Integer.parseInt(data.get(i).get(4)),Integer.parseInt(data.get(i).get(5)),Integer.parseInt(data.get(i).get(6)),Integer.parseInt(data.get(i).get(7)),Integer.parseInt(data.get(i).get(8))));
                            break;
                        case "CommanderUnit":
                            readArmy.add(new CommanderUnit(data.get(i).get(1),Integer.parseInt(data.get(i).get(2)),Integer.parseInt(data.get(i).get(3)),Integer.parseInt(data.get(i).get(4)),Integer.parseInt(data.get(i).get(5)),Integer.parseInt(data.get(i).get(6)),Integer.parseInt(data.get(i).get(7)),Integer.parseInt(data.get(i).get(8))));
                            break;
                        default: throw new IllegalArgumentException("One of the units does not have a unit type.");
                    }
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

    /**
     * Static method that stores the name and all the units into a csv file in a defined
     * location in the computer, to be opened, read and used later.
     * This method will be called from the GUI and combine with class FileChooser of javaFx.
     * @param pathOfFile String with the wanted path to be store the army.
     * @param fileName String with the name of the new csv file.
     * @throws IOException This method may throw a IOException if a
     * problem happens while making,opening, writing or closing the file.
     */
    public static void WriteAFile(Army armyToWrite, String pathOfFile, String fileName) throws IOException {
        if(pathOfFile.isEmpty())
            throw new IOException("The path of file cannot be empty. Define a path of the file.");

        if(fileName.isEmpty())
            throw new IOException("The name of file cannot be empty. Define the name of the file.");

        if(fileName.contains(".") && !fileName.toLowerCase().endsWith(".csv"))
            throw new IOException("The name of the file cannot contain a '.'. Define a correct name.");

        if(fileName.contains("/"))
            throw new IOException("The name of the file cannot contain a '/'. Define a correct name.");

        if(fileName.contains("\\"))
            throw new IOException("The name of the file cannot contain a '\\'. Define a correct name.");

        if (!fileName.toLowerCase().endsWith(".csv")) fileName+=".csv";
        File file = new File(pathOfFile+"\\"+fileName);
        FileWriter armyFile = new FileWriter(file);
        CSVWriter writer = new CSVWriter(armyFile, ',',
                CSVWriter.NO_QUOTE_CHARACTER,
                CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                CSVWriter.DEFAULT_LINE_END);
        List<String[]> data = new ArrayList<>();
        data.add(new String[]{armyToWrite.getName()});
        armyToWrite.getInfantryUnits().forEach(unit -> data.add(new String[]{"InfantryUnit",unit.getName(),unit.getHealth()+"",
                unit.getAttack()+"",unit.getArmor()+"",unit.getAttackSpeedPerSecond()+"",
                unit.getHitRate()+"",unit.getCriticRate()+"",unit.getCriticDamage()+""}));
        armyToWrite.getRangedUnits().forEach(unit -> data.add(new String[]{"RangedUnit",unit.getName(),unit.getHealth()+"",
                unit.getAttack()+"",unit.getArmor()+"",unit.getAttackSpeedPerSecond()+"",
                unit.getHitRate()+"",unit.getCriticRate()+"",unit.getCriticDamage()+""}));
        armyToWrite.getCavalryUnits().forEach(unit -> data.add(new String[]{"CavalryUnit",unit.getName(),unit.getHealth()+"",
                unit.getAttack()+"",unit.getArmor()+"",unit.getAttackSpeedPerSecond()+"",
                unit.getHitRate()+"",unit.getCriticRate()+"",unit.getCriticDamage()+""}));
        armyToWrite.getCommanderUnits().forEach(unit -> data.add(new String[]{"CommanderUnit",unit.getName(),unit.getHealth()+"",
                unit.getAttack()+"",unit.getArmor()+"",unit.getAttackSpeedPerSecond()+"",
                unit.getHitRate()+"",unit.getCriticRate()+"",unit.getCriticDamage()+""}));
        writer.writeAll(data);
        writer.close();
    }
}
