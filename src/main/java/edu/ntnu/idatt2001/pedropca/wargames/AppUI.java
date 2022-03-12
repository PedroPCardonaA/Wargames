package edu.ntnu.idatt2001.pedropca.wargames;

/**
 * UI was an optional task.Therefore, I did not see the need to make a javaDoc or a test
 * for this class. But I am sure that this class is functional :D.
 */

import edu.ntnu.idatt2001.pedropca.wargames.units.CavalryUnit;
import edu.ntnu.idatt2001.pedropca.wargames.units.CommanderUnit;
import edu.ntnu.idatt2001.pedropca.wargames.units.InfantryUnit;
import edu.ntnu.idatt2001.pedropca.wargames.units.RangedUnit;

import java.util.Scanner;

public class AppUI {
    private Army army1;
    private Army army2;

    public AppUI() {
        army1 = null;
        army2 = null;
    }

    public void start() {
        final int EDIT_ARMY1 = 1;
        final int EDIT_ARMY2 = 2;
        final int DISPLAY_ARMY1 = 3;
        final int DISPLAY_ARMY2 = 4;
        final int BATTLE = 5;
        final int BATTLE_WITH_DEFAULT_ARMIES = 6;
        final int EXIT = 9;

        boolean finished = false;
        // The while-loop will run as long as the user has not selected
        // to quit the application
        while (!finished) {
            int menuChoice = this.showMenu();
            switch (menuChoice) {
                case EDIT_ARMY1: //call the method this.registerALongJumpResult();
                    this.editArmy1();
                    break;
                case EDIT_ARMY2: // Print out hte best jump result in the registry
                    this.editArmy2();
                    break;
                case DISPLAY_ARMY1:  //Print out all the jump result in the registry
                    this.displayArmy1();
                    break;
                case DISPLAY_ARMY2: //call the method this.showResultByAnAthlete()
                    this.displayArmy2();
                    break;
                case BATTLE:
                    this.battleWithDefinedUnit();
                    break;
                case BATTLE_WITH_DEFAULT_ARMIES: // Print out the average result
                    this.battleWithDefaultArmies();
                    break;
                //todo: have more options
                case EXIT:
                    finished = true; // finish the program
                    System.out.println("Thanks for war games app :D");
                    break;
                default:
                    System.out.println("Unrecognized menu selected..");
                    break;
            }
        }
    }
    private int showMenu(){
        int menuChoice = 0;
        System.out.println("\n***** War games V1.0-SNAPSHOT *****\n");
        System.out.println("1. Edit Army 1");
        System.out.println("2. Edit Army 2");
        System.out.println("3. Display army 1");
        System.out.println("4. Display army 2");
        System.out.println("5. Battle between armies");
        System.out.println("6. Battle between default armies");
//TODO: Add more option to the menu
        System.out.println("9. Quit");
        System.out.println("\nPlease enter a number between 1 and 9.\n");
        Scanner sc = new Scanner(System.in);
        if (sc.hasNextInt()) {
            menuChoice = sc.nextInt();
        } else {
            System.out.println("You must enter a number, not text");
        }
        return menuChoice;
    }

    private int checkInt () {
        while (true) {
            Scanner sc = new Scanner(System.in);
            if (sc.hasNextInt()) {
                return sc.nextInt();
            }
            System.out.println("You need to input a valid number: ");
        }
    }
    private String checkString () {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    private void displayArmy1(){
        if(army1==null){
            System.out.println("This army is not defined yet.");
        } else {
            System.out.println(army1);
        }
    }
    private void displayArmy2(){
        if(army1==null){
            System.out.println("This army is not defined yet.");
        } else {
            System.out.println(army2);
        }
    }
    private void editArmy1(){
        System.out.println("Enter the name of the army: ");
        String name = this.checkString();
        army1 = new Army(name);
        boolean finished = false;
        while (!finished) {
            System.out.println("1. Enter a unit \n2. exit");
            int answer = this.checkInt();
            switch (answer){
                case 1:this.addUnit(army1);
                    break;
                case 2:finished=true;
                    break;
                default: System.out.println("Enter a correct answer.");
                    break;
            }
        }
    }
    private void editArmy2(){
        System.out.println("Enter the name of the army: ");
        String name = this.checkString();
        army2 = new Army(name);
        boolean finished = false;
        while (!finished) {
            System.out.println("1. Enter a unit \n2. exit");
            int answer = this.checkInt();
            switch (answer){
                case 1:this.addUnit(army2);
                    break;
                case 2:finished=true;
                    break;
                default: System.out.println("Enter a correct answer.");
                    break;
            }
        }
    }
    private void addUnit(Army army){
        System.out.println("1. Infantry \n2. Ranged \n3. Cavalry");
        int unitType = this.checkInt();
        System.out.println("Enter the name og the unit: ");
        String name = this.checkString();
        System.out.println("Enter the healths point of the unit: ");
        int health= this.checkInt();
        System.out.println("Enter the number of this unit to add: ");
        int numberOfUnit = this.checkInt();
        switch (unitType){
            case 1: for(int i=0;i<numberOfUnit;i++){
                army.add(new InfantryUnit(name,health));
                }break;
            case 2: for(int i=0;i<numberOfUnit;i++){
                army.add(new RangedUnit(name,health));
                }break;
            case 3: for(int i=0;i<numberOfUnit;i++){
                army.add(new CavalryUnit(name,health));
                }break;
            default: System.out.println("Type of the unit has to be 1 ,2 or 3");
        }
    }
    private void battleWithDefaultArmies(){
        army1 = new Army("Alliance");
        army2 = new Army("Horde");
        for(int i = 0 ; i<500;i++){
            army1.add(new InfantryUnit("Footman",100));
            army2.add(new InfantryUnit("Grunt",100));
        }
        for(int i = 0 ; i<100;i++){
            army1.add(new CavalryUnit("Knight",100));
            army2.add(new CavalryUnit("Raider",100));
        }
        for(int i = 0 ; i<200;i++){
            army1.add(new RangedUnit("Archer",100));
            army2.add(new RangedUnit("Spearman",100));
        }
        army1.add(new CommanderUnit("Mountain King",180));
        army2.add(new CommanderUnit("Gul'dan",180));
        Battle battle = new Battle(army2,army1);
        Army winner = battle.simulate();
        System.out.println(battle);
        if(winner == null){
            System.out.println("It was a draw.");
        } else {System.out.println("The winner of the battle was " + winner.getName());}
    }
    private void battleWithDefinedUnit(){
        if(!(army1==null)&&!(army2==null)){
            Battle battle = new Battle(army1,army2);
            Army winner = battle.simulate();
            System.out.println(battle);
            if(winner == null){
                System.out.println("It was a draw.");
            } else {System.out.println("The winner of the battle was " + winner.getName());
                System.exit(0);}
        }else{
            System.out.println("One of the armies is not defined. Define it.");
        }
    }
}

