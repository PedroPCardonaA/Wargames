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
            void writeAnArmyWithUnitsInACsvFile(){
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
                    FileArmyHandler.writeAFile(army, Objects.requireNonNull(getClass().getResource("\\Armies")).toString(),"ArmyWithUnits.csv");
                }catch (Exception e){
                    System.out.println(e.getMessage());
                    fail();
                }
            }
        }

        @Nested
        class Negative {
        }
    }
    @Nested
    class ReadArmyTest{
        @Nested
        class Positive{

        }

        @Nested
        class Negative{

        }
    }

}