package edu.ntnu.idatt2001.pedropca.wargames.util;

import edu.ntnu.idatt2001.pedropca.wargames.models.Army;
import javafx.application.Application;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import javax.management.InvalidApplicationException;

import static org.junit.jupiter.api.Assertions.*;

class SingletonArmiesTest {

    @Nested
    class getSingletonArmiesTest{

        @Nested
        class GetSingletonArmiesTest{

            @Nested
            class Positive{

                @Test
                void makingAnInstanceOfSingletonArmy(){
                    try {
                        SingletonArmies.getSingletonArmies();
                    }catch (Exception e){
                        fail();
                    }
                }

                @Test
                void checkIfSingletonArmiesIsUnique(){
                    SingletonArmies singletonArmies1 = SingletonArmies.getSingletonArmies();
                    singletonArmies1.setArmyNumber(100);
                    assertEquals(100,singletonArmies1.getArmyNumber());
                    SingletonArmies singletonArmies2 = SingletonArmies.getSingletonArmies();
                    assertEquals(100,singletonArmies2.getArmyNumber());
                }
            }
        }
    }

    @Nested
    class getAndPutArmyTest{

        @Nested
        class Positive{

            @Test
            void getAnArmyFromSingleton(){
                SingletonArmies singletonArmies = SingletonArmies.getSingletonArmies();
                singletonArmies.putArmy( new Army("army"),0);
                assertEquals(new Army("army"),singletonArmies.getArmy(0));
            }
        }

        @Nested
        class Negative{

            @Test
            void getAnArmyFromSingletonWithANegativeIndex(){
                SingletonArmies singletonArmies = SingletonArmies.getSingletonArmies();
                singletonArmies.putArmy( new Army("army"),0);
                try {
                    singletonArmies.getArmy(-10);
                    fail();
                }catch (IllegalArgumentException e){
                    assertEquals("The index of the armies has to be 0 or 1. Define a correct index.", e.getMessage());
                }
            }
            @Test
            void getAnArmyFromSingletonWithATwoAsIndex(){
                SingletonArmies singletonArmies = SingletonArmies.getSingletonArmies();
                singletonArmies.putArmy( new Army("army"),0);
                try {
                    singletonArmies.getArmy(2);
                    fail();
                }catch (IllegalArgumentException e){
                    assertEquals("The index of the armies has to be 0 or 1. Define a correct index.", e.getMessage());
                }
            }
        }
    }

    @Nested
    class getAndPutArmyFromBackUpTest{

        @Nested
        class Positive{

            @Test
            void getAnArmyFromSingleton(){
                SingletonArmies singletonArmies = SingletonArmies.getSingletonArmies();
                singletonArmies.putArmyInBackUp( new Army("army"),0);
                assertEquals(new Army("army"),singletonArmies.getArmyFromBackUp(0));
            }
        }

        @Nested
        class Negative{

            @Test
            void getAnArmyFromSingletonWithANegativeIndex(){
                SingletonArmies singletonArmies = SingletonArmies.getSingletonArmies();
                singletonArmies.putArmyInBackUp( new Army("army"),0);
                try {
                    singletonArmies.getArmyFromBackUp(-10);
                    fail();
                }catch (IllegalArgumentException e){
                    assertEquals("The index of the armies has to be 0 or 1. Define a correct index.", e.getMessage());
                }
            }
            @Test
            void getAnArmyFromSingletonWithATwoAsIndex(){
                SingletonArmies singletonArmies = SingletonArmies.getSingletonArmies();
                singletonArmies.putArmy( new Army("army"),0);
                try {
                    singletonArmies.getArmyFromBackUp(2);
                    fail();
                }catch (IllegalArgumentException e){
                    assertEquals("The index of the armies has to be 0 or 1. Define a correct index.", e.getMessage());
                }
            }
        }
    }

    @Nested
    class SetEmptyTest{

        @Nested
        class Positive{

            @Test
            void setEmptyInTheArmiesListOfTheSingleton(){
                try {
                    SingletonArmies singletonArmies = SingletonArmies.getSingletonArmies();
                    singletonArmies.putArmy( new Army("army"),0);
                    singletonArmies.setEmptySingletonArmy();
                }catch (Exception e){
                    fail();
                }
            }
        }

    }

    @Nested
    class SetEmptyArmyBackUpTest{

        @Nested
        class Positive{

            @Test
            void setEmptyInTheBackUpArmiesListOfTheSingleton(){
                try {
                    SingletonArmies singletonArmies = SingletonArmies.getSingletonArmies();
                    singletonArmies.putArmyInBackUp( new Army("army"),0);
                    singletonArmies.setEmptyArmyBackUp();
                }catch (Exception e){
                    fail();
                }
            }
        }
    }

    @Nested
    class RemoveArmyTest{
        @Nested
        class Positive{
            @Test
            void removeAnArmyFromMainArmyList(){
                try {
                    SingletonArmies singletonArmies = SingletonArmies.getSingletonArmies();
                    singletonArmies.putArmy( new Army("army"),0);
                    singletonArmies.removeArmy(0);
                }catch (Exception e){
                    fail();
                }
            }
        }
    }

    @Nested
    class RemoveArmyBackUpTest{
        @Nested
        class Positive{
            @Test
            void removeAnArmyFromBackUpList(){
                try {
                    SingletonArmies singletonArmies = SingletonArmies.getSingletonArmies();
                    singletonArmies.putArmyInBackUp( new Army("army"),0);
                    singletonArmies.removeArmyBackUp(0);
                }catch (Exception e){
                    fail();
                }
            }
        }
    }
}