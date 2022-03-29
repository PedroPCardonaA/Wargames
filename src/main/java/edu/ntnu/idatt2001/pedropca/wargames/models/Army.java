package edu.ntnu.idatt2001.pedropca.wargames.models;

import com.opencsv.CSVWriter;
import edu.ntnu.idatt2001.pedropca.wargames.models.units.*;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * edu.ntnu.idatt2001.pedropca.Army class that represents an army in the war games.
 * The armies are composed by a final field NAME that contains the name of the army
 * and by a final list with all the units tha the army has. Objects of this class
 * will be used to simulate a battle between armies in the war games.
 *
 * @author Pedro Cardona
 * @version 1.0
 * @since 1.0-SNAPSHOT
 */
public class Army {
    private String name;
    private final List<Unit> units;

    /**
     * Constructor of class edu.ntnu.idatt2001.pedropca.Army that has a String variable and a list of object units as signature.
     * This constructor will be used when the list of units is defined before the army object.
     * @param name name of the army
     * @param units list of units
     * @throws IllegalArgumentException this constructor may throw illegal argument exception
     * if the string variable name is empty or if the units list is defined as null
     */

    public Army(String name, List<Unit> units) throws IllegalArgumentException{
        if(name.isEmpty()) throw new IllegalArgumentException("The name of the army cannot be empty. Enter a name for the army.");
        if(units == null) throw new IllegalArgumentException("List of unit cannot be defined as null. Enter a correct unit list.");
        this.name = name.trim();
        this.units = units;
    }

    /**
     * Constructor of class edu.ntnu.idatt2001.pedropca.Army that has a String variable as signature.
     * This constructor will be used when the list of units is not defined before the army object.
     * @param name name of the army
     * @throws IllegalArgumentException this constructor may throw illegal argument exception
     * if the string variable name is empty
     */

    public Army(String name){
        if(name.isEmpty()) throw new IllegalArgumentException("The name of the army cannot be empty. Enter a name for the army.");
        this.name = name;
        units = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    /**
     * Method that add a defined unit into list UNITS and ergo into the army.
     * This method has on object of class edu.ntnu.idatt2001.pedropca.Unit as signature.
     * @param unit edu.ntnu.idatt2001.pedropca.Unit to be added into the army.
     */
    public void add(Unit unit){
        this.units.add(unit);
    }

    /**
     * Method that add a defined list of units into list UNITS and ergo into the army.
     * This method has a list of objects of class edu.ntnu.idatt2001.pedropca.Unit as signature.
     * @param units List of units to be added into army
     */
    public void addAll(List<Unit> units){
        this.units.addAll(units);
    }

    /**
     * Method that remove a defined unit from the list UNITS and ergo from the army.
     * @param unit edu.ntnu.idatt2001.pedropca.Unit to be removed from the army.
     */
    public void removeUnit(Unit unit){
        this.units.remove(unit);
    }

    /**
     * Method that checks if the list UNITS has elements inside or in other words is not empty.
     * @return boolean true if army has unit or false if not.
     */
    public boolean hasUnit(){
        return (!this.units.isEmpty());
    }

    /**
     * Method that returns current list of units of the army
     * @return current list of units
     */
    public List<Unit> getAllUnits() {
        return this.units;
    }

    /**
     * Method that return a random unit from the list by using Random class.
     * Using av Random class is necessary to get a random integer that represents an index of the list,
     * and return the unit related to that index
     * @return edu.ntnu.idatt2001.pedropca.Unit related to the random index
     */
    public Unit getRandom(){
        // This method may throw a nullPointerException when list UNITS is empty
        // , but the method that call this method checks it already. That is why I think
        // to throw a NullPointerException is unnecessary.
        return this.units.get(new Random().nextInt(this.units.size()));
    }

    /**
     * Method getInfantryUnits that return a list of all infantry units in the army.
     * @return List<Unit> List with all the infantry units in the army.
     */
    public List<Unit> getInfantryUnits(){
        return units.stream().filter(unit -> unit instanceof InfantryUnit).collect(Collectors.toList());
    }

    /**
     * Method getCavalryUnits that return a list of all cavalry units in the army.
     * @return List<Unit> List with all the cavalry units in the army.
     */
    public List<Unit> getCavalryUnits(){
        return units.stream().filter(unit -> unit instanceof CavalryUnit&&!(unit instanceof CommanderUnit)).collect(Collectors.toList());
    }

    /**
     * Method getRangedUnits that return a list of all ranged units in the army.
     * @return List<Unit> List with all the ranged units in the army.
     */
    public List<Unit> getRangedUnits(){
        return units.stream().filter(unit -> unit instanceof RangedUnit).collect(Collectors.toList());
    }

    /**
     * Method getCommanderUnits that return a list of all ranged units in the army.
     * @return List<Unit> List with all the commander units in the army.
     */
    public List<Unit> getCommanderUnits(){
        return units.stream().filter(unit -> unit instanceof CommanderUnit).collect(Collectors.toList());
    }

    /**
     * Provisional method that stores the name and all the units into a csv file in a defined
     * location in the computer, to be opened, read and used later.
     * This method will be relocated into the controller class or GUI.
     * @param pathOfFile String with the wanted path to be store the army.
     * @param fileName String with the name of the new csv file.
     * @throws IOException This method may throw a IOException if a
     * problem happens while making,opening, writing or closing the file.
     */

    //I will use fileChooser in the GUI but for now I just use a normal path
    //That is why it is not necessary to have an if sentence for empty pathFIle
    public void createAFileArmy(String pathOfFile, String fileName) throws IOException {
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
        data.add(new String[]{this.getName()});
        this.getInfantryUnits().forEach(unit -> data.add(new String[]{"InfantryUnit",unit.getName(),unit.getHealth()+"",
                unit.getAttack()+"",unit.getArmor()+"",unit.getAttackSpeedPerSecond()+"",
                unit.getHitRate()+"",unit.getCriticRate()+"",unit.getCriticDamage()+""}));
        this.getRangedUnits().forEach(unit -> data.add(new String[]{"RangedUnit",unit.getName(),unit.getHealth()+"",
                unit.getAttack()+"",unit.getArmor()+"",unit.getAttackSpeedPerSecond()+"",
                unit.getHitRate()+"",unit.getCriticRate()+"",unit.getCriticDamage()+""}));
        this.getCavalryUnits().forEach(unit -> data.add(new String[]{"CavalryUnit",unit.getName(),unit.getHealth()+"",
                unit.getAttack()+"",unit.getArmor()+"",unit.getAttackSpeedPerSecond()+"",
                unit.getHitRate()+"",unit.getCriticRate()+"",unit.getCriticDamage()+""}));
        this.getCommanderUnits().forEach(unit -> data.add(new String[]{"CommanderUnit",unit.getName(),unit.getHealth()+"",
                unit.getAttack()+"",unit.getArmor()+"",unit.getAttackSpeedPerSecond()+"",
                unit.getHitRate()+"",unit.getCriticRate()+"",unit.getCriticDamage()+""}));
        writer.writeAll(data);
        writer.close();
    }

    /**
     *Provisional method that read an army from a csv file and change the name and all the units
     * base on the army in the file. This method will be relocated into the controller class ot GUI.
     * @param pathOfFile String - Path og file to be open
     */

    //I will use fileChooser in the GUI but for now I just use a normal path
    public void readAFileArmy(String pathOfFile) throws Exception{
        if(pathOfFile.isEmpty()){
            throw new IOException("The path of file cannot be empty. Define a path of the file");
        }
        if(!pathOfFile.toLowerCase().endsWith(".csv")){
            throw new IOException("The defined file is not a .csv (Comma separated value). Define " +
                    "A correct file.");
        }
        this.removeAllUnits();
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
                    this.setName(data.get(i).get(0));
                } else {
                    String unitType = data.get(i).get(0);
                    switch (unitType){
                        case "InfantryUnit":
                            this.add(new InfantryUnit(data.get(i).get(1),Integer.parseInt(data.get(i).get(2)),Integer.parseInt(data.get(i).get(3)),Integer.parseInt(data.get(i).get(4)),Integer.parseInt(data.get(i).get(5)),Integer.parseInt(data.get(i).get(6)),Integer.parseInt(data.get(i).get(7)),Integer.parseInt(data.get(i).get(8))));
                            break;
                        case  "RangedUnit":
                            this.add(new RangedUnit(data.get(i).get(1),Integer.parseInt(data.get(i).get(2)),Integer.parseInt(data.get(i).get(3)),Integer.parseInt(data.get(i).get(4)),Integer.parseInt(data.get(i).get(5)),Integer.parseInt(data.get(i).get(6)),Integer.parseInt(data.get(i).get(7)),Integer.parseInt(data.get(i).get(8))));
                            break;
                        case "CavalryUnit":
                            this.add(new CavalryUnit(data.get(i).get(1),Integer.parseInt(data.get(i).get(2)),Integer.parseInt(data.get(i).get(3)),Integer.parseInt(data.get(i).get(4)),Integer.parseInt(data.get(i).get(5)),Integer.parseInt(data.get(i).get(6)),Integer.parseInt(data.get(i).get(7)),Integer.parseInt(data.get(i).get(8))));
                            break;
                        case "CommanderUnit":
                            this.add(new CommanderUnit(data.get(i).get(1),Integer.parseInt(data.get(i).get(2)),Integer.parseInt(data.get(i).get(3)),Integer.parseInt(data.get(i).get(4)),Integer.parseInt(data.get(i).get(5)),Integer.parseInt(data.get(i).get(6)),Integer.parseInt(data.get(i).get(7)),Integer.parseInt(data.get(i).get(8))));
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
    }

    /**
     * Void method that remove all units from the army.
     */
    public void removeAllUnits(){
        units.forEach(this::removeUnit);
    }


    /**
     * Set method tha defines a new for the army
     * @param name String - New name of the army
     * @throws IllegalArgumentException If the String army is empty
     */
    public void setName(String name) throws IllegalArgumentException{
        if(name.isEmpty()) throw new IllegalArgumentException("The name of the army cannot be empty. Enter a name for the army.");
        this.name = name.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%90S",this.getName())).append("\n");
        sb.append(String.format("%15S|%15S|%15S|%15S|%15S|%15S|%15S|%15S|%15S|"
                ,"NAME","HEALTH","ATTACK TYPE","ATTACK","ARMOR","ATTACK SPEED /s","HIT RATE", "CRITIC RATE", "CRITIC DAMAGE %")).append("\n");
        for(Unit unit: units){
            sb.append(unit.toString()).append("\n");
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Army)) return false;
        Army army = (Army) o;
        return name.equals(army.name); // I defined string variable NAME as key value to differentiate
                                       // to object of class edu.ntnu.idatt2001.pedropca.Army. This way makes more sense that use of HashCode
                                       // that use of HashCode to differentiate objects for me.
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
