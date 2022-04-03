package edu.ntnu.idatt2001.pedropca.wargames.models.units;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommanderUnitTest {


    @Nested
    class TestingOverrideMethodAttack{
        @Nested
        class Positive{
            @Test
            void aCommanderWithAllLifePointsIsAttacking(){
                CommanderUnit commanderUnit = new CommanderUnit("Commander",100);
                commanderUnit.attack(new RangedUnit("Ranged",100));
                assertEquals(100,commanderUnit.getHealth());
            }
            @Test
            void  aCommanderWithHalfPartOfLifePointsIsAttacking(){
                CommanderUnit commanderUnit = new CommanderUnit("Commander",100);
                commanderUnit.setHealth(50);
                commanderUnit.attack(new RangedUnit("Ranged",100));
                assertEquals(52,commanderUnit.getHealth());
            }
            @Test
            void  aCommanderWithZeroLifePointsIsAttacking(){
                CommanderUnit commanderUnit = new CommanderUnit("Commander",100);
                commanderUnit.setHealth(0);
                commanderUnit.attack(new RangedUnit("Ranged",100));
                assertEquals(0,commanderUnit.getHealth());
            }
        }
    }
}